package uy.com.group05.baassdk.sync;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uy.com.group05.baassdk.MyApplication;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.util.Log;
 
public class BaasContentProvider extends ContentProvider {
 
	private BaasSqlHelper mBaasSqlHelper;
	
	private MyApplication myApplication;
	
	@Override
	public boolean onCreate() {
		
		mBaasSqlHelper = new BaasSqlHelper(getContext(), BaasProviderContract.BD_NAME, null, BaasProviderContract.BD_VERSION);
		
		myApplication = (MyApplication)(getContext().getApplicationContext());
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] columns, String selection,
			String[] selectionArgs, String orderBy) {
		
		int matchUri = myApplication.getmUriMatcher().match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("QueryNotSupportedException: " + uri);
		}
		
		int tableIndex = (int)Math.floor(matchUri/2);
		String table = myApplication.getmTablesDB().get(tableIndex);
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
		
		while (cursor.moveToNext()) {
			Log.i("BaasContentProvider", "##### Entity ######");
			Log.i("BaasContentProvider", "_id: " + cursor.getString(0));
			Log.i("BaasContentProvider", "entity: " + cursor.getString(1));
		}
		
		return cursor;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		int matchUri = myApplication.getmUriMatcher().match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("InsertNotSupportedException: " + uri);
		}
		
		int tableIndex = (int)Math.floor(matchUri/2);
		String table = myApplication.getmTablesDB().get(tableIndex);
		
		SQLiteDatabase db = mBaasSqlHelper.getWritableDatabase();
		
		long newRowId = db.insert(table, null, values);
		
		return ContentUris.withAppendedId(uri, newRowId);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int matchUri = myApplication.getmUriMatcher().match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("InsertNotSupportedException: " + uri);
		}
		
		int tableIndex = (int)Math.floor(matchUri/2);
		String table = myApplication.getmTablesDB().get(tableIndex);
		
		SQLiteDatabase db = mBaasSqlHelper.getWritableDatabase();
		
		int rowsAffected = db.update(table, values, selection, selectionArgs);
		
		return rowsAffected;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int matchUri = myApplication.getmUriMatcher().match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("InsertNotSupportedException: " + uri);
		}
		
		int tableIndex = (int)Math.floor(matchUri/2);
		String table = myApplication.getmTablesDB().get(tableIndex);
		
		SQLiteDatabase db = mBaasSqlHelper.getWritableDatabase();
		
		return db.delete(table, null, null);
	}

	@Override
	public String getType(Uri uri) {
		
		String type;
		
		int matchUri = myApplication.getmUriMatcher().match(uri);
		
		if (matchUri == -1) {
			throw new IllegalArgumentException("GetTypeNotSupportedException: " + uri);
		}
		
		int tableIndex = (int)Math.floor(matchUri/2);
		String table = myApplication.getmTablesDB().get(tableIndex);
		
		if (matchUri % 2 == 0) {
			type = "vnd.android.cursor.dir/vnd.com.example.syncexample.provider" + "." + table;
		} else {
			type = "vnd.android.cursor.item/vnd.com.example.syncexample.provider" + "." + table;	
		}
		
		return type;
	}

}
