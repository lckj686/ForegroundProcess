package com.blue.frame.utils.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by liwei on 16/11/15.
 */

public class BreatheAnimation {

    View.OnClickListener listener;

    ImageView breathImageView;
    RotateAnimation rotateAnim;

    public void init(ImageView breathImageView) {

        this.breathImageView = breathImageView;

    }

    ObjectAnimator animator;
    public void start(int res){
       // breathImageView.startAnimation(animationFadeOut);

        if (animator != null){
            animator.cancel();
           animator = null;
        }

        if (rotateAnim != null){
            rotateAnim.cancel();
            rotateAnim = null;
        }

        breathImageView.setImageResource(res);
        animator = ObjectAnimator.ofFloat(breathImageView, "alpha", 0.3F, 1f, 0.3f);//
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(1400)//
                .start();
        breathImageView.setImageResource(res);
        breathImageView.setClickable(true);
    }

    public void release(){
    if (animator != null){
        animator.cancel();
    }




    }

    public void stop2view(int res){
        if (animator != null){
            animator.cancel();
            animator = null;
        }


        breathImageView.setAlpha(1f);
        breathImageView.setClickable(false);
        toggleIcon(0, res);
    }

    public void stop2view(int res, boolean isPause){
        if (isPause){

            stop2viewNoeffect(res);
        }else {
            if (animator != null) {
                animator.cancel();
                animator = null;
            }


            breathImageView.setAlpha(1f);
            breathImageView.setClickable(false);
            toggleIcon(0, res);
        }
    }

    public void stop2viewNoeffect(int res){
        if (animator != null) {
            animator.end();
        }
        breathImageView.setAlpha(1f);

        breathImageView.setClickable(false);
        breathImageView.setImageResource(res);
    }

    private void toggleIcon(int position, int res) {

        float cX = breathImageView.getWidth() / 2.0f;
        float cY = breathImageView.getHeight() / 2.0f;

        if (position == 0) {
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_INCREASE);
        } else {
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
        }

        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(600);
        rotateAnim.setInterpolatedTimeListener(new RotateAnimation.InterpolatedTimeListener() {
            boolean oneshoot = true;

            @Override
            public void interpolatedTime(float interpolatedTime) {

                // 监听到翻转进度过半时，更新txtNumber显示内容。
                if (oneshoot && interpolatedTime > 0.5f) {//计时
                    oneshoot = false;
                    if (position == 0) {
                        breathImageView.setImageResource(res);

                    } else if (position == 1) {//计数

                    }
                }
            }
        });


        breathImageView.startAnimation(rotateAnim);
    }

}
