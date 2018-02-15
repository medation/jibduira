package com.devit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devit.model.Circuit;

@Repository("circuitRepository")
public interface CircuitRepository extends CrudRepository<Circuit, Integer> {

}
