package com.example.locationsaver.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.locationsaver.model.User;
import com.example.locationsaver.remote.ApiInterface;
import com.example.locationsaver.session.PrefConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository loginRepository;
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

        prefConfig=new PrefConfig(context);
        Log.e("Loginrepository",prefConfig.readName() + "  " + prefConfig.readLoginStatus());

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
