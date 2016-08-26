package com.roscasend.android.virtualphonecaller.business.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;
import com.roscasend.android.virtualphonecaller.business.db.dao.TelehoneNumberDao;

import java.util.List;

public class SqlPhoneTestHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SQPhoneTestHelper.db";

    private static SqlPhoneTestHelper dbHelper;

    private static SQLiteDatabase db;

    private SqlPhoneTestHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);

        // create fresh table
        this.onCreate(db);
    }

    public static SqlPhoneTestHelper getDb(Context context) {
        if (dbHelper == null) {
            dbHelper = new SqlPhoneTestHelper(context);
        }

        return dbHelper;
    }

    public void resetDatabase() {
        dropTables(db);
        createTables(db);
    }
    private void createTables(SQLiteDatabase db) {
        db.execSQL(TelehoneNumberDao.TELEPHONE_NUMBER_TABLE_CREATE);
    }

    private void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TelehoneNumberDao.TABLE_TELEPHONE_NUMBER);
    }

    public void saveTelephone(TelephoneNumberBean telephoneNumberBean){
        TelehoneNumberDao.getInstance().saveTelephoneNumbers(db, telephoneNumberBean);
    }

    public List<TelephoneNumberBean> getTelephoneNumbersByCountry(String country){
       return  TelehoneNumberDao.getInstance().getTelephoneNumbersByCountry(db, country);
    }


}
