package com.example.syncexample.sync;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	}
}