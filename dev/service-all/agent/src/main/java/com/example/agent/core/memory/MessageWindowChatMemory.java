package com.example.agent.core.memory;

import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.message.ChatMessage;

public class MessageWindowChatMemory implements ChatMemory {
    private final List<ChatMessage> messages = new ArrayList<>();

    @Override
    public void add(ChatMessage message) {
        messages.add(message);
    }

    @Override
    public List<ChatMessage> messages() {
        return messages;
    }

}
