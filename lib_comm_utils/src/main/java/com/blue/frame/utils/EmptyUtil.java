package com.blue.frame.utils;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 容器判断是否为空
 * Created by sucer on 2016/4/17.
 */
public class EmptyUtil {

    public static boolean isEmpty(String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Set set) {
        if (set == null || set.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Integer value) {
        if (value == null) {
            return true;
        }
        return false;
    }
}
