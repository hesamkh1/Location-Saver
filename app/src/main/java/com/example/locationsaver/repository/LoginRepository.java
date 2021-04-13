package com.example.locationsaver.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.model.User;
import com.example.locationsaver.remote.ApiClient;
import com.example.locationsaver.remote.ApiInterface;
import com.example.locationsaver.session.PrefConfig;
import com.example.locationsaver.ui.ui.LoginNavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository loginRepository;
    private ApiInterface apiInterface;
    public static PrefConfig prefConfig;

    private Context context;







    public static LoginRepository getInstance(Context context){
        if (loginRepository == null){
            loginRepository = new LoginRepository(context);
        }
        return loginRepository;

//        loginRepository = new LoginRepository(context);
//        return loginRepository;

    }

    public LoginRepository(Context context){
        this.context=context;
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig=new PrefConfig(context);
        Log.e("Loginrepository",prefConfig.readName() + "  " + prefConfig.readLoginStatus());

    }

    public MutableLiveData<User> performlogin(String username, String userpassword){

        final MutableLiveData<User> mUser = new MutableLiveData<>();


        apiInterface.performUserLogin(username, userpassword).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                      Log.e("Networl" ,"ok");
                  //  Log.e("Networl" ,response.body().getName());
                    prefConfig.writeLoginStatus(true);
                    prefConfig.writeName(response.body().getName());
                    mUser.setValue(response.body());

                    Toast.makeText(context,"successful login",Toast.LENGTH_SHORT).show();

                }

                else if(response.body().getResponse().equals("failed"))
                {

                    Log.e("Networl" ,"not ok");
                    Toast.makeText(context,"worng pass",Toast.LENGTH_SHORT).show();
                    mUser.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                //Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
                Log.e("Error",t.toString());
                mUser.setValue(null);
            }
        });
        return mUser;
    }

    public void performlogout(){
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
    }

    public Boolean LoginCheker() {
        if(prefConfig.readLoginStatus()){
            return true;
        }
        else{ return false;}
    }

    public String getName(){
        return prefConfig.readName();
    }

}
