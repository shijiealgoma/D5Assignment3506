package com.d5assignment3506.localmessagingsystem.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/register")
    public String register(Model model2) {
        model2.addAttribute("title", "register Page");
        return "register";
    }
    
}
