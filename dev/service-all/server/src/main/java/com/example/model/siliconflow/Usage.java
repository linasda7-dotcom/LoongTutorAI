package com.example.model.siliconflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {
    @JsonProperty("prompt_tokens")
    private int promptTokens;

    @JsonProperty("completion_tokens")
    private int completionTokens;

    @JsonProperty("total_tokens")
    private int totalTokens;

    @JsonProperty("completion_tokens_details")
    private CompletionTokensDetails completionTokensDetails;

    @JsonProperty("prompt_tokens_details")
    private PromptTokensDetails promptTokensDetails;

    @JsonProperty("prompt_cache_hit_tokens")
    private int promptCacheHitTokens;

    @JsonProperty("prompt_cache_miss_tokens")
    private int promptCacheMissTokens;

    public Usage(int promptTokens, int completionTokens, int totalTokens,
            CompletionTokensDetails completionTokensDetails, PromptTokensDetails promptTokensDetails,
            int promptCacheHitTokens, int promptCacheMissTokens) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.completionTokensDetails = completionTokensDetails;
        this.promptTokensDetails = promptTokensDetails;
        this.promptCacheHitTokens = promptCacheHitTokens;
        this.promptCacheMissTokens = promptCacheMissTokens;
    }

    public Usage() {
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public CompletionTokensDetails getCompletionTokensDetails() {
        return completionTokensDetails;
    }

    public void setCompletionTokensDetails(CompletionTokensDetails completionTokensDetails) {
        this.completionTokensDetails = completionTokensDetails;
    }

    public PromptTokensDetails getPromptTokensDetails() {
        return promptTokensDetails;
    }

    public void setPromptTokensDetails(PromptTokensDetails promptTokensDetails) {
        this.promptTokensDetails = promptTokensDetails;
    }

    public int getPromptCacheHitTokens() {
        return promptCacheHitTokens;
    }

    public void setPromptCacheHitTokens(int promptCacheHitTokens) {
        this.promptCacheHitTokens = promptCacheHitTokens;
    }

    public int getPromptCacheMissTokens() {
        return promptCacheMissTokens;
    }

    public void setPromptCacheMissTokens(int promptCacheMissTokens) {
        this.promptCacheMissTokens = promptCacheMissTokens;
    }
}
