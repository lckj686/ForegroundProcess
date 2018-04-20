package com.leon.tool

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import com.blue.frame.utils.log4j.LoggerUtil
import com.leon.tool.utils.TileUtils

/**
 * 通知管理类
 * Created by leon on 2018/4/16.
 */

object NotificationLogic {


    val ID_NORMAL = 1002;

    /**
     * 前台服务的通知
     */
    fun getForegroundNotification(context: Context): Notification {

        //启用前台服务，主要是startForeground()

        val pendingIntent1 = PendingIntent.getActivity(context.applicationContext, 1, // requestCode是0的时候三星手机点击通知栏通知不起作用
                Intent(context.applicationContext, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)


        return Notification.Builder(context.applicationContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("前台进程  : " + BuildConfig.FLAVOR)
                .setContentText("运行中： ")

                .setContentIntent(pendingIntent1)
                //.setFullScreenIntent(pendingIntent1, true)
                //.addAction(action)
                .build()
    }


    /**
     * 普通的通知
     */
    fun popNotification(context: Context) {


        LoggerUtil.i(ConstElement.TAG, "pop 通知" + "运行时间 : " + TileUtils.distanceTime)
        val intent = Intent(context, MainActivity::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED// 关键的一步，设置启动模式

        val pendingIntent = PendingIntent.getActivity(context.applicationContext, 0, intent, 0)
        val builder = Notification.Builder(context.applicationContext)
        builder.setPriority(Notification.PRIORITY_HIGH)


        //通知消息下拉是显示的文本内容
        builder.setContentText("运行时间 : " + BuildConfig.FLAVOR + "  " + TileUtils.distanceTime)
        //通知栏消息下拉时显示的标题
        builder.setContentTitle("title：demo2 " + TileUtils.time)
        //接收到通知时，按手机的默认设置进行处理，声音，震动，灯
        //builder.setDefaults(Notification.DEFAULT_ALL);
        //通知栏显示图标
        builder.setSmallIcon(R.drawable.notification_template_icon_bg)

        //通知栏显示内容
        builder.setTicker("notify_activity")
        builder.setContentIntent(pendingIntent)

        //5.0 + 的悬挂式的通知
        builder.setFullScreenIntent(pendingIntent, true)

        if (Build.VERSION.SDK_INT >= 21) {

            builder.setVisibility(Notification.VISIBILITY_PUBLIC)//设置通知的显示等级
        }
        //builder.setFullScreenIntent(pendingIntent1, false);

        val notification = builder.build()

        //点击跳转后消失
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(ID_NORMAL, notification)
    }
}
