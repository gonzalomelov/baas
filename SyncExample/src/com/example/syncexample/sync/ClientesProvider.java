package com.example.syncexample.sync;
 
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
 
public class ClientesProvider extends ContentProvider {
 
	private ClientesSqlHelper mClientesSqlHelper;
	
	private static final UriMatcher sUriMatcher;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(ClientesProviderContract.AUTHORITY, "clientes", ClientesProviderContract.CLIENTES);
		sUriMatcher.addURI(ClientesProviderContract.AUTHORITY, "clientes/#", ClientesProviderContract.CLIENTES_ID);
	}
	
	@Override
	public boolean onCreate() {
		
		mClientesSqlHelper = new ClientesSqlHelper(getContext(), ClientesProviderContract.BD_NAME, null, ClientesProviderContract.BD_VERSION);
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] columns, String selection,
			String[] selectionArgs, String orderBy) {
				
		String table;
		
		switch (sUriMatcher.match(uri)) {
		
			case ClientesProviderContract.CLIENTES:
				table = ClientesProviderContract.TABLA_CLIENTES;
				break;
				
			case ClientesProviderContract.CLIENTES_ID:
				table = ClientesProviderContract.TABLA_CLIENTES;
				selection = selection + "_ID = " + uri.getLastPathSegment();
				break;
			
			default:
				throw new IllegalArgumentException("QueryNotSupportedException: " + uri);
		}
		
		SQLiteDatabase db = mClientesSqlHelper.getReadableDatabase();
		
		String groupBy = null;
		String having = null;
		String limit = null;
		
		Cursor cursor = db.query(
				table,
				columns,
				selection,
				selectionArgs,
				groupBy,
				having,
				orderBy,
				limit);
		
		return cursor;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String table;
		
		switch (sUriMatcher.match(uri)) {
		
			case ClientesProviderContract.CLIENTES:
				table = ClientesProviderContract.TABLA_CLIENTES;
				break;
				
			case ClientesProviderContract.CLIENTES_ID:
				table = ClientesProviderContract.TABLA_CLIENTES;
				break;
			
			default:
				throw new IllegalArgumentException("QueryNotSupportedException: " + uri);
		}
		
		SQLiteDatabase db = mClientesSqlHelper.getReadableDatabase();
		
		long newRowId = db.insert(table, null, values);
		
		return ContentUris.withAppendedId(uri, newRowId);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		String type;
		
		switch (sUriMatcher.match(uri)) {
		
			case ClientesProviderContract.CLIENTES:
				type = "vnd.android.cursor.dir/vnd.com.example.syncexample.provider.clientes";
				break;
				
			case ClientesProviderContract.CLIENTES_ID:
				type = "vnd.android.cursor.item/vnd.com.example.syncexample.provider.clientes";
				break;
			
			default:
				throw new IllegalArgumentException("QueryNotSupportedException: " + uri);
		}
		
		return type;
	}

	public static final class Clientes implements BaseColumns {
		private Clientes() {}
		
		public static final String COL_NOMBRE = "nombre";
		public static final String COL_TELEFONO = "telefono";
		public static final String COL_EMAIL = "email";
		
	}

}
