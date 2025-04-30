package com.example.personalizedlearningapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewScore, textViewResults;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewScore = findViewById(R.id.textViewScore);
        textViewResults = findViewById(R.id.textViewResults);
        buttonContinue = findViewById(R.id.buttonContinue);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String[] questions = getIntent().getStringArrayExtra("questions");
        ArrayList<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");

        textViewScore.setText("You scored " + score + " out of " + total);

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < questions.length; i++) {
            resultBuilder.append((i + 1))
                    .append(". ")
                    .append(questions[i])
                    .append("\nYour Answer: ")
                    .append(userAnswers.get(i))
                    .append("\n\n");
        }

        textViewResults.setText(resultBuilder.toString());

        buttonContinue.setOnClickListener(v -> finish());
    }
}
