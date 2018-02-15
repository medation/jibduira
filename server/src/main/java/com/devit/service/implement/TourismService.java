package com.devit.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devit.model.Circuit;
import com.devit.model.City;
import com.devit.model.Monument;
import com.devit.model.User;
import com.devit.repository.CircuitRepository;
import com.devit.repository.CityRepository;
import com.devit.repository.MonumentRepository;
import com.devit.repository.UserRepository;
import com.devit.service.ITourismService;

@Service("tourismService")
public class TourismService implements ITourismService {

	@Autowired
	public MonumentRepository monumentRepository;
	
	@Autowired
	public CircuitRepository circuitRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public CityRepository cityRepository;
	
	//Gestion des monuments
	
	@Override
	public void saveMonument(Monument monument, String email) {
		//TODO Methode a verifier
		monumentRepository.save(monument);
	}

	@Override
	public List<Monument> findAllMonument() {
		return (List<Monument>) monumentRepository.findAll();
	}

	@Override
	public List<Monument> findAllMonumentsByUser(String email) {
		User user = userRepository.findByEmail(email);
		if(user.hasRole("ADMIN"))
			return (List<Monument>) monumentRepository.findAll();
		else
			return user.getMonuments();
	}

	@Override
	public List<Monument> findAllMonumentsByCity(String name) {
		City city = cityRepository.findByName(name);
		return city.getMonuments();
	}

	@Override
	public Monument findMonumentById(int id) {
		return monumentRepository.findOne(id);
	}

	
	@Override
	public void deleteMonument(int id) {
		monumentRepository.delete(id);
	}

	@Override
	public void updateMonument(Monument monument) {
		// TODO Auto-generated method stub

	}

	
	
	//Gestion des circuits
	
	@Override
	public void saveCircuit(Circuit circuit, String email) {
		circuit.setUser(userRepository.findByEmail(email));
		circuitRepository.save(circuit);
	}

	@Override
	public List<Circuit> findAllCircuit() {
		return (List<Circuit>) circuitRepository.findAll();
	}

	@Override
	public List<Circuit> findAllCircuitsByUser(String email) {
		User user = userRepository.findByEmail(email);
		if(user.hasRole("ADMIN"))
			return (List<Circuit>) circuitRepository.findAll();
		else
			return user.getCircuits();
	}

	@Override
	public void deleteCircuit(int id) {
		circuitRepository.delete(id);
	}

	@Override
	public void updateCircuit(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
