package com.example.rickmortiapi.service;

import com.example.rickmortiapi.dto.api.mapper.EpisodeMapper;
import com.example.rickmortiapi.dto.api.response.ApiEpisodeDto;
import com.example.rickmortiapi.dto.api.response.ApiEpisodeListDto;
import com.example.rickmortiapi.entity.Episode;
import com.example.rickmortiapi.repository.EpisodeRepository;
import com.example.rickmortiapi.util.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private EpisodeRepository episodeRepository;
    private EpisodeMapper episodeMapper;
    private HttpClient httpClient;

    @Value("${rickmortiapi.episode.url}")
    private String episodesUrl;

    @Autowired
    public EpisodeServiceImpl(EpisodeRepository episodeRepository, EpisodeMapper episodeMapper,
                              HttpClient httpClient) {
        this.episodeRepository = episodeRepository;
        this.episodeMapper = episodeMapper;
        this.httpClient = httpClient;
    }

    @Override
    public List<Episode> saveAll(List<Episode> episodes) {
        return episodeRepository.saveAll(episodes);
    }

    @Override
    public List<Episode> getEpisodesByUrlIn(List<String> url) {
        return episodeRepository.getEpisodesByUrlIn(url);
    }

    @Override
    public void updateEpisodesRepository() {
        String response = httpClient.getResponse(episodesUrl);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ApiEpisodeListDto episodeListDto = mapper.readValue(response,
                    ApiEpisodeListDto.class);
            List<ApiEpisodeDto> episodeDtos = episodeListDto.getResults();

            String next = episodeListDto.getInfo().getNext();
            while (!next.isEmpty()) {
                response = httpClient.getResponse(next);
                episodeListDto = mapper.readValue(response, ApiEpisodeListDto.class);
                episodeDtos.addAll(episodeListDto.getResults());
                next = episodeListDto.getInfo().getNext();
            }

            saveAll(mapEpisodes(episodeDtos));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error occurred while parsing episode response!", e);
        }
    }

    private List<Episode> mapEpisodes(List<ApiEpisodeDto> episodeDtos) {
        return episodeDtos.stream()
                .map(episodeMapper::getEpisodeFromDto)
                .collect(Collectors.toList());
    }
}
