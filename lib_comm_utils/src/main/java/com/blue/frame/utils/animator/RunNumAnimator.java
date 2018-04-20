package com.blue.frame.utils.animator;

import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * Description:
 * Created by liwei on 17/2/20.
 */

public class RunNumAnimator {


    static public  void runInt(int fromN, int toNum, TextView tv){
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int)fromN, (int)toNum);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                tv.setText(valueAnimator.getAnimatedValue().toString());
                if (valueAnimator.getAnimatedFraction()>=1){

                }
            }
        });
        valueAnimator.start();
    }
}
