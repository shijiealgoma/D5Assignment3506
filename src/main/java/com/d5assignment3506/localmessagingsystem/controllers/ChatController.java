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
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepo;

    private final MessageRepository messageRepo;

    @Autowired
    public ChatController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    // @Autowired
    // private MessageService messageService;

    // chat page with all users and messages
    @RequestMapping(value = "/chat")
    public String register(HttpServletRequest request, Model model) {
        List<User> listUsers = userRepo.findAll();
        List<Message> listMessages = messageRepo.findAll();

        // get username from session
        String username = (String) request.getSession().getAttribute("username");
        User user = userRepo.findByUsername(username);
        String userID = user.getId().toString();
        Long id = user.getId();
        String userFullName = user.getFirstName() + " " + user.getLastName();
        String senderImage = user.getImage();

        User userToRemove = null;
        for (User user1 : listUsers) {
            if (user1.getId().equals(id)) {
                userToRemove = user1;
                break;
            }
        }

        // If the user is found, remove them from the list
        if (userToRemove != null) {
            listUsers.remove(userToRemove);
        }

        model.addAttribute("allUsers", listUsers);
        model.addAttribute("senderID", userID);
        model.addAttribute("senderName", userFullName);
        model.addAttribute("senderImage", senderImage);
        // model.addAttribute("messages", listMessages);

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

    @PostMapping("/getMessages")
    public ResponseEntity<String> getMessages(
            @ModelAttribute("receiver") String receiver,
            @ModelAttribute("sender") String sender,

            Model model, HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Message> messages = messageRepo.findMessagesByReceiverAndSender(receiver, sender);
            return new ResponseEntity<>(mapper.writeValueAsString(messages),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // add message to database
    @PostMapping("/add")
    public String userDetails(
            @ModelAttribute("content") String content,
            @ModelAttribute("receiver") String receiver,
            Model model, HttpServletRequest request) {

        String username = (String) request.getSession().getAttribute("username");
        User user = userRepo.findByUsername(username);
        String userID = user.getId().toString();
        // print userID
        System.out.println("userID: " + userID);

        // print out the content
        System.out.println("content: " + content);

        // print out the receiver
        System.out.println("receiver: " + receiver);

        // check if the content is empty
        if (content != null && !content.isEmpty() &&
                receiver != null && !receiver.isEmpty()) {
            // save the message to database
            Message message = new Message();
            message.setSender(userID);
            message.setReceiver(receiver);
            message.setTimestamp(LocalDateTime.now().toString());
            message.setContent(content);

            messageRepo.save(message);
        } else {
            System.out.println("Something is empty!!");
        }

        return "chat";
    }

}
