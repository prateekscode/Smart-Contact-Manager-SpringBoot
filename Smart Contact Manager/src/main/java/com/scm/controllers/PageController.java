package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	@Autowired
	private UserService userService;
	
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

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

	//this is showing login page
	@GetMapping("/login")
	public String login() {
		return new String("login");
	}

	//registration page
	@GetMapping("/register")
	public String register(Model model) {
		// default data
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "register";
	}

	// processing register

	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult bindingResult,HttpSession session) {
		// fetch form data
		// userForm
		System.out.println(userForm);
		// validate form data
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		// save in database
//		userService
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setAbout(userForm.getAbout());
		user.setProfilePic("/Images/defaultprofilepic.png");
		
		
		User savedUser = userService.saveUser(user);
		System.out.println("user Saved");
		// message:Registration successful
		
		//add message
		
		Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
		session.setAttribute("message", message);
		
		// redirect to login page
		return "redirect:/register";
	}

}
