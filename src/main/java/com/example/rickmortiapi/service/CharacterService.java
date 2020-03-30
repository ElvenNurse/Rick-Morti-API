package com.example.rickmortiapi.service;

import com.example.rickmortiapi.dto.api.response.ApiCharacterDto;
import com.example.rickmortiapi.entity.Character;
import java.util.List;

public interface CharacterService {
    List<Character> saveAll(List<Character> characters);

    ApiCharacterDto getRandomCharacter();

    List<ApiCharacterDto> getCharactersByNamePart(String namePart);

    void updateCharactersRepository();
}
