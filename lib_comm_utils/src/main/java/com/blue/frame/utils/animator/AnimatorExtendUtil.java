package com.blue.frame.utils.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.*;
import android.view.animation.RotateAnimation;

/**
 * Description:收缩动画
 * Created by liwei on 16/8/2.
 */
public class AnimatorExtendUtil {
    //展开View，动画效果
    static public void showView2(final View v, int height, int milliSecond) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofInt(0, height);
        animator.setDuration(milliSecond);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }

    //展开View，动画效果
    static public void showView(final View v, int height, int milliSecond, Animator.AnimatorListener listener) {
        v.setVisibility(View.VISIBLE);


        //通过AnimatiorSet来设计同步执行的多个属性动画

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", 0, -1 * height);//Y轴平移旋转
        animator.setDuration(milliSecond);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);//Y轴平移旋转
        animator2.setDuration(milliSecond);

        AnimatorSet set = new AnimatorSet();

        set.playTogether(animator, animator2);//同步执行
        if (listener != null){
            set.addListener(listener);
        }

        set.start();


    }

    /**
     * 显示
     * @param v
     * @param height
     * @param milliSecond
     */
    static public void showViewFromTop(final View v, int height, int milliSecond) {
        v.setVisibility(View.VISIBLE);

        //通过AnimatiorSet来设计同步执行的多个属性动画

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",  -1 * height,  0);//Y轴平移旋转
        animator.setDuration(milliSecond);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);//Y轴平移旋转
        animator2.setDuration(milliSecond);

        AnimatorSet set = new AnimatorSet();

        set.playTogether(animator, animator2);//同步执行
        set.start();

    }


    /**
     * 关闭
     * @param v
     * @param height
     * @param milliSecond
     */
    static public void dissViewFromTop(final View v, int height, int milliSecond) {
        v.setVisibility(View.VISIBLE);


        //通过AnimatiorSet来设计同步执行的多个属性动画

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",  0,  -1 * height);//Y轴平移旋转
        animator.setDuration(milliSecond);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);//Y轴平移旋转
        animator2.setDuration(milliSecond);

        AnimatorSet set = new AnimatorSet();

        set.playTogether(animator, animator2);//同步执行
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    //展开View，动画效果
    static public void showAlphaView(final View v) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        animator2.setDuration(200);
        animator2.start();
    }

    //展开View，动画效果
    static public void showAlphaView2(final View v) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        animator2.setDuration(2000);
        animator2.start();
    }
    //展开View，动画效果
    static public void showAlphaView(final View v, int time) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        animator2.setRepeatCount(1);
        animator2.setDuration(time);
        animator2.start();
    }

    //展开View，动画效果
    static public void showAlphaView(final View v, int time, int delaytime) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        animator2.setRepeatCount(1);
        animator2.setStartDelay(delaytime);
        animator2.setDuration(time);
        animator2.start();
    }

    static public void dismissAlphaView(final View v) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        animator2.setDuration(200);
        animator2.start();
    }

    static public void dismissAlphaView(final View v, int time) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        animator2.setDuration(time);
        animator2.start();
    }

    //收起View，动画效果
    static public void dismissView(final View v, int height, int milliSecond) {

        ValueAnimator animator = ValueAnimator.ofInt(height, 0);
        animator.setDuration(milliSecond);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                if (value == 0) {
                    v.setVisibility(View.GONE);
                }
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }

        });
        animator.start();

    }

    static public ObjectAnimator ringView(final View v) {

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f);
        animator2.setDuration(1000);
        //animator2.setRepeatCount(30);
        animator2.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator2.setRepeatMode(ValueAnimator.RESTART);
        //animator2.start();



       return animator2;
    }

    public static android.view.animation.RotateAnimation ringView(){
        RotateAnimation stepAnimation = new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        stepAnimation.setDuration(1000);
        stepAnimation.setRepeatCount(-1);
        stepAnimation.setRepeatMode(Animation.RESTART);
        return stepAnimation;
    }

}
