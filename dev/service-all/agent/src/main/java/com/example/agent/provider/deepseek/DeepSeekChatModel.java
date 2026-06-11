package com.example.agent.provider.deepseek;

import com.example.agent.core.model.ChatModel;

public class DeepSeekChatModel implements ChatModel {

    @Override
    public String chat(String message) {
        return "Deepseek callback:" + message;
    }

}
