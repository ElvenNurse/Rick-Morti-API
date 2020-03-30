package com.example.rickmortiapi.service;

import com.example.rickmortiapi.dto.api.mapper.CharacterMapper;
import com.example.rickmortiapi.dto.api.response.ApiCharacterDto;
import com.example.rickmortiapi.dto.api.response.ApiCharacterListDto;
import com.example.rickmortiapi.entity.Character;
import com.example.rickmortiapi.entity.Location;
import com.example.rickmortiapi.repository.CharacterRepository;
import com.example.rickmortiapi.util.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;
    private HttpClient httpClient;

    @Value("${rickmortiapi.character.url}")
    private String charactersUrl;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterMapper characterMapper, HttpClient httpClient) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.httpClient = httpClient;
    }

    @Override
    public List<Character> saveAll(List<Character> characters) {
        return characterRepository.saveAll(characters);
    }

    @Override
    public ApiCharacterDto getRandomCharacter() {
        Random random = new Random();
        int randomId = random.nextInt(characterRepository.countByIdIsNotNull()) + 1;
        return characterMapper.getDtoFromCharacter(characterRepository.getOne(randomId));
    }

    @Override
    public List<ApiCharacterDto> getCharactersByNamePart(String namePart) {
        return characterRepository.getCharactersByNameContains(namePart)
                .stream()
                .map(characterMapper::getDtoFromCharacter)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCharactersRepository() {
        String response = httpClient.getResponse(charactersUrl);

        ObjectMapper mapper = new ObjectMapper();
        try {
            ApiCharacterListDto characterListDto = mapper.readValue(response,
                    ApiCharacterListDto.class);
            List<ApiCharacterDto> characterDtos = characterListDto.getResults();

            String next = characterListDto.getInfo().getNext();
            while (!next.isEmpty()) {
                response = httpClient.getResponse(next);
                characterListDto = mapper.readValue(response, ApiCharacterListDto.class);
                characterDtos.addAll(characterListDto.getResults());
                next = characterListDto.getInfo().getNext();
            }

            mapAndSaveCharacters(characterDtos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error occurred while parsing character response!", e);
        }
    }

    private void mapAndSaveCharacters(List<ApiCharacterDto> characterDtos) {
        saveAll(characterDtos.stream()
                .map(characterMapper::getCharacterFromDto)
                .collect(Collectors.toList())
        );
    }
}
