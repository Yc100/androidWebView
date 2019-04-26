package com.dse.gpsdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.dse.gpsdemo.R;

public class LoginActivity extends AppCompatActivity {

    //获取当前版本号
    public  int currentapiVersion=android.os.Build.VERSION.SDK_INT;
    // 登录按钮
    private Button loginIn = null;
    // 输入用户名
    private EditText user = null;
    // 输入密码
    private EditText password = null;
    // 记住用户名
    private CheckBox remberMe = null;
    //账号密码 记忆
    private SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        loginIn = findViewById(R.id.btn_login);
        user = findViewById(R.id.login_account);
        password = findViewById(R.id.login_pwd);
        remberMe = findViewById(R.id.Login_Remember);

        int spMode =currentapiVersion;

        if(currentapiVersion>=24){
            spMode = Context.MODE_PRIVATE;
        }

        //获得实例对象
        sp = this.getSharedPreferences("userInfo", spMode);

        Boolean ifCheck = (Boolean) sp.getBoolean("ISCHECK",false);

        //判断记住密码多选框的状态
        if (ifCheck) {
            //设置默认是记录密码状态
            remberMe.setChecked(true);
            user.setText((CharSequence) sp.getString("USER_NAME", ""));
            password.setText((CharSequence) sp.getString("PASSWORD", ""));

        }
    }

    static class LoginParams {
        public String userName;
        public String pwd;

        public LoginParams(String name, String pwd) {
            this.pwd = pwd;
            this.userName = name;
        }
    }

    public void finish_login(View view) {

        final String userNameValue = user.getText().toString();
        final String passwordValue = password.getText().toString();

//        Gson gson = new Gson();
//        LoginParams loginParams = new LoginParams(userNameValue, passwordValue);
//        String params = gson.toJson(loginParams);
//        String url = "www.baidu.com";
//        OkHttpUtils.post(url, params, new OkHttpCallback() {
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                super.onResponse(call, response);
//
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                super.onFailure(call, e);
//            }
//
//            @Override
//            public void onFinish(String status, String msg) {
//                super.onFinish(status, msg);
//
//                if (status == "200") {
//                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    if (remberMe.isChecked()) {
//                        //记住用户名、密码、
//                        sp.put("USER_NAME", userNameValue);
//                        sp.put("PASSWORD", passwordValue);
//                    }
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent1=new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent1);
//                            LoginActivity.this.finish();
//                        }
//                    },2000);//3000表示延迟的毫秒数。
//                } else {
//                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        if(userNameValue.equals("ycc")&&passwordValue.equals("123")){
            Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
            //登录成功和记住密码框为选中状态才保存用户信息
            if(remberMe.isChecked()){
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", userNameValue);
                editor.putString("PASSWORD",passwordValue);
                editor.putBoolean("ISCHECK",true);
                editor.commit();
            }else{
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", "");
                editor.putString("PASSWORD","");
                editor.putBoolean("ISCHECK",false);
                editor.commit();
            }
            new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1=new Intent(LoginActivity.this, LocationActivity.class);
                            startActivity(intent1);
                            LoginActivity.this.finish();
                        }
                    },2000);//3000表示延迟的毫秒数。

        }else{
            Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
        }

    }

    public  void  toggleSelect(View view){
       final String userNameValue = user.getText().toString();
       final String passwordValue = password.getText().toString();
        remberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中
                SharedPreferences.Editor editor = sp.edit();

                Log.i("点击多选框状态", String.valueOf(isChecked));

                if (isChecked) {
                    //记住用户名、密码、
                    editor.putString("USER_NAME", userNameValue);
                    editor.putString("PASSWORD",passwordValue);
                    editor.putBoolean("ISCHECK",true);
                    editor.commit();
                }else{
                    //记住用户名、密码、
                    editor.putString("USER_NAME", "");
                    editor.putString("PASSWORD","");
                    editor.putBoolean("ISCHECK",false);
                    editor.commit();
                }
            }
        });


    }

}
