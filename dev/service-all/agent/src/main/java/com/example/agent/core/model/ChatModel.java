package com.example.agent.core.model;

import com.example.agent.core.request.ChatRequest;

public interface ChatModel {

    String chat(ChatRequest message);

    String modelName();
}
