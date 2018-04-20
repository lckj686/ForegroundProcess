package com.blue.frame.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MetricAffectingSpan;

/**
 * 富文本的处理 方式。封装
 * Created by liwei on 16/11/15.
 */

public class TextViewUtil {

    /**
     * 富文本设置字体大小
     *
     * @param srcString
     * @param tartgetString
     * @param txtSize
     * @return
     */
    static public SpannableString setTextPartTextSize(String srcString, String tartgetString, int txtSize) {
        int position = srcString.indexOf(tartgetString);
        SpannableString mSp = new SpannableString(srcString);
        if (position >= 0) {
            mSp.setSpan(new AbsoluteSizeSpan(txtSize, false), position, position + tartgetString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mSp;

    }

    /**
     * 设置文本指定段落的字体大小
     *
     * @param srcString
     * @param startIndex
     * @param endIndex
     * @param texSize
     * @return
     */
    static public SpannableString setTextPartTextSize(String srcString, int startIndex, int endIndex, int texSize) {
        SpannableString mSp = new SpannableString(srcString);
        if (startIndex >= 0 && endIndex <= srcString.length() - 1 && startIndex < endIndex) {
            mSp.setSpan(new AbsoluteSizeSpan(texSize, false), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mSp;
    }


    /**
     * 设置指定文本的 字体大小和颜色
     *
     * @param srcString
     * @param tartgetString
     * @param targetColor
     * @param txtSizeDp
     * @return
     */
    static public SpannableString setTextPartTextSize(String srcString, String tartgetString, int targetColor, int txtSizeDp) {
        int position = srcString.indexOf(tartgetString);
        SpannableString mSp = new SpannableString(srcString);
        if (position >= 0) {
            mSp.setSpan(new AbsoluteSizeSpan(txtSizeDp, true), position, position + tartgetString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mSp.setSpan(new ForegroundColorSpan(targetColor), position, position + tartgetString.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        return mSp;

    }


//---------------------------- 改字体类型，----------------------------

    /**
     * 改指定段的字体
     *
     * @param srcString  原字符串
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     * @param typeface   Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/DINCond-Bold.ttf");
     */
    public static void setType(String srcString, int startIndex, int endIndex, Typeface typeface) {
        SpannableString spannableString = new SpannableString(srcString);

        MetricAffectingSpan typefaceSpan = new NumTypefaceSpan(typeface);

        spannableString.setSpan(typefaceSpan,
                startIndex, startIndex + endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    public static class NumTypefaceSpan extends MetricAffectingSpan {

        private Typeface typeface;

        public NumTypefaceSpan(Typeface typeface) {
            this.typeface = typeface;
        }

        @Override
        public void updateMeasureState(TextPaint textPaint) {
            apply(textPaint);
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            apply(textPaint);
        }

        private void apply(final Paint paint) {
            final Typeface oldTypeface = paint.getTypeface();
            final int oldStyle = oldTypeface != null ? oldTypeface.getStyle() : 0;
            final int fakeStyle = oldStyle & ~typeface.getStyle();
            if ((fakeStyle & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((fakeStyle & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.setTypeface(typeface);
        }
    }

}
