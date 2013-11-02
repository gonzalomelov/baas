package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.CrearObjeto.CrearObjetoTask;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CrearTrueque extends Fragment{//Activity {

	private CrearTruequeTask mAuthTask = null;
	private String mail;
	
	private String nombre;
	private String desc;
	private float valor;
	private String descBusca;
	private float minVal;
	
	private EditText nombreView;
	private EditText descView;
	private EditText valorView;
	private EditText descBuscaView;
	private EditText minValView;
	private CrearTruequeListener listener;
	

	 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_crear_trueque, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_crear_trueque);
		
		nombreView = (EditText) getView().findViewById(R.id.nom_obj);
		descView = (EditText) getView().findViewById(R.id.desc_obj);
		valorView = (EditText) getView().findViewById(R.id.valor_obj);
		descBuscaView = (EditText) getView().findViewById(R.id.desc_deseado);
		minValView = (EditText) getView().findViewById(R.id.minValor_deseado);
		
		SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
//		Bundle bundle = this.getIntent().getExtras();
//		this.mail = bundle.getString("mail");
		 
		getView().findViewById(R.id.BtnCrear).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptCrearTrueque();
					}
				});
		
	}
	
	public interface CrearTruequeListener {
        void onTruequeCreado();
    }
 
    public void setCrearTruequeListener(CrearTruequeListener listener) {
        this.listener=listener;
    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.crear_trueque, menu);
//		return true;
//	}
	
	//++++++++++++++++++
	public void attemptCrearTrueque() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		nombreView.setError(null);
		descView.setError(null);
		valorView.setError(null);
		descBuscaView.setError(null);
		minValView.setError(null);

		// Store values at the time of the login attempt.
		nombre = nombreView.getText().toString();
		desc = descView.getText().toString();
		valor = 0;
		descBusca = descBuscaView.getText().toString();
		minVal = 0;
		
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
		
		// Check VALOR
		if (TextUtils.isEmpty(minValView.getText().toString())) {
			minValView.setError(getString(R.string.error_field_required));
			focusView = minValView;
			cancel = true;
		}else {
			minVal = Float.valueOf(minValView.getText().toString());
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
		if (TextUtils.isEmpty(descBusca)) {
			descBuscaView.setError(getString(R.string.error_field_required));
			focusView = descBuscaView;
			cancel = true;
		} 

		// Check for a valid VALOR.
		if (!(valor>0)) {
			valorView.setError("Valor debe ser mayor a 0");
			focusView = valorView;
			cancel = true;
		}
		// Check for a valid VALOR.
		if (!(minVal>0)) {
			minValView.setError("Valor Minimo debe ser mayor a 0");
			focusView = minValView;
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
			mAuthTask = new CrearTruequeTask();
			mAuthTask.execute((Void) null);
		}
	}
	
	public class CrearTruequeTask extends AsyncTask<Void, Void, Boolean> {
		private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			int idObj= Factory.getObjetoCtrl().crearObjeto(mail, nombre, desc, valor);
			Objeto obj = Factory.getObjetoCtrl().getObjeto(idObj);
			if (obj!=null){
				this.idTrueque = Factory.getTruequeCtrl().crearTrueque(obj, descBusca, minVal);
				return idTrueque>0;
			}
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
				
				//AVISO AL ACTIVITY
				if (listener!=null) {
                    listener.onTruequeCreado();
                }
				
//				Intent intent = new Intent(CrearTrueque.this, Perfil.class);
//				//intent.putExtra("mail", mail);
//		    	startActivity(intent);
//				//+++
		    	
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
