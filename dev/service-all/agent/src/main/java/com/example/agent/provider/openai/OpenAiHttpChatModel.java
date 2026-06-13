package com.example.agent.provider.openai;

import com.example.agent.core.model.ChatModel;
import com.example.agent.core.model.ChatModelResponse;
import com.example.agent.core.request.ChatRequest;

public class OpenAiHttpChatModel implements ChatModel {
    private static final String DEFAULT_BASE_URL = "https://api.siliconflow.cn/v1";
    private static final String CHAT_COMPLETIONS_PATH = "/chat/completions";

    @Override
    public ChatModelResponse chat(ChatRequest message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chat'");
    }

}
