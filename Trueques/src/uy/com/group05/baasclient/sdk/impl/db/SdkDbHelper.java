package uy.com.group05.baasclient.sdk.impl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SdkDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "BaaSSdk.db";
	
	public SdkDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		String createTablesSql =
				"create table Trueque (" +
				"id integer primary key," +
				"a text," +
				"b text" +
				")";
		
		db.execSQL(createTablesSql);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String deleteTablesSql =
				"drop table if exists Trueque";
		
		db.execSQL(deleteTablesSql);
		onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
		
	}
}
