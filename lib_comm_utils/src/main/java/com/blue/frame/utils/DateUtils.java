package com.blue.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: 日期相关的工具类
 * Created by crx on 2016/8/4.
 */
public class DateUtils {


    /**
     * 获得当前年
     * @return 如：2015
     */
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     * @return 如 1  、 12
     */
    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 获取当前周是一年的第几周
     * @return
     */
    public static int getCurrentWeek(){
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 是否为闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year){
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取某一年某一个月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month){
        if (month == 2) {
            if (isLeapYear(year)){
                return 29;
            }else {
                return 28;
            }
        }
        if (month==4 || month == 6 || month == 9 || month == 11)
            return 30;
        else
            return 31;
    }

    /**
     * 获取某个时间点的unix时间戳
     * @param dateString  时间点 格式为 "20160805 15:02:33"
     * @return
     */
    public static long getUnixTimeStamp(String dateString){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        try {
            Date date = df.parse(dateString);
            long timeStamp = date.getTime();
            return timeStamp/1000                  ;
        }catch (ParseException e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 把时间戳转换为普通时间
     * @param timeStamp 时间戳
     * @return
     */
    public static String getFormatTimeByTimeStamp(String timeStamp){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date(Integer.valueOf(timeStamp)*1000L));
    }
}
