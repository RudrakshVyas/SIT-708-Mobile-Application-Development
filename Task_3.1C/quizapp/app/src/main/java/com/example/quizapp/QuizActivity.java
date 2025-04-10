package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, questionCount, userName;
    Button option1, option2, option3, buttonSubmit;
    ProgressBar progressBar;

    Question[] questions;
    int currentIndex = 0;
    int score = 0;
    int selectedAnswer = -1;
    String name = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.textQuestion);
        questionCount = findViewById(R.id.textQuestionCount);
        userName = findViewById(R.id.textUserName);
        option1 = findViewById(R.id.buttonOption1);
        option2 = findViewById(R.id.buttonOption2);
        option3 = findViewById(R.id.buttonOption3);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        progressBar = findViewById(R.id.progressBar);

        name = getIntent().getStringExtra("userName");
        userName.setText("Welcome, " + name);

        questions = QuestionBank.getQuestions();
        loadQuestion();

        option1.setOnClickListener(v -> selectAnswer(0));
        option2.setOnClickListener(v -> selectAnswer(1));
        option3.setOnClickListener(v -> selectAnswer(2));

        buttonSubmit.setOnClickListener(v -> {
            if (selectedAnswer == -1) return;

            int correct = questions[currentIndex].getAnswerIndex();

            if (selectedAnswer == correct) {
                score++;
                getSelectedButton().setBackgroundColor(Color.GREEN);
            } else {
                getSelectedButton().setBackgroundColor(Color.RED);
                getCorrectButton().setBackgroundColor(Color.GREEN);
            }

            buttonSubmit.setText("Next");
            buttonSubmit.setOnClickListener(v2 -> {
                currentIndex++;
                if (currentIndex < questions.length) {
                    resetOptions();
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("userName", name);
                    startActivity(intent);
                    finish();
                }
            });
        });
    }

    void loadQuestion() {
        Question q = questions[currentIndex];
        questionText.setText(q.getQuestion());
        option1.setText(q.getOptions()[0]);
        option2.setText(q.getOptions()[1]);
        option3.setText(q.getOptions()[2]);
        questionCount.setText("Q" + (currentIndex + 1) + "/" + questions.length);
        progressBar.setProgress((int)(((double)(currentIndex) / questions.length) * 100));
    }

    void selectAnswer(int index) {
        resetOptions();
        selectedAnswer = index;
        getSelectedButton().setBackgroundColor(Color.YELLOW);
    }

    Button getSelectedButton() {
        if (selectedAnswer == 0) return option1;
        if (selectedAnswer == 1) return option2;
        return option3;
    }

    Button getCorrectButton() {
        int ans = questions[currentIndex].getAnswerIndex();
        if (ans == 0) return option1;
        if (ans == 1) return option2;
        return option3;
    }

    @SuppressLint("SetTextI18n")
    void resetOptions() {
        selectedAnswer = -1;
        option1.setBackgroundColor(Color.LTGRAY);
        option2.setBackgroundColor(Color.LTGRAY);
        option3.setBackgroundColor(Color.LTGRAY);
        buttonSubmit.setText("Submit");
    }
}
