

package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/contact")
    public String register(Model model) {

        List<User> listUsers = userRepo.findAll();
        model.addAttribute("allUsers", listUsers);

        return "contact";
    }

    @PostMapping("/userDetail")
    public String userDetails(@ModelAttribute("user") String username, Model model) {

        User userDetail = userRepo.findByUsername(username);

        model.addAttribute("userDetail", userDetail);

        return "contact";
    }
    
}
