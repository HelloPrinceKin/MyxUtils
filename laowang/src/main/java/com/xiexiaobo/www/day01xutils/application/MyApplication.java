package com.xiexiaobo.www.day01xutils.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Bob on 2016/10/24.
 */
public class MyApplication extends Application {
    //当程序被创建的时候调用的生命周期的方法
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
    // 程序被销毁的时候调用的生命周期的方法
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
