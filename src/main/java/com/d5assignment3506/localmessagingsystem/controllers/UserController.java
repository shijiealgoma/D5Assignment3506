package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/registerUser")
    public String newUser(User user, Model model) {
        // create new user
        System.out.println(user.getEmail() + "," + user.getUsername());
        User checkUser = userRepo.findByUsername(user.getUsername());

        if(checkUser != null) {
            model.addAttribute("errorMessage", "The username exist");
            return "register";
        }

        userRepo.save(user);

        return "login";
    }

}
