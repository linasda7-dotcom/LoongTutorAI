package com.example.agent.core.tool;

public class ToolCallParser {
    private static final String TOOL_CALL_PREFIX = "TOOL_CALL:";

    public static ToolCall parse(String content) {

        if (content == null || content.isBlank()) {
            return null;
        }

        // AI返回的数据是否是TOOL_CALL开头
        if (!content.startsWith(TOOL_CALL_PREFIX)) {
            return null;
        }

        String body = content.substring(TOOL_CALL_PREFIX.length());
        String[] parts = body.split(":", 2);

        if (parts.length < 2) {
            throw new RuntimeException("工具调用格式错误:" + content);
        }

        String name = parts[0].trim();
        String arguments = parts[1].trim();

        if (name == null || name.isBlank()) {
            throw new RuntimeException("工具名称不能为空:" + content);
        }

        if (arguments.isBlank()) {
            throw new RuntimeException("工具参数不能为空:" + content);
        }

        return new ToolCall("1", name, arguments);
    }
}
