package com.example.agent.provider.openai.dto.request;

import java.util.List;

public record OpenAiChatRequest(
        String model,
        Double temperature,
        List<OpenAiMessage> messages,
        List<OpenAiTool> tools) {

}
