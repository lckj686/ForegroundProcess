package com.demo.mydemo;

import android.app.Application;

import com.blue.frame.utils.log4j.LoggerUtil;

/**
 * Created by leon on 2018/4/15.
 */

public class CustApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtil.init(this);
    }
}
