package com.devit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devit.model.Monument;

@Repository("monumentRepository")
public interface MonumentRepository extends CrudRepository<Monument, Integer> {
	Monument findByName(String name);
}
