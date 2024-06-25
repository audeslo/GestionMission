package com.eneam.gestionmission.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppConfiguration {
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello word !";
	}

}
