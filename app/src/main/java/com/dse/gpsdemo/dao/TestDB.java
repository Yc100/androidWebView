package com.dse.gpsdemo.dao;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class TestDB {

    private Context context;

    public TestDB(Context context) {
        this.context = context;
    }
    public void Unitest() {
        DBManager dbManager = (DBManager) DBManager.getInstance(context);
        for (int i = 0; i < 5; i++) {
            LocationDB locationDB = new LocationDB();
            locationDB.setId(String.valueOf(i));
            dbManager.insertLocalDB(locationDB);
        }
        List<LocationDB> locationDBList = dbManager.queryLocalDBList();
        for (LocationDB locationDB : locationDBList) {
            Log.e("TAG", "locationDBList--before-->" + locationDB.getId() + "--" + locationDB.getUser() + "--" + locationDB.getLgt());
            if (locationDB.getId() == "") {
                dbManager.deleteLocalDB(locationDB);
            }
        }
    }
}
