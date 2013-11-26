package uy.trueques_beta.gcm;

import java.io.IOException;
import java.util.Date;

import uy.com.group05.baassdk.R;
import uy.com.group05.baassdk.sync.BaasProviderContract;
import uy.com.group05.baassdk.sync.Constants;
import uy.trueques_beta.activity.Home;

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
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService {
    public long NOTIFICATION_ID;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    private SharedPreferences.Editor editor;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        
        String messageType = gcm.getMessageType(intent);
        
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.editor = prefs.edit();

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
            	String diferenciador = extras.getString("dif");
            	
            	Log.i("GCM SDK", "Mensaje recibido: " + extras.toString());
            	
            	if (type.equals("notification")) {
            		if (diferenciador == null || ((diferenciador.equals("ofertaNueva") && prefs.getBoolean("notifOfertasNuevas", false))
            			|| (diferenciador.equals("ofertaAceptada") && prefs.getBoolean("notifOfertasAceptadas", false))
            			|| (diferenciador.equals("ofertaRechazada") && prefs.getBoolean("notifOfertasRechazada", false)))) {
            			
            					Log.i("GCM SDK", "Se muestra la notificación.");
            					sendNotification(extras.getString("message"));
            					
            		}
            		else
            			Log.i("GCM SDK", "No se muestra la notificación.");
            		
            	} else if (type.equals("sync")){
            		Account mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
                	
                	Bundle bundle = new Bundle();
                	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                	bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                	
                	String entity = extras.getString("entity");
                	Log.e("SYNCHRONIZATIONENTITY", "Entidad: " + entity);
                	
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

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, Home.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_logo) 
        .setContentTitle("Trueques")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg)
        .setAutoCancel(true);

        mBuilder.setContentIntent(contentIntent);
        
        Date date = new Date();
        NOTIFICATION_ID = date.getTime();
        
        mNotificationManager.notify((int) NOTIFICATION_ID, mBuilder.build());
    }

}