package com.example.locationsaver;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.example.locationsaver.di.AppComponent;
import com.example.locationsaver.di.AppModule;
import com.example.locationsaver.di.DaggerAppComponent;




public class App extends MultiDexApplication {

    private final AppComponent mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}






