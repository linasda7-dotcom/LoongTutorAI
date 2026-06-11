package com.example.model.siliconflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromptTokensDetails {
    @JsonProperty("cached_tokens")
    private int cachedTokens;

    public PromptTokensDetails() {
    }

    public PromptTokensDetails(int cachedTokens) {
        this.cachedTokens = cachedTokens;
    }

    public int getCachedTokens() {
        return cachedTokens;
    }

    public void setCachedTokens(int cachedTokens) {
        this.cachedTokens = cachedTokens;
    }
}
