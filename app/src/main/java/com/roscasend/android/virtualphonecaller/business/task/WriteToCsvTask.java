package com.roscasend.android.virtualphonecaller.business.task;

import android.os.AsyncTask;


import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;
import com.roscasend.android.virtualphonecaller.business.util.BeanUtils;

import java.util.List;

public class WriteToCsvTask extends AsyncTask<List<TelephoneNumberBean>, Void, Object> {

    @Override
    protected Object doInBackground(List<TelephoneNumberBean>... params) {
        BeanUtils.writeCsvFileWithApache2(params[0], "dolj33");
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {


    }
}
