package com.example.trixi.service;

import com.example.trixi.entity.PartCity;
import com.example.trixi.repository.PartCitiesRepository;
import com.example.trixi.service.api.PartCiyApiService;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PartCityService {

    private PartCiyApiService partCiyApiService;

    @Autowired
    private PartCitiesRepository partCitiesRepository;

    public Iterable<PartCity> getAllPartCities(){
        return partCitiesRepository.findAll();
    }

    public PartCity getPartCityById(long id){
        return partCitiesRepository.findById(id).get();
    }

    public void deletePartCityById(long id){
        partCitiesRepository.deleteById(id);
    }

    @PostConstruct
    public void setPartCityProperties() throws UnirestException, IOException {
        List<PartCity> partCities = partCiyApiService.parsePartCities();
        for (PartCity partCity : partCities) {
            partCitiesRepository.save(partCity);
            System.out.println("Kod casti obce " + partCity.getCode());
            System.out.println("Nazev obce " + partCity.getName());
            System.out.println("Kod obce " + partCity.getCodeOfCity());
            System.out.println("id: " + partCity.getId());
        }
    }
}
