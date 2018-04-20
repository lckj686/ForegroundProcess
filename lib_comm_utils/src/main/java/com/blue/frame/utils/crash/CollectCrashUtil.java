package com.blue.frame.utils.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:收集错误异常
 * Created by liw on 2016/3/24.
 */
public class CollectCrashUtil {
    public static final String TAG = "CollectCrashUtil";

    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    private static CollectCrashUtil collectCrashUtil;

    synchronized public static CollectCrashUtil getInstance() {
        if (collectCrashUtil == null) {
            collectCrashUtil = new CollectCrashUtil();
        }

        return collectCrashUtil;

    }

    private String getCurrentTime() {
        //HH 24小时  hh 12小时
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    private boolean isSdcardExist() {
        boolean reval = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

        if (!reval) {
            Log.e(TAG, "Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) = false");
        }

        return reval;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public Map<String, String> collectDeviceInfo(Context ctx) {
        infos.clear();
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
                infos.put("error_time", getCurrentTime());
            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }

        return infos;
    }

    private String getCrashDirPath(Context context) {

        if (isSdcardExist()) {
            //DIRECTORY_DOCUMENTS api =19
           // if (BuildConfig.DEBUG) {

                return Environment.getExternalStorageDirectory() + File.separator + "loop" + File.separator;
            //}
           // return context.getExternalFilesDir(Environment.DIRECTORY_ALARMS) + File.separator;
        } else {
            File file = context.getDir("crash", Context.MODE_PRIVATE);
            return file.getPath() + File.separator + "crash" + File.separator;
        }

    }

    /**
     * 保存错误信息到文件中 全部存在一个文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    public String saveCrashInfoTo1File(Context context, Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
//        Throwable cause = ex.getCause();
//        while (cause != null) {
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//        printWriter.close();
//        String result = writer.toString();

        String logE = Log.getStackTraceString(ex);
        sb.append(logE);
        try {

            String fileName = "crash.txt";
            String logPathDir = getCrashDirPath(context);

            File dir = new File(logPathDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dstFile = new File(logPathDir + fileName);
            if (dstFile.isFile()) {
                long count = dstFile.length();
                //大于1M 删除了
                if (count > 10240) {
                    dstFile.delete();

                }
            }
            FileOutputStream fos = new FileOutputStream(logPathDir + fileName, true);
            fos.write(sb.toString().getBytes());
            fos.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
}
