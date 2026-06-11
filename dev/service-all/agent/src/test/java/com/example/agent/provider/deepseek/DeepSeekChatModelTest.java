package com.example.agent.provider.deepseek;

import org.junit.jupiter.api.Test;

import com.example.agent.core.model.ChatModel;

public class DeepSeekChatModelTest {

    @Test
    void testChat() {
        ChatModel openAiChatModel = new DeepSeekChatModel();
        System.out.println(openAiChatModel.chat("你好"));
    }
}
