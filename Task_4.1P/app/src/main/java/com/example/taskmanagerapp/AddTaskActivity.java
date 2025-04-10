package com.example.taskmanagerapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanagerapp.database.TaskDatabase;
import com.example.taskmanagerapp.model.Task;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription, editTextDueDate;
    private Button buttonSave;
    private int taskId = -1; // default: new task

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDueDate = findViewById(R.id.editTextDueDate);
        buttonSave = findViewById(R.id.buttonSave);

        //  Open DatePicker on click
        editTextDueDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                        editTextDueDate.setText(selectedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });

        //  Check if we're editing
        if (getIntent().hasExtra("taskId")) {
            taskId = getIntent().getIntExtra("taskId", -1);
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            String dueDate = getIntent().getStringExtra("dueDate");

            editTextTitle.setText(title);
            editTextDescription.setText(description);
            editTextDueDate.setText(dueDate);
        }

        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();
            String dueDate = editTextDueDate.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(dueDate)) {
                Toast.makeText(this, "Title and due date required", Toast.LENGTH_SHORT).show();
                return;
            }

            TaskDatabase db = TaskDatabase.getInstance(this);

            if (taskId == -1) {
                // Add new task
                db.taskDao().insert(new Task(title, description, dueDate));
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
            } else {
                // Update task
                Task updatedTask = new Task(title, description, dueDate);
                updatedTask.setId(taskId);
                db.taskDao().update(updatedTask);
                Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
