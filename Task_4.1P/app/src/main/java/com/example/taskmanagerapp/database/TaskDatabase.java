package com.example.taskmanagerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskmanagerapp.dao.TaskDao;
import com.example.taskmanagerapp.model.Task;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                    .allowMainThreadQueries() // for simplicity in small apps
                    .fallbackToDestructiveMigration() // reset DB on schema change
                    .build();
        }
        return instance;
    }
}
