package com.roscasend.android.virtualphonecaller.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;
import com.roscasend.android.virtualphonecaller.business.controller.PhoneDataController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CallReceiver extends PhonecallReceiver {

    private String fullNumber = "";
    private boolean isValid;
  private BeeperControl beeperControl;

    @Override
    protected  void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        Log.i("TAG", "onIncomingCallReceived call " + number);
    }

    @Override
    protected  void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        Log.i("TAG", "onIncomingCallAnswered call");
    }

    @Override
    protected  void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        Log.i("TAG", "onIncomingCallEnded call");
    }

    @Override
    protected  void onOutgoingCallStarted(final Context ctx, String number, Date start)
    {
        Log.i("TAG", "outgping call started " + number);

         beeperControl = new BeeperControl(number);
         beeperControl.beepForAnHour(ctx);
    }

    private void endCall(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Class c = null;
        try {
            c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            Object telephonyService = m.invoke(tm); // Get the internal ITelephony object
            c = Class.forName(telephonyService.getClass().getName()); // Get its class
            m = c.getDeclaredMethod("endCall"); // Get the "endCall()" method
            m.setAccessible(true); // Make it accessible
            m.invoke(telephonyService); // invoke endCall()
        } catch (ClassNotFoundException e) {
            Log.e("TAG", "missed call" + e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e("TAG", "missed call" + e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e("TAG", "missed call" + e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("TAG", "missed call" + e.getMessage());
        }

    }


    @Override
    protected  void onOutgoingCallEnded(Context ctx, final String number, Date start, Date end)
    {
       final long time = end.getTime()  - start.getTime();

        Toast.makeText(ctx, "Durata apelului efectuat pentru " + number + " a fost de : " + (time /1000) +"s", Toast.LENGTH_LONG).show();
        Log.i("TAG", "outgoinc call ended "+ start.getTime() + " - " + end.getTime());

                TelephoneNumberBean bean = new TelephoneNumberBean();
               if (time /1000 < 4) {
                   bean.setValid(false);
               } else {
                   bean.setValid(true);
               }
                bean.setCalled(true);
                bean.setNumber(number);
                PhoneDataController.getInstance().addTelephoneNumbers(bean);

        if (beeperControl != null ) {
            beeperControl.abort();
            //   beeperControl.scheduler.shutdown();
        }
    }

    @Override
    protected  void onMissedCall(Context ctx, String number, Date start)
    {
        Log.i("TAG", "missed call");

    }

   class BeeperControl {
        private final ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);
       private String number;
       public BeeperControl (String number) {
           this.number= number;
       }
        public void beepForAnHour(final Context ctx) {

            final Runnable beeper = new Runnable() {
                public void run() {
                Log.e("BeeperControl", "inchid apelul pentru numarul " + number);
                  //  abortBroadcast();
                    endCall(ctx);
                }
            };
            final ScheduledFuture<?> beeperHandle =
                    scheduler.scheduleAtFixedRate(beeper, 0, 3, TimeUnit.SECONDS);
            scheduler.schedule(new Runnable() {
                public void run() { beeperHandle.cancel(true); }
            }, 3, TimeUnit.SECONDS);
        }

       public void abort() {
           if(!scheduler.isTerminated()) {
               scheduler.shutdown();
           }
       }
    }

    interface ITelephony {

        boolean endCall();

        void answerRingingCall();

        void silenceRinger();

    }
}
