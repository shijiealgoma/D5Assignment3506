package com.d5assignment3506.localmessagingsystem;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import com.d5assignment3506.localmessagingsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setTitle("Engineer");

        userService.save(user);
        Long id = user.getId();

        User savedUser = userService.get(id);
        assertNotNull(savedUser);
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getTitle(), savedUser.getTitle());
    }

    @Test
    public void testSaveUser1() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setTitle("Engineer");

        userService.save(user);

        User savedUser = userRepository.findById(user.getId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("password", savedUser.getPassword());
        assertEquals("Engineer", savedUser.getTitle());
    }
}


















