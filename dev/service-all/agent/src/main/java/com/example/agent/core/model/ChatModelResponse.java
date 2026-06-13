package com.example.agent.core.model;

import java.util.List;

import com.example.agent.core.tool.ToolCall;

public record ChatModelResponse(
        String content,
        List<ToolCall> toolCalls,
        String finisReason) {
    public boolean hasToolCalls() {
        return toolCalls != null && !toolCalls.isEmpty();
    }

    public String contentOrEmpty() {
        return content == null ? "" : content;
    }

    public ToolCall firstToolCall() {
        if (!hasToolCalls()) {
            return null;
        }
        return toolCalls.get(0);
    }

    // 普通回复
    public static ChatModelResponse content(String content) {
        return new ChatModelResponse(content, List.of(), "stop");
    }

    // 工具调用回复
    public static ChatModelResponse toolCall(List<ToolCall> toolCalls) {
        return new ChatModelResponse(null, toolCalls, "tool_calls");
    }
}
