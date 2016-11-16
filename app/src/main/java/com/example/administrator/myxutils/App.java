package com.example.administrator.myxutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/15/015.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
