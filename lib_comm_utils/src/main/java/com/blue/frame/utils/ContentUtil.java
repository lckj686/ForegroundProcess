package com.blue.frame.utils;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by liwei on 16/7/13.
 */
public class ContentUtil {

    public static boolean isJsonEmty(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return true;
        }

        if (jsonString.equals("[]")) {
            return true;
        }

        if (jsonString.equals("{}")) {
            return true;
        }

        return false;
    }


    public static boolean isValid(String content) {
        return !TextUtils.isEmpty(content);
    }


    public static boolean isValid(List list) {
        return !EmptyUtil.isEmpty(list);
    }

    public static boolean isValid(Map map) {
        return !EmptyUtil.isEmpty(map);
    }

    public static boolean isEqual(String src, String dst) {
        if (src == null && dst == null) {
            return true;
        }

        if (src == null || dst == null) {
            return false;
        }

        if (src.equals(dst)) {
            return true;
        }
        return false;
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


    public static String getUrlLastFileName(String url) {

        int index = url.lastIndexOf("/");
        // String before = url.substring(0,index + 1);
        String after = url.substring(index + 1);


        return after;
    }

    public static boolean isValidJsonString(String json) {
        if (TextUtils.isEmpty(json)) {
            return true;
        }

        if (json.startsWith("[") && json.endsWith("]")) {

            return true;
        } else if (json.startsWith("{") && json.endsWith("}")) {

            return true;
        }

        return false;
    }
}
