package com.example.agent.provider.openai.dto.request;

public record OpenAiMessage(String role,
        String content) {
}
