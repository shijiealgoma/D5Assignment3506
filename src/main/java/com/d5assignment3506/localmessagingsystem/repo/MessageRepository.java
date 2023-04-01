package com.d5assignment3506.localmessagingsystem.repo;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>  {
    // Message findMessageById(Long id);

    Message findBySender(String c);


    Message findByReceiver(String receiver);

    Message findBySenderOrReceiver(String sender, String receiver);

    Message findByTimestamp(String timestamp);
}
