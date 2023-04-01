package com.d5assignment3506.localmessagingsystem;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import com.d5assignment3506.localmessagingsystem.repo.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)

public class MessageRepoTest {
    
    @Autowired
    private MessageRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Message message = new Message();
        message.setSender("1");
        message.setReceiver("2");
        message.setContent("test");
        message.setTimestamp(
                "2020-11-11 11:11:12"
        );

        Message savedMessage = repo.save(message);
        Message existMessage = entityManager.find(Message.class, savedMessage.getId());
        assertThat(existMessage.getSender()).isEqualTo(message.getSender());

        Message message2 = new Message();
        message2.setSender("2");
        message2.setReceiver("1");
        message2.setContent("Hi! How are you?");
        message2.setTimestamp(
                "2020-11-11 11:12:12"
        );

        Message savedMessage2 = repo.save(message2);
        Message existMessage2 = entityManager.find(Message.class, savedMessage2.getId());
        assertThat(existMessage2.getSender()).isEqualTo(message2.getSender());


       
    }

    @Test
    public void readMessages() {
        Iterable<Message> messages = repo.findAll();
        messages.forEach(message -> System.out.println(message));
    }

    @Test
    public void readMessagesBySender() {
        Iterable<Message> messages = (Iterable<Message>) repo.findBySender("1");
        messages.forEach(message -> System.out.println(message));
    }


}
