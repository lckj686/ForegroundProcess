package com.blue.frame.utils;

import android.os.Build;

/**
 * Created by leon on 2018/1/23.
 */

public class BuildUtils {

   public static boolean isAtLeast17Api() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return true;
        }

        return false;
    }

    public static boolean isAtLeast24Api() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return true;
        }

        return false;
    }



}
