package com.example.locationsaver.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.locationsaver.model.Location;
import com.example.locationsaver.session.AppDatabase;
import com.example.locationsaver.session.LocationDAO;

import java.util.List;

public class LocationRepository {

    public static LocationRepository locationRepository;
    private Context context;
    private AppDatabase db;
    private LocationDAO locationDAO ;

    public LocationRepository(Context context) {
        this.context=context;
         db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();
         locationDAO=db.locationDAO();
    }

    public static LocationRepository getInstance(Context context){
        if (locationRepository == null){
            locationRepository = new LocationRepository(context);
        }
        return locationRepository;
    }

    public List<Location>getall(){
        List<Location> locations=locationDAO.getAll();
        return locations;
    }
}
