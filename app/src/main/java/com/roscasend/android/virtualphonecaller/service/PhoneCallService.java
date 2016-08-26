package com.roscasend.android.virtualphonecaller.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PhoneCallService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_START_CALL = "com.roscasend.android.virtualphonecaller.service.action.CALL_PHONE";
    private static final String ACTION_END_CALL = "com.roscasend.android.virtualphonecaller.service.action.END_CALL";

    // TODO: Rename parameters
    private static final String EXTRA_CALL_NUMBER = "com.roscasend.android.virtualphonecaller.service.extra.CALL_NUMBER";
    private static final String TAG = PhoneCallService.class.getName();

    public PhoneCallService() {
        super("PhoneCallService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionCallNumber(Context context, String callNUmber) {
        Intent intent = new Intent(context, PhoneCallService.class);
        intent.setAction(ACTION_START_CALL);
        intent.putExtra(EXTRA_CALL_NUMBER, callNUmber);
        context.startService(intent);

    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionEndCall(Context context, String callNumber) {
        Intent intent = new Intent(context, PhoneCallService.class);
        intent.setAction(ACTION_END_CALL);
        intent.putExtra(EXTRA_CALL_NUMBER, callNumber);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_CALL.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_CALL_NUMBER);
                handleActionCallNumber(param1);
            } else if (ACTION_END_CALL.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_CALL_NUMBER);
                handleActionEndCall(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionCallNumber(String phoneNumber) {
        try {
            String uri = "tel:" + phoneNumber;
            final Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(uri));
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(callIntent);
        } catch (SecurityException e) {
            Toast.makeText(this, "Nu am primit permisiune de la dv ca sa dau telefoane de pe acest dispozititv.", Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionEndCall(String param1) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
