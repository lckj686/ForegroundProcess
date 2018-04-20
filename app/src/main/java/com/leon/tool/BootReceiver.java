package com.leon.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blue.frame.utils.log4j.LoggerUtil;

/**
 * Created by leon on 2018/4/15.
 */

/**
 * 开机广播
 */
public class BootReceiver extends BroadcastReceiver {


    public void onReceive(Context context, Intent mintent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) {
            LoggerUtil.i(ConstElement.TAG, "接收到开机广播，会去执行前台服务");

            Intent intent = new Intent(context, ForegroundService.class);

            intent.setAction(ConstElement.ACTION_BOOT);
            context.startService(intent);

//            LoggerUtil.i(ConstElement.TAG, "自启动广播监听到");
//            // 启动完成
//            Intent intent = new Intent(context, AlarmReceiver.class);
//            intent.setAction("arui.alarm.action");
//            PendingIntent sender = PendingIntent.getBroadcast(context, 0,
//                    intent, 0);
//            long firstime = SystemClock.elapsedRealtime();
//            AlarmManager am = (AlarmManager) context
//                    .getSystemService(Context.ALARM_SERVICE);
//
//            // 10秒一个周期，不停的发送广播
//            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
//                    10 * 1000, sender);
        }
    }


}
