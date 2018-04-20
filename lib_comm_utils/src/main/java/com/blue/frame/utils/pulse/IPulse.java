package com.blue.frame.utils.pulse;

/**
 * Description:脉冲器 接口
 * Created by liwei on 17/6/12.
 */

public interface IPulse {


    /**
     * 启动脉冲
     */
    void start();

    /**
     * 结束脉冲
     */
    void end();


    int getUnits();

    void setUnits(int units);


    interface OnListener {
        void onPulse(int unit);

    }

    OnListener getListener();

    void setListener(OnListener listener);

    void release();

    boolean isEnd();
}
