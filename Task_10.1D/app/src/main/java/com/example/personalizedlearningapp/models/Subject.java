package com.example.personalizedlearningapp.models;

public class Subject {
    private String title;
    private int imageResId;

    public Subject(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}
