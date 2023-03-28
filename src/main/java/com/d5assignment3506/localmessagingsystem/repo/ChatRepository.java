package com.d5assignment3506.localmessagingsystem.repo;

import java.util.List;

/**

 An interface for a chat repository.

 Defines methods for getting chat messages and adding new chat messages.
 */
public interface ChatRepository {

    /**

     Gets a list of chat messages starting from the specified index.
     @param messageIndex the starting index of the chat messages to retrieve
     @return a list of chat messages
     */
    List<String> getMessages(int messageIndex);
    /**

     Adds a chat message to the repository.
     @param message the chat message to add
     */
    void addMessage(String message);
}
