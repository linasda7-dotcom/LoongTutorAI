package com.example.agent.core.tool;

public class ToolCall {
    private final String name;
    private final String argument;

    public ToolCall(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }

    public String name() {
        return name;
    }

    public String argument() {
        return argument;
    }
}
