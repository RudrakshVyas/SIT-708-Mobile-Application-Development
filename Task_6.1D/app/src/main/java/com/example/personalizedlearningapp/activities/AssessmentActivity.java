package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;

import java.util.ArrayList;

public class AssessmentActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private RadioButton radioButtonOption1, radioButtonOption2, radioButtonOption3;
    private Button buttonNext;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "What is 2 + 2?",
            "What is the capital of India?",
            "What is the largest planet?"
    };

    private String[][] options = {
            {"3", "4", "5"},
            {"New Delhi", "Mumbai", "Chennai"},
            {"Earth", "Mars", "Jupiter"}
    };

    private int[] correctAnswers = {1, 0, 2}; // correct answers
    private ArrayList<String> userAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        radioButtonOption3 = findViewById(R.id.radioButtonOption3);
        buttonNext = findViewById(R.id.buttonNext);

        showQuestion();

        buttonNext.setOnClickListener(view -> {
            int selectedId = radioGroupOptions.getCheckedRadioButtonId();
            if (selectedId == -1) return;

            int selectedIndex = radioGroupOptions.indexOfChild(findViewById(selectedId));
            userAnswers.add(options[currentQuestionIndex][selectedIndex]);

            if (selectedIndex == correctAnswers[currentQuestionIndex]) {
                score++;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                showQuestion();
            } else {
                showResult();
            }
        });
    }

    private void showQuestion() {
        textViewQuestion.setText((currentQuestionIndex + 1) + ". " + questions[currentQuestionIndex]);
        radioButtonOption1.setText(options[currentQuestionIndex][0]);
        radioButtonOption2.setText(options[currentQuestionIndex][1]);
        radioButtonOption3.setText(options[currentQuestionIndex][2]);
        radioGroupOptions.clearCheck();
    }

    private void showResult() {
        Intent intent = new Intent(AssessmentActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questions.length);
        intent.putExtra("userAnswers", userAnswers);
        intent.putExtra("questions", questions);
        startActivity(intent);
        finish();
    }
}
