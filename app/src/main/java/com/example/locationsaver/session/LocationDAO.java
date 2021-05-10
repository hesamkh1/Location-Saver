package com.example.locationsaver.session;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.locationsaver.model.Location;

import java.util.List;

@Dao
public interface LocationDAO {

    @Query("SELECT * FROM location_table")
    LiveData<List<Location>> getAll();

    @Query("SELECT * FROM location_table WHERE id IN (:userIds)")
    List<Location> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM location_table WHERE name LIKE :first AND " +
            "number LIKE :last LIMIT 1")
    Location findByName(String first, String last);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);
}
