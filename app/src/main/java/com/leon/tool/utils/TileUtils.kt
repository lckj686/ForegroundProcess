package com.leon.tool.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by leon on 2018/4/15.
 */

object TileUtils {
    internal var oldTime: Long = 0

    init {
        oldTime = System.currentTimeMillis()
    }

    val time: String
        get() {
            val currentTime = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return formatter.format(currentTime)
        }


    val distanceTime: String
        get() {
            var currentTime = System.currentTimeMillis();
            var distance = currentTime - oldTime;
            distance = distance / 1000;
            val shi = distance / 3600;
            val fen = distance % 3600 / 60;
            val miao = distance % 60;

            return String.format("%02d:%02d:%02d", shi, fen, miao)
        }
}
