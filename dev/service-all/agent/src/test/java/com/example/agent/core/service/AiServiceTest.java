package com.example.agent.core.service;

import org.junit.jupiter.api.Test;

import com.example.agent.core.agent.Assistant;
import com.example.agent.core.memory.ChatMemory;
import com.example.agent.core.memory.MessageWindowChatMemory;
import com.example.agent.core.tool.WeatherTool;
import com.example.agent.provider.openai.OpenAiChatModel;

public class AiServiceTest {
    @Test
    void testBuild() {
        ChatMemory memory = new MessageWindowChatMemory();
        Assistant assistant = AiService.builder(Assistant.class)
                .chatMemory(memory)
                .systemMessage("你是一个Java架构设计专家")
                .chatModel(new OpenAiChatModel())
                .tools(new WeatherTool())
                .build();

        assistant.chat("你好!今天广州天气怎么样");
        assistant.chat("我叫林");
        assistant.chat("我叫什么");
        assistant.chat("Builder的设计模式是什么？");
        System.out.println("memory = " + memory.messages().size());
    }
}