package com.devit.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devit.model.City;
import com.devit.model.Monument;
import com.devit.service.ISpotService;
import com.devit.service.ITourismService;
import com.devit.service.IUserService;

@Controller
public class AdministrationController {

	@Autowired
	private IUserService userService;
	@Autowired
	public ITourismService tourismService;
	@Autowired
	public ISpotService spotService;
	
	@GetMapping(value="/listMonuments")
	public ModelAndView listMonuments() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("monuments", tourismService.findAllMonument());
		modelAndView.setViewName("listMonuments");
		return modelAndView;
	}
	
	@GetMapping(value="{id}/editMonument")
	public ModelAndView getEditVideo(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("success", "");
		modelAndView.addObject("error", "");
		modelAndView.addObject("currentMonument", tourismService.findMonumentById(Integer.parseInt(id)));
		modelAndView.addObject("cities", spotService.findAllCity());
		modelAndView.setViewName("editMonument");
		return modelAndView;
	}
	
	@PostMapping(value="/editMonument")
	public ModelAndView postEditVideo(@Valid Monument monument, Errors errors) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(monument.getId());
		List<City> cities = spotService.findAllCity();
		if (errors.hasErrors()) {
			modelAndView.addObject("error", "Erreur lors de l'ajout de la video");
			modelAndView.addObject("currentMonument",monument);
			modelAndView.addObject("Cities", cities);
			modelAndView.setViewName("editMonument");
            return modelAndView;
        }
		else {
			tourismService.updateMonument(monument);
			modelAndView.addObject("success", "Video a été bien modifiée");
			modelAndView.addObject("currentMonument",monument);
			modelAndView.addObject("Cities", cities);
			modelAndView.setViewName("editMonument");
			return modelAndView;
		}
	}
	
	@GetMapping(value="{id}/delete")
	public ModelAndView deleteMonument(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		tourismService.deleteMonument(Integer.parseInt(id));
		modelAndView.addObject("monuments", tourismService.findAllMonument());
		modelAndView.setViewName("redirect:/listMonuments");
		return modelAndView;
	}
}
