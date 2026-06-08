package com.example.model.siliconflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choices {
    private int index;

    private Delta delta;

    @JsonProperty("finish_reason")
    private String finishReason;

    public Choices() {
    }

    public Choices(int index, Delta delta, String finishReason) {
        this.index = index;
        this.delta = delta;
        this.finishReason = finishReason;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
