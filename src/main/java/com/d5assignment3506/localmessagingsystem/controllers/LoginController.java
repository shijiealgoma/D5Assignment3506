package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/userLogin")
    public String login(@ModelAttribute("user") User user, Model model) {

        String username = user.getUsername();
        User checkUser = userRepo.findByUsername(username);

        if(checkUser == null || !user.getPassword().equals(checkUser.getPassword())) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }

        return "chat";
    }

    @RequestMapping(value = "/registerUser")
    public String register(Model model2) {
        model2.addAttribute("title", "register Page");
        return "register";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


}
