package com.example.contexttest;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MyApplication() {
        Log.e("TAG","MyApplication MyApplication()");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
