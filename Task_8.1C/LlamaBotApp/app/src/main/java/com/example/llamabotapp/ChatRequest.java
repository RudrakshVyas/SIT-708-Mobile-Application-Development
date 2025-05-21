package com.example.llamabotapp;

public class ChatRequest {
    private String message;

    public ChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
