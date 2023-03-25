package com.example.trixi.controller;

import com.example.trixi.entity.PartCity;
import com.example.trixi.service.PartCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/part_city")
public class PartCityController {

    @Autowired
    private PartCityService partCityService;

    @GetMapping
    public Iterable<PartCity> findAllPartCities(){
        return partCityService.getAllPartCities();
    }

    @GetMapping("/{id}")
    public PartCity getPartCityById(@PathVariable("id") long id){
        return partCityService.getPartCityById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePartCityById(@PathVariable("id") long id){
        partCityService.deletePartCityById(id);
    }
}
