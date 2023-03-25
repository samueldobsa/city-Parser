package com.example.trixi.service;

import com.example.trixi.entity.City;
import com.example.trixi.repository.CityRepository;
import com.example.trixi.service.api.CityApiService;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CityService {

    private CityApiService cityApiService;
    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getAllCities(){
        return cityRepository.findAll();
    }

    public City getCityById(long id){
        return cityRepository.findById(id).get();
    }

    public void deleteCityById(long id){
        cityRepository.deleteById(id);
    }

    @PostConstruct
    public void setObecProperties() throws UnirestException, IOException {
            List<City> cities = cityApiService.parseCities();
            for (City city : cities) {
                cityRepository.save(city);
                System.out.println("Uložená obec: " + city.getName());
                System.out.println("Cislo " + city.getCode());
                System.out.println("id " + city.getId());
        }
    }

}
