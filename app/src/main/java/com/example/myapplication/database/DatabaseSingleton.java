package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {
    private static AppDatabase db = null;

    private DatabaseSingleton() {}

    public static AppDatabase getInstance(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "calculator").allowMainThreadQueries().build();
        return db;
    }

}
