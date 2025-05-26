package com.example.personalizedlearningapp.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuizHistory.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract QuizHistoryDao quizHistoryDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "quiz_history_db"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}
