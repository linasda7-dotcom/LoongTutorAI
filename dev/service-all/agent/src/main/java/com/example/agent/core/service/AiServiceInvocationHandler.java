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
import com.example.agent.core.model.ChatModelResponse;
import com.example.agent.core.prompt.PromptBuilder;
import com.example.agent.core.request.ChatRequest;
import com.example.agent.core.tool.ToolCall;

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

        // 用户消息
        String userMessage = buildUserMessage(args);
        // 构造一次远程调用模型对话请求
        ChatRequest firstRequest = promptBuilder.buildFirstRequest(
                chatModel.modelName(), // 模型名称
                systemMessage, // 系统提示词
                chatMemory, // 历史消息
                userMessage, // 本次用户消息
                toolMetadataList, // 工具列表
                null);// 温度

        // 对话开始---->得到对话结果
        ChatModelResponse firstResponse = chatModel.chat(firstRequest);

        // 模型不想调用工具
        if (!firstResponse.hasToolCalls()) {
            String aiResponse = firstResponse.contentOrEmpty();
            saveConversation(userMessage, aiResponse);
            return aiResponse;
        }

        ToolCall toolCall = firstResponse.firstToolCall();
        // 执行工具得到工具执行结果
        String toolResult = toolExecutor.execute(toolCall);

        // 工具执行结果
        ToolMessage toolMessage = new ToolMessage(toolCall.name(), toolResult);

        // 构造第二次请求
        ChatRequest secondRequest = promptBuilder.buildSecondRequest(
                chatModel.modelName(),
                systemMessage,
                chatMemory,
                userMessage,
                toolMessage,
                toolMetadataList,
                null);
        // 第二次请求
        ChatModelResponse secondResponse = chatModel.chat(secondRequest);
        // 最终回应
        String finalResponse = secondResponse.contentOrEmpty();
        // 保存消息
        saveConversation(userMessage, toolMessage, finalResponse);
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

    public void saveConversation(String userMessage, ToolMessage toolMessage, String aiResponse) {
        if (chatMemory == null) {
            return;
        }
        chatMemory.add(new UserMessage(userMessage));
        chatMemory.add(toolMessage);
        chatMemory.add(new AssistantMessage(aiResponse));
    }

    public void saveConversation(String userMessage, String aiResponse) {
        if (chatMemory == null) {
            return;
        }
        chatMemory.add(new UserMessage(userMessage));
        chatMemory.add(new AssistantMessage(aiResponse));
    }

}
