package com.example.agent.core.request;

public class ChatRequest {
    private String model;
    private String message;
    private Double temperature;

    public ChatRequest() {
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private final ChatRequest request = new ChatRequest();

        public Builder model(String model) {
            request.model = model;
            return this;
        }

        public Builder message(String message) {
            request.message = message;
            return this;
        }

        public Builder temperature(Double temperature) {
            request.temperature = temperature;
            return this;
        }

        public ChatRequest builder() {
            if (request.model == null || request.model.isBlank()) {
                throw new IllegalArgumentException("model is null");
            }
            if (request.message == null || request.message.isBlank()) {
                throw new IllegalArgumentException("message is null");
            }
            if (request.temperature == null) {
                request.temperature = 0.7;
            }
            return request;
        }
    }
}
