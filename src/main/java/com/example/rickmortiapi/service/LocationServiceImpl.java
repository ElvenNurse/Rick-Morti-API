package com.example.rickmortiapi.service;

import com.example.rickmortiapi.dto.api.mapper.LocationMapper;
import com.example.rickmortiapi.dto.api.response.ApiLocationDto;
import com.example.rickmortiapi.dto.api.response.ApiLocationListDto;
import com.example.rickmortiapi.entity.Location;
import com.example.rickmortiapi.repository.LocationRepository;
import com.example.rickmortiapi.util.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;
    private LocationMapper locationMapper;
    private HttpClient httpClient;

    @Value("${rickmortiapi.location.url}")
    private String locationsUrl;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               LocationMapper locationMapper, HttpClient httpClient) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.httpClient = httpClient;
    }

    @Override
    public List<Location> saveAll(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    @Override
    public Location getLocationByUrl(String url) {
        return locationRepository.getLocationByUrl(url);
    }

    @Override
    public void updateLocationRepository() {
        String response = httpClient.getResponse(locationsUrl);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ApiLocationListDto locationListDto = mapper.readValue(response,
                    ApiLocationListDto.class);
            List<ApiLocationDto> locationDtos = locationListDto.getResults();

            String next = locationListDto.getInfo().getNext();
            while (!next.isEmpty()) {
                response = httpClient.getResponse(next);
                locationListDto = mapper.readValue(response, ApiLocationListDto.class);
                locationDtos.addAll(locationListDto.getResults());
                next = locationListDto.getInfo().getNext();
            }

            saveAll(mapLocations(locationDtos));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error occurred while parsing location response!", e);
        }
    }

    private List<Location> mapLocations(List<ApiLocationDto> locationDtos) {
        return locationDtos.stream()
                .map(locationMapper::getLocationFromDto)
                .collect(Collectors.toList());
    }
}
