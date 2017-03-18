package com.example.lwl.retrofit2;

import android.app.Application;
import android.content.Context;

/**
 * Created by LWL on 2017/3/6.
 */

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
