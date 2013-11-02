package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.Login.UserLoginTask;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Registrar extends Activity {

	private RegistroTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String nombre;
	private String email;
	private String pass;
	private String rePass;

	// UI references.
	private EditText nombreView;
	private EditText mailView;
	private EditText passView;
	private EditText rePassView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar);
		
		
		nombreView = (EditText) findViewById(R.id.nombre_reg);
		mailView = (EditText) findViewById(R.id.email_reg);
		passView = (EditText) findViewById(R.id.pass_reg);
		rePassView = (EditText) findViewById(R.id.repass_reg);
		
		findViewById(R.id.BtnRegistrar).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptRegistrar();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar, menu);
		return true;
	}
	
	public void attemptRegistrar() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mailView.setError(null);
		passView.setError(null);
		nombreView.setError(null);
		rePassView.setError(null);

		// Store values at the time of the login attempt.
		nombre = nombreView.getText().toString();
		email = mailView.getText().toString();
		pass = passView.getText().toString();
		rePass = rePassView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid NOMBRE
		if (TextUtils.isEmpty(nombre)) {
			nombreView.setError("Ingrese un nombre");
			focusView = nombreView;
			cancel = true;
		}
				
		// Check for a valid password.
		if (TextUtils.isEmpty(pass)) {
			passView.setError(getString(R.string.error_field_required));
			focusView = passView;
			cancel = true;
		} else if (pass.length() < 4) {
			passView.setError(getString(R.string.error_invalid_password));
			focusView = passView;
			cancel = true;
		} else if (!pass.equals(rePass)){
			rePassView.setError("No coinciden");
			focusView = rePassView;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mailView.setError(getString(R.string.error_field_required));
			focusView = mailView;
			cancel = true;
		} else if (!email.contains("@")) {
			mailView.setError(getString(R.string.error_invalid_email));
			focusView = mailView;
			cancel = true;
		} else if (Factory.getUsuarioCtrl().getUsuario(email)!=null){
			mailView.setError("El email ya esta registrado");
			focusView = mailView;
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
			mAuthTask = new RegistroTask();
			mAuthTask.execute((Void) null);
		}
	}
	
	public class RegistroTask extends AsyncTask<Void, Void, Boolean> {
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
			
			return Factory.getUsuarioCtrl().registrarUsuario(email, nombre, pass);
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);

			if (success) {
				//finish();
				//++Me voy al perfil del usuario
				Intent intent = new Intent(Registrar.this, Home.class);
				//intent.putExtra("mail", email);
				SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("mail", email);
				editor.commit();
				startActivity(intent);
				//+++
				finish();
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
