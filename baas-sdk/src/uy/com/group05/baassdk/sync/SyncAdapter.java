package uy.com.group05.baassdk.sync;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import uy.com.group05.baassdk.MyApplication;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
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
			Log.e("TAG", "EPA");
			MyApplication myApplication = (MyApplication)(getContext().getApplicationContext());
			Timestamp updatedAt = myApplication.getUpdatedAt();
			
			String entity = extras.getString("entity");
			
			Log.e("TAG", entity);
			
			//Obtengo entidades modificados desde base local
			Cursor cursor = provider.query(Uri.parse("content://uy.com.group05.baassdk.sync.provider" + "/" + entity), null, null, null, null);
			
			Log.e("TAG", "query");
			
			List<String> localEntities = new ArrayList<String>();
			if (cursor != null) {
				while (cursor.moveToNext()) {
					//Si modifiqué el elemento local, entonces lo mando al servidor para mergear

					String entityValue = cursor.getString(cursor.getColumnIndex("entity"));
					JsonParser jsonParser = new JsonParser();
					JsonObject jsonObject = (JsonObject) jsonParser.parse(entityValue);
					
					Log.e("TAG", "jsonObject");
					
					if (updatedAt == null ||
						jsonObject.get("updatedat") == null ||
						Timestamp.valueOf(jsonObject.get("updatedat").getAsString()).after(updatedAt)) {
						
						localEntities.add(jsonObject.toString());
						Log.e("TAG", "Update: " + jsonObject.toString());
					}
				}
			}
			
			//Sincronizo mis objetos con los del servidor y obtengo la nueva lista para dicha entidad
			Gson gson = new Gson();
			APIRestClient apiRestClient = new APIRestClient(getContext());
			String remoteStringEntities = apiRestClient.sync(entity, gson.toJson(localEntities));

			JsonParser jsonParser = new JsonParser();
			JsonArray remoteEntities = (JsonArray) jsonParser.parse(remoteStringEntities); 

			provider.delete(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null, null);
			
			Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
			
			for (int i = 0; i < remoteEntities.size(); i++) {
				JsonObject jsonObj = (JsonObject) remoteEntities.get(i);
				
				JsonObject remoteObjectId = (JsonObject) jsonObj.get("_id");
				String syncId = remoteObjectId.get("$oid").getAsString();
				jsonObj.remove("_id");
				
				jsonObj.add("syncid", jsonParser.parse(syncId));
				
				ContentValues values = new ContentValues();
				Log.e("TAG", "entity: " + jsonObj.toString());
				values.put("entity", jsonObj.toString());
				
				Log.e("TAG", "after insert");
				provider.insert(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), values);
				Log.e("TAG", "after insert");
			}
			
			myApplication.setUpdatedAt(newTimestamp);
			
			Log.e("TAG", "newTimestamp: " + newTimestamp.toString());
			
			
		}
		catch (Exception e) {
			
		}
		
		
	} 
}
