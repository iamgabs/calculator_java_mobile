package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {

    static DatabaseSingleton object;

    public AppDatabase db;

    private DatabaseSingleton() {}

    public static DatabaseSingleton getInstance(Context context) {
        if (object == null) {
            object = new DatabaseSingleton();
            object.db = Room.databaseBuilder(context, AppDatabase.class, "calculator").allowMainThreadQueries().build();
        }
        return object;
    }

}
