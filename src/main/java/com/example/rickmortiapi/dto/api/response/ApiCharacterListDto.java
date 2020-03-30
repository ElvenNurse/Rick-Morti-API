package com.example.rickmortiapi.dto.api.response;

import java.util.List;
import lombok.Data;

@Data
public class ApiCharacterListDto {
    private ApiInfoDto info;
    private List<ApiCharacterDto> results;
}
