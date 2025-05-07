package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewItems;
    FloatingActionButton fabAddItem;
    DatabaseHelper dbHelper;
    ItemAdapter adapter;
    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerViewItems = findViewById(R.id.recyclerViewItems);
        fabAddItem = findViewById(R.id.fabAddItem);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

        // Appling  layout animation to RecyclerView
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fade_slide);
        recyclerViewItems.setLayoutAnimation(animation);

        // DB setup
        dbHelper = new DatabaseHelper(this);
        loadItems();


        fabAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivity(intent);
        });
    }

    // Refresh list when coming back from AddItem screen
    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    // Fetch items from DataBase and bind it to RecyclerView
    private void loadItems() {
        itemList = dbHelper.getAllItems();
        adapter = new ItemAdapter(this, itemList, dbHelper);
        recyclerViewItems.setAdapter(adapter);
        recyclerViewItems.scheduleLayoutAnimation();
    }
}
