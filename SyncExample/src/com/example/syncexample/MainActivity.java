package com.example.syncexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.sync.BaasProviderContract;
import uy.com.group05.baassdk.sync.BaasProviderObserver;
import uy.com.group05.baassdk.sync.Constants;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {	

	//Authenticator
	private AccountManager mAccountManager;
	private Account mAccount;
	
	//ContentProviderObserver
	private ContentResolver mResolver;
	public static final String SCHEME = "content://";
	private List<BaasProviderObserver> mObservers = new ArrayList<BaasProviderObserver>();
	
	//GCM
	private GoogleCloudMessaging gcm;
	private AtomicInteger msgId = new AtomicInteger();
    private SharedPreferences prefs;
	private String regid;
	private Context context;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = getApplicationContext();
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		if (checkPlayServices()) {
			
			gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
			
			mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
			mAccountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
			if (mAccountManager.addAccountExplicitly(mAccount, null, null)) {
				int a = 1;
				a += 1;
			} else {
				int b = 1;
				b += 1;
			}
			
	        //Register content observers for all tables
			mResolver = getContentResolver();
	        
			for (int i = 0; i < myApplication.getmTablesDB().size(); i++) {
				String entityDB = myApplication.getmTablesDB().get(i);
				
				BaasProviderObserver observer = new BaasProviderObserver(null);
		        mResolver.registerContentObserver(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entityDB), true, observer);
		        mObservers.add(observer);
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		for (BaasProviderObserver observer : mObservers) {
			mResolver.unregisterContentObserver(observer);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
 
	@Override
	protected void onResume() {
		super.onResume();
		checkPlayServices();
	}

	public void showLocalList(View view) {
		Log.i("SYNCEXAMPLE", "showLocalList");
		Intent i = new Intent(this, ListActivity.class);
		i.putExtra("local", true);
		startActivity(i);
	}
	
	public void addLocalClient(View view) {
		Log.i("SYNCEXAMPLE", "addLocalClient");
		Intent i = new Intent(this, AddActivity.class);
		i.putExtra("local", true);
		startActivity(i);
	}
	
	public void showRemoteList(View view) {
		Log.i("SYNCEXAMPLE", "showRemoteList");
		Intent i = new Intent(this, ListActivity.class);
		i.putExtra("local", false);
		startActivity(i);
	}
	
	public void addRemoteClient(View view) {
		Log.i("SYNCEXAMPLE", "addRemoteClient");
		Intent i = new Intent(this, AddActivity.class);
		i.putExtra("local", false);
		startActivity(i);
	}
	
	public void sync(View view) {
		Log.i("SYNCEXAMPLE", "sync");
		
		Bundle extras = new Bundle();
		extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
		extras.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
		
		ContentResolver.requestSync(mAccount, BaasProviderContract.AUTHORITY, extras);
	}
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	            		Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(Constants.PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    int registeredVersion = prefs.getInt(Constants.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        return "";
	    }
	    return registrationId;
	}
	
	private SharedPreferences getGCMPreferences(Context context) {
	    return getSharedPreferences(MainActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}
			
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
	            try {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(context);
	                }
	                regid = gcm.register(Constants.SENDER_ID);
	                msg = "Device registered, registration ID=" + regid;

	                sendRegistrationIdToBackend();

	                storeRegistrationId(context, regid);
	            } catch (IOException ex) {
	                msg = "Error :" + ex.getMessage();
	                // If there is an error, don't just keep trying to register.
	                // Require the user to click a button again, or perform
	                // exponential back-off.
	            }
	            return msg;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}

		}.execute(null, null, null);
	}
	
	private void sendRegistrationIdToBackend() {
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				
				return null;
			}
			
		}.execute(null, null, null);
	}
	
	private void storeRegistrationId(Context context, String regId) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i("Gonzalo", "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    Log.i("Gonzalo", "RegId: " + regId);
	    editor.putString(Constants.PROPERTY_REG_ID, regId);
	    editor.putInt(Constants.PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}
}
