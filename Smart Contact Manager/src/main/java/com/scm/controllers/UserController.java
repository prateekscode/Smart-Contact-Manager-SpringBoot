package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	//user dashboard page
	
	@RequestMapping(value = "/dashboard")
	public String userDashboard() {
		return "user/dashboard";
	}
	
	@RequestMapping(value = "/profile")
	public String userProfile() {
		return "user/profile";
	}
	
	
	//user add contacts page
	
	//user view contacts
	
	//user edit contacts
	
	//user delete contacts
	
	//user search contacts
}
