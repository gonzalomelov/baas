package uy.com.group05.baassdk.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baassdk.APIFacade;
import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.sync.APIRestClient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class APIClientImpl implements APIFacade {
	private Context context;
	
	public APIClientImpl(Context context) {
		this.context = context;
	}
	
	@Override
	public String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		if (myApplication.getmTablesDB().contains(entity)) {
			Cursor entities = context.getContentResolver().query(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null, null, null, null);
			
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonQuery = (JsonObject)jsonParser.parse(query);
			
			String cmpType = ""; 
			String cmpValue = "";
			
			for (Map.Entry<String, JsonElement> entry : jsonQuery.entrySet()) {
				cmpType = entry.getKey();
				cmpValue = entry.getValue().getAsString();
			}
			
			Gson gson = new Gson();
			
			JsonArray jsonArray = new JsonArray();
			
			for (boolean hasItem = entities.moveToFirst();  hasItem;  hasItem = entities.moveToNext()) {
				String entityValue = entities.getString(1);
				
				JsonElement jsonElement = gson.fromJson(entityValue, JsonElement.class);
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				if (cmpType.isEmpty()) {
					jsonObject.remove("updatedat");
					jsonObject.remove("syncid");
					
					jsonArray.add(jsonObject);
					
				} else if (cmpType.equals("objeto.duenio")) {
					//Veo si es de algun hijo
					JsonObject jsonObjectChild = (JsonObject)jsonObject.get("objeto");
					
					if (jsonObjectChild.has("duenio") && jsonObjectChild.get("duenio").getAsString().equals(cmpValue)) {
						jsonArray.add(jsonObject);	
					}
					
				} else if (jsonObject.has(cmpType) && jsonObject.get(cmpType).getAsString().equals(cmpValue)) {
					jsonObject.remove("updatedat");
					jsonObject.remove("syncid");
					
					jsonArray.add(jsonObject);
				}
			}
			
			return jsonArray.toString();
		} else { //if (myApplication.getmTablesREST().contains(entity)) {
			
			APIRestClient apiRestClient = new APIRestClient(context);
			String remoteStringEntities = apiRestClient.get(entity, query);

			return remoteStringEntities; 
		}
	}
	
	@Override
	public boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		if (myApplication.getmTablesDB().contains(entity)) {
			ContentValues values = new ContentValues();
			values.put("entity", json);
			
			Uri result = context.getContentResolver().insert(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), values);
			
			context.getContentResolver().notifyChange(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null);
			
			return true;	
		} else { //if (myApplication.getmTablesREST().contains(entity)) {
			APIRestClient apiRestClient = new APIRestClient(context);
			
			return apiRestClient.post(entity, json);
			
		}	
	}
	
	@Override
	public boolean update(String entity, String query, String values)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		if (myApplication.getmTablesDB().contains(entity)) {
			Cursor entities = context.getContentResolver().query(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null, null, null, null);
			
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonQuery = (JsonObject)jsonParser.parse(query);
			JsonObject jsonValues = (JsonObject)jsonParser.parse(values);
			
			String cmpType = ""; 
			String cmpValue = "";
			
			for (Map.Entry<String, JsonElement> entry : jsonQuery.entrySet()) {
				cmpType = entry.getKey();
				cmpValue = entry.getValue().getAsString();
			}
			
			Gson gson = new Gson();
			
			for (boolean hasItem = entities.moveToFirst();  hasItem;  hasItem = entities.moveToNext()) {
				String entityValue = entities.getString(1);
				
				JsonElement jsonElement = gson.fromJson(entityValue, JsonElement.class);
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				if (cmpType.isEmpty() || jsonObject.has(cmpType) && jsonObject.get(cmpType).getAsString().equals(cmpValue)) {

					for (Map.Entry<String, JsonElement> entry : jsonValues.entrySet()) {
						String key = entry.getKey();
						JsonElement value = entry.getValue();
						
						jsonObject.add(key, value);
					}
					
//					JsonPrimitive timestamp = new JsonPrimitive(new Timestamp(System.currentTimeMillis()).toString());
//					jsonObject.add("updatedat", timestamp);
					
					ContentValues contentValues = new ContentValues();
					contentValues.put("entity", jsonObject.toString());
					
					String where = "_id = " + entities.getString(entities.getColumnIndex("_id"));
					
					context.getContentResolver().update(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), contentValues, where, null);
				}
			}
			
			context.getContentResolver().notifyChange(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null);
			
			return true;
		} else { //if (myApplication.getmTablesREST().contains(entity)) {
			APIRestClient apiRestClient = new APIRestClient(context);
			
			return apiRestClient.put(entity, values, query);		
		}
	}
	
	
	
	public boolean delete(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		if (myApplication.getmTablesDB().contains(entity)) {
			Cursor entities = context.getContentResolver().query(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null, null, null, null);
			
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonQuery = (JsonObject)jsonParser.parse(query);
			
			String cmpType = ""; 
			String cmpValue = "";
			
			for (Map.Entry<String, JsonElement> entry : jsonQuery.entrySet()) {
				cmpType = entry.getKey();
				cmpValue = entry.getValue().getAsString();
			}
			
			Gson gson = new Gson();
			
			for (boolean hasItem = entities.moveToFirst();  hasItem;  hasItem = entities.moveToNext()) {
				String entityValue = entities.getString(1);
				
				JsonElement jsonElement = gson.fromJson(entityValue, JsonElement.class);
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				if (cmpType.isEmpty() || jsonObject.has(cmpType) && jsonObject.get(cmpType).getAsString().equals(cmpValue)) {
					
					JsonPrimitive timestamp = new JsonPrimitive(new Timestamp(System.currentTimeMillis()).toString());
					jsonObject.add("updatedat", timestamp);
					
					JsonPrimitive delete = new JsonPrimitive(true);
					jsonObject.add("delete", delete);
					
					ContentValues contentValues = new ContentValues();
					contentValues.put("entity", jsonObject.toString());
					
					String where = "_id = " + entities.getString(entities.getColumnIndex("_id"));
					
					context.getContentResolver().update(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), contentValues, where, null);
				}
			}
			
			context.getContentResolver().notifyChange(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null);
			
			return true;
		} else { //if (myApplication.getmTablesREST().contains(entity)) {
			APIRestClient apiRestClient = new APIRestClient(context);
			
			return apiRestClient.delete(entity, query);
		}	
	}
	
	public boolean updateAll(List<String> entities)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		for (String entity : entities) {
			context.getContentResolver().notifyChange(Uri.parse("content://uy.com.group05.baassdk.sync.provider/" + entity), null);	
		}
		
		return true;
	}
}
