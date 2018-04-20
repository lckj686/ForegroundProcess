package com.blue.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Description:
 * Created by liwei on 17/2/6.
 */

public class ContextUtil {

    /**
     * 根据context 找 activity
     * @param cont
     * @return
     */
    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }
}
