package uy.com.group05.baassdk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uy.com.group05.baassdk.sync.BaasProviderContract;
import uy.com.group05.baassdk.sync.BaasProviderObserver;
import uy.com.group05.baassdk.sync.Constants;

import com.google.gson.Gson;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class MyApplication extends Application {

	private Timestamp updatedAt = null;
    private UriMatcher mUriMatcher;
    private List<String> mTablesDB = new ArrayList<String>();
    private List<String> mTablesREST = new ArrayList<String>();
    private boolean initialized = false;
    
    //Authenticator
  	private AccountManager mAccountManager;
  	private Account mAccount;
  	//ContentProviderObserver
  	private ContentResolver mResolver;
    
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
 
	public UriMatcher getmUriMatcher() {
		return mUriMatcher;
	}

	public void setmUriMatcher(UriMatcher mUriMatcher) {
		this.mUriMatcher = mUriMatcher;
	}

	public List<String> getmTablesDB() {
		return mTablesDB;
	}

	public void setmTablesDB(List<String> mTablesDB) {
		this.mTablesDB = mTablesDB;
	}

	public List<String> getmTablesREST() {
		return mTablesREST;
	}

	public void setmTablesREST(List<String> mTablesREST) {
		this.mTablesREST = mTablesREST;
	}

	public void init(Activity activity, List<String> syncEntities, List<String> commonEntities) {
		if (!initialized) {
			mTablesDB = syncEntities;
			mTablesREST = commonEntities;

			if (!mTablesDB.isEmpty()) {
				//Inicializo las URIS de las tablas
				mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
				
				for (int i = 0; i < mTablesDB.size(); i++) {
					String entityDB = mTablesDB.get(i);
					mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB, i*2);
					mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB + "/#", i*2+1);
				}
				
				//Inicializo el Sync Adapter
				mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
				mAccountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
				
				mAccountManager.addAccountExplicitly(mAccount, null, null);
				
		        //Register content observers for all tables
				mResolver = getContentResolver();
		        
				//Registro los observers
				for (int i = 0; i < mTablesDB.size(); i++) {
					String entityDB = mTablesDB.get(i);
					
					BaasProviderObserver observer = new BaasProviderObserver(null);
			        mResolver.registerContentObserver(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entityDB), true, observer);
				}	
			}
			
			initialized = true;
		}
	}
}