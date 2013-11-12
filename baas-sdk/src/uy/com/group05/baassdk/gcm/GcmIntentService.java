package uy.com.group05.baassdk.gcm;

import java.io.IOException;

import uy.com.group05.baassdk.R;
import uy.com.group05.baassdk.sync.BaasProviderContract;
import uy.com.group05.baassdk.sync.Constants;

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
                
            	String type = extras.getString("type");
            	
            	if (type.equals("notification")) {
            		sendNotification(extras.getString("message"));
            	} else if (type.equals("sync")){
            		Account mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
                	
                	Bundle bundle = new Bundle();
                	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                	bundle.putString("entity", extras.getString("entity"));
            		
            		ContentResolver.requestSync(mAccount, BaasProviderContract.AUTHORITY, bundle);	
            	}	
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, SuscribirCanalPushActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_logo) 
        .setContentTitle("Trueques")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg)
        .setAutoCancel(true);

//        mBuilder.setContentIntent(contentIntent);
        
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}