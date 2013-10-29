package uy.com.group05.baasclient.trueques;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.internal.aq;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import uy.com.group05.baasclient.sdk.GCMService;
import uy.com.group05.baasclient.sdk.SDKFactory;
import uy.com.group05.baasclient.sdk.entities.SimplePushChannelDTO;

public class SuscribirCanalPushActivity extends Activity {

    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    String SENDER_ID = "892349087446"; // Project ID creado desde la consola de Google.

    // Para los logs
    static final String TAG = "GCM Trueque";

    TextView mDisplay;
    CheckBox chk_gcm;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;
    boolean registrado = false;
    GCMService gcms;

    String regid;
    
    ArrayList<SimplePushChannelDTO> canales = new ArrayList<SimplePushChannelDTO>();
	ListAdapter boxAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribircanalpush);
        
        gcms = new GCMService(this);
        
        fillData(this);
	    
	    // Lo de antes
        context = getApplicationContext();
    }
    
    void fillData(final Activity act) {
	    new AsyncTask<Void, Void, Boolean>() {
	    	private final ProgressDialog dialog = new ProgressDialog(act);
    		@Override
    		protected void onPreExecute() {
    			this.dialog.setMessage("Cargando canales...");
    			this.dialog.setIndeterminate(true);
    			this.dialog.setCancelable(false);
    			this.dialog.show();
    		}
    		
		    @Override
		    protected Boolean doInBackground(Void... params) {
				try {
					List<SimplePushChannelDTO> listaCanales = gcms.getPushChannels();
					for (SimplePushChannelDTO canal : listaCanales) {
						Log.i("TAG", "Agregando canal: " + canal.getName());
			    		canales.add(canal);
			    	}
					return true;
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
		    	if (this.dialog.isShowing()) {
		    		this.dialog.dismiss();
		    	}
		    	
		    	boxAdapter = new ListAdapter(act, canales);

			    ListView lvMain = (ListView) findViewById(R.id.lvMain);
			    lvMain.setAdapter(boxAdapter);
            }
		}.execute(null, null, null);
    	
    	/*for (int i = 1; i <= 20; i++) {
    		SimplePushChannelDTO chan = new SimplePushChannelDTO();
    		chan.setId(i);
    		chan.setName("Canal " + i);
    		canales.add(chan);
    	}*/
	  }
    
    public void actualizarSuscripciones(View view) {
    	final Activity act = (Activity) view.getContext();
    	new AsyncTask<Void, Void, Boolean>() {
    		private final ProgressDialog dialog = new ProgressDialog(act);
    		@Override
    		protected void onPreExecute() {
    			this.dialog.setMessage("Suscribiendo a canales...");
    			this.dialog.setIndeterminate(true);
    			this.dialog.setCancelable(false);
    			this.dialog.show();
    		}
    		
		    @Override
		    protected Boolean doInBackground(Void... params) {
		    	for (SimplePushChannelDTO canal : boxAdapter.getBox()) {
					try {
						gcms.subscribeToPushChannel(canal.getName());
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
		    	return true;
		    }
		    
		    @Override
            protected void onPostExecute(Boolean ok) {
		    	if (this.dialog.isShowing()) {
		    		this.dialog.dismiss();
		    	}
		    	Toast.makeText(act, "Suscripciones realizadas", Toast.LENGTH_LONG).show();
		    	act.finish();
            }
		}.execute(null, null, null);
	    
    	/*if (registrado) {
    		mDisplay.setText("");
    		//unRegisterInBackground();
    		removeRegistrationIdFromBackend();
    	}
    	else {
    		registerInBackground();
    	}*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    /**
     * Verifica que el dispositivo tenga instalado Google Play Services.
     * Si no lo tiene instalado, muestra un mensaje para permitirle al
     * usuario bajarlo desde la Play Store o activarlo desde las opciones.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "El dispositivo no es soportado.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Guarda en {@code SharedPreferences} el registration ID y la versión de la aplicación.
     *
     * @param context contexto de la aplicación.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Guardando el regId con appVersion: " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }
    
    /**
     * Borra del {@code SharedPreferences} el registration ID y la versión de la aplicación.
     */
    private void removeRegistrationId() {
        final SharedPreferences prefs = getGcmPreferences(context);
        Log.i(TAG, "Borrando regId y appVersion");
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PROPERTY_REG_ID);
        editor.remove(PROPERTY_APP_VERSION);
        editor.commit();
    }

    /**
     * Devuelve el registration ID para la aplicación en GCM, si existe.
     * <p>
     * Si el resultado es vacío, la aplicación se tiene que registrar.
     *
     * @return registration ID, o vacío si no existe.
     *//**
     * Des-registra la aplicación de GCM.
     * <p>
     * Borra el regid y la versión de la app del {@code SharedPreferences} de la aplicación.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context) {
        return getSharedPreferences(SuscribirCanalPushActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    
    /**
     * Mandar al baas el regid almacenado.
     */
    
    /*
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "No se encontró regId.");
            return "";
        }
        // Si la app cambió de versión, se tiene que registrar de nuevo
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "Cambió la versión de la aplicación.");
            return "";
        }
        return registrationId;
    }

    *//**
     * Registra la aplicación con GCM.
     * <p>
     * Guarda el regid y la versión de la app en {@code SharedPreferences} de la aplicación.
     *//*
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Dispositivo registrado, registration ID = " + regid;
                    
                    registrado = true;

                    sendRegistrationIdToBackend();

                    // Guardo el regid
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                mDisplay.setText(msg + "\n");
                if (registrado) {
                	chk_gcm.setChecked(true);
                	Log.i(TAG, regid);
                }
            }
        }.execute(null, null, null);
    }
    
    */
    
    /*
    private void unRegisterInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    gcm.unregister();
                    
                    registrado = false;

                    removeRegistrationIdFromBackend();

                    // Borro el regid
                    removeRegistrationId();
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            	mDisplay.setText(msg + "\n");
                if (!registrado) {
                	mDisplay.setText("Se borró el registro con GCM.");
                	chk_gcm.setChecked(false);
                }
            }
        }.execute(null, null, null);
    }*/
    
    /*private void sendRegistrationIdToBackend() {
    	
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) {
    	    	new AsyncTask<String, Void, Boolean>() {
    	            @Override
    	            protected Boolean doInBackground(String... args) {
    	            	String regId = args[0];
    	    			
    	    			boolean res;
    	    			
    	    			try
    	    			{
    	    				res = SDKFactory.getClientFacade(context).updateRegIdOfClient(context, regId);
    	    				
    	    				return res;
    	    			}
    	    			catch (UnsupportedEncodingException e) {
    	    				return false;
    	    			}
    	    			catch (ClientProtocolException e) {
    	    				return false;
    	    			}
    	    			catch (IOException e) {
    	    				return false;
    	    			}
    	            }

    	            @Override
    	            protected void onPostExecute(Boolean res) {
    	                Log.i(TAG, regid);
    	            }
    	        }.execute(regid);
    	    } else {
    	    	// TODO: Deberia volver a intentar
    	    }
    }
    
    *//**
     * Borrar del baas el regid almacenado.
     *//*
    private void removeRegistrationIdFromBackend() {
      // TODO
    }*/
    
    
}

