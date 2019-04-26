package com.dse.gpsdemo.dao;

import android.content.Context;

import java.util.Date;
import java.util.UUID;

public class DB_action {
    private  Context context;

    private LocationDB locationDB;

    private  String userName;
    private  String userId;
    private  double lgt;
    private  double ltt;
    private  DBManager dbManager;

    private  String id;  // 主键

    public DB_action(Context context, LocationDB locationDB, String userName, String userId, double lgt, double ltt) {
        this.context = context;
        this.locationDB = locationDB;
        this.userName = userName;
        this.userId = userId;
        this.lgt = lgt;
        this.ltt = ltt;
        dbManager = (DBManager) DBManager.getInstance(context);
    }

    public DB_action(Context context, String id){

        this.context = context;
        this.id = id;
    }

    public DB_action(Context context){
        this.context = context;
    }

    /**
     * 插入一条数据
     *
     * @return
     */
    private void   insertDB(){

        locationDB = new LocationDB();
        locationDB.setId(getUUID());
        locationDB.setUser(userName);
        locationDB.setUserId(userId);
        Date date = new Date();
        locationDB.setDate(date);

        locationDB.setLgt(lgt);
        locationDB.setLtt(ltt);

        dbManager.insertLocalDB(locationDB);

    }

    /**
     *  删除一条数据
     * @param id  主键
     */

    private  void del_data(String id ){

        dbManager.deleteLocalDB(id);

    }



    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }
}
