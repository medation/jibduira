package com.devit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devit.model.Circuit;

@Repository("circuitRepository")
public interface CircuitRepository extends JpaRepository<Circuit, Integer> {
    /*
    @Query("DELETE monument_circuit WHERE circuit_id=:idCircuit")
    void deleteLinkCircuit(@Param("idCircuit")int id);
    */
}
