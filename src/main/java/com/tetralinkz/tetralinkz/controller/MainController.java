package com.tetralinkz.tetralinkz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.services.MainService;

@Controller
@RequestMapping("/main")
public class MainController {
	//DEPENDENCY INJECTION
	@Autowired
	private MainService mainService;
	
	public MainController (MainService mainService) {
		this.mainService = mainService;
	}
	
	// RESTFUL ROUTES //
	
	// // // / //
	// USERS  //
	// // // //
	
	// Registration || Login page render
	@GetMapping("/landing")
	public String landingRender(@ModelAttribute("user")User user) {
		return "index.jsp";
	}
	
	@GetMapping("/admin")
	public String adminPage() {
		return "admin.jsp";
	}

}
