package uy.com.group05.baassdk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uy.com.group05.baassdk.sync.BaasProviderContract;

import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.util.Log;

public class MyApplication extends Application {

	private Timestamp updatedAt = null;

    private UriMatcher mUriMatcher;
    
    private List<String> mTablesDB = new ArrayList<String>();
    
    private boolean initialized = false;
    
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

	public void init(List<String> entities) {
		if (!initialized) {
			mTablesDB = entities;

			mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
			
			for (int i = 0; i < mTablesDB.size(); i++) {
				String entityDB = mTablesDB.get(i);
				mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB, i*2);
				mUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB + "/#", i*2+1);
			}
			
			initialized = true;
		}
	}
}