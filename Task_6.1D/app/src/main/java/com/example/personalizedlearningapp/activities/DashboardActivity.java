package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.personalizedlearningapp.utils.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.adapters.SubjectAdapter;
import com.example.personalizedlearningapp.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSubjects;
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList;
    private Button buttonLogout;
    private TextView textViewGreeting, textViewNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewNotification = findViewById(R.id.textViewNotification);

        recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects);
        buttonLogout = findViewById(R.id.buttonLogout);


        String username = SharedPrefManager.getInstance(this).getUsername();
        textViewGreeting.setText("Hello, " + username);
        textViewNotification.setText("🔔 You have 1 task due");

        // Initialize Subject List
        subjectList = new ArrayList<>();
        subjectList.add(new Subject("Mathematics", R.drawable.maths_icon));
        subjectList.add(new Subject("Science", R.drawable.science_icon));
        subjectList.add(new Subject("History", R.drawable.history_icon));

        // Setup RecyclerView
        recyclerViewSubjects.setLayoutManager(new LinearLayoutManager(this));
        subjectAdapter = new SubjectAdapter(this, subjectList);
        recyclerViewSubjects.setAdapter(subjectAdapter);

        // Logout button click
        buttonLogout.setOnClickListener(v -> {
            SharedPrefManager.getInstance(this).logout();
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
