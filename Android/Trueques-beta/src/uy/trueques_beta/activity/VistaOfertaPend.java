package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VistaOfertaPend extends Activity {

	private OfertaTask mAuthTask =null;
	private String mail;
	private Oferta o;
	private Usuario u;
	private Trueque t;
	
	private TextView nombre;
	private TextView objeto;
	private TextView duenio;
	private TextView desc;
	private TextView ubicacion;
	private TextView valor;
	private TextView minVal;
	private ImageView img;
	private Button buttonRechazar;
	private Button buttonAceptar;
	private String idOferta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_oferta_pend);
		
		Intent intent = this.getIntent();
		String ofertaJson= intent.getExtras().getString("Oferta");
		this.o = Oferta.fromJson(ofertaJson); //this.o = Factory.getOfertaCtrl().getOferta(idOferta);
		
		this.t = Factory.getTruequeCtrl().getTrueque(this, this.o.getIdTrueque());
		
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");

		if(o==null || t==null){
			nombre = (TextView)findViewById(R.id.LblNomTrueque);
			nombre.setText("ERROR VUELVA A INTENTAR");
		}else{
			
			nombre = (TextView)findViewById(R.id.LblNomTrueque);
			nombre.setText(nombre.getText() +" "+ t.getObjeto().getNombre());
			minVal = (TextView)findViewById(R.id.LblMinVal);
			minVal.setText("$"+t.getMinVal());
			
			duenio = (TextView)findViewById(R.id.LblDuenio);
			duenio.setText(o.getUsuario());
			valor = (TextView)findViewById(R.id.LblValor);
			valor.setText("$"+o.getObjeto().getValor());
			
			objeto = (TextView)findViewById(R.id.LblObjeto);
			objeto.setText(o.getObjeto().getNombre());
			desc = (TextView)findViewById(R.id.LblDesc);
			desc.setText(o.getObjeto().getDescripcion());
			ubicacion = (TextView)findViewById(R.id.LblUbicacion);
			ubicacion.setText(o.getUbicacion());
			
			img = (ImageView)findViewById(R.id.imageView1);
			img.setImageBitmap(o.getImagen());
			
//			buttonRechazar = (Button)findViewById(R.id.BtnRechazar);
//			buttonAceptar = (Button)findViewById(R.id.BtnAceptar);
			
			buttonRechazar = (Button)findViewById(R.id.BtnRechazar);
			buttonRechazar.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							SharedPreferences prefs = VistaOfertaPend.this.getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
							if (prefs.getBoolean("isBloqueado", false)){
							//if (Factory.getUsuarioCtrl().getUsuario(VistaOfertaPend.this, mail).isBloqueado()){	
								Toast.makeText(VistaOfertaPend.this, "Usuario bloqueado, no puede realizar la acción", Toast.LENGTH_SHORT).show();
							}
							else{
								if(o!=null){
									Factory.getTruequeCtrl().rechazarOferta(VistaOfertaPend.this, t.getIdTrueque(), o.getIdOferta());
									Intent intent = new Intent(VistaOfertaPend.this, Home.class);
									startActivity(intent);
								}
							}
						}
					});
			
			buttonAceptar = (Button)findViewById(R.id.BtnAceptar);			
			buttonAceptar.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							SharedPreferences prefs = VistaOfertaPend.this.getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
							if (prefs.getBoolean("isBloqueado", false)){
							//if (Factory.getUsuarioCtrl().getUsuario(VistaOfertaPend.this, mail).isBloqueado()){	
								Toast.makeText(VistaOfertaPend.this, "Usuario bloqueado, no puede realizar la acción", Toast.LENGTH_SHORT).show();
							}
							else{
								if(o!=null){
									Factory.getTruequeCtrl().aceptarOferta(VistaOfertaPend.this, t.getIdTrueque(), o.getIdOferta());
									Intent intent = new Intent(VistaOfertaPend.this, Home.class);
									startActivity(intent);
								}
							}
						}
					});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.vista_oferta_pend, menu);
		return true;
	}

	
	public class OfertaTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			u = Factory.getUsuarioCtrl().getUsuario(VistaOfertaPend.this,mail);
			return u!=null;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);
//			if(pd!=null & pd.isShowing())
//				pd.dismiss();
//			
			if (success) {	
				buttonRechazar.setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								if (u.isBloqueado()){	
									Toast.makeText(VistaOfertaPend.this, "Usuario bloqueado, no puede realizar la acción", Toast.LENGTH_SHORT).show();
								}
								else{
									if(o!=null){
										Factory.getTruequeCtrl().rechazarOferta(VistaOfertaPend.this, t.getIdTrueque(), o.getIdOferta());
										Intent intent = new Intent(VistaOfertaPend.this, Home.class);
										startActivity(intent);
									}
								}
							}
						});
						
				buttonAceptar.setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								if (u.isBloqueado()){	
									Toast.makeText(VistaOfertaPend.this, "Usuario bloqueado, no puede realizar la acción", Toast.LENGTH_SHORT).show();
								}
								else{
									if(o!=null){
										Factory.getTruequeCtrl().aceptarOferta(VistaOfertaPend.this, t.getIdTrueque(), o.getIdOferta());
										Intent intent = new Intent(VistaOfertaPend.this, Home.class);
										startActivity(intent);
									}
								}
							}
						});
				
		    	
			} else {
				Log.i("[VistaTrueque]:", "ERROR");
				Toast.makeText(VistaOfertaPend.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
//			if(pd!=null & pd.isShowing())
//				pd.dismiss();
		}
	}	
	
}
