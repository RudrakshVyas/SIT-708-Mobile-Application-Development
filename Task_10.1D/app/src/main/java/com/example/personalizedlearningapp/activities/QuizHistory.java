package com.example.personalizedlearningapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuizHistory {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public int score;
    public int total;
    public String questionsJson;
    public String userAnswersJson;
}
