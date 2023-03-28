package com.d5assignment3506.localmessagingsystem.repo;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**

 An in-memory implementation of the {@link ChatRepository} interface.

 Keeps track of chat messages in a {@link java.util.List}.
 */
@Repository
public class InMemoryChatRepository implements ChatRepository {

    // A thread-safe list to store the chat messages
    private final List<String> messages = new CopyOnWriteArrayList<String>();

    /**

     Gets a list of chat messages starting from the specified index.
     @param index the starting index of the chat messages to retrieve
     @return a list of chat messages
     @throws IllegalArgumentException if the specified index is out of bounds
     */
    public List<String> getMessages(int index) {
        if (this.messages.isEmpty()) {
            return Collections.<String> emptyList();
        }
        Assert.isTrue((index >= 0) && (index <= this.messages.size()), "Invalid message index");
        return this.messages.subList(index, this.messages.size());
    }
    /**

     Adds a chat message to the list.
     @param message the chat message to add
     */
    public void addMessage(String message) {
        this.messages.add(message);
    }
}

