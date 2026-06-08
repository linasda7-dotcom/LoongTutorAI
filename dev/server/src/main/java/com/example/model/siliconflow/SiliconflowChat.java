package com.example.model.siliconflow;

import java.util.List;

public class SiliconflowChat {
    private String model;
    private List<Delta> deltas;
    private Boolean stream = false;

    public SiliconflowChat(String model, List<Delta> deltas, boolean stream) {
        this.model = model;
        this.deltas = deltas;
        this.stream = stream;
    }

    public SiliconflowChat() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Delta> getMessages() {
        return deltas;
    }

    public void setMessages(List<Delta> deltas) {
        this.deltas = deltas;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }
}
