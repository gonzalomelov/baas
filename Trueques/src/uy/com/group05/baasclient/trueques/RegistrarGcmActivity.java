package uy.com.group05.baasclient.trueques;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RegistrarGcmActivity extends Activity {

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

    String regid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrargcm);
        mDisplay = (TextView) findViewById(R.id.display_resGCM);
        chk_gcm = (CheckBox) findViewById(R.id.chk_regGCM);

        context = getApplicationContext();

        // Verifico que esté instalado Google Play Services en el dispositivo (necesario para GCM).
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (!regid.isEmpty()) {
            	registrado = true;
            	chk_gcm.setChecked(true);
            	mDisplay.setText("Ya esta registrado con el id: " + regid + "\n");
            }
            else {
            	mDisplay.setText("No está registrado.");
            }
        } else {
            Log.i(TAG, "No se encontró Google Play Services.");
        }
    }
    
    public void action_registrarDesregistrarGCM(View view) {
    	if (registrado) {
    		mDisplay.setText("");
    		unRegisterInBackground();
    	}
    	else {
    		registerInBackground();
    	}
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
     */
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
                }
            }
        }.execute(null, null, null);
    }
    
    /**
     * Des-registra la aplicación de GCM.
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
    }

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
        return getSharedPreferences(RegistrarGcmActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    
    /**
     * Mandar al baas el regid almacenado.
     */
    private void sendRegistrationIdToBackend() {
      // TODO
    }
    
    /**
     * Borrar del baas el regid almacenado.
     */
    private void removeRegistrationIdFromBackend() {
      // TODO
    }
}

