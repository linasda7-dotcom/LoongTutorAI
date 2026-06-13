package com.example.agent.core.request;

import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.message.ChatMessage;
import com.example.agent.core.tool.ToolMetadata;

public class ChatRequest {
    private String model;
    private String systemMessage;
    private List<ChatMessage> messages = new ArrayList<>();
    private List<ToolMetadata> tools = new ArrayList<>();
    private Double temperature;

    public ChatRequest() {
    }

    public String getModel() {
        return model;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public List<ToolMetadata> getTools() {
        return tools;
    }

    public Double getTemperature() {
        return temperature;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ChatRequest request = new ChatRequest();

        public Builder model(String model) {
            request.model = model;
            return this;
        }

        public Builder systemMessage(String systemMessage) {
            request.systemMessage = systemMessage;
            return this;
        }

        public Builder messages(List<ChatMessage> message) {
            if (message != null) {
                request.messages = message;
            }
            return this;
        }

        public Builder tools(List<ToolMetadata> tools) {
            if (tools != null) {
                request.tools = tools;
            }
            return this;
        }

        public Builder temperature(Double temperature) {
            request.temperature = temperature;
            return this;
        }

        public ChatRequest build() {
            if (request.model == null || request.model.isBlank()) {
                throw new IllegalArgumentException("model is null");
            }

            if (request.messages == null || request.messages.isEmpty()) {
                throw new IllegalArgumentException("messages is empty");
            }

            if (request.temperature == null) {
                request.temperature = 0.7;
            }

            return request;
        }
    }
}
