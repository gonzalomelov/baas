package com.example.syncexample.sync;

import com.example.syncexample.Constants;

import android.accounts.Account;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class BaasProviderObserver extends ContentObserver {

	public BaasProviderObserver(Handler handler) {
		super(handler);
	}

	@Override
	public void onChange(boolean selfChange, Uri uri) {
		Account mAccount = new Account(Constants.ACCOUNT, Constants.ACCOUNT_TYPE);
		
		Bundle extras = new Bundle();
		extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
		extras.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
		extras.putString("entity", "Cliente");
		
		ContentResolver.requestSync(mAccount, BaasProviderContract.AUTHORITY, extras);
	}

	@Override
	public void onChange(boolean selfChange) {
		onChange(selfChange, null);
	}

	
}
