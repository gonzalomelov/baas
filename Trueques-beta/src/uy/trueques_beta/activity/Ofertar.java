package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.R.string;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Trueque;
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

public class Ofertar extends Activity {

	private CrearOfertaTask mAuthTask = null;
	private String mail;
	private int idTrueque;
	private Trueque t;
	
	private String nombre;
	private String desc;
	private float valor;
	private String ubicacion;
	
	private TextView tituloView;
	private EditText nombreView;
	private EditText descView;
	private EditText valorView;
	private EditText ubicacionView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ofertar);

		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		this.idTrueque = this.getIntent().getIntExtra("idTrueque", -1);
		
		this.t = Factory.getTruequeCtrl().getTrueque(idTrueque);
		
		tituloView = (TextView)findViewById(R.id.LblTitleOfertar);
		if (this.t!=null)
			tituloView.setText("Oferta por "+t.getObjeto().getNombre());
		else
			tituloView.setText("Error al identificar el trueque");
		nombreView = (EditText) findViewById(R.id.nom_obj);
		descView = (EditText) findViewById(R.id.desc_obj);
		valorView = (EditText) findViewById(R.id.valor_obj);
		ubicacionView = (EditText) findViewById(R.id.ubicacion);
		 
		findViewById(R.id.BtnOfertar).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if(t!=null)
							attemptCrearOferta();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.ofertar, menu);
		return true;
	}

	
	//++++++++++++++++++
		public void attemptCrearOferta() {
			if (mAuthTask != null) {
				return;
			}

			// Reset errors.
			nombreView.setError(null);
			descView.setError(null);
			valorView.setError(null);
			ubicacionView.setError(null);

			// Store values at the time of the login attempt.
			nombre = nombreView.getText().toString();
			desc = descView.getText().toString();
			valor = 0;
			ubicacion = ubicacionView.getText().toString();
			
			boolean cancel = false;
			View focusView = null;
			// Check VALOR
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
			// Check for a valid DESCRIPCION.
			if (TextUtils.isEmpty(ubicacion)) {
				ubicacionView.setError(getString(R.string.error_field_required));
				focusView = ubicacionView;
				cancel = true;
			} 

			// Check for a valid VALOR.
			if (!(valor >= this.t.getMinVal())) {
				valorView.setError("Valor debe ser mayor a "+this.t.getMinVal());
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
//				mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
//				showProgress(true);
				mAuthTask = new CrearOfertaTask();
				mAuthTask.execute((Void) null);
			}
		}
		
		public class CrearOfertaTask extends AsyncTask<Void, Void, Boolean> {
			@Override
			protected Boolean doInBackground(Void... params) {
				if (t!=null)
					return Factory.getTruequeCtrl().crearOferta(t.getIdTrueque(), mail, nombre, desc, valor, ubicacion);
				else
					return false;
			}

			@Override
			protected void onPostExecute(final Boolean success) {
				mAuthTask = null;
				//showProgress(false);

				if (success) {
					//finish();
					//++Me voy al perfil del usuario
					Intent intent = new Intent(Ofertar.this, Home.class);
					//intent.putExtra("mail", mail);
			    	startActivity(intent);
					//+++
				} else {
					nombreView.setError("Error al publicar");
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
