

package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/contact")
    public String showContact(Model model) {
        List<User> allUsers = userRepo.findAll();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("user", new User());
        return "contact";
    }

    @PostMapping("/userDetail")
    public String getUserDetail(@ModelAttribute("user") User user, Model model) {
        // Get user data from database and set it to the User object
        User userData = userRepo.findById(user.getId()).orElse(null);
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setTitle(userData.getTitle());
//        user.setJoinDate(userData.getJoinDate());
        model.addAttribute("user", user);
        return "contact";
    }


}

