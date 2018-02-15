package com.devit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devit.model.City;

@Repository("cityRepository")
public interface CityRepository extends CrudRepository<City, Integer> {
	City findByName(String name);
}
