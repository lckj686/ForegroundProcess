package com.demo.mydemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.demo.mydemo.utils.TileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        textView = findViewById(R.id.textView);
        if (BuildConfig.FLAVOR.equals("demo4")) {
            textView.setText("有加上 解锁 和锁屏 1像素的监控");
        } else if (BuildConfig.FLAVOR.equals("demo3")) {
            textView.setText("没有加上 解锁 和锁屏 1像素的监控");
        } else if (BuildConfig.FLAVOR.equals("demo5")) {
            textView.setText("没有加上 解锁 和锁屏 1像素的监控, 闹钟查阅但是不弹出通知");
        }


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForegroundService.class);
                intent.setAction(ConstElement.ACTION_ACTIVITY);
                startService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MessageEvent event) {
        textView.setText("没有加上 解锁 和锁屏 1像素的监控, 闹钟查阅但是不谈通知 \n"
                + "运行时间：" + TileUtils.INSTANCE.getDistanceTime());
    }
}
