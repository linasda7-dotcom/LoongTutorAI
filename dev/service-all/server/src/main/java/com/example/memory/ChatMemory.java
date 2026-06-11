package com.example.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.siliconflow.Delta;


@Component
public class ChatMemory {
    private final List<Delta> memoryMsg;

    public ChatMemory() {
        this.memoryMsg = new ArrayList<Delta>();
    }

    public List<Delta> getMemoryMsg() {
        return memoryMsg;
    }

    public void addMessage(Delta msg) {
        this.memoryMsg.add(msg);
    }
}
