package com.example.agent.core.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.memory.ChatMemory;
import com.example.agent.core.message.AssistantMessage;
import com.example.agent.core.message.ToolMessage;
import com.example.agent.core.message.UserMessage;
import com.example.agent.core.model.ChatModel;
import com.example.agent.core.prompt.PromptBuilder;
import com.example.agent.core.request.ChatRequest;
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
    private final ToolExecutor toolExecutor;
    private final PromptBuilder promptBuilder;

    public AiServiceInvocationHandler(ChatModel chatModel, ChatMemory chatMemory, String systemMessage,
            Object[] tools) {

        this.chatModel = chatModel;
        this.chatMemory = chatMemory;
        this.systemMessage = systemMessage;
        this.promptBuilder = new PromptBuilder();
        if (tools != null) {
            for (Object tool : tools) {
                toolMetadataList.addAll(ToolScanner.scan(tool));
            }
        }
        this.toolExecutor = new ToolExecutor(toolMetadataList);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取类声明
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }
        String userMessage = buildUserMessage(args);
        ChatRequest firstRequest = promptBuilder.buildFirstRequest(
                chatModel.modelName(),
                systemMessage,
                chatMemory,
                userMessage,
                toolMetadataList,
                null);

        String firstResponse = chatModel.chat(firstRequest);
        ToolCall toolCall = ToolCallParser.parse(firstResponse);
        if (toolCall == null) {
            saveConversation(userMessage, firstResponse);
            return firstResponse;
        }

        String toolResult = toolExecutor.execute(toolCall);

        ToolMessage toolMessage = new ToolMessage(
                toolCall.name(), toolResult);

        ChatRequest secondRequest = promptBuilder.buildSecondRequest(chatModel.modelName(),
                systemMessage,
                chatMemory,
                userMessage,
                toolMessage,
                toolMetadataList,
                null);

        String finalResponse = chatModel.chat(secondRequest);

        saveConversation(userMessage, finalResponse);

        return finalResponse;
    }

    private String buildUserMessage(Object[] args) {
        // 如果用户输入为空
        if (args == null || args.length == 0) {
            // 返回空数据
            return "";
        }

        if (args.length == 1) {
            return String.valueOf(args[0]);
        }

        StringBuilder builder = new StringBuilder();

        for (Object arg : args) {
            builder.append(arg).append("\n");
        }
        return builder.toString().trim();
    }

    public void saveConversation(String userMessage, String aiResponse) {
        if (chatMemory == null) {
            return;
        }
        chatMemory.add(new UserMessage(userMessage));
        chatMemory.add(new AssistantMessage(aiResponse));
    }

}
