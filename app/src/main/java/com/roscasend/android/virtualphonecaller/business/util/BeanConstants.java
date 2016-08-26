package com.roscasend.android.virtualphonecaller.business.util;

import android.os.Environment;

/**
 * Created by Nemo on 6/17/2016.
 */
public class BeanConstants {
    public static String APP_BASE_DIRECTORY_PATH = null;
    public static final String APP_BASE_EXTERNAL_DIRECTORY_PATH = Environment.getExternalStorageDirectory() + BeanConstants.APP_BASE_DIRECTORY_NAME;
    public static String SAVED_CROP_FILE_PATH;
    //TODO change the folder name to nexte or patria
    public static final String APP_BASE_DIRECTORY_NAME = "/PhoneCallTest/";
    public static String TAG = "Demo::PhoneCallTest";

}
