package uy.com.group05.baasclient.sdk.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import uy.com.group05.baasclient.sdk.impl.db.SdkDbHelper;
import uy.com.group05.baasclient.trueques.Trueque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class APISQLiteClient {
	
	private Context context;
	
	public APISQLiteClient(Context context) {
		this.context = context;
	}
	
	public String get(String entity) {
		
		SdkDbHelper dbHelper = new SdkDbHelper(context); 
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String table = "Trueque";
		String[] columns = {
			"a",
			"b"
		};
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		String limit = null;
		
		Cursor c = db.query(
				table,
				columns,
				selection,
				selectionArgs,
				groupBy,
				having,
				orderBy,
				limit);
		
		List<Trueque> trueques = new ArrayList<Trueque>();
		
		while (c.moveToNext()) {
			Trueque t = new Trueque();
			
			t.setAnio(c.getString(0));
			t.setMarca(c.getString(1));
			
			trueques.add(t);
		}
		
		Gson gson = new Gson();
		
		Type type = new TypeToken<List<Trueque>>(){}.getType();
		String ret = gson.toJson(trueques, type);
		
		return ret;
	}
	
	public boolean post(String entity, Object jsonObj, Type type) {
		SdkDbHelper dbHelper = new SdkDbHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		Trueque t = (Trueque)jsonObj;
		
		ContentValues values = new ContentValues();
		values.put("a", t.getAnio());
		values.put("b", t.getMarca());
		
		long newRowId = db.insert("Trueque", "", values);
		
		return newRowId > -1;
	}
}
