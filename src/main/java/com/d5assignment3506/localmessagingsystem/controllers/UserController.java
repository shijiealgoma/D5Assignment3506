package com.d5assignment3506.localmessagingsystem.controllers;

import com.alibaba.fastjson.JSONObject;
import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import com.d5assignment3506.localmessagingsystem.service.UserService;
import com.d5assignment3506.localmessagingsystem.utils.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
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

        if (checkUser != null) {
            model.addAttribute("errorMessage", "The username exist");
            return "register";
        }

        userRepo.save(user);

        return "login";
    }

    @PostMapping("/save")
    public void saveUser(User user, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        // save new user
        User checkUser = userRepo.findByUsername(user.getUsername());
        if (checkUser != null) {

            if (user.getUsername() != null) {
                checkUser.setUsername(user.getUsername());
            }
            if (user.getFirstName() != null) {
                checkUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                checkUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                checkUser.setEmail(user.getEmail());
            }
            if (user.getTitle() != null) {
                checkUser.setTitle(user.getTitle());
            }
            if (user.getImage() != null) {
                checkUser.setImage(user.getImage());
            }

            userRepo.save(checkUser);

            result.put("code", 200);
            result.put("msg", "Update user information successfully！");

        } else {

            result.put("code", 200);
            result.put("msg", "User does not exist, please create");
        }
        ControllerHelper.sendJson(response, result.toString());
    }

    @PostMapping("/delete")
    public void deleteUser(User user, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        // save new user
        User checkUser = userRepo.findByUsername(user.getUsername());
        if (checkUser != null) {

            userRepo.deleteById(checkUser.getId());

            result.put("code", 200);
            result.put("msg", "Delete user information successfully！");

        } else {

            result.put("code", 200);
            result.put("msg", "Error deleting user information! This user does not exist, please check!");
        }
        ControllerHelper.sendJson(response, result.toString());
    }

    @PostMapping("/search")
    public void searchUser(User user, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        // save new user
        User checkUser = userRepo.findByUsername(user.getUsername());
        if (checkUser != null) {
            result.put("code", 200);
            result.put("data", checkUser);
        } else {
            result.put("code", 200);
            result.put("msg", "Error checking user information! user does it not exist！");
        }
        ControllerHelper.sendJson(response, result.toString());
    }

    @PostMapping("/searchAllUser")
    public void searchAllUser(HttpServletResponse response) {

        List<User> userList = userRepo.findAll();

        JSONObject result = new JSONObject();

        result.put("code", 200);
        result.put("data", userList);
        ControllerHelper.sendJson(response, result.toString());
    }

    @GetMapping("/loginUsers")
    public String showLoginUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "loginUsers";
    }

}
