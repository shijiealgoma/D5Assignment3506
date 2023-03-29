package com.d5assignment3506.localmessagingsystem.controllers;


import com.d5assignment3506.localmessagingsystem.repo.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatRepository chatRepository;
    private final Map<DeferredResult<List<String>>, Integer> chatRequests = new ConcurrentHashMap<>();

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public String chatroom() {
        return "chat";
    }

    @GetMapping("/messages")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public DeferredResult<List<String>> getMessages(@RequestParam(name = "messageIndex", defaultValue = "0") int messageIndex) {
        final DeferredResult<List<String>> deferredResult = new DeferredResult<>(null, Collections.emptyList());
        chatRequests.put(deferredResult, messageIndex);

        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        List<String> messages = chatRepository.getMessages(messageIndex);
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }

        return deferredResult;
    }

    @PostMapping("/messages")
    public void postMessage(@RequestParam("message") String message) {
        chatRepository.addMessage(message);

        for (Map.Entry<DeferredResult<List<String>>, Integer> entry : chatRequests.entrySet()) {
            List<String> messages = chatRepository.getMessages(entry.getValue());
            entry.getKey().setResult(messages);
        }
    }

    @PostMapping("/leave")
    public String leaveChat() {
        chatRepository.clearMessages();
        return "redirect:/chat";
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
