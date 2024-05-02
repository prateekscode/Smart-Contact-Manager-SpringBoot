package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("HomePage");

		// sending data to view
		model.addAttribute("name", "Substring tech");
		model.addAttribute("MCA", "Prateek");
		model.addAttribute("Github", "https://github.com/prateekscode");
		return "home";
	}

	@RequestMapping("/about")
	public String aboutPage(Model model) {
		System.out.println("Loading about page");
		model.addAttribute("isLogin", false);
		return "About";
	}

	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("Loading services page");
		return "Services";
	}

	@GetMapping("/contact")
	public String contact() {
		return new String("contact");
	}
	
	@GetMapping("/login")
	public String login() {
		return new String("login");
	}
	
	@GetMapping("/register")
	public String register() {
		return new String("register");
	}
	
	

}
