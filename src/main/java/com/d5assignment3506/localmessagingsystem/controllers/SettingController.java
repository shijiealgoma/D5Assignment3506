package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setting")
    public String register(Model model3) {
        model3.addAttribute("title", "setting Page");
        return "setting";
    }

    @GetMapping("/setting")
    public String showSetting(Model model) {
        model.addAttribute("user", new User());
        return "setting";
    }




}