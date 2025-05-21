package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnCreate, btnShowAll, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCreate = findViewById(R.id.btnCreateAdvert);
        btnShowAll = findViewById(R.id.btnShowItems);
        btnMap = findViewById(R.id.btnShowMap);

        btnCreate.setOnClickListener(v -> startActivity(new Intent(this, AddItemActivity.class)));
        btnShowAll.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        btnMap.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class))); // Fixed
    }
}
