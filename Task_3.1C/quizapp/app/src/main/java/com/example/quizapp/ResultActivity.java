package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView textResult;
    Button buttonNewQuiz, buttonFinish;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textResult = findViewById(R.id.textResult);
        buttonNewQuiz = findViewById(R.id.buttonNewQuiz);
        buttonFinish = findViewById(R.id.buttonFinish);

        int score = getIntent().getIntExtra("score", 0);
        name = getIntent().getStringExtra("userName");

        textResult.setText("Congratulations " + name + "\nYour score: " + score + "/5");

        buttonNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.putExtra("userName", name);
            startActivity(intent);
            finish();
        });

        buttonFinish.setOnClickListener(v -> finishAffinity());
    }
}
