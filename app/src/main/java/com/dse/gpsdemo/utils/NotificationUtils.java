package com.dse.gpsdemo.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.Context;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;


import com.dse.gpsdemo.R;

import static android.content.Context.NOTIFICATION_SERVICE;


public class NotificationUtils  {

    private  Context context;

    public  NotificationUtils (Context context){

        this.context = context;
    }



    //发一个普通通知，新增一个通知
    public void sendNormalNotification(boolean isRoutingView,long warkingHours){
        if(isRoutingView){

            Log.i("当前定位的基数为", String.valueOf(warkingHours));

            Notification notification = getNotificationBuilder(warkingHours).build();
            getNotificationManager().notify(1,notification);
        }

    }


    //获取系统服务
    private NotificationManager mNotificationManager;
    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null){
            mNotificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    //兼容android8.0以及之前版本获取Notification.Builder方法
    private Notification.Builder getNotificationBuilder( long warkingHours){

//        String time_str = TimerUtils.getTime(warkingHours);
        String time_str = TimerUtils.showTime(warkingHours);

        Notification.Builder builder = new Notification.Builder(context)
                .setAutoCancel(true)//是否自动取消，设置为true，点击通知栏 ，移除通知
                .setContentTitle("本次巡检用时")
                .setContentText(""+time_str)
                .setSmallIcon(R.mipmap.ic_launcher)//通知栏消息小图标，不设置是不会显示通知的
                //ledARGB 表示灯光颜色、ledOnMs 亮持续时间、ledOffMs 暗的时间
//                .setLights(Color.RED, 3000, 3000)
                //.setVibrate(new long[]{100,100,200})//震动的模式，第一次100ms，第二次100ms，第三次200ms
                //.setStyle(new Notification.BigTextStyle())
                ;
        //没加版本判断会报Call requires API level 26 (current min is 16):android.app.Notification.Builder#Builder）错误
        //builder.setChannelId("channel_id");
        //通过版本号判断兼容了低版本没有通知渠道方法的问题，只有当版本号大于26（Build.VERSION_CODES.O）时才使用渠道相关方法
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //builder的channelId需和下面channel的保持一致；
            builder.setChannelId("channel_id");
            NotificationChannel channel = new
                    NotificationChannel("channel_id","本次巡检用时",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setBypassDnd(true);//设置可以绕过请勿打扰模式
            channel.canBypassDnd();//可否绕过请勿打扰模式
            channel.setSound(null,null);
            //锁屏显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
//            channel.shouldShowLights();//是否会闪光
//            channel.enableLights(true);//闪光
            //指定闪光时的灯光颜色，为了兼容低版本在上面builder上通过setLights方法设置了
            //channel.setLightColor(Color.RED);
            channel.canShowBadge();//桌面launcher消息角标
//            channel.enableVibration(true);//是否允许震动
            //震动模式，第一次100ms，第二次100ms，第三次200ms，为了兼容低版本在上面builder上设置了
            //channel.setVibrationPattern(new long[]{100,100,200});
//            channel.getAudioAttributes();//获取系统通知响铃声音的配置
            channel.getGroup();//获取通知渠道组
            //绑定通知渠道
            getNotificationManager().createNotificationChannel(channel);
        }
        return builder;
    };
}
