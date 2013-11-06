package com.example.syncexample;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.syncexample.sync.APIRestClient;
import com.google.gson.Gson;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
	
	private ContentResolver mContentResolver;
	 
	private Context context;
	
	public SyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		this.mContentResolver = context.getContentResolver();
		this.context = context;
	}
	
	public SyncAdapter(
			Context context,
			boolean autoInitialize,
			boolean allowParallelSyncs) {
		super(context, autoInitialize, allowParallelSyncs);
		mContentResolver = context.getContentResolver();
		this.context = context;
	}
	
	@Override
	public void onPerformSync(
			Account account,
			Bundle extras,
			String authority,
			ContentProviderClient provider,
			SyncResult syncResult) {
		
		try {
			MyApplication myApplication = (MyApplication)(getContext().getApplicationContext());
			Timestamp updatedAt = myApplication.getUpdatedAt();
			Log.e("TAG", updatedAt == null ? "primera vez" : myApplication.toString());
			String entity = extras.getString("entity");
			
			//Obtengo entidades modificados desde base local
			Cursor cursor = provider.query(Uri.parse("content://com.example.syncexample.sync.provider" + "/" + entity), null, null, null, null);
			
			List<String> localEntities = new ArrayList<String>();
			if (cursor != null) {
				while (cursor.moveToNext()) {
					//Si modifiqué el elemento local, entonces lo mando al servidor para mergear
					String strModifiedAt = cursor.getString(cursor.getColumnIndex("modifiedat"));
					Timestamp modifiedAt = Timestamp.valueOf(strModifiedAt);
					
					if (updatedAt == null && modifiedAt.after(updatedAt)) {
						String entityValue = cursor.getString(1);
						localEntities.add(entityValue);	
					}
				}
			}
			
			Log.e("TAG", "Pasa");
			
			//Sincronizo mis objetos con los del servidor y obtengo la nueva lista para dicha entidad
			Gson gson = new Gson();
			APIRestClient apiRestClient = new APIRestClient(getContext());
			String json = apiRestClient.sync(entity, gson.toJson(localEntities));
			
			Log.e("TAG", json);
			
			//Actualizar objetos de la entidad locales
			
		}
		catch (Exception e) {
			
		}
		
		
	} 
}
