package com.example.agent.core.model;

import com.example.agent.core.request.ChatRequest;

public interface ChatModel {

    ChatModelResponse chat(ChatRequest message);

    default String modelName() {
        return "";
    };
}
