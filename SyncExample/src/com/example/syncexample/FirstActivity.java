package com.example.syncexample;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.sync.BaasProviderContract;

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
		
		List<String> entities = new ArrayList<String>();
		entities.add("E1");
		entities.add("E2");
		entities.add("E3");
		entities.add("Cliente");
		
		myApplication.init(entities);
	}

	public void main(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
