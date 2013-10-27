package com.example.syncexample.sync;

import android.net.Uri;
 
public class ClientesProviderContract {
	public static final String BD_NAME = "DBClientes";
	public static final int BD_VERSION = 1;
	public static final String TABLA_CLIENTES = "clientes";
	
	public static final String AUTHORITY = "com.example.syncexample.sync.provider";
	public static final String CLIENTES_URI_STRING = "content://com.example.syncexample.sync.provider/clientes";
	public static final int CLIENTES = 1;
	public static final int CLIENTES_ID = 2;
	
	public static final Uri CLIENTES_URI = Uri.parse(ClientesProviderContract.CLIENTES_URI_STRING);
}
