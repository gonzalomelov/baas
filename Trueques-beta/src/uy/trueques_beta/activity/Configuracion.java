package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;

public class Configuracion extends Activity {
	private CheckBox nueva, aceptada, rechazada;
	private SharedPreferences.Editor editor;
	private String mail;
	private boolean notiNO;
	private boolean notiOA;
	private boolean notiOR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracion);
		
		
		//SETEO EL MAIL LOGEADO
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.editor = prefs.edit();
//		editor.putString("mail", mail);
//		editor.commit();
		notiNO = prefs.getBoolean("NotiNuevaOferta", true);
		notiOA =prefs.getBoolean("NotiOfertaAceptada", true);
		notiOR =prefs.getBoolean("NotiOfertaRechazada", true);
		
		nueva = (CheckBox) findViewById(R.id.chkNuevasOfertas);
		aceptada = (CheckBox) findViewById(R.id.chkOfertasAceptadas);
		rechazada = (CheckBox) findViewById(R.id.chkOfertasRechazadas);
		
		nueva.setChecked(notiNO);
		aceptada.setChecked(notiOA);
		rechazada.setChecked(notiOR);
		
		addListenerOnCheck();
	}

	
	 public void addListenerOnCheck() {
		 
			nueva.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiNuevaOferta", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiNuevaOferta", false);
				}
				editor.commit();
			  }
			});
			
			aceptada.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiOfertaAceptada", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiOfertaAceptada", false);
				}
				editor.commit();
			  }
			});
			
			rechazada.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiOfertaRechazada", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean("NotiOfertaRechazada", false);
				}
				editor.commit();
			  }
			});
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.configuracion, menu);
		return true;
	}

}
