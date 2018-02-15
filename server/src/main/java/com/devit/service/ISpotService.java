package com.devit.service;

import java.util.List;

import com.devit.model.City;
import com.devit.model.Region;

public interface ISpotService {

	//Gestion des villes
	public City findCityByName(String name);
	public void saveCity(City city);
	public List<City> findAllCity();
	
	//Gestion des regions
	public Region findRegionByName(String name);
	public void saveRegion(Region region);
	public List<Region> findAllRegion();
}
