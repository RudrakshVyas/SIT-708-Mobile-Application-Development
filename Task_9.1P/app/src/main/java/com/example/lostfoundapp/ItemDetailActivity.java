package com.example.lostfoundapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    TextView txtTitle, txtDate, txtLocation;
    Button btnRemove;
    int itemId;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtLocation = findViewById(R.id.txtLocation);
        btnRemove = findViewById(R.id.btnRemove);
        db = new DatabaseHelper(this);

        itemId = getIntent().getIntExtra("itemId", -1);
        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String location = getIntent().getStringExtra("location");

        txtTitle.setText(title);
        txtDate.setText(date);
        txtLocation.setText(location);

        btnRemove.setOnClickListener(v -> {
            db.deleteItem(itemId);
            Toast.makeText(this, "Item removed!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
