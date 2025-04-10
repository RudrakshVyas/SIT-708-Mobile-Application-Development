package com.example.quizapp;

public class Question {
    private final String question;
    private final String[] options;
    private final int answerIndex;

    public Question(String question, String[] options, int answerIndex) {
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}
