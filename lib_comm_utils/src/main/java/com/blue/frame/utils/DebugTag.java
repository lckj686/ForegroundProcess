package com.blue.frame.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.blue.frame.utils.log4j.LogDebugUtil;

/**
 * Description:
 * Created by liwei on 16/8/17.
 */
public class DebugTag {
    private final static String TAG = "DebugTag";

    static public boolean isDebugText(Application application) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = application.getPackageManager()
                    .getApplicationInfo(application.getPackageName(),
                            PackageManager.GET_META_DATA);


            String msg = appInfo.metaData.getString("onlytest");
            LogDebugUtil.d(TAG, "UMENG_CHANNEL = " + msg);
            if ("test".equals(msg)) {
                return true;
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }


}
