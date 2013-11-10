package uy.com.group05.baassdk.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baassdk.APIFacade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class APIClientImpl implements APIFacade {
	private Context context;
	
	public APIClientImpl(Context context) {
		this.context = context;
	}
	
	@Override
	public String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		Cursor entities = context.getContentResolver().query(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null, null, null, null);
		
		JsonArray jsonArray = new JsonArray();
		
		Gson gson = new Gson();
		
		while (entities.moveToNext()) {
			String entityValue = entities.getString(1);
			
			JsonElement jsonElement = gson.fromJson(entityValue, JsonElement.class);
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			jsonArray.add(jsonObject);
		}
		
		return jsonArray.toString();
	}
	
	@Override
	public boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ContentValues values = new ContentValues();
		values.put("entity", json);
		
		Uri result = context.getContentResolver().insert(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), values);
		
		context.getContentResolver().notifyChange(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null);
		
		return true;
	}
}
