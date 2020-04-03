package com.example.rickmortiapi.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @ManyToOne
    private Location origin;
    @ManyToOne
    private Location location;
    private String image;
    @ManyToMany
    private List<Episode> episodes;
    @NotNull
    private String url;
    private String created;
}
