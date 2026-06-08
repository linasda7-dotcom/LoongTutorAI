package com.example.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AIChatService {
    SseEmitter streamChat(String msg);
}
