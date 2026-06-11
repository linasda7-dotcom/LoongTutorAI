package com.example.agent.provider.openai;

import com.example.agent.core.message.ChatMessage;
import java.util.List;

import com.example.agent.core.model.ChatModel;
import com.example.agent.core.request.ChatRequest;
import com.example.agent.core.tool.ToolMetadata;

public class OpenAiChatModel implements ChatModel {
    private final String model;

    public OpenAiChatModel(String model) {
        this.model = model;
    }

    @Override
    public String chat(ChatRequest request) {
        String requestModel = request.getModel();
        String systemMessage = request.getSystemMessage();
        List<ChatMessage> messages = request.getMessages();
        List<ToolMetadata> tools = request.getTools();
        Double temperature = request.getTemperature();
        System.out.println("目标即将接收的参数:");
        System.err.println("model = " + requestModel);
        System.out.println("systemMessage = " + systemMessage);
        System.out.println("temperature = " + temperature);

        if (tools != null && !tools.isEmpty()) {
            System.out.println("tools:");
            tools.forEach(tool -> {
                System.out.println("- name:" + tool.name() + ",description:" + tool.description());
            });
        }

        messages.forEach(msg -> {
            System.out.println(msg.role() + ":" + msg.content());
        });

        return doChat(request);
    }

    private String doChat(ChatRequest request) {
        List<ChatMessage> messages = request.getMessages();

        if (messages == null || messages.isEmpty()) {
            return "没有消息";
        }

        ChatMessage lastMessage = messages.get(messages.size() - 1);

        if ("tool".equals(lastMessage.role())) {
            return "根据工具查询结果" + lastMessage.content();
        }

        if ("user".equals(lastMessage.role())) {
            String userMessage = lastMessage.content();
            if (userMessage.contains("天气")) {
                return "TOOL_CALL:weather:广州";
            }
        }

        return "OpenAI response";
    }

    @Override
    public String modelName() {
        return model;
    }

}
