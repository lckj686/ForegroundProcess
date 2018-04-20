package com.leon.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.blue.frame.utils.log4j.LoggerUtil;
import com.leon.tool.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by leon on 2018/4/15.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals("arui.alarm.action")) {

            LoggerUtil.i(ConstElement.TAG, " 定时启动广播");


            EventBus.getDefault().post(new MessageEvent());


            if (!BuildConfig.FLAVOR.equals("demo5")) {
                EventBus.getDefault().post(new MessageEvent());

                if (!AppUtils.isServiceRunning(context, ForegroundService.class.getName())) {
                    LoggerUtil.w(ConstElement.TAG, "-------------- ---------------------- -------------");
                    LoggerUtil.w(ConstElement.TAG, "-------------- 前台进程被杀死了，重启唤醒 -------------");
                    LoggerUtil.w(ConstElement.TAG, "-------------- ---------------------- -------------");
                    Intent i = new Intent();
                    i.setClass(context, ForegroundService.class);
                    i.setAction(ConstElement.ACTION_ALARM);
                    // 启动service  正式业务中这里可以做判断是否在运行
                    // 多次调用startService并不会启动多个service 而是会多次调用onStart
                    context.startService(i);
                } else {
                    LoggerUtil.w(ConstElement.TAG, "***** 前台进程被杀死了，重启唤醒 *****");
                }

            } else {
                Intent i = new Intent();
                i.setClass(context, ForegroundService.class);
                i.setAction(ConstElement.ACTION_ALARM);
                // 启动service  正式业务中这里可以做判断是否在运行
                // 多次调用startService并不会启动多个service 而是会多次调用onStart
                context.startService(i);
            }

        }
    }
}
