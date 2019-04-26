package com.dse.gpsdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBManager {

    private final static String dbName = "loc_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param locationDB
     */
    public void insertLocalDB(LocationDB locationDB) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        locationDBDao.insert(locationDB);
    }

    /**
     * 插入用户集合
     *
     * @param locationDBS
     */
    public void insertLocalDBList(List<LocationDB> locationDBS) {
        if (locationDBS == null || locationDBS.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        locationDBDao.insertInTx(locationDBS);
    }

    /**
     * 删除一条记录  依靠对象
     *
     * @param locationDB
     */
    public void deleteLocalDB(LocationDB locationDB) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        locationDBDao.delete(locationDB);
    }

    public  void deleteLocalDB(String id){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        locationDBDao.deleteByKey(id);


    }

    /**
     * 更新一条记录   依靠 对象
     *
     * @param locationDB
     */
    public void updateLocalDB(LocationDB locationDB) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        locationDBDao.update(locationDB);
    }



    /**
     * 查询定位列表
     */
    public List<LocationDB> queryLocalDBList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        QueryBuilder<LocationDB> qb = locationDBDao.queryBuilder();
        List<LocationDB> list = qb.list();
        return list;
    }

    /**
     * 查询定位列表  依靠主键来查询
     */
    public List<LocationDB> queryLocalDBList(String id) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LocationDBDao locationDBDao = daoSession.getLocationDBDao();
        QueryBuilder<LocationDB> qb = locationDBDao.queryBuilder();
        qb.where(LocationDBDao.Properties.Id.gt(id)).orderAsc(LocationDBDao.Properties.Id);
        List<LocationDB> list = qb.list();
        return list;
    }

}
