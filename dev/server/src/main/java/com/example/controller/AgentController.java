package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.service.AIChatService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AgentController {

    private final AIChatService aiChatsService;

    public AgentController(AIChatService aiChat) {
        this.aiChatsService = aiChat;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestParam String param) {
        return aiChatsService.streamChat(param);
    }

}