package com.example.rickmortiapi.service;

import com.example.rickmortiapi.entity.Location;
import java.util.List;

public interface LocationService {
    List<Location> saveAll(List<Location> locations);

    Location getLocationByUrl(String url);

    void updateLocationRepository();
}
