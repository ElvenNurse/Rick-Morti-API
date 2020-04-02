package com.example.rickmortiapi.controller;

import com.example.rickmortiapi.dto.api.response.ApiCharacterDto;
import com.example.rickmortiapi.service.CharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    @ApiOperation(value = "Get random character wiki")
    private ApiCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/name/{namePart}")
    @ApiOperation(value = "Get characters which contains name part")
    private List<ApiCharacterDto> getCharactersByNamePart(@ApiParam(value =
            "Part of name which will be searched") @PathVariable("namePart") String name) {
        return characterService.getCharactersByNamePart(name);
    }
}
