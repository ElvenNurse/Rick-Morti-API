package com.example.rickmortiapi.repository;

import com.example.rickmortiapi.entity.Episode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> getEpisodesByUrlIn(List<String> url);
}
