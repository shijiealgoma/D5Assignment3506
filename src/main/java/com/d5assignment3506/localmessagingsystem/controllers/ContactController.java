

package com.d5assignment3506.localmessagingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @RequestMapping(value = "/contact")
    public String register(Model model3) {
        model3.addAttribute("title", "contact Page");
        return "contact";
    }
    
}
