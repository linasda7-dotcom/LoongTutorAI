package com.example.dto.res;

public class ChatRes {
    private String content;
    private String role;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ChatRes(String content, String role) {
        this.content = content;
        this.role = role;
    }

    public ChatRes() {
    }

}
