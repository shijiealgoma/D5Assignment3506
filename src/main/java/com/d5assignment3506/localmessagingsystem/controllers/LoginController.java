package com.d5assignment3506.localmessagingsystem.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String index(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }
    
}
