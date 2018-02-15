package com.devit.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devit.model.Role;
import com.devit.model.User;
import com.devit.repository.RoleRepository;
import com.devit.service.IUserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public AuthenticationController(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value={"/registration"}, method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("passwordConfirme", "");
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user,BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		User userExists = userService.findByUsernameOrEmail(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Il existe déja un utilisateur avec cette adresse email !");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findOne(1));
			user.setRoles(roles);
			user.encodePassword(this.passwordEncoder);
			userService.save(user);
			modelAndView.addObject("successMessage", "Vous avez bien été enregister, connectez vous dés maintenant !");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
	

}
