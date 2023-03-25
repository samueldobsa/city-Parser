package com.example.trixi.controller;

import com.example.trixi.entity.City;
import com.example.trixi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public Iterable<City> findAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable("id") long id){
        return cityService.getCityById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable("id") long id){
        cityService.deleteCityById(id);
    }

}
