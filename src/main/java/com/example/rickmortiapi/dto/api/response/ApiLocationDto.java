package com.example.rickmortiapi.dto.api.response;

import java.util.List;
import lombok.Data;

@Data
public class ApiLocationDto {
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
    private String created;
}
