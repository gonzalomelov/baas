package uy.com.group05.baasclient.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baasclient.sdk.entities.SimplePushChannelDTO;
import uy.com.group05.baasclient.sdk.utils.AssetsPropertyReader;
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
import com.google.gson.Gson;
import com.google.gson.reflect.*;

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
        	/*new AsyncTask<Void, Void, String>() {
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
            }.execute(null, null, null);*/
            
            
            
            
            
            
            
            
        } else {
            Log.i(TAG, "No se encontr� Google Play Services.");
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
     * Guarda en {@code SharedPreferences} el registration ID y la versi�n de la aplicaci�n.
     *
     * @param activity contexto de la aplicaci�n.
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
     * Borra del {@code SharedPreferences} el registration ID y la versi�n de la aplicaci�n.
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
     * Devuelve el registration ID de la aplicaci�n en GCM, si existe.
     * <p>
     * Si el resultado es vac�o, la aplicaci�n se tiene que registrar.
     *
     * @return registration ID, o vac�o si no existe.
     */
    public String getRegistrationId() {
        final SharedPreferences prefs = getGcmPreferences(appContext);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "No se encontr� regId.");
            return "";
        }
        // Si la app cambi� de versi�n, se tiene que registrar de nuevo
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(appContext);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "Cambi� la versi�n de la aplicaci�n.");
            return "";
        }
        return registrationId;
    }

    /**
     * Registra la aplicaci�n con GCM.
     * <p>
     * Guarda el regid y la versi�n de la app en {@code SharedPreferences} de la aplicaci�n.
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
     * Des-registra la aplicaci�n de GCM.
     * <p>
     * Nota: No se deber�a usar, a no ser que se desinstale la aplicaci�n. Usar {@code removeRegistrationIdFromBackend()}
     * <p>
     * Borra el regid y la versi�n de la app del {@code SharedPreferences} de la aplicaci�n.
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
    
    private boolean sendNotificationToPushChannel(Context context, String nombreCanal, String msgKey, String msgValue)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/push/sendNotificationToPushChannel";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
			     context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		String accessToken = prefs.getString("accessToken", "");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("accessToken", accessToken);
		
		List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
		formParameters.add(new BasicNameValuePair("appName", appName));
		formParameters.add(new BasicNameValuePair("pushChanName", nombreCanal));
		formParameters.add(new BasicNameValuePair("msgKey", msgKey));
		formParameters.add(new BasicNameValuePair("msgValue", msgValue));
		
		httpPost.setEntity(new UrlEncodedFormEntity(formParameters));
		
		android.util.Log.i("GCM SDK", "accessToken: " + accessToken);
		android.util.Log.i("GCM SDK", "appName: " + appName);
		android.util.Log.i("GCM SDK", "pushChanName: " + nombreCanal);
		android.util.Log.i("GCM SDK", "msgKey: " + msgKey);
		android.util.Log.i("GCM SDK", "msgValue: " + msgValue);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		android.util.Log.i("GCM SDK", "statusCode: " + statusCode);
		
		if (statusCode == HttpStatus.SC_OK) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public boolean sendNotificationToPushChannel(String nombreCanalPush, String msgKey, String msgValue) throws UnsupportedEncodingException, ClientProtocolException, IOException {
    	ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	boolean res = sendNotificationToPushChannel(appContext, nombreCanalPush, msgKey, msgValue);
	    	return res;
	    }
	    else {
	    	return false;
	    }
    }
    
    private List<SimplePushChannelDTO> getPushChannels(Context context)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {    	
    	String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/push/getPushChannels";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		httpGet.setHeader("appName", appName);
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		Log.i("TAG", "statusCode: " + statusCode);
		
		if (statusCode != HttpStatus.SC_OK) {
			return new ArrayList<SimplePushChannelDTO>();
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
			
		Gson gson = new Gson();
		
		//listaCanalesDTO = (List<SimplePushChannelDTO>) gson.fromJson(br, new TypeToken<ArrayList<SimplePushChannelDTO>>() {}.getType());
		SimplePushChannelDTO[] arrayCanalesDTO = gson.fromJson(br, SimplePushChannelDTO[].class);
		List<SimplePushChannelDTO> listaCanalesDTO = arrayCanalesDTO != null && arrayCanalesDTO.length > 0 ? Arrays.asList(arrayCanalesDTO) : new ArrayList<SimplePushChannelDTO>();
		Log.i("TAG", "Cantidad de canales: " + listaCanalesDTO.size());
		
		return listaCanalesDTO;
	}
    
    public List<SimplePushChannelDTO> getPushChannels() throws UnsupportedEncodingException, ClientProtocolException, IOException {
    	ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	List<SimplePushChannelDTO> res = getPushChannels(appContext);
	    	return res;
	    }
	    else {
	    	return new ArrayList<SimplePushChannelDTO>();
	    }
    }
    
    private boolean subscribeToPushChannel(Context context, String nombreCanal)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/push/subscribe";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
			     context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		String accessToken = prefs.getString("accessToken", "");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("accessToken", accessToken);
		
		List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
		formParameters.add(new BasicNameValuePair("appName", appName));
		formParameters.add(new BasicNameValuePair("pushChanName", nombreCanal));
		
		httpPost.setEntity(new UrlEncodedFormEntity(formParameters));
		
		android.util.Log.i("GCM SDK", "accessToken: " + accessToken);
		android.util.Log.i("GCM SDK", "appName: " + appName);
		android.util.Log.i("GCM SDK", "pushChanName: " + nombreCanal);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		android.util.Log.i("GCM SDK", "statusCode: " + statusCode);
		
		if (statusCode == HttpStatus.SC_OK) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public boolean subscribeToPushChannel(String nombreCanalPush) throws UnsupportedEncodingException, ClientProtocolException, IOException {
    	ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	boolean res = subscribeToPushChannel(appContext, nombreCanalPush);
	    	return res;
	    }
	    else {
	    	return false;
	    }
    }
    
}
