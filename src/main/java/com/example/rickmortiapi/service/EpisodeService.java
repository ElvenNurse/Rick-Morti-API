package com.example.rickmortiapi.service;

import com.example.rickmortiapi.entity.Episode;
import java.util.List;

public interface EpisodeService {
    List<Episode> saveAll(List<Episode> episodes);

    List<Episode> getEpisodesByUrlIn(List<String> url);

    void updateEpisodesRepository();
}
