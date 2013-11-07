package com.example.syncexample.sync;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
 
public class BaasContentProvider extends ContentProvider {
 
	private BaasSqlHelper mBaasSqlHelper;
	
	private static final UriMatcher sUriMatcher;
	
	private static List<String> mTablesDB;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		//Llamar a servicio
		
		//Prueba
		mTablesDB = new ArrayList<String>();
		mTablesDB.add("Cliente");
		//Fin Prueba
		
		for (int i = 0; i < mTablesDB.size(); i++) {
			String entityDB = mTablesDB.get(i);
			sUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB, i*2);
			sUriMatcher.addURI(BaasProviderContract.AUTHORITY, entityDB + "/#", i*2+1);
		}
		
	}
	
	@Override
	public boolean onCreate() {
		
		mBaasSqlHelper = new BaasSqlHelper(getContext(), BaasProviderContract.BD_NAME, null, BaasProviderContract.BD_VERSION);
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] columns, String selection,
			String[] selectionArgs, String orderBy) {
		
		int matchUri = sUriMatcher.match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("QueryNotSupportedException: " + uri);
		}
		
		String table = mTablesDB.get(matchUri);
		if (matchUri % 2 != 0) {
			selection = selection + "_ID = " + uri.getLastPathSegment();
		}
		
		SQLiteDatabase db = mBaasSqlHelper.getReadableDatabase();
		
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
		
		int matchUri = sUriMatcher.match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("InsertNotSupportedException: " + uri);
		}
		
		String table = mTablesDB.get(matchUri);
		
		SQLiteDatabase db = mBaasSqlHelper.getWritableDatabase();
		
		//Valor de modificacion para syncronización
		values.put("modifiedat", new Timestamp(System.currentTimeMillis()).toString());
		
		long newRowId = db.insert(table, null, values);
		
//		this.getContext().getContentResolver().notifyChange(Uri.parse("content://com.example.syncexample.sync.provider/" + table), null);
		
		return ContentUris.withAppendedId(uri, newRowId);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		throw new UnsupportedOperationException();
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int matchUri = sUriMatcher.match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("InsertNotSupportedException: " + uri);
		}
		
		String table = mTablesDB.get(matchUri);
		
		SQLiteDatabase db = mBaasSqlHelper.getWritableDatabase();
		
		return db.delete(table, null, null);
	}

	@Override
	public String getType(Uri uri) {
		
		String type;
		
		int matchUri = sUriMatcher.match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("GetTypeNotSupportedException: " + uri);
		}
		
		String table = mTablesDB.get(matchUri);
		
		if (matchUri % 2 == 0) {
			type = "vnd.android.cursor.dir/vnd.com.example.syncexample.provider" + "." + table;
		} else {
			type = "vnd.android.cursor.item/vnd.com.example.syncexample.provider" + "." + table;	
		}
		
		return type;
	}

//	public static final class GenericTable implements BaseColumns {
//		private GenericTable() {}
//		
//		public static final String COL_ENTIDAD = "entity";
//		public static final String COL_SYNCID = "syncid";
//		public static final String COL_MODIFIEDAT = "modifiedat";
//		
//	}

}
