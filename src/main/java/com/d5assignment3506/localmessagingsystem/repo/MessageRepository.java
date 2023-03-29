package com.d5assignment3506.localmessagingsystem.repo;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>  {
    Message findBySender(String sender);

    Message findByReceiver(String receiver);

    Message findByTimestamp(String timestamp);
}
