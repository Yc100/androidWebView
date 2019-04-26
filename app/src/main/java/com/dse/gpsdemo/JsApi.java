package com.dse.gpsdemo;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.JavascriptInterface;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;

import com.dse.gpsdemo.activity.MainActivity;

import com.dse.gpsdemo.activity.SplashActivity;

import java.util.Map;

/**
 *  所有的 jsbridge 接口写在这
 */

public class JsApi{

    /**
     *   返回 经纬度和当前所在的位置地址
     * @param object
     * @return
     */
    @JavascriptInterface
    public String tellHtml_loc(Object object){
        class Loc{
            private  double lgt;
            private double lat;
            private String address;

            public double getLgt() {
                return lgt;
            }

            public void setLgt(double lgt) {
                this.lgt = lgt;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }



        AMapLocation aMapLocation =   SplashActivity.loc;

        Log.i("当前经纬度是", String.valueOf(aMapLocation));

        if(aMapLocation!=null){
            Loc loc = new Loc();
            loc.setLgt(aMapLocation.getLongitude());
            loc.setLat(aMapLocation.getLatitude());
            loc.setAddress(aMapLocation.getAddress());
            Object obj = JSONObject.toJSON(loc);

            return obj.toString();
        }

        return null;

    }

    /**
     *
     * @param object   字符串 文件的路径  返回一个新页面
     */
    @JavascriptInterface
    public  void gotoPage(Object object){
        MainActivity.dWebView.loadUrl((String) object);
    }


    /**
     *  保存或者返回 账号的 数据
     * @param object  json数据
     * @return
     */

    @JavascriptInterface
    public  String userCount(Object  object){

        Log.i("测试","*****************************************");

        if(object.equals("")){

            Log.i("数据是",MainActivity.login_msg);

            return  MainActivity.login_msg;

        }else{
            // 参数为字符串
            String dataStr = (String) object;
            JSONObject jsonObject = JSONObject.parseObject(dataStr);

            Log.i("数据是",dataStr);

            // 参数为map
            //Map<String, Object> dataMap = (Map<String, Object>) object;

            String name=(String)jsonObject.get("name");
            String pwd=(String)jsonObject.get("pwd");
            boolean isChecked = (boolean) jsonObject.getBoolean("isChecked");

            SharedPreferences.Editor editor = MainActivity.sp.edit();
            editor.putString("USERNAME", name);
            editor.putString("PWD",pwd);
            editor.putBoolean("ISCHECKED",isChecked);
            editor.commit();

            return  "";
        }


    }


    /**
     *   点击是否记住账号密码
     * @param object  json数据
     */


    public  void remberMe(Object object){

        String dataStr = (String) object;
        JSONObject jsonObject = JSONObject.parseObject(dataStr);

        boolean isChecked = jsonObject.getBoolean("isChecked");
        String name=(String)jsonObject.get("name");
        String pwd=(String)jsonObject.get("pwd");
        SharedPreferences.Editor editor = MainActivity.sp.edit();
        if(isChecked){


            editor.putString("USERNAME", name);
            editor.putString("PWD",pwd);
            editor.putBoolean("ISCHECKED",isChecked);
            editor.commit();

        }else{

            editor.putString("USERNAME", "");
            editor.putString("PWD","");
            editor.putBoolean("ISCHECKED",false);
            editor.commit();
        }

    }


    @JavascriptInterface
    String testSyn(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString("msg") + "［syn call］";
    }



}