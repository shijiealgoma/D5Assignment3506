package com.d5assignment3506.localmessagingsystem.repo;

import com.d5assignment3506.localmessagingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
