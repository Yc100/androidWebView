package com.dse.gpsdemo.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity
public class LocationDB {

    @Id
    private String id;

    private double lgt;

    private double  ltt;

    private Date date;

    private  String user;

    private String  userId;

    @Generated(hash = 1715538955)
    public LocationDB(String id, double lgt, double ltt, Date date, String user, String userId) {
        this.id = id;
        this.lgt = lgt;
        this.ltt = ltt;
        this.date = date;
        this.user = user;
        this.userId = userId;
    }

    @Generated(hash = 708380913)
    public LocationDB() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLgt() {
        return lgt;
    }

    public void setLgt(double lgt) {
        this.lgt = lgt;
    }

    public double getLtt() {
        return ltt;
    }

    public void setLtt(double ltt) {
        this.ltt = ltt;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
