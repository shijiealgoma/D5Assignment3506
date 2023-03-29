package com.d5assignment3506.localmessagingsystem.repo;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InMemoryChatRepository implements ChatRepository {

    // A thread-safe list to store the chat messages
    private final List<String> messages = new CopyOnWriteArrayList<>();

    /**
     * Gets a list of chat messages starting from the specified index.
     * @param messageIndex the starting index of the chat messages to retrieve
     * @return a list of chat messages
     */
    @Override
    public List<String> getMessages(Integer messageIndex) {
        if (this.messages.isEmpty()) {
            return Collections.emptyList();
        }
        int startIndex = (messageIndex != null) ? messageIndex : 0;
        return this.messages.subList(startIndex, this.messages.size());
    }

    @Override
    public List<String> getMessages(int messageIndex) {
        return null;
    }

    /**
     * Adds a chat message to the repository.
     * @param message the chat message to add
     */
    @Override
    public void addMessage(String message) {
        this.messages.add(message);
    }

    /**
     * Clears all messages from the repository.
     */
    @Override
    public void clearMessages() {
        this.messages.clear();
    }
}
