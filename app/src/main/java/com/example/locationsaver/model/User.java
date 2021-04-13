package com.example.locationsaver.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public User(String response, String name) {
        Response = response;
        Name = name;
    }

    @SerializedName("response")
    private String Response;
    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
