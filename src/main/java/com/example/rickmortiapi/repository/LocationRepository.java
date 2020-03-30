package com.example.rickmortiapi.repository;

import com.example.rickmortiapi.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location getLocationByUrl(String url);
}
