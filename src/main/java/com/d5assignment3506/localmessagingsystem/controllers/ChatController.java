package com.d5assignment3506.localmessagingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    @RequestMapping(value = "/chat")
    public String register(Model model3) {
        model3.addAttribute("title", "register Page");
        return "chat";
    }
    
}
