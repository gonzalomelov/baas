package com.example.syncexample;

import com.example.syncexample.sync.BaasProviderContract;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends Activity {

	private EditText nombreEditText;
	private EditText telefonoEditText;
	private EditText emailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}
	
	public void save(View view) {
		Log.i("SYNCEXAMPLE", "save");
		
		boolean local = getIntent().getExtras().getBoolean("local");
		
		if (local) {
			ContentValues values = new ContentValues();
			
			nombreEditText = (EditText) findViewById(R.id.editText1);
			telefonoEditText = (EditText) findViewById(R.id.editText2);
			emailEditText = (EditText) findViewById(R.id.editText3);
			
			String nombre = nombreEditText.getText().toString();
			String telefono = telefonoEditText.getText().toString();
			String email = emailEditText.getText().toString();
			
			Cliente cliente = new Cliente();
			cliente.setEmail(email);
			cliente.setNombre(nombre);
			cliente.setTelefono(telefono);
			
			Gson gson = new Gson();
			
			values.put("entity", gson.toJson(cliente, Cliente.class));
			
			Uri result = getContentResolver().insert(Uri.parse("content://com.example.syncexample.sync.provider/" + "Cliente"), values);
			
		} else {
			
		}
		
		finish();
	}
}
