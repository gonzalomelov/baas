package uy.com.group05.baasclient.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baasclient.trueques.GcmIntentService;
import uy.com.group05.baasclient.trueques.MainActivity;
import uy.com.group05.baasclient.trueques.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMService {
	public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    String SENDER_ID = "892349087446"; // Project ID creado desde la consola de Google.

    // Para los logs
    static final String TAG = "GCM SDK";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Activity activity;
    Context appContext;
    boolean registrado = false;

    String regid;
    
    public GCMService(Activity act) {
    	appContext = act.getApplicationContext();
    	activity = act;
    	if (checkPlayServices()) {
    		//gcm = GoogleCloudMessaging.getInstance(appContext);
            regid = getRegistrationId();
            
            if (!regid.isEmpty()) {
            	registrado = true;
            }
            else {
            	registerInBackground();
            }
            
            
            
            
            
            
            registerInBackground();
        	new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String msg = "";
                    try {
						sendNotificationToPushChannel("ofertas", "testKey", "testValue");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    return msg;
                }
            }.execute(null, null, null);
            
            
            
            
            
            
            
            
        } else {
            Log.i(TAG, "No se encontró Google Play Services.");
    	}
    }

    /**
     * Verifica que el dispositivo tenga instalado Google Play Services.
     * Si no lo tiene instalado, muestra un mensaje para permitirle al
     * usuario bajarlo desde la Play Store o activarlo desde las opciones.
     */
    public boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "El dispositivo no es soportado.");
                activity.finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Guarda en {@code SharedPreferences} el registration ID y la versión de la aplicación.
     *
     * @param activity contexto de la aplicación.
     * @param regId registration ID
     */
    private void storeRegistrationId(String regId) {
        final SharedPreferences prefs = getGcmPreferences(appContext);
        int appVersion = getAppVersion(appContext);
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
        final SharedPreferences prefs = getGcmPreferences(appContext);
        Log.i(TAG, "Borrando regId y appVersion");
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PROPERTY_REG_ID);
        editor.remove(PROPERTY_APP_VERSION);
        editor.commit();
    }

    /**
     * Devuelve el registration ID de la aplicación en GCM, si existe.
     * <p>
     * Si el resultado es vacío, la aplicación se tiene que registrar.
     *
     * @return registration ID, o vacío si no existe.
     */
    public String getRegistrationId() {
        final SharedPreferences prefs = getGcmPreferences(appContext);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "No se encontró regId.");
            return "";
        }
        // Si la app cambió de versión, se tiene que registrar de nuevo
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(appContext);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "Cambió la versión de la aplicación.");
            return "";
        }
        return registrationId;
    }

    /**
     * Registra la aplicación con GCM.
     * <p>
     * Guarda el regid y la versión de la app en {@code SharedPreferences} de la aplicación.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(activity);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Dispositivo registrado, registration ID = " + regid;
                    
                    registrado = true;

                    sendRegistrationIdToBackend();

                    // Guardo el regid
                    storeRegistrationId(regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }
        }.execute(null, null, null);
    }
    
    /**
     * Des-registra la aplicación de GCM.
     * <p>
     * Nota: No se debería usar, a no ser que se desinstale la aplicación. Usar {@code removeRegistrationIdFromBackend()}
     * <p>
     * Borra el regid y la versión de la app del {@code SharedPreferences} de la aplicación.
     */
    private void unRegisterInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(activity);
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
        }.execute(null, null, null);
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
        return context.getSharedPreferences(context.getClass().getSimpleName(),
                Context.MODE_PRIVATE);
    }
    
    /**
     * Mandar al baas el regid almacenado.
     * @throws IOException 
     * @throws ClientProtocolException 
     * @throws UnsupportedEncodingException 
     */
    private boolean sendRegistrationIdToBackend() throws UnsupportedEncodingException, ClientProtocolException, IOException {
    	
    	ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	boolean res = SDKFactory.getClientFacade(appContext).updateRegIdOfClient(appContext, regid);
	    	return res;
	    }
	    else {
	    	return false;
	    }
    }
    
    /**
     * Borrar del baas el regid almacenado.
     */
    public void removeRegistrationIdFromBackend() {
      // TODO
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public boolean sendNotificationToPushChannel(String nombreCanalPush, String msgKey, String msgValue) throws UnsupportedEncodingException, ClientProtocolException, IOException {
    	ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	boolean res = SDKFactory.getClientFacade(appContext).sendNotificationToPushChannel(appContext, nombreCanalPush, msgKey, msgValue);
	    	return res;
	    }
	    else {
	    	return false;
	    }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
