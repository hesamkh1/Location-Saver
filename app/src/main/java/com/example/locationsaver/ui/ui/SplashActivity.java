package com.example.locationsaver.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.locationsaver.R;
import com.example.locationsaver.databinding.ActivitySplashBinding;
import com.example.locationsaver.viewmodels.SplashViewModel;

public class SplashActivity extends AppCompatActivity  implements SplashNavigator{

    ImageView splashimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        final SplashViewModel splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        splashViewModel.setViewListener(this);
        binding.setViewModel(splashViewModel);
        binding.setLifecycleOwner(this);

        splashimage=findViewById(R.id.splashimage);
      //  splashtimer();
        int SPASH_TIME_OUT=1000;

        Animation Imganim= AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in);
        Imganim.setDuration(SPASH_TIME_OUT);
        splashimage.startAnimation(Imganim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashViewModel.checkloginfromrespository();
            }
        },SPASH_TIME_OUT);

    }


    public void splashtimer(){
        int SPASH_TIME_OUT=500;

        Animation Imganim= AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in);
        Imganim.setDuration(SPASH_TIME_OUT);
        splashimage.startAnimation(Imganim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },SPASH_TIME_OUT);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onGoLogin() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onGoMain() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

