package com.example.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.models.AppDao;
import com.example.myapplication.models.AppEntity;

@Database(entities = {AppEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
