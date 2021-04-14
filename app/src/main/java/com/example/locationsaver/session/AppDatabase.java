package com.example.locationsaver.session;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.locationsaver.model.Location;

@Database(entities = {Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDAO locationDAO();
}
