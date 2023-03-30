package com.example.myapplication.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM appentity")
    List<AppEntity> getAllOperations();

    @Query("SELECT * FROM appentity WHERE operationID == :id")
    AppEntity getOperationById(int id);

    @Query("SELECT first_number from appentity WHERE operationID == :id")
    double getFirstOperator(int id);

    @Query("SELECT second_number from appentity WHERE operationID == :id")
    double getSecondOperator(int id);

    @Query("SELECT operator from appentity WHERE operationID == :id")
    String getOperator(int id);

    @Query("SELECT operation from appentity WHERE operationID == :id")
    String getOperation(int id);

    @Update
    void updateOperation(AppEntity entity);

    @Delete
    void clearHistory(AppEntity entity);

    @Query("DELETE FROM appentity WHERE operationID == :id")
    void deleteOperation(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void newOperation(AppEntity entity);
}
