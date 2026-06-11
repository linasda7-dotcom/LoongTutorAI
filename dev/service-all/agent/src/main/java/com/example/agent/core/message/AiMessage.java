package com.example.agent.core.message;

public class AiMessage extends ChatMessage {

    public AiMessage(String content) {
        super(content);
    }

    @Override
    public String role() {
        return "assistant";
    }

}
