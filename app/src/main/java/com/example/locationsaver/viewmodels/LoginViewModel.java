package com.example.locationsaver.viewmodels;



import android.app.Application;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.App;
import com.example.locationsaver.model.User;
import com.example.locationsaver.remote.ApiInterface;
import com.example.locationsaver.session.PrefConfig;
import com.example.locationsaver.ui.ui.LoginNavigator;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {


    private MutableLiveData<User> mutableLiveData;
  //  private LoginRepository loginRepository;
    private LoginNavigator mListener;

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();

    @Inject
    ApiInterface apiInterface;
    public static PrefConfig prefConfig;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        //loginRepository = LoginRepository.getInstance(application);
        ((App) getApplication()).getAppComponent().inject(this);

        prefConfig=new PrefConfig(application);

    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }


    public void getLoginRepository() {

//        mutableLiveData = loginRepository.performlogin(username.getValue(), password.getValue());

            apiInterface.performUserLogin(username.getValue(), password.getValue()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                    Log.e("Networl" ,"ok");
                    //  Log.e("Networl" ,response.body().getName());
                    prefConfig.writeLoginStatus(true);
                    prefConfig.writeName(response.body().getName());
                    Toast.makeText(getApplication(),"successful login",Toast.LENGTH_SHORT).show();
                    mListener.onLoginSuccess();
                }

                else if(response.body().getResponse().equals("failed"))
                {
                    Log.e("Network" ,"not ok");
                    Toast.makeText(getApplication(),"worng Email or password",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getApplication(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
                Log.e("Error",t.toString());

            }
        });


    }


    public void onSignInClicked() {

        emailError.setValue(null);
        passwordError.setValue(null);


        if (username.getValue() == null || username.getValue().isEmpty()) {
            emailError.setValue("Enter email address.");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.getValue()).matches()) {
            emailError.setValue("Enter a valid email address.");
            return;
        }

        if (password.getValue() == null || password.getValue().isEmpty()) {
            passwordError.setValue("Enter password.");
            return;
        }

         if ((password.getValue().length())<5) {
            passwordError.setValue("Password Length should be greater than 5.");
            return;
        }

           // mutableLiveData = loginRepository.performlogin(username.getValue(), password.getValue());
            getLoginRepository();





    }
    public void setViewListener(LoginNavigator listener) {
        this.mListener = listener;
    }


}