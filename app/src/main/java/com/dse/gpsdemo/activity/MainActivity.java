package com.dse.gpsdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSONObject;
import com.dse.gpsdemo.JsApi;
import com.dse.gpsdemo.JsEchoApi;
import com.dse.gpsdemo.R;
import com.dse.gpsdemo.utils.LoadingDialog;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import wendu.dsbridge.DWebView;

public class MainActivity extends Activity {
    public static DWebView dWebView ;


    // 获取当前系统版本号
    public static int currentapiVersion=android.os.Build.VERSION.SDK_INT;
    //账号密码 记忆
    public static SharedPreferences sp;
    public  static String  login_msg ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final LoadingDialog loadingDialog=new LoadingDialog(MainActivity.this,"玩命加载中...");

        dWebView = findViewById(R.id.webview);

        int spMode =currentapiVersion;

        if(currentapiVersion>=24){
            spMode = Context.MODE_PRIVATE;
        }

        //获得手机存储的共享数据 实例对象
        sp = this.getSharedPreferences("userInfo", spMode);

        String userName = sp.getString("USERNAME","");
        String pwd = sp.getString("PWD","");
        boolean isChecked = sp.getBoolean("ISCHECKED",true);

        Map<String, Object> loginMsg = new HashMap<>();
        loginMsg.put("USERNAME", userName);
        loginMsg.put("PWD", pwd);
        loginMsg.put("ISCHECKED", isChecked);

        login_msg =JSONObject.toJSONString(loginMsg,false)  ;

        // 设置 webview 的基础配置
        com.tencent.smtt.sdk.WebSettings settings = dWebView.getSettings();

        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);

        settings.setBlockNetworkImage(true);

        dWebView.setWebContentsDebuggingEnabled(true);
        dWebView.addJavascriptObject( new JsApi(),null);
        dWebView.addJavascriptObject(new JsEchoApi(),"echo");

        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = dWebView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(dWebView.getSettings(), true);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        dWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {

                if(newProgress ==100){
//                    loadingDialog.close();

                    dWebView.getSettings().setBlockNetworkImage(false);
                }else{
//                    loadingDialog.show();

                }
            }
        });
        dWebView.loadUrl("file:///android_asset/index.html");

//        dWebView.loadUrl("http://www.whdse.cn:56025/pipe/app/#/login");



    }

    private void androidJSBridge(String methodName) {
        // Android版本变量
        final int version = Build.VERSION.SDK_INT;
        String url = "javascript:window." + methodName + "()";
        if (version < 18) {
            dWebView.loadUrl(url);
        } else {

            dWebView.evaluateJavascript(url, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    //此处为 js 返回的结果
                    Log.i("返回结果是:",value);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        androidJSBridge("back");
    }

}
