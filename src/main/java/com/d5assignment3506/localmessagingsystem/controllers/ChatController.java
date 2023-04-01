/*******************
D5 Assignment 3506
Shijie Sun
Lei Xie
Shuming Lin
Duc Le
********************/ 

package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.MessageRepository;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepo;
    private MessageRepository messageRepo;

    // chat page with all users and messages
    @RequestMapping(value = "/chat")
    public String register(Model model) {
        List<User> listUsers = userRepo.findAll();
        // List<Message> listMessages = messageRepo.findAll();

        model.addAttribute("allUsers", listUsers);
        // model.addAttribute("allMessages", listMessages);

        // try {
        // List<User> listUsers = userRepo.findAll();
        // List<Message> listMessages = messageRepo.findAll();
        // model.addAttribute("allUsers", listUsers);
        // model.addAttribute("allMessages", listMessages);
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        return "chat";
    }


    @PostMapping("/userDetail2")
    public String userDetails(@ModelAttribute("user") String username, Model model) {

        User userDetail = userRepo.findByUsername(username);

        model.addAttribute("userDetail", userDetail);

        try {
            List<User> listUsers = userRepo.findAll();
            // List<Message> listMessages = messageRepo.findAll();
            model.addAttribute("allUsers", listUsers);
            // model.addAttribute("allMessages", listMessages);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "chat";
    }

}
