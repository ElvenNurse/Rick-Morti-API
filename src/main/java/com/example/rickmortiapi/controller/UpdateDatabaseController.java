package com.example.rickmortiapi.controller;

import com.example.rickmortiapi.service.CharacterService;
import com.example.rickmortiapi.service.EpisodeService;
import com.example.rickmortiapi.service.LocationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateDatabaseController {
    private CharacterService characterService;
    private EpisodeService episodeService;
    private LocationService locationService;

    public UpdateDatabaseController(CharacterService characterService,
                                    EpisodeService episodeService,
                                    LocationService locationService) {
        this.characterService = characterService;
        this.episodeService = episodeService;
        this.locationService = locationService;
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void updateDatabase() {
        episodeService.updateEpisodesRepository();
        locationService.updateLocationRepository();
        characterService.updateCharactersRepository();
    }
}
