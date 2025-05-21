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
                "latitude REAL, " +
                "longitude REAL, " +
                "status TEXT, " +
                "contact TEXT, " +
                "date TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ensure new columns exist if upgrading
        if (oldVersion < 2) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN contact TEXT");
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN date TEXT");
            } catch (Exception e) {
                Log.e("DB_UPGRADE", "Upgrade failed: " + e.getMessage());
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

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
                double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));
                String status = getColumnSafe(cursor, "status");
                String contact = getColumnSafe(cursor, "contact");
                String date = getColumnSafe(cursor, "date");

                list.add(new Item(id, title, description, location, status, contact, date, latitude, longitude));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    private String getColumnSafe(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index != -1 && !cursor.isNull(index)) {
            return cursor.getString(index);
        }
        return "";
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }
}
