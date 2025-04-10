package com.example.taskmanagerapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView titleText = findViewById(R.id.textViewDetailTitle);
        TextView descriptionText = findViewById(R.id.textViewDetailDescription);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        titleText.setText(title);
        descriptionText.setText(description);
    }
}
