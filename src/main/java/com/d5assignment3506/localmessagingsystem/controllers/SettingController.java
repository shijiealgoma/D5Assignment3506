package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/setting")
    public String register(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("title", "setting Page");
        model.addAttribute("allUsers", listUsers);
        return "setting";
    }
    
}
