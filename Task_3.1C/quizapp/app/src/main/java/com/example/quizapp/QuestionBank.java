package com.example.quizapp;

public class QuestionBank {

    public static Question[] getQuestions() {
        return new Question[]{
                new Question(
                        "Which company developed the Android operating system?",
                        new String[]{"Google", "Apple", "Microsoft"},
                        2
                ),
                new Question(
                        "What does RAM stand for in computing?",
                        new String[]{"Read-Only Memory", "Random Access Memory", "Real Application Manager"},
                        1
                ),
                new Question(
                        "Which symbol is used to denote a comment in Java?",
                        new String[]{"//", "##", "<!-- -->"},
                        0
                ),
                new Question(
                        "How many bits are there in a byte?",
                        new String[]{"4", "8", "16"},
                        1
                ),
                new Question(
                        "If 1 = 5, 2 = 25, 3 = 325, 4 = 4325, then 5 = ?",
                        new String[]{"54325", "5", "Error"},
                        1
                )
        };
    }
}
