package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.models.QuizHistory;
import com.google.gson.Gson;

public class HistoryDetailActivity extends AppCompatActivity {

    private TextView textViewDetail;
    private Button buttonShare;
    private QuizHistory selectedHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        textViewDetail = findViewById(R.id.textViewDetail);
        buttonShare = findViewById(R.id.buttonShareResult);

        // Get passed history JSON
        String historyJson = getIntent().getStringExtra("quiz_history");
        selectedHistory = new Gson().fromJson(historyJson, QuizHistory.class);

        // Decode arrays
        String[] questions = new Gson().fromJson(selectedHistory.questionsJson, String[].class);
        String[] userAnswers = new Gson().fromJson(selectedHistory.userAnswersJson, String[].class);

        // Build result detail
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("📅 ").append(selectedHistory.date).append("\n")
                .append("Score: ").append(selectedHistory.score).append("/")
                .append(selectedHistory.total).append("\n\n");

        for (int i = 0; i < questions.length; i++) {
            resultBuilder.append((i + 1)).append(". ").append(questions[i])
                    .append("\nYour Answer: ").append(userAnswers[i])
                    .append("\n\n");
        }

        textViewDetail.setText(resultBuilder.toString());

        // Share result
        buttonShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, resultBuilder.toString());
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
