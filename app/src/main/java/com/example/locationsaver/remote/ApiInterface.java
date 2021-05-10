package com.example.locationsaver.remote;



import com.example.locationsaver.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "http://192.168.100.115/Cafe/";

   @GET("register.php")
    Call<User> performRegistration(@Query("name") String Name,
                                   @Query("user_name") String UserName,
                                   @Query("user_password") String UserPassword);
    @GET("login.php")
    Call<User> performUserLogin(@Query("user_name") String UserName,
                                @Query("user_password") String UserPassword);


}
