package com.devit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devit.model.Region;

@Repository("regionRepository")
public interface RegionRepository extends CrudRepository<Region, Integer> {
	Region findByName(String name);
}
