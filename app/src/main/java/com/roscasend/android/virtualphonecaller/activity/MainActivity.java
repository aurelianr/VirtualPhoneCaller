package com.roscasend.android.virtualphonecaller.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.roscasend.android.virtualphonecaller.R;
import com.roscasend.android.virtualphonecaller.fragment.VirtualCallerFragment;
import com.roscasend.android.virtualphonecaller.interfaces.Callable;
import com.roscasend.android.virtualphonecaller.service.PhoneCallService;


public class MainActivity extends AppCompatActivity implements Callable {
    private static final String TAG = MainActivity.class.getName();

    private static final int PERMISION_FOR_PHONE_CALLING = 100;
    private String number;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PhoneCallListener phoneListener = new PhoneCallListener();
//        TelephonyManager telephonyManager = (TelephonyManager) this
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        VirtualCallerFragment vcf = VirtualCallerFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, vcf);
        fragmentTransaction.commit();

    }

    @Override
    public void callPhone(String phoneNumber) {
        number = phoneNumber;
        boolean isPhoneCallingPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
        if (isPhoneCallingPermissionGranted) {
            callToPhoneNumber(phoneNumber);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE}, PERMISION_FOR_PHONE_CALLING);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISION_FOR_PHONE_CALLING: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callToPhoneNumber(number);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "Pot sa efectuez apeluri de pe acest telefon numai daca accptati permisiunea.", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void callToPhoneNumber(String number) {
        // PhoneCallService phoneCallService = new PhoneCallService();

        startActionCallNumber(number);
    }


    public  void startActionCallNumber(String callNUmber) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_CALL);
//        String uri = "tel:" + callNUmber.trim() ;
//
//        intent.setData(Uri.parse(uri));
//
//        startActivity(intent);
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


}

   // public void endCall() {
//        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        Class c = Class.forName(tm.getClass().getName());
//        Method m = c.getDeclaredMethod("getITelephony");
//        m.setAccessible(true);
//        telephonyService = (ITelephony) m.invoke(tm);
//        telephonyService.silenceRinger();
//        telephonyService.endCall();
    //}

