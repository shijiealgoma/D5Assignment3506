package com.d5assignment3506.localmessagingsystem;

import com.d5assignment3506.localmessagingsystem.entity.User;
import com.d5assignment3506.localmessagingsystem.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.longThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepoTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        // user.setId(1); 
        user.setFirstName("test3");
        user.setLastName("test3");
        user.setUsername("test3");
        user.setPassword("test");
        user.setEmail("test@email.com");
        user.setTitle("Developer");


        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());


        User user2 = new User();
        user2.setFirstName("test2");
        user2.setLastName("test2");
        user2.setUsername("test2");
        user2.setPassword("test");
        user2.setEmail("test2@email.com");
        user2.setTitle("Manager");


        User savedUser2 = repo.save(user2);
        User existUser2 = entityManager.find(User.class, savedUser2.getId());

        assertThat(existUser2.getEmail()).isEqualTo(user2.getEmail());

    }

}
