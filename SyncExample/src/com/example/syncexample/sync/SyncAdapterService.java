package com.example.syncexample.sync;

import com.example.syncexample.SyncAdapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncAdapterService extends Service {
 
	private static final Object sSyncAdapterLock = new Object();
	private static SyncAdapter sSyncAdapter;
	
	@Override
	public void onCreate() {
		synchronized (sSyncAdapterLock) {
			if (sSyncAdapter == null) {
				sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return sSyncAdapter.getSyncAdapterBinder();
	}

}
