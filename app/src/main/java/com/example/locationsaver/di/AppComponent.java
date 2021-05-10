package com.example.locationsaver.di;

import com.example.locationsaver.App;
import com.example.locationsaver.remote.ApiModule;
import com.example.locationsaver.viewmodels.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;


@Singleton
@Component(modules = {AppModule.class ,
                        ApiModule.class,})
public interface AppComponent {

    void inject(LoginViewModel loginViewModel);
}
