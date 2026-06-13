package com.example.agent.provider.openai;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.agent.core.request.ChatRequest;
import com.example.agent.core.request.ChatRequestBuilder;
import com.example.agent.core.tool.ToolParameterMetadata;
import com.example.agent.provider.openai.dto.request.OpenAiChatRequest;
import com.example.agent.provider.openai.dto.request.OpenAiFunction;
import com.example.agent.provider.openai.dto.request.OpenAiMessage;
import com.example.agent.provider.openai.dto.request.OpenAiParameters;
import com.example.agent.provider.openai.dto.request.OpenAiProperty;
import com.example.agent.provider.openai.dto.request.OpenAiTool;

public class OpenAiRequestBuilder implements ChatRequestBuilder<OpenAiChatRequest> {

    @Override
    public OpenAiChatRequest build(ChatRequest request) {
        OpenAiChatRequest openAiChatRequest = new OpenAiChatRequest(
                request.getModel(),
                request.getTemperature(),
                buildMessage(request),
                buildTools(request));
        return openAiChatRequest;
    }

    private List<OpenAiMessage> buildMessage(ChatRequest request) {
        List<OpenAiMessage> messages = new ArrayList<>();

        // 构造系统提示词
        if (request.getSystemMessage() != null && !request.getSystemMessage().isBlank()) {
            messages.add(new OpenAiMessage("system", request.getSystemMessage()));
        }

        // 构造消息
        if (request.getMessages() != null) {
            request.getMessages().forEach(msg -> {
                OpenAiMessage openAiMessage = new OpenAiMessage(msg.role(), msg.content());
                messages.add(openAiMessage);
            });
        }
        return messages;
    }

    private List<OpenAiTool> buildTools(ChatRequest request) {
        if (request.getTools() == null || request.getTools().isEmpty()) {
            return null;
        }

        List<OpenAiTool> tools = new ArrayList<OpenAiTool>();

        request.getTools().forEach(tool -> {
            OpenAiFunction function = new OpenAiFunction(
                    tool.name(),
                    tool.description(),
                    buildParameters(tool.parameters()));
            tools.add(new OpenAiTool("function", function));
        });

        return tools;
    }

    private OpenAiParameters buildParameters(List<ToolParameterMetadata> parameters) {
        Map<String, OpenAiProperty> properties = new LinkedHashMap<>();
        List<String> required = new ArrayList<>();

        if (parameters != null) {
            parameters.forEach(parameter -> {
                properties.put(parameter.name(), new OpenAiProperty(toJsonSchemaType(parameter.type())));
                required.add(parameter.name());
            });
        }

        return new OpenAiParameters("object", properties, required);
    }

    private String toJsonSchemaType(Class<?> type) {
        if (type == String.class) {
            return "string";
        }

        if (type == int.class || type == Integer.class || type == long.class || type == Long.class) {
            return "integer";
        }

        if (type == double.class || type == Double.class || type == float.class || type == Float.class) {
            return "number";
        }

        if (type == boolean.class || type == Boolean.class) {
            return "boolean";
        }
        return "string";
    }

}
