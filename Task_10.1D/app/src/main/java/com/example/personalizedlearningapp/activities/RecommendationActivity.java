package com.example.personalizedlearningapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.adapters.TopicAdapter;
import com.example.personalizedlearningapp.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTopics;
    private TopicAdapter topicAdapter;
    private List<Topic> topicList;
    private TextView textViewSubjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        textViewSubjectName = findViewById(R.id.textViewSubjectName);
        recyclerViewTopics = findViewById(R.id.recyclerViewTopics);

        String subjectName = getIntent().getStringExtra("subject_name");
        textViewSubjectName.setText(subjectName);

        topicList = new ArrayList<>();
        loadTopics(subjectName);

        recyclerViewTopics.setLayoutManager(new LinearLayoutManager(this));
        topicAdapter = new TopicAdapter(this, topicList);
        recyclerViewTopics.setAdapter(topicAdapter);
    }

    private void loadTopics(String subject) {
        if (subject.equalsIgnoreCase("Mathematics")) {
            topicList.add(new Topic("Algebra"));
            topicList.add(new Topic("Calculus"));
            topicList.add(new Topic("Geometry"));
        } else if (subject.equalsIgnoreCase("Science")) {
            topicList.add(new Topic("Physics"));
            topicList.add(new Topic("Chemistry"));
            topicList.add(new Topic("Biology"));
        } else if (subject.equalsIgnoreCase("History")) {
            topicList.add(new Topic("Ancient History"));
            topicList.add(new Topic("Modern History"));
            topicList.add(new Topic("World Wars"));
        }
    }
}
