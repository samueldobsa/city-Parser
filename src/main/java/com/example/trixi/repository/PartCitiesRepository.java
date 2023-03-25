package com.example.trixi.repository;

import com.example.trixi.entity.PartCity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartCitiesRepository extends CrudRepository<PartCity, Long> {
}
