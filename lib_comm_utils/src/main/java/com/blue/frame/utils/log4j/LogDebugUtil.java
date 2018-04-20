package com.blue.frame.utils.log4j;

import static com.blue.frame.utils.crash.CrashHandler.TAG;

/**
 * log调试用
 *
 * @author liw
 */
public class LogDebugUtil {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    public static boolean isAppDebug = true;
    public static int LEVEL = DEBUG;
    public static boolean isJunit = false;

    public static void setDebugLogNoShow() {
        LEVEL = INFO;
    }

    public static void v(String tag, String msg) {
        if (isAppDebug && VERBOSE >= LEVEL)
            android.util.Log.v(tag, "" + msg);
    }

    public static void junitPrint(String mark, Object value) {
        if (isJunit) {
            LogDebugUtil.d(TAG, "mark= " + mark + "  value= " + value);
        }
    }

    public static void d(String tag, String msg) {
        if (isJunit) {
            System.out.println("tag:" + tag + " " + msg);
        } else if (isAppDebug && DEBUG >= LEVEL)
            android.util.Log.d(tag, "" + msg);
    }

    public static void i(String tag, String msg) {
        if (isAppDebug && INFO >= LEVEL)
            android.util.Log.i(tag, "" + msg);
    }

    public static void w(String tag, String msg) {
//		if (isAppDebug && WARN >= LEVEL)
        android.util.Log.w(tag, "" + msg);
    }

    public static void e(String tag, String msg) {
//		if (isAppDebug)
        android.util.Log.e(tag, "" + msg);
    }

    public static void e(String tag, String msg, Throwable e) {
//		if (isAppDebug)
        android.util.Log.e(tag, msg, e);
    }

    //
    public static void v(boolean isFileDebug, String tag, String msg) {
        if (isAppDebug && isFileDebug)
            android.util.Log.v(tag, "" + msg);
    }

    public static void d(boolean isFileDebug, String tag, String msg) {
        if (isAppDebug && isFileDebug)
            android.util.Log.d(tag, "" + msg);
    }

    public static void d(boolean isFileDebug, String tag, Throwable e) {
        if (isAppDebug && isFileDebug)
            android.util.Log.d(tag, "" + android.util.Log.getStackTraceString(e));
    }


    public static void i(boolean isFileDebug, String tag, String msg) {
        if (isAppDebug && isFileDebug)
            android.util.Log.i(tag, "" + msg);
    }

    public static void w(boolean isFileDebug, String tag, String msg) {
        if (isAppDebug && isFileDebug)
            android.util.Log.w(tag, "" + msg);
    }

    public static void e(boolean isFileDebug, String tag, String msg) {
//		if (isAppDebug && isFileDebug)
        android.util.Log.e(tag, "" + msg);
    }

    public static void e(String tag, Throwable e) {
        android.util.Log.e(tag, "", e);
    }

    public static void w(String tag, Throwable e) {
        android.util.Log.w(tag, "", e);
    }

    public static String printf(Object o) {
        if (o == null) {
            return "null";
        } else {
            return o.toString();
        }
    }
}