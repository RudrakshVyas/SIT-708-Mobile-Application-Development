package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;

import java.util.ArrayList;
import java.util.List;

public class InterestsActivity extends AppCompatActivity {

    private List<CheckBox> checkBoxList = new ArrayList<>();
    private Button nextButton;

    private final int MAX_SELECTION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        nextButton = findViewById(R.id.buttonNext);
        LinearLayout topicsLayout = findViewById(R.id.topicsLayout);

        // List of interests
        String[] topics = {
                "Algorithms", "Data Structures", "Web Development", "Testing",
                "AI", "Machine Learning", "Databases", "Networking",
                "Cloud Computing", "Cyber Security"
        };

        for (String topic : topics) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(topic);
            checkBox.setTextSize(18f);
            checkBox.setPadding(8, 16, 8, 16);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int selectedCount = getSelectedCount();
                if (selectedCount > MAX_SELECTION) {
                    checkBox.setChecked(false);
                    Toast.makeText(this, "Max 10 topics allowed", Toast.LENGTH_SHORT).show();
                }
            });
            checkBoxList.add(checkBox);
            topicsLayout.addView(checkBox);
        }

        nextButton.setOnClickListener(view -> {
            List<String> selectedTopics = new ArrayList<>();
            for (CheckBox cb : checkBoxList) {
                if (cb.isChecked()) selectedTopics.add(cb.getText().toString());
            }

            if (selectedTopics.isEmpty()) {
                Toast.makeText(this, "Please select at least one topic", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proceed to dashboard
            Toast.makeText(this, "Selected: " + selectedTopics, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(InterestsActivity.this, LoginActivity.class);

            startActivity(intent);
            finish();
        });
    }

    private int getSelectedCount() {
        int count = 0;
        for (CheckBox cb : checkBoxList) {
            if (cb.isChecked()) count++;
        }
        return count;
    }
}
