package com.polymerization.core.corebase;

import android.app.Application;
import android.content.Context;

public abstract class CoreBase extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static Context getInstance() {
        return context;
    }
}
