package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.R.string;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CrearObjeto extends Activity {

	private CrearObjetoTask mAuthTask = null;
	private String mail;
	
	private String nombre;
	private String desc;
	private float valor;
	
	private EditText nombreView;
	private EditText descView;
	private EditText valorView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_objeto);
		
		nombreView = (EditText) findViewById(R.id.nom_obj);
		descView = (EditText) findViewById(R.id.desc_obj);
		valorView = (EditText) findViewById(R.id.valor_obj);
		
		Bundle bundle = this.getIntent().getExtras();
		this.mail = bundle.getString("mail");
		 
		findViewById(R.id.BtnCrear).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptCrear();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar, menu);
		return true;
	}
	
	public void attemptCrear() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		nombreView.setError(null);
		descView.setError(null);
		valorView.setError(null);

		// Store values at the time of the login attempt.
		nombre = nombreView.getText().toString();
		desc = descView.getText().toString();
		valor = 0;

		boolean cancel = false;
		View focusView = null;
		if (TextUtils.isEmpty(valorView.getText().toString())) {
			valorView.setError(getString(R.string.error_field_required));
			focusView = valorView;
			cancel = true;
		}else {
			valor = Float.valueOf(valorView.getText().toString());
		}

		// Check for a valid NOMBRE
		if (TextUtils.isEmpty(nombre)) {
			nombreView.setError(getString(R.string.error_field_required));
			focusView = nombreView;
			cancel = true;
		}
				
		// Check for a valid DESCRIPCION.
		if (TextUtils.isEmpty(desc)) {
			descView.setError(getString(R.string.error_field_required));
			focusView = descView;
			cancel = true;
		} 

		// Check for a valid VALOR.
		if (!(valor>0)) {
			valorView.setError("Valor debe ser mayor a 0");
			focusView = valorView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
//			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
//			showProgress(true);
			mAuthTask = new CrearObjetoTask();
			mAuthTask.execute((Void) null);
		}
	}
	
	public class CrearObjetoTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			/*try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}
			

			// TODO: register the new account here.
			return true;*/
			
			return Factory.getObjetoCtrl().crearObjeto(mail, nombre, desc, valor) !=0;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);

			if (success) {
				//finish();
				//++Me voy al perfil del usuario
				Intent intent = new Intent(CrearObjeto.this, Home.class);
				intent.putExtra("mail", mail);
		    	startActivity(intent);
				//+++
			} else {
				nombreView.setError("Error al registrar");
				nombreView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
		}
	}
}
