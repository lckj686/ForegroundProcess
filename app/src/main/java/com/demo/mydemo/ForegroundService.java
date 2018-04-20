package com.demo.mydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.blue.frame.utils.log4j.LoggerUtil;
import com.demo.mydemo.utils.TileUtils;

/**
 * Created by leon on 2018/4/15.
 */

public class ForegroundService extends Service {
    private String TAG = ConstElement.TAG;
    int i = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtil.i(TAG, "\n\n\n---------------------  记录运行栈 开始 ------------------------------");
        LoggerUtil.i(TAG, "-->>onCreate");

        if (BuildConfig.FLAVOR.equals("demo4")) {
            KeepLiveManager.getInstance().registerReceiver(this);
        }


        startForeground(1, NotificationLogic.INSTANCE.getForegroundNotification(this));


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LoggerUtil.i(TAG, "-->>onStartCommand-->>" + startId + " 时间= " + TileUtils.INSTANCE.getDistanceTime());
        //flags = START_STICKY;

        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case ConstElement.ACTION_ACTIVITY:
                    LoggerUtil.i(TAG, "++++++++++++++ 来自 activity的启动 ++++++++++++++");
                    break;

                case ConstElement.ACTION_ALARM:
                    LoggerUtil.i(TAG, "++++++++++++++ 来自 alarm的启动 ++++++++++++++");
                    break;

                case ConstElement.ACTION_BOOT:
                    LoggerUtil.i(TAG, "++++++++++++++ 来自 boot的启动 ++++++++++++++");
                    break;

            }
        }else{
            //把 live 手动
            LoggerUtil.i(TAG,"intent = null");
        }


        NotificationLogic.INSTANCE.popNotification(this);

        //if (!BuildConfig.FLAVOR.equals("demo5")) {
        AlarmUtilManager.INSTANCE.initAlarm(this);
        //}


        return START_STICKY;//被销毁了自启动, 可以用logcat 这边的工具杀进程看
    }
    //监听服务2实现函数


    @Override
    public IBinder onBind(Intent arg0) {
        LoggerUtil.i(TAG, "-->>onBind");
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        LoggerUtil.i(TAG, "-->>onDestroy");
        super.onDestroy();


        AlarmUtilManager.INSTANCE.cancel();

        if (BuildConfig.FLAVOR.equals("demo4")) {
            KeepLiveManager.getInstance().unRegisterReceiver(this);
        }
    }


}