package com.devit.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devit.model.City;
import com.devit.model.Region;
import com.devit.repository.CityRepository;
import com.devit.repository.RegionRepository;
import com.devit.service.ISpotService;

@Service("spotService")
public class SpotService implements ISpotService {

	@Autowired
	public CityRepository cityRepository;
	
	@Autowired
	public RegionRepository regionRepository;
	
	@Override
	public City findCityByName(String name) {
		return cityRepository.findByName(name);
	}

	@Override
	public void saveCity(City city) {
		cityRepository.save(city);
	}

	@Override
	public List<City> findAllCity() {
		return (List<City>) cityRepository.findAll();
	}

	@Override
	public Region findRegionByName(String name) {
		return regionRepository.findByName(name);
	}

	@Override
	public void saveRegion(Region region) {
		regionRepository.save(region);
	}

	@Override
	public List<Region> findAllRegion() {
		return (List<Region>) regionRepository.findAll();
	}

}
