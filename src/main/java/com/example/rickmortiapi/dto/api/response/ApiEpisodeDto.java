package com.example.rickmortiapi.dto.api.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import lombok.Data;

@Data
public class ApiEpisodeDto {
    private Integer id;
    private String name;
    @JsonAlias({"air_date"})
    private String airDate;
    private String episode;
    private List<String> characters;
    private String url;
    private String created;
}
