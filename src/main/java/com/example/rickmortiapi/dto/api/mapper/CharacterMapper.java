package com.example.rickmortiapi.dto.api.mapper;

import com.example.rickmortiapi.dto.api.response.ApiCharacterDto;
import com.example.rickmortiapi.dto.api.response.ApiCharacterLocationDto;
import com.example.rickmortiapi.entity.Character;
import com.example.rickmortiapi.entity.Episode;
import com.example.rickmortiapi.entity.Location;
import com.example.rickmortiapi.service.EpisodeService;
import com.example.rickmortiapi.service.LocationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    private EpisodeService episodeService;
    private LocationService locationService;

    @Autowired
    public CharacterMapper(EpisodeService episodeService, LocationService locationService) {
        this.episodeService = episodeService;
        this.locationService = locationService;
    }

    public Character getCharacterFromDto(ApiCharacterDto characterDto) {
        Character character = new Character();
        character.setId(characterDto.getId());
        character.setName(characterDto.getName());
        character.setStatus(characterDto.getStatus());
        character.setSpecies(characterDto.getSpecies());
        character.setType(characterDto.getType());
        character.setGender(characterDto.getGender());
        Location origin = locationService.getLocationByUrl(characterDto.getOrigin().getUrl());
        character.setOrigin(origin);
        Location location = locationService.getLocationByUrl(characterDto.getLocation().getUrl());
        character.setLocation(location);
        character.setImage(characterDto.getImage());
        List<Episode> episodes = episodeService.getEpisodesByUrlIn(characterDto.getEpisode());
        character.setEpisodes(episodes);
        character.setUrl(characterDto.getUrl());
        character.setCreated(characterDto.getCreated());
        return character;
    }

    public ApiCharacterDto getDtoFromCharacter(Character character) {
        ApiCharacterDto characterDto = new ApiCharacterDto();
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setSpecies(character.getSpecies());
        characterDto.setType(character.getType());
        characterDto.setGender(character.getGender());
        ApiCharacterLocationDto originDto = null;
        if (character.getOrigin() != null) {
            originDto = new ApiCharacterLocationDto();
            originDto.setName(character.getOrigin().getName());
            originDto.setUrl(character.getOrigin().getUrl());
        }
        characterDto.setOrigin(originDto);
        ApiCharacterLocationDto locationDto = null;
        if (character.getLocation() != null) {
            locationDto = new ApiCharacterLocationDto();
            locationDto.setName(character.getLocation().getName());
            locationDto.setUrl(character.getLocation().getUrl());
        }
        characterDto.setLocation(locationDto);
        characterDto.setImage(character.getImage());
        List<String> episodes = character.getEpisodes()
                .stream()
                .map(Episode::getUrl)
                .collect(Collectors.toList());
        characterDto.setEpisode(episodes);
        characterDto.setUrl(character.getUrl());
        characterDto.setCreated(character.getCreated());
        return characterDto;
    }
}
