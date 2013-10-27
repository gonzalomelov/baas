package com.example.syncexample.sync;

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
import android.os.Bundle;

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
			
			//Para saber si un cambio en el Content Provider ejecuto el onPerformSync
			//ContentResolver.SYNC_EXTRAS_UPLOAD
			
			APIRestClient apiRestClient = new APIRestClient(getContext());
			
			//Obtengo clientes desde servidor remoto
			String json = apiRestClient.get("Cliente");
			Gson gson = new Gson();
			Cliente[] clientesArray = gson.fromJson(json, Cliente[].class);
			List<Cliente> remoteClients = clientesArray != null && clientesArray.length > 0 ? Arrays.asList(clientesArray) : new ArrayList<Cliente>();
			
			//Obtengo clientes desde base local
			Cursor cursor = provider.query(ClientesProviderContract.CLIENTES_URI, null, null, null, null);
			List<Cliente> localClients = new ArrayList<Cliente>();
			if (cursor != null) {
				while (cursor.moveToNext()) {
					Cliente cliente = new Cliente();
					
					cliente.setNombre(cursor.getString(0));
					cliente.setTelefono(cursor.getString(1));
					cliente.setEmail(cursor.getString(2));
					
					localClients.add(cliente);
				}
			}
			
			//Ver qué clientes tengo local y no remoto
			
			//Ver qué clientes tengo remoto y no local
			
			//Actualizar clientes remotos
			
			//Actualizar clientes locales
			
		}
		catch (Exception e) {
			
		}
		
		
	} 
}
