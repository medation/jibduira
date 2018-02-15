package com.devit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devit.fake.DataLoad;

@Controller
@SpringBootApplication
public class JibDuiraApplication {

	@Autowired
	private DataLoad dataLoad;
	
	public static void main(String[] args) {
		SpringApplication.run(JibDuiraApplication.class, args);
	}
	
	@RequestMapping (value={"/loadCity"})
	public String loadCity() {
		dataLoad.loadCity();
		return "loaded";
	}
	
	@RequestMapping (value={"/loadRegion"})
	public String loadRegion() {
		dataLoad.loadRegion();
		return "loaded";
	}
	
	@RequestMapping (value={"/loadMonument"})
	public String loadMonument() {
		dataLoad.loadMonument();
		return "loaded";
	}
}
