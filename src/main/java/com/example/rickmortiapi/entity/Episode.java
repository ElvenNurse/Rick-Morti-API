package com.example.rickmortiapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    private Integer id;
    private String name;
    @Column(name = "air_date")
    private String airDate;
    private String episode;
    @NotNull
    private String url;
    private String created;
}
