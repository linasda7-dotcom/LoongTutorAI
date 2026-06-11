package com.example.agent.core.message;

public class ToolMessage extends ChatMessage {

    public ToolMessage(String content) {
        super(content);
    }

    @Override
    public String role() {
        return "tool";
    }

}
