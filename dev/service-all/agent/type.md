```java

public class Main {
    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    private Usage usage;

    @SerializedName("system_fingerprint")
    private String systemFingerprint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getSystemFingerprint() {
        return systemFingerprint;
    }

    public void setSystemFingerprint(String systemFingerprint) {
        this.systemFingerprint = systemFingerprint;
    }
}

public class Usage {
    @SerializedName("prompt_tokens")
    private int promptTokens;

    @SerializedName("completion_tokens")
    private int completionTokens;

    @SerializedName("total_tokens")
    private int totalTokens;

    @SerializedName("completion_tokens_details")
    private CompletionTokensDetails completionTokensDetails;

    @SerializedName("prompt_tokens_details")
    private PromptTokensDetails promptTokensDetails;

    @SerializedName("prompt_cache_hit_tokens")
    private int promptCacheHitTokens;

    @SerializedName("prompt_cache_miss_tokens")
    private int promptCacheMissTokens;

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

public class PromptTokensDetails {
    @SerializedName("cached_tokens")
    private int cachedTokens;

    public int getCachedTokens() {
        return cachedTokens;
    }

    public void setCachedTokens(int cachedTokens) {
        this.cachedTokens = cachedTokens;
    }
}

public class CompletionTokensDetails {
    @SerializedName("reasoning_tokens")
    private int reasoningTokens;

    public int getReasoningTokens() {
        return reasoningTokens;
    }

    public void setReasoningTokens(int reasoningTokens) {
        this.reasoningTokens = reasoningTokens;
    }
}

public class Choices {
    private int index;

    private Message message;

    @SerializedName("finish_reason")
    private String finishReason;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}

public class Message {
    private String role;

    private String content;

    @SerializedName("reasoning_content")
    private String reasoningContent;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReasoningContent() {
        return reasoningContent;
    }

    public void setReasoningContent(String reasoningContent) {
        this.reasoningContent = reasoningContent;
    }
}
```
