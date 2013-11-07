package com.example.syncexample;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.syncexample.sync.APIRestClient;
import com.example.syncexample.sync.BaasProviderContract;
import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.content.UriMatcher;
import android.os.AsyncTask;

public class MyApplication extends Application {

	private Timestamp updatedAt = null;

    private UriMatcher mUriMatcher;
    
    private List<String> mTablesDB = new ArrayList<String>();
    
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

	@Override
	public void onCreate() {		
		super.onCreate();
		
		
		
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		for (int i = 0; i < mTablesDB.size(); i++) {
			String entityDB = mTablesDB.get(i);
			mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB, i*2);
			mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB + "/#", i*2+1);
		}
	}
	
	private class GetEntitiesAsyncTask extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(Void... params) {
			
			List<String> entitiesNames = new ArrayList<String>();
			
			APIRestClient apiRestClient  = new APIRestClient(getApplicationContext());
			
			try {
				entitiesNames = apiRestClient.getEntities();	  
			}
			catch (Exception e) {
				
			}
			
			return entitiesNames;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			
			mTablesDB = result;
		}
		
		
		
	}
	
}