package com.example.locationsaver.session;

public interface prefNavigatior {

    public void writeLoginStatus(boolean status);
    public  boolean readLoginStatus();
    public  void writeName(String  name);
    public String readName();
}
