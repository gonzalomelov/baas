package com.example.syncexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uy.com.group05.baassdk.APIFacade;
import uy.com.group05.baassdk.SDKFactory;
import uy.com.group05.baassdk.impl.APIClientImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		mListView = (ListView)findViewById(R.id.listView1);
		
		boolean local = getIntent().getExtras().getBoolean("local");
		
		if (local) {
			
			String clientesJson = "";
			
			APIFacade apiClient = SDKFactory.getAPIFacade(this);
			try {
				clientesJson = apiClient.get("Cliente", "");	
			}
			catch (Exception e) {
				
			}
			
			JsonParser jsonParser = new JsonParser();
			JsonElement clientesJsonArray = jsonParser.parse(clientesJson); 
			
			List<String> list = new ArrayList<String>();
			
			if (clientesJsonArray instanceof JsonArray) {
				JsonArray jsonArray = (JsonArray) clientesJsonArray;
				for (int i=0; i < jsonArray.size(); i++) {
				    JsonObject jsonObject = (JsonObject) jsonArray.get(i);
					list.add(jsonObject.toString());
				}	
			}
			
			
			
			mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, list));			
	        
		}
	}
}
