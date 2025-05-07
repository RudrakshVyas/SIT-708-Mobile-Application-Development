package com.example.lostfoundapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText editTitle, editDescription, editLocation, editContact, editDate;
    RadioGroup radioGroupStatus;
    RadioButton radioLost, radioFound;
    Button btnSave;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dbHelper = new DatabaseHelper(this);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        editLocation = findViewById(R.id.editLocation);
        editContact = findViewById(R.id.editContact);
        editDate = findViewById(R.id.editDate);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        radioLost = findViewById(R.id.radioLost);
        radioFound = findViewById(R.id.radioFound);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String status = "";
            int selectedId = radioGroupStatus.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select Lost or Found", Toast.LENGTH_SHORT).show();
                return;
            } else {
                RadioButton selectedRadio = findViewById(selectedId);
                status = selectedRadio.getText().toString();
            }

            ContentValues values = new ContentValues();
            values.put("title", editTitle.getText().toString());
            values.put("description", editDescription.getText().toString());
            values.put("location", editLocation.getText().toString());
            values.put("status", status);
            values.put("contact", editContact.getText().toString());
            values.put("date", editDate.getText().toString());

            long result = dbHelper.insertItem(values);
            if (result != -1) {
                Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
