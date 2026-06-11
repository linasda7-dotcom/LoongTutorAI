package com.example.agent.provider.openai;

import org.junit.jupiter.api.Test;

import com.example.agent.core.model.ChatModel;

public class OpenAiChatModelTest {
    @Test
    void testChat() {
        ChatModel chatModel = new OpenAiChatModel();
        System.out.println(chatModel.chat("你好"));
    }
}
