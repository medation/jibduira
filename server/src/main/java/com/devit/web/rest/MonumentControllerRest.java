package com.devit.web.rest;

import java.util.List;

import com.devit.model.*;
import com.devit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.devit.service.ISpotService;
import com.devit.service.ITourismService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class MonumentControllerRest {
	
	@Autowired
	public ITourismService tourismService;
	@Autowired
	public ISpotService spotService;
	@Autowired
	public IUserService userService;

	@RequestMapping(value={"/cities"}, method = RequestMethod.GET)
	public List<City> getCitiesApi(){
		return spotService.findAllCity();
	}
	
	@RequestMapping(value={"/regions"}, method = RequestMethod.GET)
	public List<Region> getRegionsApi(){
		return spotService.findAllRegion();
	}
	
	@RequestMapping(value={"/monuments"}, method = RequestMethod.GET)
	public List<Monument> getMonumentsApi(){
		return tourismService.findAllMonument();
	}
	
	@RequestMapping(value={"/{type}/{spot}/monuments"}, method = RequestMethod.GET)
	public List<Monument> getMonumentsApiByType(@PathVariable String spot, @PathVariable String type){
		if(type.equals("City")) {
			City citySearch = spotService.findCityByName(spot);
			return citySearch.getMonuments();
		}
		else if(type.equals("Region")) {
			Region regionSearch = spotService.findRegionByName(spot);
			return regionSearch.getMonuments();
		}
		return null;
	}

	@RequestMapping(value={"/{id}/addMonument"}, method = RequestMethod.GET)
	public Monument addMonument(@PathVariable String id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsernameOrEmail(auth.getName());
		Monument monument = tourismService.findMonumentById(Integer.parseInt(id));
		if(!user.containsMonument(monument)){
			user.addMonument(monument);
			userService.save(user);
		}
		System.out.println(auth.getName());
		return monument;
	}

	@RequestMapping(value={"/favories"}, method = RequestMethod.GET)
	public List<Monument> getFavoriesMonuments(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsernameOrEmail(auth.getName());
		return user.getMonuments();
	}

	@DeleteMapping("/{id}/deleteMonumentFavorie")
	public List<Monument> deleteMonumentFavorieApi(@PathVariable String id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsernameOrEmail(auth.getName());
		Monument monument = tourismService.findMonumentById(Integer.parseInt(id));
		if(user.deleteMonument(monument)){
			userService.save(user);
		}
		return user.getMonuments();
	}

}
