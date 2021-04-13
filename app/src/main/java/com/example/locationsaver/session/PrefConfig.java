package com.example.locationsaver.session;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.locationsaver.R;


public class PrefConfig implements prefNavigatior{

    public static SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }


    public  boolean readLoginStatus()
    {
        return  sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }


    public  void writeName(String  name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.commit();

    }


    public String readName()
    {
        return  sharedPreferences.getString(context.getString(R.string.pref_user_name),"User");
    }


}
