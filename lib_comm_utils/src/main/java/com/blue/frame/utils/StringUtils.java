package com.blue.frame.utils;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * 字符串相关工具类
 * Created by oldpan on 2017/6/14.
 */

public class StringUtils {

    /**
     * 如果是okhttp不支持字符就转成Unicode编码
     * 中文unicode范围 	4E00-9FA5
     *
     * @param headInfo
     * @return
     */
    public static String encodeHeadInfo(String headInfo) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append(String.format("\\u%04x", (int) c));
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }


    static public float changePercentToPoint(String percent) {
        return new Float(percent.substring(0, percent.indexOf("%"))) / 100;
    }

    /**
     * 输入汉字返回拼音的通用方法函数。
     *
     * @param hanzi 汉字
     * @return 汉字转化为小写拼音
     */
    public static String getPinYin(String hanzi) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(hanzi);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }

        return sb.toString().toLowerCase();
    }

    /**
     * 获得汉字拼音的首字母
     *
     * @param hanzi
     * @return
     */
    public static char getHanziFirstChar(String hanzi) {
        String pinYin = getPinYin(hanzi);
        if (TextUtils.isEmpty(pinYin)) {
            return "".toCharArray()[0];
        } else {
            return pinYin.toCharArray()[0];
        }
    }

    /**
     * 是否是中文汉字  	4E00-9FA5
     *
     * @param c
     * @return true, 是中文汉字，false otherwise
     */
    public static boolean isChinese(char c) {

        return c >= '\u4E00' && c <= '\u9FA5';
    }
}
