package uy.trueques_beta.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baassdk.GCMService;
import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
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
		notiNO = prefs.getBoolean("NotiNuevaOferta", false);
		notiOA =prefs.getBoolean("NotiOfertaAceptada", false);
		notiOR =prefs.getBoolean("NotiOfertaRechazada", false);
		
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
					
					new AsyncTask<Void, Void, Boolean>() {
//			    		private final ProgressDialog dialog = new ProgressDialog(act);
			    		@Override
			    		protected void onPreExecute() {
//			    			this.dialog.setMessage("Suscribiendo a canales...");
//			    			this.dialog.setIndeterminate(true);
//			    			this.dialog.show();
			    		}
			    		
					    @Override
					    protected Boolean doInBackground(Void... params) {
					    	
							try {
								GCMService gcms = SDKFactory.getGCMService(Configuracion.this);
								gcms.subscribeToPushChannel("NotiNuevaOferta");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return false;
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return false;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return false;
							}
					    	
					    	return true;
					    }
					    
					    @Override
			            protected void onPostExecute(Boolean ok) {
//					    	if (this.dialog.isShowing()) {
//					    		this.dialog.dismiss();
//					    	}
//					    	Toast.makeText(act, "Suscripciones realizadas", Toast.LENGTH_LONG).show();
//					    	act.finish();
			            }
					}.execute(null, null, null);
					
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
