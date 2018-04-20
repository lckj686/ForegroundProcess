package com.blue.frame.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.blue.frame.utils.log4j.LogDebugUtil;

/**
 * Description: 进程判断工具类
 * Created by liw on 2016/3/16.
 */
public class ProcessUtil {
    private static String TAG = "ProcessUtil";

   public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                LogDebugUtil.i(TAG, "current process pid = " + pid + "  name = " + appProcess.processName);
                return appProcess.processName;
            }
        }
        return null;
    }
}
