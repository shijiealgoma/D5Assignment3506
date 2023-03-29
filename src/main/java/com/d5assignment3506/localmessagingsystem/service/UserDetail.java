package com.d5assignment3506.localmessagingsystem.service;

import com.d5assignment3506.localmessagingsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetail {

    private User user;

    public UserDetail(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

}
