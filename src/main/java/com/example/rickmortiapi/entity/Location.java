package com.example.rickmortiapi.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    @NotNull
    private String url;
    private String created;
}
