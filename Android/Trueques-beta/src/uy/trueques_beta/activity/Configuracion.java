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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;

public class Configuracion extends Activity {
	private CheckBox chkOfertasNuevas, chkOfertasAceptadas, chkOfertasRechazadas, chkTruequesNuevos;
	private SharedPreferences.Editor editor;
	private String mail;
	private boolean notiNO;
	private boolean notiOA;
	private boolean notiOR;
	private boolean notiTruequesNuevos;
	private String usuarioLogueado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracion);
		
		
		//SETEO EL MAIL LOGEADO
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.editor = prefs.edit();
//		editor.putString("mail", mail);
//		editor.commit();
		usuarioLogueado = prefs.getString("mail", "");
		notiNO = prefs.getBoolean(usuarioLogueado + "_notifOfertasNuevas", false);
		notiOA =prefs.getBoolean(usuarioLogueado + "_notifOfertasAceptadas", false);
		notiOR =prefs.getBoolean(usuarioLogueado + "_notifOfertasRechazadas", false);
		notiTruequesNuevos=prefs.getBoolean(usuarioLogueado + "_truequesNuevos", false);
		
		chkOfertasNuevas = (CheckBox) findViewById(R.id.chkNuevasOfertas);
		chkOfertasAceptadas = (CheckBox) findViewById(R.id.chkOfertasAceptadas);
		chkOfertasRechazadas = (CheckBox) findViewById(R.id.chkOfertasRechazadas);
		chkTruequesNuevos = (CheckBox) findViewById(R.id.chkTruequesNuevos);
		
		chkOfertasNuevas.setChecked(notiNO);
		chkOfertasAceptadas.setChecked(notiOA);
		chkOfertasRechazadas.setChecked(notiOR);
		chkTruequesNuevos.setChecked(notiTruequesNuevos);
		
		ProgressDialog pd = ProgressDialog.show(Configuracion.this,"Configuración","Espere por favor...",true,false,null);
		// Inicializo GCM por si no se mandó el regId al baas
		GCMService gcms = SDKFactory.getGCMService(Configuracion.this, usuarioLogueado);
		
		if (!gcms.checkStatus(usuarioLogueado)) {
			final Activity act = Configuracion.this;
			new AlertDialog.Builder(this)
		    .setTitle("Notificaciones")
		    .setMessage("No está listo para recibir notificaciones. Intente nuevamente más tarde.")
		    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            act.finish();
		        }
		     })
		     .show();
		}
		
		pd.dismiss();
		
		addListenerOnCheck();
	}

	
	 public void addListenerOnCheck() {
		 
		 chkOfertasNuevas.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasNuevas", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasNuevas", false);
				}
				editor.commit();
			  }
			});
			
			chkOfertasAceptadas.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasAceptadas", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasAceptadas", false);
				}
				editor.commit();
			  }
			});
			
			chkOfertasRechazadas.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasRechazadas", true);
				}else{
					Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
					editor.putBoolean(usuarioLogueado + "_notifOfertasRechazadas", false);
				}
				editor.commit();
			  }
			});
			
			// Canal Push de Trueques Nuevos
			chkTruequesNuevos.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  final CheckBox check = (CheckBox) v;
					if (check.isChecked()) {
						new AsyncTask<Void, Void, Boolean>() {
//				    		private final ProgressDialog dialog = new ProgressDialog(act);
				    		@Override
				    		protected void onPreExecute() {
//				    			this.dialog.setMessage("Suscribiendo a canales...");
//				    			this.dialog.setIndeterminate(true);
//				    			this.dialog.show();
				    		}
				    		
						    @Override
						    protected Boolean doInBackground(Void... params) {
						    	
								try {
									GCMService gcms = SDKFactory.getGCMService(Configuracion.this, usuarioLogueado);
									return gcms.subscribeToPushChannel("truequesNuevos");
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
						    }
						    
						    @Override
				            protected void onPostExecute(Boolean ok) {
//						    	if (this.dialog.isShowing()) {
//						    		this.dialog.dismiss();
//						    	}
//						    	Toast.makeText(act, "Suscripciones realizadas", Toast.LENGTH_LONG).show();
//						    	act.finish();
						    	if (ok) {
						    		Toast.makeText(Configuracion.this, "Registrado", Toast.LENGTH_SHORT).show();
						    		editor.putBoolean(usuarioLogueado + "_truequesNuevos", true);
						    		editor.commit();
						    	}
						    	else {
						    		Toast.makeText(Configuracion.this, "No existe el canal push o no hay conexión.", Toast.LENGTH_SHORT).show();
						    		check.setChecked(false);
						    	}
				            }
						}.execute(null, null, null);
					}else{
						new AsyncTask<Void, Void, Boolean>() {
//				    		private final ProgressDialog dialog = new ProgressDialog(act);
				    		@Override
				    		protected void onPreExecute() {
//				    			this.dialog.setMessage("Suscribiendo a canales...");
//				    			this.dialog.setIndeterminate(true);
//				    			this.dialog.show();
				    		}
				    		
						    @Override
						    protected Boolean doInBackground(Void... params) {
						    	
								try {
									GCMService gcms = SDKFactory.getGCMService(Configuracion.this, usuarioLogueado);
									return gcms.unsubscribeFromPushChannel("truequesNuevos");
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
						    }
						    
						    @Override
				            protected void onPostExecute(Boolean ok) {
//						    	if (this.dialog.isShowing()) {
//						    		this.dialog.dismiss();
//						    	}
//						    	Toast.makeText(act, "Suscripciones realizadas", Toast.LENGTH_LONG).show();
//						    	act.finish();
						    	if (ok) {
						    		Toast.makeText(Configuracion.this, "Eliminado", Toast.LENGTH_SHORT).show();
						    		editor.putBoolean(usuarioLogueado + "_truequesNuevos", false);
						    		editor.commit();
						    	}
						    	else {
						    		Toast.makeText(Configuracion.this, "No existe el canal push o no hay conexión.", Toast.LENGTH_SHORT).show();
						    		check.setChecked(true);
						    	}
				            }
						}.execute(null, null, null);
					}
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
