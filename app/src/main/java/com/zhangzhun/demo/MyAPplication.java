package com.zhangzhun.demo;

import android.app.Application;

/**
 * @author zhangzhun
 * @date 2018/5/6
 */

public class MyAPplication extends Application{

    public static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

}
