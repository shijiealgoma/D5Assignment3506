package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/setting")
    public String register(HttpServletRequest request, Model model) {
//        List<User> listUsers = userRepo.findAll();
//        model.addAttribute("title", "setting Page");
//        model.addAttribute("allUsers", listUsers);
        String username = (String) request.getSession().getAttribute("username");
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "setting";
    }
    
}
