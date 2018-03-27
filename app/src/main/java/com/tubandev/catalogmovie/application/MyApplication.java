package com.tubandev.catalogmovie.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by sulistiyanto on 06/03/18.
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
