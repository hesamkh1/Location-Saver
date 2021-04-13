package com.example.locationsaver.viewmodels;

import android.app.Application;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.R;
import com.example.locationsaver.model.User;
import com.example.locationsaver.repository.LoginRepository;
import com.example.locationsaver.ui.ui.LoginNavigator;
import com.example.locationsaver.ui.ui.SplashNavigator;


public class SplashViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private SplashNavigator mListener;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance(application);

    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public void setViewListener(SplashNavigator listener) {
        this.mListener = listener;
    }

    public String  checkloginfromrespository() {

        if (loginRepository.LoginCheker()) {
            mListener.onGoMain();
            return loginRepository.getName();
        } else {
            mListener.onGoLogin();
            return null;
        }



    }





}
