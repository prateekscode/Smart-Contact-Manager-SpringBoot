package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void loggedInUserInformation(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in user information to the model.");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}",username);
        //get user from db and show on page
        User user = userService.getUserByEmail(username);
        System.out.println(user);
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser",user);
    }
}
