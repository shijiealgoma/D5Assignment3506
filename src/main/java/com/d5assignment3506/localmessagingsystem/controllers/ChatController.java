package com.d5assignment3506.localmessagingsystem.controllers;


import com.d5assignment3506.localmessagingsystem.repo.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/mvc/chat") //
public class ChatController {

    private final ChatRepository chatRepository;

    private final Map<DeferredResult<List<String>>, Integer> chatRequests =
            new ConcurrentHashMap<DeferredResult<List<String>>, Integer>();


    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public DeferredResult<List<String>> getMessages(@RequestParam int messageIndex) {

        final DeferredResult<List<String>> deferredResult = new DeferredResult<List<String>>(null, Collections.emptyList());
        this.chatRequests.put(deferredResult, messageIndex);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                chatRequests.remove(deferredResult);
            }
        });

        List<String> messages = this.chatRepository.getMessages(messageIndex);
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }

        return deferredResult;
    }

    @PostMapping
    public void postMessage(@RequestParam String message) {

        this.chatRepository.addMessage(message);

        // Update all chat requests as part of the POST request


        for (Entry<DeferredResult<List<String>>, Integer> entry : this.chatRequests.entrySet()) {
            List<String> messages = this.chatRepository.getMessages(entry.getValue());
            entry.getKey().setResult(messages);
        }
    }

}



//@Controller
//public class ChatController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public ChatController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @RequestMapping(value = "/chat")
//    public String showChat(Model model) {
//        List<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "chat";
//    }
//}
