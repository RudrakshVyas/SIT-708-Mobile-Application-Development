package com.example.quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText input1, input2;
    Button buttonAdd, buttonSubtract;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        input1 = findViewById(R.id.editTextNumber1);
        input2 = findViewById(R.id.editTextNumber2);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        resultView = findViewById(R.id.textResult);

        buttonAdd.setOnClickListener(v -> calculate(true));
        buttonSubtract.setOnClickListener(v -> calculate(false));
    }

    private void calculate(boolean isAdd) {
        try {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result = isAdd ? num1 + num2 : num1 - num2;
            resultView.setText("Result: " + result);
        } catch (NumberFormatException e) {
            resultView.setText("Please enter valid numbers.");
        }
    }
}
