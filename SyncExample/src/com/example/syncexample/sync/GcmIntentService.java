package com.example.syncexample.sync;

import java.io.IOException;

import com.example.syncexample.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.R.anim;
import android.accounts.Account;
import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                
            	//Algo?
            	
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
            	
            	//Algo?
            
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                
            	Account mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
            	
            	Bundle bundle = new Bundle();
            	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
            	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
            	bundle.putString("entity", extras.getString("entity"));
        		
        		ContentResolver.requestSync(mAccount, BaasProviderContract.AUTHORITY, bundle);
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}