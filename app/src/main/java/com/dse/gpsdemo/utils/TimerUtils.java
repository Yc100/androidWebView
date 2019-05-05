package com.dse.gpsdemo.utils;

import java.text.DecimalFormat;

public class TimerUtils {
    public static String getTime(long second) {
        if (second < 10) {
            return "0小时0分" + second+"秒";
        }
        if (second < 60) {
            return "0小时0分" + second+"秒";
        }
        if (second < 3600) {
            long minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0小时"+"" + minute + "分" + second+"秒";
                }
                return "0小时"+"" + minute + "分" + second+"秒";
            }
            if (second < 10) {
                return "0小时"+minute + "分" + second+"秒";
            }
            return "0小时"+minute + "分" + second+"秒";
        }
        long hour = second / 3600;
        long minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return  hour + "小时" + minute + "分" + second+"秒";
                }
                return  hour + "小时" + minute + "分" + second+"秒";
            }
            if (second < 10) {
                return hour +"小时"+ minute + "分" + second +"秒";
            }
            return hour +"小时"+ minute + "分" + second+"秒";
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + "小时" + minute + "分" + second+"秒";
            }
            return hour + "小时" + minute + "分" + second+"秒";
        }
        if (second < 10) {
            return hour +"小时"+ minute + "分" + second+"秒";
        }
        return hour +"小时"+ minute + "分" + second+"秒";
    }

    public  static String showTime(long seconds){

        if(seconds<3600){
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            double temp_mins = Double.parseDouble(decimalFormat.format(seconds/60.0));
            int mins = (int) Math.rint(temp_mins);

            if(mins==60){
                return  "1小时0分钟";
            }else{
                return  mins+"分钟";
            }
        }
        if(seconds>=3600){

            double temp_hours = seconds/3600.00;

            int hours = (int) Math.floor(temp_hours);

            int mins = (int) (seconds%3600)/60;

            return  hours+"小时"+mins+"分钟";
        }
        return "";
    }


}
