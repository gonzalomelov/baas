package com.example.syncexample;

import java.util.ArrayList;
import java.util.List;

import com.example.syncexample.sync.APIRestClient;
import com.example.syncexample.sync.BaasProviderContract;
import com.example.syncexample.sync.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends Activity {

	MyApplication myApplication;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_first);
		super.onCreate(savedInstanceState);
		
		myApplication = (MyApplication)getApplicationContext();
		
		myApplication.getmTablesDB().add("E1");
		myApplication.getmTablesDB().add("E2");
		myApplication.getmTablesDB().add("E3");
		myApplication.getmTablesDB().add("Cliente");
		
		myApplication.setmUriMatcher(new UriMatcher(UriMatcher.NO_MATCH));
		
		for (int i = 0; i < myApplication.getmTablesDB().size(); i++) {
			String entityDB = myApplication.getmTablesDB().get(i);
			myApplication.getmUriMatcher().addURI(BaasProviderContract.AUTHORITY, entityDB, i*2);
			myApplication.getmUriMatcher().addURI(BaasProviderContract.AUTHORITY, entityDB + "/#", i*2+1);
		}
	}

	public void main(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
