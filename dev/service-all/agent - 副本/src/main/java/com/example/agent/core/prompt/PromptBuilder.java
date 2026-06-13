package com.example.agent.core.prompt;

import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.memory.ChatMemory;
import com.example.agent.core.message.ChatMessage;
import com.example.agent.core.message.ToolMessage;
import com.example.agent.core.message.UserMessage;
import com.example.agent.core.request.ChatRequest;
import com.example.agent.core.tool.ToolMetadata;

/**
 * 这个类只负责构造ChatRequest请求并不负责存储消息
 */
public class PromptBuilder {
    public ChatRequest buildFirstRequest(
            String model,
            String systemMessage,
            ChatMemory chatMemory,
            String userMessage,
            List<ToolMetadata> tools,
            Double temperature) {

        ArrayList<ChatMessage> messages = new ArrayList<>();

        if (chatMemory != null) {
            messages.addAll(chatMemory.messages());
        }

        messages.add(new UserMessage(userMessage));

        return ChatRequest.builder()
                .model(model)
                .systemMessage(systemMessage)
                .messages(messages)
                .tools(tools)
                .temperature(temperature)
                .build();

    }

    public ChatRequest buildSecondRequest(
            String model,
            String systemMessage,
            ChatMemory chatMemory,
            String userMessage,
            ToolMessage toolMessage,
            List<ToolMetadata> tools,
            Double temperature) {
        ArrayList<ChatMessage> message = new ArrayList<>();

        // 如果历史消息存在记录则拷贝
        if (chatMemory != null) {
            message.addAll(chatMemory.messages());
        }

        // 添加本次用户的对话
        message.add(new UserMessage(userMessage));
        // 添加工具消息的对话
        message.add(toolMessage);

        return ChatRequest.builder()
                .model(model)
                .systemMessage(systemMessage)
                .tools(tools)
                .messages(message)
                .temperature(temperature)
                .build();
    }
}