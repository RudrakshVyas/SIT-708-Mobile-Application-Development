package com.example.personalizedlearningapp.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizHistoryDao {
    @Insert
    void insert(QuizHistory history);

    @Query("SELECT * FROM QuizHistory ORDER BY id DESC")
    List<QuizHistory> getAllHistories();
}
