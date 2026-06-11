package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;

import com.example.callback.StreamCallback;
import com.example.model.siliconflow.Delta;

@SpringBootTest
public class SiliconflowClientTest {

    @Autowired
    private SiliconflowClient client;

    @Test
    void testChat() {

        client.chat(
                new Delta("user", "你好"),
                new StreamCallback() {
                    @Override
                    public void onMessage(@NonNull Delta chunk) {
                        System.out.print(chunk);
                    }
                });
    }
}