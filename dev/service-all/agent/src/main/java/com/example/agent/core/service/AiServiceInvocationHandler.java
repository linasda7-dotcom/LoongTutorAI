package com.example.agent.core.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.memory.ChatMemory;
import com.example.agent.core.message.AiMessage;
import com.example.agent.core.message.UserMessage;
import com.example.agent.core.model.ChatModel;
import com.example.agent.core.tool.ToolCall;
import com.example.agent.core.tool.ToolCallParser;
import com.example.agent.core.tool.ToolExecutor;
import com.example.agent.core.tool.ToolMetadata;
import com.example.agent.core.tool.ToolScanner;

public class AiServiceInvocationHandler implements InvocationHandler {
    private final ChatModel chatModel;
    private final ChatMemory chatMemory;
    private final String systemMessage;
    private final List<ToolMetadata> toolMetadataList = new ArrayList<>();

    public AiServiceInvocationHandler(ChatModel chatModel, ChatMemory chatMemory, String systemMessage,
            Object[] tools) {
        this.chatModel = chatModel;
        this.chatMemory = chatMemory;
        this.systemMessage = systemMessage;

        if (tools != null) {
            for (Object tool : tools) {
                toolMetadataList.addAll(ToolScanner.scan(tool));
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 对象数组下标的第一条消息即用户输入的消息
        String message = (String) args[0];
        if (chatMemory != null) {
            // 如果消息里不是空就添加
            chatMemory.add(new UserMessage(message));
        }
        String prompt;
        if (chatMemory != null) {
            // 把历史消息组成prompt
            prompt = buildPrompt();
        } else {
            prompt = buildPromptWithoutMemory(message);
        }

        String chat = chatModel.chat(prompt);
        if (ToolCallParser.isToolCall(chat)) {
            ToolCall toolCall = ToolCallParser.pares(chat);
            ToolMetadata tool = findTool(toolCall.name());
            Object execute = ToolExecutor.execute(tool, toolCall.argument());
            chat = String.valueOf(execute);
        }
        if (chatMemory != null) {
            chatMemory.add(new AiMessage(chat));
        }
        return chat;
    }

    /**
     * 构造提示词
     * 
     * @return
     */
    private String buildPrompt() {
        StringBuilder promptBuilder = new StringBuilder();
        appendSystemMessage(promptBuilder);
        appendTools(promptBuilder);
        chatMemory.messages().forEach(msg -> {
            promptBuilder
                    .append(msg.role())
                    .append(":")
                    .append(msg.content())
                    .append("\n");
        });
        return promptBuilder.toString();
    }

    /**
     * 构造提示词并输出历史消息
     * 
     * @param message
     * @return
     */
    private String buildPromptWithoutMemory(String message) {
        StringBuilder promptBuilder = new StringBuilder();
        appendSystemMessage(promptBuilder);
        appendTools(promptBuilder);
        promptBuilder
                .append("user")
                .append(message)
                .append("\n");
        return promptBuilder.toString();
    }

    /**
     * 系统提示词构造
     * 
     * @param promptBuilder
     */
    private void appendSystemMessage(StringBuilder promptBuilder) {

        if (promptBuilder != null && !systemMessage.isBlank()) {
            promptBuilder
                    .append("system:")
                    .append(systemMessage)
                    .append("\n");
        }
    }

    private void appendTools(StringBuilder promptBuilder) {
        if (toolMetadataList.isEmpty()) {
            return;
        }
        promptBuilder.append("tools:\n");
        for (ToolMetadata tool : toolMetadataList) {
            promptBuilder
                    .append("- name:")
                    .append(tool.name())
                    .append(",description:")
                    .append(tool.description())
                    .append("\n");
        }
    }

    private ToolMetadata findTool(String name) {
        for (ToolMetadata toll : toolMetadataList) {
            if (toll.name().equals(name)) {
                return toll;
            }
        }
        throw new IllegalArgumentException("未找到工具：" + name);
    }

}
