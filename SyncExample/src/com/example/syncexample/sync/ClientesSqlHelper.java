package com.example.syncexample.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientesSqlHelper extends SQLiteOpenHelper {
 
	//Sentencia SQL para crear la tabla de Clientes
    private static final String SQL_CREATE_CLIENTES =
    		"CREATE TABLE Clientes (" +
			"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"nombre TEXT, " +
			"telefono TEXT, " +
			"email TEXT)";
	
	public ClientesSqlHelper(
			Context context,
			String dbName,
			CursorFactory cursorFactory,
			int version) {
		
		super(context, dbName, cursorFactory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_CLIENTES);
		for (int i=1; i<16; i++) {
			String nombre = "Cliente" + i;
			String telefono = "0912719" + i;
			String email = "email" + i + "@smartech.com.uy";
			
			db.execSQL("INSERT INTO CLIENTES (nombre, telefono, email) VALUES ('"+nombre+"','"+telefono+"','"+email+"')");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Clientes");
		db.execSQL(SQL_CREATE_CLIENTES);
	}

}
