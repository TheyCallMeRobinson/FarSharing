package ru.vsu.cs.farsharing.activity;

import android.app.Application;
import android.content.Context;

public class FarSharingApp extends Application {
    private static FarSharingApp instance;
    private static Context mContext;

    //private FarSharingApp() {}

    public static FarSharingApp getInstance() {
        if(null == instance) {
            instance = new FarSharingApp();
        }
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }
}
