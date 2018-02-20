package com.devit.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.devit.model.City;
import com.devit.model.Monument;
import com.devit.model.Region;
import com.devit.service.ISpotService;
import com.devit.service.ITourismService;

@Controller
public class MonumentController {
	
	@Autowired
	public ITourismService tourismService;
	@Autowired
	public ISpotService spotService;
	
	@RequestMapping(value={"/myMonuments"}, method = RequestMethod.GET)
	public ModelAndView getMyMonuments(@Valid Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("monuments", tourismService.findAllMonumentsByUser(principal.getName()));
		modelAndView.setViewName("mymonuments");
		return modelAndView;
	}
	
	@RequestMapping(value={"/monuments"}, method = RequestMethod.GET)
	public ModelAndView getMonuments() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.addObject("regions", spotService.findAllRegion());
		modelAndView.setViewName("monuments");
		return modelAndView;
	}
	
	@RequestMapping(value={"/addMonument"}, method = RequestMethod.GET)
	public ModelAndView addMonument() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("monument", new Monument());
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.addObject("regions", spotService.findAllRegion());
		modelAndView.setViewName("formMonument");
		return modelAndView;
	}
	
	@RequestMapping(value={"/addMonument"}, method = RequestMethod.POST)
	public ModelAndView createMonument(@Valid Monument monument, BindingResult bindingResult, @RequestParam String cityName, @RequestParam String regionName, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		City city = spotService.findCityByName(cityName);
		Region region = spotService.findRegionByName(regionName);
		monument.setCity(city);
		monument.setRegion(region);
		tourismService.saveMonument(monument, principal.getName());
		modelAndView.addObject("successMessage", "Monument has been registered successfully");
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.addObject("regions", spotService.findAllRegion());
		modelAndView.addObject("monument", new Monument());
		modelAndView.setViewName("formMonument");
		return modelAndView;
	}
	
	@GetMapping(value="/monument/{id}")
	public ModelAndView getEditVideo(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("monument", tourismService.findMonumentById(Integer.parseInt(id)));
		modelAndView.setViewName("monument");
		return modelAndView;
	}
	
	@RequestMapping(value={"/searchByRegion"}, method = RequestMethod.GET)
	public ModelAndView searchMonumentByRegion(@RequestParam String region) {
		ModelAndView modelAndView = new ModelAndView();
		Region regionSearch = spotService.findRegionByName(region);
		modelAndView.addObject("monuments", regionSearch.getMonuments());
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.addObject("regions", spotService.findAllRegion());
		modelAndView.addObject("messageSearch", "Résultat de la recherche dans la région de "+regionSearch.getName()+" : ");
		modelAndView.setViewName("monuments");
		return modelAndView;
	}
	
	@RequestMapping(value={"/searchByCity"}, method = RequestMethod.GET)
	public ModelAndView searchMonumentByCity(@RequestParam String city) {
		ModelAndView modelAndView = new ModelAndView();
		City citySearch = spotService.findCityByName(city);
		modelAndView.addObject("monuments", citySearch.getMonuments());
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.addObject("regions", spotService.findAllRegion());
		modelAndView.addObject("messageSearch", "Résultat de la recherche dans la ville de "+citySearch.getName()+" : ");
		modelAndView.setViewName("monuments");
		return modelAndView;
	}
	
}
