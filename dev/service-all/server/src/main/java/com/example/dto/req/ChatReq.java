package com.example.dto.req;

//TODO 后续需要升级
public class ChatReq {
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ChatReq() {
    }

    public ChatReq(String question) {
        this.question = question;
    }

}
