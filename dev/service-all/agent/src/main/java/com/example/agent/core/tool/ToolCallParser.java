package com.example.agent.core.tool;

public class ToolCallParser {
    private static final String PREFIX = "TOOL_CALL:";

    public static boolean isToolCall(String text) {
        return text != null && text.startsWith(PREFIX);
    }

    public static ToolCall parse(String text) {

        if (!isToolCall(text)) {
            throw new IllegalArgumentException("不是工具调用指令:" + text);
        }

        String body = text.substring(PREFIX.length());

        String[] parts = body.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException("工具调用格式错误:" + text);
        }

        String name = parts[0];

        String argument = parts[1];

        return new ToolCall(name, argument);
    }
}
