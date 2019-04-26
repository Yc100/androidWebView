package com.dse.gpsdemo.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.dse.gpsdemo.activity.MainActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * 文件描述 :
 * 创建人员 : 幽默GUY
 * 创建日期 : 2018/7/16 0016
 * 创建时间 : 0:57
 * 最后修改 :
 */
public class PermissionTool extends Activity {

    private Context context;

    private String[] permissionStorage = Permission.Group.STORAGE;

    private  String[]  permissionLoc = Permission.Group.LOCATION;


    private static final int REQ_CODE_PERMISSION = 1;

    public PermissionTool() {
        this.context = this;
    }

    private Rationale mRationale = new Rationale() {
        @Override
        public void showRationale(Context context, Object data, RequestExecutor executor) {
            // 这里使用一个Dialog询问用户是否继续授权。

            // 如果用户继续：
            executor.execute();

            // 如果用户中断：
            executor.cancel();
        }
    };


    public void getPermission() {

        AndPermission.with(context)
                .runtime()
                .permission(
                        permissionStorage,
                        permissionLoc
                )
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(Object data) {

                        //  有对应的权限
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1 = new Intent(context, MainActivity.class);
                                startActivity(intent1);
                                PermissionTool.this.finish();
                            }
                        }, 2000);//3000表示延迟的毫秒数。

                    }

                }).onDenied(new Action() {

            @Override
            public void onAction(Object data) {
                if (AndPermission.hasAlwaysDeniedPermission(context, permissionStorage)) {
                    // 这些权限被用户总是拒绝。
                    // 用Dialog展示没有某权限，询问用户是否去设置中授权。
                    AndPermission.with(context)
                            .runtime()
                            .setting()
                            .start(REQ_CODE_PERMISSION);
                }

                if (AndPermission.hasAlwaysDeniedPermission(context, permissionLoc)) {
                    // 这些权限被用户总是拒绝。
                    // 用Dialog展示没有某权限，询问用户是否去设置中授权。
                    AndPermission.with(context)
                            .runtime()
                            .setting()
                            .start(REQ_CODE_PERMISSION);
                }
            }

        }).start();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        switch (reqCode) {
            case REQ_CODE_PERMISSION: {
                if (AndPermission.hasPermissions(context, permissionStorage)) {


                } else if(AndPermission.hasPermissions(context, permissionLoc)){
                    // 没有对应的权限
                }
                break;
            }
        }
    }


}
