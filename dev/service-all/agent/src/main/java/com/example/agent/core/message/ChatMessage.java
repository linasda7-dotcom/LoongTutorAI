package com.example.agent.core.message;

public abstract class ChatMessage {
    private final String content;

    protected ChatMessage(String content) {
        this.content = content;
    }

    public String content() {
        return content;
    }

    public abstract String role();
}
