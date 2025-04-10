package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonStartQuiz, buttonCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonStartQuiz = findViewById(R.id.buttonStart);
        buttonCalculator = findViewById(R.id.buttonCalculator);

        String name = getIntent().getStringExtra("userName");
        if (name != null) {
            editTextName.setText(name);
        }

        buttonStartQuiz.setOnClickListener(v -> {
            String userName = editTextName.getText().toString();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        });

        buttonCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
