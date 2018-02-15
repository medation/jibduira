package com.devit.service;

import java.util.List;

import com.devit.model.Circuit;
import com.devit.model.Monument;

public interface ITourismService {

	//Gestion des monuments
	public void saveMonument(Monument monument, String email);
	public List<Monument> findAllMonument();
	public List<Monument> findAllMonumentsByUser(String email);
	public List<Monument> findAllMonumentsByCity(String name);
	public Monument findMonumentById(int id);
	public void deleteMonument(int id);
	void updateMonument(Monument monument);
	
	//Gestion des circuits
	public void saveCircuit(Circuit circuit, String email);
	public List<Circuit> findAllCircuit();
	public List<Circuit> findAllCircuitsByUser(String email);
	public void deleteCircuit(int id);
	public void updateCircuit(int id);
	
	
}
