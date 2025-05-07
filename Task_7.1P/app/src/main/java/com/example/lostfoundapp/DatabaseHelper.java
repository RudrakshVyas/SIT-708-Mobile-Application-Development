package com.example.lostfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LostFound.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "items";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "description TEXT, " +
                "location TEXT, " +
                "status TEXT, " +
                "contact TEXT, " +
                "date TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN date TEXT");
            } catch (Exception e) {
                Log.e("DB_UPGRADE", "Failed to alter table: " + e.getMessage());
            }
        }
    }

    public long insertItem(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            return db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("DB_INSERT_ERROR", "Insert failed: " + e.getMessage());
            return -1;
        }
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "id DESC");

        while (cursor.moveToNext()) {
            Item item = new Item(
                    cursor.getInt(0),    // id
                    cursor.getString(1), // title
                    cursor.getString(2), // description
                    cursor.getString(3), // location
                    cursor.getString(4), // status
                    cursor.getString(5), // contact
                    cursor.getString(6)  // date
            );
            list.add(item);
        }

        cursor.close();
        return list;
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }
}
