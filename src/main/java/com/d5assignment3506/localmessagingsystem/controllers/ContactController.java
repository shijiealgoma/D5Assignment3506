/*******************
D5 Assignment 3506
Shijie Sun
Lei Xie
Shuming Lin
Duc Le
********************/ 

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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

    @Autowired
    private UserRepository userRepo;

    // contact page with all users
    @RequestMapping(value = "/contact")
    public String register(Model model, HttpServletRequest request) {

        String username = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    model.addAttribute("username", username);

                }
            }
        }

        List<User> listUsers = userRepo.findAll();
        model.addAttribute("allUsers", listUsers);

        return "contact";
    }

    // contact page user detail
    @PostMapping("/userDetail")
    public String userDetails(@ModelAttribute("user") String username, Model model) {

        User userDetail = userRepo.findByUsername(username);

        model.addAttribute("userDetail", userDetail);

        return "contact";
    }
    
}
