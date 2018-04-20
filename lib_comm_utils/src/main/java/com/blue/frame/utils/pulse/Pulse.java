package com.blue.frame.utils.pulse;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Description:脉冲器
 * 用handler 实现， 实时性不强，对实时性要求强的  用PulseEx
 *
 * 只提供 开始脉冲  结束脉冲  具体脉冲个数 上层业务自统计
 * Created by liwei on 16/7/27.
 */
public class Pulse implements IPulse {

    private Runnable runnable;
    private int units = 1000;
    private OnListener listener;
    private volatile boolean isEnd = true;

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (listener != null && !isEnd) {
                listener.onPulse(units);
            }

        }
    };


    public Pulse() {

        runnable = new Runnable() {
            @Override
            public void run() {

                if (handler == null) {

                    return;
                }

//                handler.sendEmptyMessage(1);

                if (listener != null) {
                    listener.onPulse(units);
                }

                if (!isEnd) {

                    if (handler != null) {
                        handler.postDelayed(this, units);
                    }
                } else {
                    if (handler != null) {
                        handler.removeCallbacks(this);
                    }
                }
            }
        };


    }

    /**
     * 启动脉冲
     */
    public void start() {
        isEnd = false;
        if (handler != null) {
            handler.postDelayed(runnable, units);
        }

    }


    /**
     * 结束脉冲
     */
    public void end() {
        isEnd = true;
        if (handler != null) {
            handler.removeCallbacks(runnable);
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

        if (handler != null) {
            handler.removeCallbacks(runnable);
            listener = null;
            runnable = null;
            //handler = null;
        }
    }

    public boolean isEnd() {
        return isEnd;
    }
}
