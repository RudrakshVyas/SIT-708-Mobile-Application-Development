package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.models.AppDatabase;
import com.example.personalizedlearningapp.models.QuizHistory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewHistory = findViewById(R.id.listViewHistory);

        List<QuizHistory> historyList = AppDatabase.getInstance(this)
                .quizHistoryDao().getAllHistories();

        List<String> displayList = new ArrayList<>();
        for (QuizHistory q : historyList) {
            displayList.add("📅 " + q.date + "\nScore: " + q.score + "/" + q.total);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        listViewHistory.setAdapter(adapter);

        listViewHistory.setOnItemClickListener((parent, view, position, id) -> {
            QuizHistory selected = historyList.get(position);
            Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
            intent.putExtra("quiz_history", new Gson().toJson(selected));
            startActivity(intent);
        });
    }
}
