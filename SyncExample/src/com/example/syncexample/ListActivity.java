package com.example.syncexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.syncexample.sync.APIRestClient;
import com.example.syncexample.sync.Cliente;
import com.example.syncexample.sync.ClientesProviderContract;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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
			Cursor clientes = getContentResolver().query(ClientesProviderContract.CLIENTES_URI, null, null, null, null);
			
			List<String> clientesStr = new ArrayList<String>();
			
			while (clientes.moveToNext()) {
				String nombre = clientes.getString(1);
				String telefono = clientes.getString(2);
				String email = clientes.getString(3);
				
				clientesStr.add(nombre + " - " + telefono + " - " + email);
			}
			
			mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, clientesStr));			
	        
		} else {
			new ListAsync(this).execute();
		}
	}
	
	private class ListAsync extends AsyncTask<String, Void, List<Cliente>> {

		private Context mContext;
		
		public ListAsync(Context context) {
			mContext = context.getApplicationContext();
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected List<Cliente> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			APIRestClient apiRestClient  = new APIRestClient(getApplicationContext());
			
			try {
				String json = apiRestClient.get("Cliente");
				Gson gson = new Gson();
				Cliente[] clientsArray = gson.fromJson(json, Cliente[].class);
				return clientsArray.length == 0 ? new ArrayList<Cliente>() : Arrays.asList(clientsArray);  
			}
			catch (Exception e) {
				
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(List<Cliente> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			List<String> clientesStr = new ArrayList<String>();
			
			if (result != null) {
				
				for (Cliente c : result) {
					String nombre = c.getNombre();
					String telefono = c.getTelefono();
					String email = c.getEmail();
					
					clientesStr.add(nombre + " - " + telefono + " - " + email + " - ");
				}	
			}
			
			
			mListView.setAdapter(new ArrayAdapter<String>(mContext, R.layout.list_item, clientesStr));
		}
		 
	} 

}
