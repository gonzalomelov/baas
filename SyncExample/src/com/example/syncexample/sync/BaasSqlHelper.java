package com.example.syncexample.sync;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaasSqlHelper extends SQLiteOpenHelper {
 
	private List<String> mTablesDB;
	
	public BaasSqlHelper(
			Context context,
			String dbName,
			CursorFactory cursorFactory,
			int version) {
		
		super(context, dbName, cursorFactory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		mTablesDB = new ArrayList<String>();
		mTablesDB.add("cliente");
		
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
		mTablesDB = new ArrayList<String>();
		mTablesDB.add("cliente");
		
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
