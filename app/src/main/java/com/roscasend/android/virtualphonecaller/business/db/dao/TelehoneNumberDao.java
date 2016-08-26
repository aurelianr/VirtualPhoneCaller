package com.roscasend.android.virtualphonecaller.business.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;
import com.roscasend.android.virtualphonecaller.business.util.LogS;

import java.util.ArrayList;
import java.util.List;

public class TelehoneNumberDao {
    public static final String TABLE_TELEPHONE_NUMBER = "TelephoneNumbers";

    private static final String CT_TABLE_TELEPHONE_COUNTRY = "country";
    private static final String CT_TABLE_TELEPHONE_NUMBER = "number";
    private static final String CT_TABLE_TELEPHONE_IS_VALID = "is_walid";
    private static final String CT_TABLE_TELEPHONE_WAS_CALLED = "was_called";

    public static final String TELEPHONE_NUMBER_TABLE_CREATE = "CREATE TABLE " + TABLE_TELEPHONE_NUMBER
            + "("
            + CT_TABLE_TELEPHONE_NUMBER + " INTEGER, "
            + CT_TABLE_TELEPHONE_IS_VALID    + " INTEGER, "
            + CT_TABLE_TELEPHONE_WAS_CALLED + " INTEGER, "
            + CT_TABLE_TELEPHONE_COUNTRY + " TEXT, "

            + " PRIMARY KEY ("
            + CT_TABLE_TELEPHONE_NUMBER + ", "
            + CT_TABLE_TELEPHONE_COUNTRY
            + ")"
            + ");";

    private static TelehoneNumberDao instance;

    private TelehoneNumberDao() {
    }

    public static TelehoneNumberDao getInstance() {
        if (null == instance) {
            instance = new TelehoneNumberDao();
        }
        return instance;
    }

    public void saveTelephoneNumbers(SQLiteDatabase db, TelephoneNumberBean telephoneBean){
        try {
            ContentValues cv = new ContentValues();
            cv.put(CT_TABLE_TELEPHONE_NUMBER, telephoneBean.getNumber());
            cv.put(CT_TABLE_TELEPHONE_IS_VALID, telephoneBean.isValid());
            cv.put(CT_TABLE_TELEPHONE_WAS_CALLED, telephoneBean.isCalled());
            cv.put(CT_TABLE_TELEPHONE_COUNTRY, telephoneBean.getCountry());

            db.insertWithOnConflict(TABLE_TELEPHONE_NUMBER, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        } catch(Exception e) {
            LogS.e("Error at inserting in " + TABLE_TELEPHONE_NUMBER + ": " + e.getMessage());
        }
    }

    public List<TelephoneNumberBean> getTelephoneNumbersByCountry(SQLiteDatabase db, String country) {
        List<TelephoneNumberBean> result = new ArrayList<TelephoneNumberBean>();
        Cursor c = null;
        try {
            c = db.query(TABLE_TELEPHONE_NUMBER, null,  CT_TABLE_TELEPHONE_COUNTRY + " = ? " , new String[] {String.valueOf(country)}, null, null, null);

            if (c != null && c.moveToFirst()) {

                do {
                    TelephoneNumberBean bean = new TelephoneNumberBean();

                    bean.setNumber(c.getString(c.getColumnIndex(CT_TABLE_TELEPHONE_NUMBER)));
                    bean.setCalled(c.getInt(c.getColumnIndex(CT_TABLE_TELEPHONE_WAS_CALLED)) == 1? true : false);
                    bean.setValid(c.getInt(c.getColumnIndex(CT_TABLE_TELEPHONE_IS_VALID)) == 1? true : false);
                    bean.setCountry(c.getString(c.getColumnIndex(CT_TABLE_TELEPHONE_COUNTRY)));

                    result.add(bean);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            LogS.e("Error at retrieving from " + TABLE_TELEPHONE_NUMBER + " using the " + CT_TABLE_TELEPHONE_COUNTRY + " = " + country +" : " + e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return result;
    }

    public List<TelephoneNumberBean> getAllPhoneNumbers(SQLiteDatabase db) {
        List<TelephoneNumberBean> result = new ArrayList<TelephoneNumberBean>();
        Cursor c = null;
        try {
            c = db.query(TABLE_TELEPHONE_NUMBER, null, null, null, null, null, null);

            if (c != null && c.moveToFirst()) {

                do {
                    TelephoneNumberBean bean = new TelephoneNumberBean();

                    bean.setNumber(c.getString(c.getColumnIndex(CT_TABLE_TELEPHONE_NUMBER)));
                    bean.setCalled(c.getInt(c.getColumnIndex(CT_TABLE_TELEPHONE_WAS_CALLED)) == 1? true : false);
                    bean.setValid(c.getInt(c.getColumnIndex(CT_TABLE_TELEPHONE_IS_VALID)) == 1? true : false);
                    bean.setCountry(c.getString(c.getColumnIndex(CT_TABLE_TELEPHONE_COUNTRY)));

                    result.add(bean);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            LogS.e("Error at retrieving from " + TABLE_TELEPHONE_NUMBER + ":" + e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return result;
    }

}

