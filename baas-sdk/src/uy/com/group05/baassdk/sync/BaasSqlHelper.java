package uy.com.group05.baassdk.sync;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baassdk.MyApplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaasSqlHelper extends SQLiteOpenHelper {
 
	private Context context;
	
	public BaasSqlHelper(
			Context context,
			String dbName,
			CursorFactory cursorFactory,
			int version) {
		
		super(context, dbName, cursorFactory, version);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		MyApplication myApplication = (MyApplication)(this.context.getApplicationContext());
		List<String> mTablesDB = myApplication.getmTablesDB();
		
		for (String entity : mTablesDB) {
			db.execSQL(
					"CREATE TABLE " + entity + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"entity TEXT, " +
					"syncid TEXT, " +
					"modifiedat TEXT)");
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MyApplication myApplication = (MyApplication)(this.context.getApplicationContext());
		List<String> mTablesDB = myApplication.getmTablesDB();
		
		for (String entity : mTablesDB) {
			db.execSQL("DROP TABLE IF EXISTS " + entity);
			
			db.execSQL(
					"CREATE TABLE " + entity + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"entity TEXT, " +
					"syncid TEXT, " +
					"modifiedat TEXT)");
		}
	}

}
