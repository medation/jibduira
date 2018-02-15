package com.devit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devit.model.User;
import com.devit.service.ISpotService;
import com.devit.service.ITourismService;
import com.devit.service.IUserService;

@Controller
public class IndexController {

	@Autowired
	private IUserService userService;
	@Autowired
	public ITourismService tourismService;
	@Autowired
	public ISpotService spotService;
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView accueil() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsernameOrEmail(auth.getName());
		modelAndView.addObject("user", user);
		modelAndView.addObject("monuments", tourismService.findAllMonument());
		modelAndView.setViewName("accueil");
		return modelAndView;
	}

	
}
