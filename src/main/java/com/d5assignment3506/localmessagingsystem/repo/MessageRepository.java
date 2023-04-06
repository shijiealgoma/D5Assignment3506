package com.d5assignment3506.localmessagingsystem.repo;

import com.d5assignment3506.localmessagingsystem.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long>  {
    // Message findMessageById(Long id);
    
    <S extends Message> S save(S message);

    Message findBySender(String c);

    Object findAllBySender(String s);


    // Message findByReceiver(String receiver);

    List<Message> findALLBySenderOrReceiver(String sender, String receiver);

    // Message findByTimestamp(String timestamp);

    @Query(value = "SELECT * FROM messages WHERE (receiver = :receiver AND sender = :sender) OR (receiver = :sender AND sender = :receiver)", nativeQuery = true)
    List<Message> findMessagesByReceiverAndSender(@Param("receiver") String receiver, @Param("sender") String sender);
}
