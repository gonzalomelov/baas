package com.example.syncexample.sync;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.syncexample.MyApplication;
import com.example.syncexample.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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
			
			String entity = extras.getString("entity");
			
			//Obtengo entidades modificados desde base local
			Cursor cursor = provider.query(Uri.parse("content://com.example.syncexample.sync.provider" + "/" + entity), null, null, null, null);
			
			List<String> localEntities = new ArrayList<String>();
			if (cursor != null) {
				while (cursor.moveToNext()) {
					//Si modifiqué el elemento local, entonces lo mando al servidor para mergear
					int modifiedAtIndex = cursor.getColumnIndex("modifiedat");
					String strModifiedAt = cursor.getString(modifiedAtIndex);
					Timestamp modifiedAt = Timestamp.valueOf(strModifiedAt);
					
					if (updatedAt == null || modifiedAt.after(updatedAt)) {
						int entityIndex = cursor.getColumnIndex("entity");
						int syncidIndex = cursor.getColumnIndex("syncid");
						
						String entityValue = cursor.getString(entityIndex);
						String syncid = cursor.getString(syncidIndex);
						
						JsonParser jsonParser = new JsonParser();
						JsonObject jsonObject = (JsonObject) jsonParser.parse(entityValue);
						
						if (syncid != null && !syncid.isEmpty()) {
							jsonObject.addProperty("syncid", syncid);	
						}
						
						localEntities.add(jsonObject.toString());
					}
				}
			}
			
			//Sincronizo mis objetos con los del servidor y obtengo la nueva lista para dicha entidad
			Gson gson = new Gson();
			APIRestClient apiRestClient = new APIRestClient(getContext());
			String remoteStringEntities = apiRestClient.sync(entity, gson.toJson(localEntities));

			JsonParser jsonParser = new JsonParser();
			JsonArray remoteEntities = (JsonArray) jsonParser.parse(remoteStringEntities); 

			provider.delete(Uri.parse("content://com.example.syncexample.sync.provider/" + entity), null, null);
			
			Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
			
			for (int i = 0; i < remoteEntities.size(); i++) {
				
				JsonObject jsonObj = (JsonObject) remoteEntities.get(i);
				JsonObject remoteObjectId = (JsonObject) jsonObj.get("_id");
				
				String syncId = remoteObjectId.get("$oid").getAsString();

				jsonObj.remove("_id");
				
				ContentValues values = new ContentValues();
				Log.e("TAG", "entity: " + jsonObj.toString());
				values.put("entity", jsonObj.toString());
				values.put("syncid", syncId);
				values.put("modifiedat", newTimestamp.toString());
				
				Log.e("TAG", "after insert");
				provider.insert(Uri.parse("content://com.example.syncexample.sync.provider/" + entity), values);
				Log.e("TAG", "after insert");
			}
			
			myApplication.setUpdatedAt(newTimestamp);
			
			Log.e("TAG", "newTimestamp: " + newTimestamp.toString());
			
			
		}
		catch (Exception e) {
			
		}
		
		
	} 
}
