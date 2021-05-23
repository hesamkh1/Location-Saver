package com.example.locationsaver.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.locationsaver.model.Location;
import com.example.locationsaver.session.AppDatabase;
import com.example.locationsaver.session.LocationDAO;

import java.util.List;

public class LocationRepository {

  //  public static LocationRepository locationRepository;
    private Context context;
    private AppDatabase database;
    private LocationDAO db ;

    public LocationRepository(Context context) {
        this.context=context;
         database = Room.databaseBuilder(context,
                AppDatabase.class, "mi-database.db").allowMainThreadQueries().build();
         db=database.locationDAO();
    }

//    public static LocationRepository getInstance(Context context){
//        if (locationRepository == null){
//            locationRepository = new LocationRepository(context);
//        }
//        return locationRepository;
//    }

    public void InsertToDb(Location location){
        db.insert(location);
    }
    public  void DeleteFromDb(Location location){db.delete(location);}
    public LiveData<List<Location>> GetAllDb()
    {
        return db.getAll();
    }
}
