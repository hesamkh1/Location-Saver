package com.example.locationsaver.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.ui.ui.MainNavigator;
import com.example.locationsaver.ui.ui.MapNavigator;

public class MapViewModel extends AndroidViewModel {

    private MutableLiveData<String> mlatitude;
    private MutableLiveData<String> mlongitude;
    public MapNavigator mListener;

    public MapViewModel(@NonNull Application application) {
        super(application);
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public void setViewListener(MapNavigator listener) {
        this.mListener = listener;
    }

    public void onSaveclick(){
        if (getLatitude().getValue() != null && getLongitude().getValue() != null){
            mListener.onPerformSave();
        }
    }

    public MutableLiveData<String> getLatitude(){
        if(mlatitude==null){
            mlatitude=new MutableLiveData<>();
        }
        return mlatitude;
    }

    public void setLatitude(String latitude){
        mlatitude.setValue(latitude);
    }

    public MutableLiveData<String> getLongitude(){
        if(mlongitude==null){
            mlongitude=new MutableLiveData<>();
        }
        return mlongitude;
    }

    public void setLongitude(String longitude){
        mlongitude.setValue(longitude);
    }
}
