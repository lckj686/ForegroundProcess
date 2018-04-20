package com.blue.frame.utils.log4j;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.blue.frame.utils.SDCardUtils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import de.mindpipe.android.logging.log4j.LogConfigurator;


/**
 * 本地记录工具类,log打到本地时使用
 * 在application 里初始化  LoggerUtil.init(application);
 */
public class LoggerUtil {
    static private final String TAG = "LoggerUtil";

    private static String root = null;
    private static String LogPath = null;
    private static String FileName = null;

    private static final LogConfigurator logConfigurator = new LogConfigurator();

    static private LoggerUtil loggerUtil;
    private static Logger log;// = Logger.getLogger(root);

    private LoggerUtil() {

    }

//    /**
//     *
//     * @param lroot tag
//     * @param path 路径
//     * @param name 文件名
//     */
//    static public void init(String lroot, String path, String name) {
//        root = lroot;
//        LogPath = path;
//        FileName = name;
//        setLog();
//    }


    static public void init(Application application) {
        root = application.getPackageName();

        if (SDCardUtils.isSDCardEnable()) {
            //DIRECTORY_DOCUMENTS api =19
            LogPath = application.getExternalFilesDir(Environment.DIRECTORY_ALARMS).getPath() + File.separator;
        } else {
            LogPath = application.getFilesDir().getPath() + File.separator;
        }
        LogDebugUtil.i(TAG, LogPath);

        FileName = "log4j.txt";
        setLog();
    }


    public static void setLog() {
        log = Logger.getLogger(root);
        if (LogPath != null) {
            File dir = new File(LogPath);
            Log.d("dir", "LogPath:" + LogPath + ",dir:" + dir.getName());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f = new File(LogPath + FileName);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            logConfigurator.setFileName(LogPath + FileName);
            logConfigurator.setLevel(root, Level.ALL);
//            if (BuildConfig.DEBUG) {
//                logConfigurator.setLevel(root, Level.ALL);
//            } else {
//                logConfigurator.setLevel(root, Level.INFO);
//            }
            logConfigurator.setFilePattern("%d{yyyy:MM:dd HH:mm:ss} -%p, %m%n");
            logConfigurator.setMaxBackupSize(3);
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setImmediateFlush(true);

            try {
                if (f.canWrite()) {
                    Log.d("test", "configure log");
                    logConfigurator.configure();
                } else {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 输出Level.DEBUG级别日志,一般开发调试信息用
    public static void d(String tag, String message) {
        if (null != log && log.isDebugEnabled()) {
            log.debug(tag + " > " + message);
        }
    }

    // 输出Level.INFO级别日志
    public static void i(String tag, String message) {
        if (null != log && log.isInfoEnabled()) {
            log.info(tag + " > " + message);
        }
    }

    // 输出Level.WARN级别日志
    public static void w(String tag, String message) {
        if (null != log && log.isEnabledFor(Level.WARN)) {
            log.warn(tag + " > " + message);
        }
    }

    // 输出Level.ERROR级别日志,一般catch住异常后使用,使用e.printStackTrace()打印出错误信息;
    public static void e(String tag, String message) {
        if (null != log && log.isEnabledFor(Level.ERROR)) {
            log.error(tag + " > " + message);
        }
    }

    public static void e(String tag, Exception e) {
        if (null != log && log.isEnabledFor(Level.ERROR)) {
            log.error(tag + " > " + getErrStack(e));
        }
    }

    // 输出Level.FATAL级别日志
    public static void f(String tag, Object message) {
        if (null != log && log.isEnabledFor(Level.FATAL)) {
            log.fatal(tag + " > " + message);
        }
    }

    // 输出Level.FATAL级别日志
    public static void f(String tag, Throwable e) {
        if (null != log && log.isEnabledFor(Level.FATAL)) {
            log.fatal(tag + "," + System.currentTimeMillis() + "," + getStackTraceString(e));
        }
    }

    private static String getErrStack(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } catch (Exception e1) {
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen. The
     * error will always be logged at level ASSERT with the call stack.
     * Depending on system configuration, a report may be added to the
     * {@link android.os.DropBoxManager} and/or the process may be terminated
     * immediately with an error dialog.
     *
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     */
    public static int wtf(String tag, String msg) {
        return Log.wtf(tag, msg, null);
    }

    /**
     * What a Terrible Failure: Report an exception that should never happen.
     * Similar to {@link #wtf(String, String)}, with an exception to log.
     *
     * @param tag Used to identify the source of a log message.
     * @param tr  An exception to log.
     */
    public static int wtf(String tag, Throwable tr) {
        return Log.wtf(tag, tr.getMessage(), tr);
    }

    /**
     * What a Terrible Failure: Report an exception that should never happen.
     * Similar to {@link #wtf(String, Throwable)}, with a message as well.
     *
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     * @param tr  An exception to log. May be null.
     */
    public static int wtf(String tag, String msg, Throwable tr) {
        return Log.wtf(tag, msg, tr);
    }

    /**
     * Handy function to get a loggable stack trace from a Throwable
     *
     * @param tr An exception to log
     */
    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    /**
     * Low-level logging call.
     *
     * @param priority The priority/type of this log message
     * @param tag      Used to identify the source of a log message. It usually
     *                 identifies the class or activity where the log call occurs.
     * @param msg      The message you would like logged.
     * @return The number of bytes written.
     */
    public static int println(int priority, String tag, String msg) {
        return Log.println(priority, tag, msg);
    }

    private static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String getErrStack(Exception e) {
        if (e == null) {
            return "";
        }
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } catch (Exception e1) {
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
