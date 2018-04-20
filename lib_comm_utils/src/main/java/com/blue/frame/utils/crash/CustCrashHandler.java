package com.blue.frame.utils.crash;

import android.app.Application;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 */
public class CustCrashHandler implements UncaughtExceptionHandler {

    private  UncaughtExceptionHandler mDefaultHandler;

    private Application application;

    public CustCrashHandler(Application application) {
        this.application = application;
    }

    public void init() {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    // 发生异常时，需要把activity栈清空。
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//      LoggerUtil.f("error_exit", ex);

        handleException(ex);
//        if ( mDefaultHandler != null) {
//            //如果用户没有处理则让系统默认的异常处理器来处理
//            mDefaultHandler.uncaughtException(thread, ex);
//        }

        //不需要处理 上一步交给默认的处理就可以了
//        ActivitiesManager.getActivitiesManager().Exit(application, false);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
}

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        //收集设备参数信息
        CollectCrashUtil.getInstance().collectDeviceInfo(application);


        //保存日志文件
        CollectCrashUtil.getInstance().saveCrashInfoTo1File(application, ex);
        return true;
    }
}
