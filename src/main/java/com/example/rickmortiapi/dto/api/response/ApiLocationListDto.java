package com.example.rickmortiapi.dto.api.response;

import java.util.List;
import lombok.Data;

@Data
public class ApiLocationListDto {
    private ApiInfoDto info;
    private List<ApiLocationDto> results;
}
