package com.example.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.callback.StreamCallback;
import com.example.client.SiliconflowClient;
import com.example.model.siliconflow.Delta;
import com.example.service.AIChatService;

@Service
public class AIChatServiceImpl implements AIChatService {
    private final SiliconflowClient siliconflowClient;

    public AIChatServiceImpl(SiliconflowClient siliconflowClient) {
        this.siliconflowClient = siliconflowClient;
    }

    @Override
    public SseEmitter streamChat(String msg) {
        Delta userMsg = new Delta("user", msg);
        SseEmitter emitter = new SseEmitter(30_000L);// 30秒超时

        new Thread(() -> {
            siliconflowClient.chat(userMsg, new StreamCallback() {
                @Override
                public void onMessage(Delta chunk) {
                    try {
                        emitter.send(chunk);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }).start();
        return emitter;
    }
}
