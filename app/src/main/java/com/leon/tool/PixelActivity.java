package com.leon.tool;

/**
 * Created by leon on 2018/4/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.blue.frame.utils.log4j.LoggerUtil;
import com.leon.tool.utils.TileUtils;

/**
 * Created by dml on 2018/3/3.
 * 一像素的Activity
 */
public class PixelActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggerUtil.i(ConstElement.TAG, "PixelActivity 启动 " + " 时间= " + TileUtils.INSTANCE.getDistanceTime());
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
        KeepLiveManager.getInstance().setKeepLiveActivity(this);
    }
}
