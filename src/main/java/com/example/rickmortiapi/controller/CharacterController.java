package com.example.rickmortiapi.controller;

import com.example.rickmortiapi.dto.api.response.ApiCharacterDto;
import com.example.rickmortiapi.service.CharacterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private UpdateDatabaseController updateDatabaseController;
    private CharacterService characterService;

    @Autowired
    public CharacterController(UpdateDatabaseController updateDatabaseController,
                               CharacterService characterService) {
        this.updateDatabaseController = updateDatabaseController;
        this.characterService = characterService;
    }

    @GetMapping
    private ApiCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/name/{namePart}")
    private List<ApiCharacterDto> getCharactersByNamePart(@PathVariable("namePart") String name) {
        return characterService.getCharactersByNamePart(name);
    }
}
