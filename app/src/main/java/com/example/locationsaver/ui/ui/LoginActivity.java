package com.example.locationsaver.ui.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.locationsaver.App;
import com.example.locationsaver.R;
import com.example.locationsaver.databinding.ActivityLoginBinding;

import com.example.locationsaver.model.User;
import com.example.locationsaver.remote.ApiInterface;
import com.example.locationsaver.viewmodels.LoginViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends AppCompatActivity implements LoginNavigator {


    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        loginViewModel.setViewListener(this);


//        loginViewModel.getLoginRepository().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(@Nullable User user) {
//                onloginchecker();
//            }
//        });



    }



    @Override
    public void onLoginSuccess() {

        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }








}