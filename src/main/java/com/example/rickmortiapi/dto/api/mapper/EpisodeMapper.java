package com.example.rickmortiapi.dto.api.mapper;

import com.example.rickmortiapi.dto.api.response.ApiEpisodeDto;
import com.example.rickmortiapi.entity.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {
    public Episode getEpisodeFromDto(ApiEpisodeDto episodeDto) {
        Episode episode = new Episode();
        episode.setId(episodeDto.getId());
        episode.setName(episodeDto.getName());
        episode.setAirDate(episodeDto.getAirDate());
        episode.setEpisode(episodeDto.getEpisode());
        episode.setUrl(episodeDto.getUrl());
        episode.setCreated(episodeDto.getCreated());
        return episode;
    }
}
