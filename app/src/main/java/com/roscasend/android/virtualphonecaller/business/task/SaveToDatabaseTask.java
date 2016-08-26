package com.roscasend.android.virtualphonecaller.business.task;


import android.os.AsyncTask;

import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;

public class SaveToDatabaseTask extends AsyncTask<List<TelephoneNumberBean>,Void,Object>{

    private WeakReference reference;

    public SaveToDatabaseTask() {

    }
    @Override
    protected Object doInBackground(List<TelephoneNumberBean>... params) {
        return null;
    }
}
