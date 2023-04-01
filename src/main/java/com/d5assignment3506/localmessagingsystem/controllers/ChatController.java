package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.MessageRepository;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepo;
    private MessageRepository messageRepo;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/chat")
    public String register(Model model) {

        try {
            List<User> listUsers = userRepo.findAll();
            List<Message> listMessages = messageRepo.findAll();
            model.addAttribute("allUsers", listUsers);
            model.addAttribute("allMessages", listMessages);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "chat";
    }

}
