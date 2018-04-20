package com.blue.frame.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;

import com.blue.frame.utils.log4j.LogDebugUtil;

/**
 * Description: 运行时权限
 * Created by crx on 2017/1/19.
 */

public class CheckRuntimePermission {

    private static final String TAG = "CheckRuntimePermission";

    public final static int CODE_ACCESS_COARSE_LOCATION = 11;
    public final static int CODE_READ_EXTERNAL_STORAGE = 12;


    /**
     * 检查地理位置权限
     *
     * @param activity
     * @param description
     * @return
     */
    public static boolean checkPositionPermission(Activity activity, String description, OnPermissionLastTime listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) || !hasSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (listener != null) {
                    //上一次的权限 走这个。普通拒绝返回true，不在询问的拒绝是走的false
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                        LogDebugUtil.i(TAG, "当次 -- 拒绝");
                        listener.onLastAsk();

                    } else {
                        listener.onLastOperationAlwaysRefuse();
                        LogDebugUtil.i(TAG, "当次 -- 永久拒绝");
                    }

                }

//                showRequestPermissionDescription(description);
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, CODE_ACCESS_COARSE_LOCATION);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }

    }

    /**
     * 检查sd卡权限
     *
     * @param activity
     * @param description
     * @return
     */
    public static boolean checkReadExternalStoragePermission(Activity activity, String description) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)){
//
//                }
//                showRequestPermissionDescription(description);
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_READ_EXTERNAL_STORAGE);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    private static boolean hasSelfPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(context, permission);
        }

        boolean reval = false;
        try {
            reval = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            LogDebugUtil.d(TAG, "reval = " + reval);
        } catch (RuntimeException t) {
            LogDebugUtil.w(TAG, t);
            reval = false;
        } finally {
            return reval;
        }
    }

    private static boolean hasSelfPermissionForXiaomi(Context context, String permission) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            // in case of normal permissions(e.g. INTERNET)
            return true;
        }
        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, Process.myUid(), context.getPackageName());
        return noteOp == AppOpsManagerCompat.MODE_ALLOWED && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    //提示用户，为什么需要此项权限
//    private static void showRequestPermissionDescription(String description) {
//        if (!TextUtils.isEmpty(description)) {
//            CustToastUtil.showPermissionRequestReason(description);
//        }
//    }


    public interface OnPermissionLastTime {
        void onLastOperationAlwaysRefuse();

        void onLastAsk();
    }

    /**
     * 跳到应用详情页面
     *
     * @param context
     */
    public static void j2AppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }
}
