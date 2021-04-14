package com.example.locationsaver.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.repository.LoginRepository;
import com.example.locationsaver.ui.ui.LoginNavigator;
import com.example.locationsaver.ui.ui.MainNavigator;

public class MainViewModel extends AndroidViewModel {


    private LoginRepository loginRepository;
    public MainNavigator mListener;

    public MainViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance(application);
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public void setViewListener(MainNavigator listener) {
        this.mListener = listener;
    }

    public void onlogoutclick(){
        loginRepository.performlogout();
        mListener.onlogout();
    }




}


