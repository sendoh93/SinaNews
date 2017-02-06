package com.tcs.sinanews;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by pingfan.yang on 2017/1/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Fresco.initialize(this);

    }
}
