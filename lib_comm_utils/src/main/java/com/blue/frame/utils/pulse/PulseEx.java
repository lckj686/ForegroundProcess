package com.blue.frame.utils.pulse;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:脉冲器
 * 定时器实现，实时性强
 * 只提供 开始脉冲  结束脉冲  具体脉冲个数 上层业务自统计
 * Created by liwei on 16/7/27.
 */
public class PulseEx implements IPulse {

    //  private Runnable runnable;
    private int units = 1000;
    private OnListener listener;
    private volatile boolean isEnd = true;

    private Timer mTimer;
    private TimerTask mTimerTask;


    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (listener != null && !isEnd) {
                listener.onPulse(units);
            }

        }
    };


    public PulseEx() {


    }

    /**
     * 启动脉冲
     */
    public void start() {
        end();
        isEnd = false;


        mTimer = new Timer();

        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                //  Auto-generated method stub
                if (handler != null) {
                    handler.sendEmptyMessage(units);
                }
            }
        };

        mTimer.schedule(mTimerTask, 0, units);


    }


    /**
     * 结束脉冲
     */
    public void end() {
        isEnd = true;


        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }


    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }


    public OnListener getListener() {
        return listener;
    }

    public void setListener(OnListener listener) {
        this.listener = listener;
    }

    public void release() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimerTask = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    public boolean isEnd() {
        return isEnd;
    }
}
