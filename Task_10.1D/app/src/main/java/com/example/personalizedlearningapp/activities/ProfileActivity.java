package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.utils.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {

    private ImageView backButton;
    private Button historyButton, upgradeButton, shareButton;
    private TextView usernameView, emailView;
    private TextView totalQuestionsView, correctAnswersView, wrongAnswersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backButton = findViewById(R.id.backIcon);
        historyButton = findViewById(R.id.buttonHistory);
        upgradeButton = findViewById(R.id.buttonUpgrade);
        shareButton = findViewById(R.id.buttonShare);
        usernameView = findViewById(R.id.textUsername);
        emailView = findViewById(R.id.textEmail);
        totalQuestionsView = findViewById(R.id.textTotalQuestions);
        correctAnswersView = findViewById(R.id.textCorrectAnswers);
        wrongAnswersView = findViewById(R.id.textWrongAnswers);

        // Load user info
        usernameView.setText(SharedPrefManager.getInstance(this).getUsername());
        emailView.setText(SharedPrefManager.getInstance(this).getEmail());

        // Set dummy stats or fetch from Room in later phase
        totalQuestionsView.setText("Total Questions: 12");
        correctAnswersView.setText("Correct Answers: 9");
        wrongAnswersView.setText("Wrong Answers: 3");

        backButton.setOnClickListener(v -> finish());

        historyButton.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, HistoryActivity.class))
        );

        upgradeButton.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, UpgradeActivity.class))
        );

        shareButton.setOnClickListener(v -> {
            String shareText = "My Profile:\nName: " + usernameView.getText()
                    + "\nEmail: " + emailView.getText()
                    + "\nTotal Questions: 12\nCorrect: 9\nWrong: 3";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
