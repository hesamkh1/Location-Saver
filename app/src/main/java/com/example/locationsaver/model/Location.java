package com.example.locationsaver.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Location_table")
public class Location {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "note")
    public String note;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "group")
    public String group;

//
//    @ColumnInfo(picture ="picture")
//    public Bitmap picture;
}
