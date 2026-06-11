package com.example.model.siliconflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletionTokensDetails {
    @JsonProperty("reasoning_tokens")
    private int reasoningTokens;

    public CompletionTokensDetails(int reasoningTokens) {
        this.reasoningTokens = reasoningTokens;
    }

    public CompletionTokensDetails() {
    }

    public int getReasoningTokens() {
        return reasoningTokens;
    }

    public void setReasoningTokens(int reasoningTokens) {
        this.reasoningTokens = reasoningTokens;
    }
}
