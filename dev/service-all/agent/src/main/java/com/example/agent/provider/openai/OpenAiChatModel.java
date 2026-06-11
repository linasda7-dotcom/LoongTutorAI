package com.example.agent.provider.openai;

import com.example.agent.core.model.ChatModel;

public class OpenAiChatModel implements ChatModel {

    @Override
    public String chat(String message) {
        System.out.println("目标即将接收的参数:");
        System.out.println(message);
        if (message.contains("天气")) {
            return "TOOL_CALL:weather:广州";
        }
        return "收到";
    }

}
