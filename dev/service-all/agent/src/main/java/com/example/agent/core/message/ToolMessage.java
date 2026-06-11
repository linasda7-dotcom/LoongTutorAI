package com.example.agent.core.message;

public class ToolMessage extends ChatMessage {
    private final String name;

    public ToolMessage(String name, String content) {
        super("tool", content);
        this.name = name;
    }

    @Override
    public String role() {
        return "tool";
    }

    public String name() {
        return name;
    }

}
