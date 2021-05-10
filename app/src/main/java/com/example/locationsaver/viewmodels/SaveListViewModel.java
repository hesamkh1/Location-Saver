package com.example.locationsaver.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.model.Location;
import com.example.locationsaver.repository.LocationRepository;

import java.util.List;


public class SaveListViewModel extends AndroidViewModel {

    MutableLiveData<List<Location>> userLiveData;
    private LocationRepository locationRepository;

    public SaveListViewModel(@NonNull Application application) {
        super(application);
        locationRepository=new LocationRepository(application);
        userLiveData = new MutableLiveData<>();



    }
    public MutableLiveData<List<Location>> getUserMutableLiveData() {
        return userLiveData;
    }

    public LiveData<List<Location>> GetLocations(){
        return locationRepository.GetAllDb();
    }


    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }
}
