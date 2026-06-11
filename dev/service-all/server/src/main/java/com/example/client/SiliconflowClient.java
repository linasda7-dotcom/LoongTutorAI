package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.example.callback.StreamCallback;
import com.example.config.Siliconflow;
import com.example.memory.ChatMemory;
import com.example.model.siliconflow.Delta;
import com.example.model.siliconflow.SiliconflowChat;
import com.example.model.siliconflow.SiliconflowRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SiliconflowClient {
    private final Siliconflow modelScopeProperties;
    private final ChatMemory chatMemory;
    private final ObjectMapper mapper;

    public SiliconflowClient(Siliconflow modelScopeProperties, ChatMemory chatMemory, ObjectMapper mapper) {
        this.modelScopeProperties = modelScopeProperties;
        this.chatMemory = chatMemory;
        this.mapper = mapper;
    }

    public void chat(Delta question, StreamCallback callback) {
        chatMemory.addMessage(question);
        SiliconflowChat chatBody = new SiliconflowChat("Pro/zai-org/GLM-5", chatMemory.getMemoryMsg(), true);

        try {
            String body = mapper.writeValueAsString(chatBody);

            URI uri = URI.create(modelScopeProperties.getBaseUrl() + "/chat/completions");
            HttpRequest request = HttpRequest.newBuilder().uri(uri)
                    .header("Authorization", "Bearer " + modelScopeProperties.getApiKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            // 创建一个客户端请求
            HttpClient client = HttpClient.newHttpClient();
            // 设置流
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // 缓冲阅读
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));
            String line;
            SiliconflowRes res = new SiliconflowRes();
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("data: ")) {
                    continue;
                }
                String json = line.substring("data: ".length());
                if ("[DONE]".equals(json)) {
                    break;
                }
                res = mapper.readValue(json, SiliconflowRes.class);
                if (res.getChoices() == null || res.getChoices().isEmpty()) {
                    continue;
                }
                callback.onMessage(res.getChoices().get(0).getDelta());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
