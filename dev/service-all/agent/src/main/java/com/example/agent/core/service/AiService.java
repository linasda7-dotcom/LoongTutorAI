package com.example.agent.core.service;

import java.lang.reflect.Proxy;

import com.example.agent.core.memory.ChatMemory;
import com.example.agent.core.model.ChatModel;

public class AiService<T> {
    private final Class<T> serviceClass;
    private ChatModel chatModel;
    private ChatMemory chatMemory;
    private String systemMessage;
    private Object[] tools;

    private AiService(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public static <T> AiService<T> builder(Class<T> serviceClass) {
        return new AiService<>(serviceClass);
    }

    public AiService<T> systemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
        return this;
    }

    public AiService<T> chatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
        return this;
    }

    public AiService<T> chatMemory(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
        return this;
    }

    public AiService<T> tools(Object... tools) {
        this.tools = tools;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T build() {
        if (serviceClass == null) {
            throw new IllegalArgumentException("serviceClass不能为空");
        }
        if (!serviceClass.isInterface()) {
            throw new IllegalArgumentException("AiService 必须是接口");
        }
        if (chatModel == null) {
            throw new IllegalArgumentException("chatModel不能为空");
        }
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[] { serviceClass },
                new AiServiceInvocationHandler(chatModel, chatMemory, systemMessage,tools));
    }

}
