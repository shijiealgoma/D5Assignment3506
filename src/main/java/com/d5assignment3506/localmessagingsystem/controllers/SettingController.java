package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String showSettingPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "setting";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        // Check for null values and handle appropriately
        if (user.getFirstName() == null) {
            user.setFirstName("");
            model.addAttribute("errorMsg", "First name cannot be null.");
        }
        if (user.getLastName() == null) {
            user.setLastName("");
            model.addAttribute("errorMsg", "Last name cannot be null.");
        }
        if (user.getEmail() == null) {
            user.setEmail("");
            model.addAttribute("errorMsg", "Email cannot be null.");
        }
        if (user.getTitle() == null) {
            user.setTitle("");
            model.addAttribute("errorMsg", "Title cannot be null.");
        }

        userService.save(user);
        model.addAttribute("user", user);
        return "setting";
    }


    @GetMapping("/contact")
    public String showContactPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "contact";
    }


}