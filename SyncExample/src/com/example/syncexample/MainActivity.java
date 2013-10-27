package com.example.syncexample;

import com.example.syncexample.sync.ClientesProviderContract;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {	

	private static final String ACCOUNT = "baas";
	private static final String ACCOUNT_TYPE = "com.example.syncexample.sync.baas";
	
	private AccountManager mAccountManager;
	private Account mAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
		
		mAccountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
		
		if (mAccountManager.addAccountExplicitly(mAccount, null, null)) {
			int a = 1;
			a += 1;
		} else {
			int b = 1;
			b += 1;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		
		ContentResolver.requestSync(mAccount, ClientesProviderContract.AUTHORITY, extras);
		
		int a = 1;
	}
	
}
