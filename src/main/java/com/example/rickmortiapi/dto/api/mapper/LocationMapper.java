package com.example.rickmortiapi.dto.api.mapper;

import com.example.rickmortiapi.dto.api.response.ApiLocationDto;
import com.example.rickmortiapi.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location getLocationFromDto(ApiLocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setName(locationDto.getName());
        location.setType(locationDto.getType());
        location.setDimension(locationDto.getDimension());
        location.setUrl(locationDto.getUrl());
        location.setCreated(locationDto.getCreated());
        return location;
    }
}
