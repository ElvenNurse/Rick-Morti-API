package com.example.rickmortiapi.dto.api.response;

import java.util.List;
import lombok.Data;

@Data
public class ApiEpisodeListDto {
    private ApiInfoDto info;
    private List<ApiEpisodeDto> results;
}
