package com.example.rickmortiapi.dto.api.response;

import lombok.Data;

@Data
public class ApiInfoDto {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
