package com.example.rickmortiapi.dto.api.response;

import java.util.List;
import lombok.Data;

@Data
public class ApiCharacterDto {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private ApiCharacterLocationDto origin;
    private ApiCharacterLocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
