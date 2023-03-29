package com.d5assignment3506.localmessagingsystem.controllers;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import com.d5assignment3506.localmessagingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/registerUser")
    public String newUser(User user, Model model) {
        // create new user
        System.out.println(user.getEmail() + "," + user.getUsername());
        User checkUser = userRepo.findByUsername(user.getUsername());

        if(checkUser != null) {
            model.addAttribute("errorMessage", "The username exist");
            return "register";
        }

        userRepo.save(user);

        return "login";
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userRepo.save(user);
        model.addAttribute("user", user);
        return "redirect:/contact";
    }


    @GetMapping("/contact")
    public String contact(Model model, @ModelAttribute("updatedUser") User user) {
        model.addAttribute("user", user);
        return "contact";
    }

//    @GetMapping("/contact/{id}")
//    public String showContact(@PathVariable("id") Long id, Model model) {
//        User user = userRepo.findById(id).orElse(null);
//        if (user == null) {
//            return "redirect:/"; // Redirect to the homepage or another page if the user is not found
//        }
//        model.addAttribute("user", user);
//        return "contact";
//    }


    //    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        userService.deleteById(id);
//        redirectAttributes.addFlashAttribute("message", "User deleted successfully.");
//        return "redirect:/users";
//    }




}
