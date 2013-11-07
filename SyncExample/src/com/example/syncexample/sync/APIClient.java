package com.example.syncexample.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class APIClient {
	private Context context;
	
	public APIClient(Context context) {
		this.context = context;
	}
	
	public String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		Cursor entities = context.getContentResolver().query(Uri.parse("content://com.example.syncexample.sync.provider/" + entity), null, null, null, null);
		
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
	
	public boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ContentValues values = new ContentValues();
		values.put("entity", json);
		
		Uri result = context.getContentResolver().insert(Uri.parse("content://com.example.syncexample.sync.provider/" + entity), values);
		
		context.getContentResolver().notifyChange(Uri.parse("content://com.example.syncexample.sync.provider/" + entity), null);
		
		return true;
	}
}
