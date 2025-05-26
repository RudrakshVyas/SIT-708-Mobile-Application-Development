package com.example.personalizedlearningapp.activities;

public class Question {

    private String questionText;
    private String option1, option2, option3;
    private int correctOptionIndex;

    public Question(String questionText, String option1, String option2, String option3, int correctOptionIndex) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
