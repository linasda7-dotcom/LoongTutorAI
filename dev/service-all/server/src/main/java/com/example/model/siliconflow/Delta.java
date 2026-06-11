package com.example.model.siliconflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Delta {
    private String role;
    private String content;

    @JsonProperty("reasoning_content")
    private String reasoningContent;

    /**
     * AI回复构造器
     * 
     * @param role
     * @param content
     * @param reasoningContent
     */
    public Delta(String role, String content, String reasoningContent) {
        this.role = role;
        this.content = content;
        this.reasoningContent = reasoningContent;
    }

    public Delta() {

    }

    /**
     * 用户提问构造器
     * 
     * @param role
     * @param content
     */
    public Delta(String role, String content) {
        this.role = role;
        this.content = content;
    }

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
