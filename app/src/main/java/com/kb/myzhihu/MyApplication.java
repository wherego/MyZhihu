package com.kb.myzhihu;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by hello_kb on 2016/9/4.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
