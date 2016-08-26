package com.roscasend.android.virtualphonecaller.business.util;

import android.util.Log;

public class LogS {

    public static void d(String message) {

        Log.d(BeanConstants.TAG, message);
    }

    public static void d(String message, Throwable e) {
        Log.d(BeanConstants.TAG, message, e);
    }

    public static void e(String message) {
        Log.e(BeanConstants.TAG, message);
    }

    public static void e(Throwable e) {
        Log.e(BeanConstants.TAG, e.getMessage(), e);
    }

    public static void e(String message, Throwable e) {
        Log.e(BeanConstants.TAG, message, e);
    }

    public static void i(String message) {
        Log.i(BeanConstants.TAG, message);
    }

    public static void i(String message, Throwable e) {
        Log.i(BeanConstants.TAG, message, e);
    }

}