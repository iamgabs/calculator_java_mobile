package com.example.myapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppEntity {
    @PrimaryKey
    public int operationID;

    @ColumnInfo(name="first_number")
    public double first_number;

    @ColumnInfo(name="second_number")
    public double second_number;

    @ColumnInfo(name="operator")
    public String operator;

    @ColumnInfo(name="operation")
    public String operation;
}
