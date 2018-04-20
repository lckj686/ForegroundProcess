package com.leon.tool

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE

import android.content.Intent
import android.os.SystemClock

/**
 * Created by leon on 2018/4/15.
 */

object AlarmUtilManager {
    private var manager: AlarmManager? = null
    private var pi: PendingIntent? = null

    fun initAlarm(context: Context) {


        manager?.cancel(pi)
        manager = null
        pi = null



        manager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        //读者可以修改此处的Minutes从而改变提醒间隔时间
        //此处是设置每隔55分钟启动一次
        //这是55分钟的毫秒数
        val Minutes = 1 * 60 * 1000
        //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
        val triggerAtTime = SystemClock.elapsedRealtime() + Minutes
        //此处设置开启AlarmReceiver这个Service
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        alarmIntent.action = "arui.alarm.action"
        pi = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)
        //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。
        manager!!.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi)

    }

    fun cancel() {
        manager?.cancel(pi)
        manager = null;
        pi = null
    }
}
