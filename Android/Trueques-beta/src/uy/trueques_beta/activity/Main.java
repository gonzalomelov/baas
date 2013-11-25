package uy.trueques_beta.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import persistence.Cursor;
import persistence.Operador;
import persistence.Query;
import sdk.application.ApplicationInfo;
import sdk.classes.JSON;
import sdk.servicios.Clientes;
import sdk.servicios.Persistencia;
import uy.com.group05.baassdk.GCMService;
import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.R;
import uy.trueques_beta.entities.Admin;
import uy.trueques_beta.negocio.Factory;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

public class Main extends Activity {

	private Context context;
	
    private long back_pressed;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = getApplicationContext();
		
        //####### Inicialización SDK nuestro
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		List<String> syncEntities = new ArrayList<String>();
		syncEntities.add("Admin");
		syncEntities.add("Objeto");
		syncEntities.add("Trueque");
		syncEntities.add("Usuario");
		syncEntities.add("Oferta");
		
		List<String> commonEntities = new ArrayList<String>();
//		commonEntities.add("Admin");
//		commonEntities.add("Objeto");
//		commonEntities.add("Trueque");
//		commonEntities.add("Usuario");
//		commonEntities.add("Oferta");
		
		myApplication.init(this, syncEntities, commonEntities); 

		//####### Inicialización SDK ellos
		//Setear id de aplicacion
        ApplicationInfo.setAppId("3");
        String deviceId = Secure.getString(Main.this.getContentResolver(), Secure.ANDROID_ID);
        ApplicationInfo.setDeviceId(deviceId);
		
        String[] columnas = new String[5];
        columnas[0] = "_id";
        columnas[1] = "nombre";
        columnas[2] = "mail";
        columnas[3] = "pass";
        columnas[4] = "json";
        
        try {
        	int tabla = Persistencia.crearTabla("admin", columnas, getApplicationContext());
        }
        catch (Exception e) {
        	
        }
		
		//+++
        Factory.getObjetoCtrl();
        Factory.getUsuarioCtrl();
        //+++++
        
        //COMPRUEBO SI EL USUARIO YA ESTA LOGEADO
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
        String mail = prefs.getString("mail", "");
        if(!mail.equals("")) {// && Factory.getUsuarioCtrl().getUsuario(this, mail)!=null){ 
        	startActivity(new Intent(Main.this, Home.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void login(View view){
    	Intent intent = new Intent(this, Login.class);
    	startActivity(intent);
    }
    
    //+++++
    public void loginExterno(View view){
    	Intent intent = new Intent(this, LoginExterno.class);
    	startActivity(intent);
    }
    //+++++
    
    public void registrar(View view){
    	Intent intent = new Intent(this, Registrar.class);
    	startActivity(intent);
    }
    
    @Override
	public void onBackPressed()
	{
	    if (back_pressed + 2000 > System.currentTimeMillis()){ 
	    	super.onBackPressed();
	    	finish();
	    	//System.exit(0);
		}
	    else 
	    	Toast.makeText(getBaseContext(), "Presiona de nuevo para salir", Toast.LENGTH_SHORT).show();
	    back_pressed = System.currentTimeMillis();
	}
    
    public void test() {
    	new AsyncTask<Void, Void, Boolean>() {
    		
		    @Override
		    protected Boolean doInBackground(Void... params) {
		    	
		    	//####### Inicialización SDK de ellos
				//Setear id de aplicacion
		        ApplicationInfo.setAppId("3");
		        String deviceId = Secure.getString(Main.this.getContentResolver(), Secure.ANDROID_ID);
		        ApplicationInfo.setDeviceId(deviceId);
				
		        String[] columnas = new String[5];
		        columnas[0] = "_id";
		        columnas[1] = "nombre";
		        columnas[2] = "mail";
		        columnas[3] = "pass";
		        columnas[4] = "json";
		        
		        try {
		        	int tabla = Persistencia.crearTabla("admin", columnas, getApplicationContext());
		        	
		        	String registro = Clientes.registrarse("gmelo@gmelo.com", "gpass", "gname", "gnick", ApplicationInfo.getDeviceId(), Main.this);
		        	
		        	String login = Clientes.login("gmelo@gmelo.com", "gpass", Main.this);
		        	
		        	Admin admin = new Admin("gname", "gmelo@gmelo.com", "gpass");
		        	
		        	Gson gson = new Gson();
		            
		            JSON json = new JSON();
		            json.addAtributo("nombre", "gname");
		            json.addAtributo("mail", "gmelo@gmelo.com");
		            json.addAtributo("pass", "gpass");
		            json.addAtributo("json", gson.toJson(admin));
		            
		            int insert = Persistencia.insertJson(json, "admin");
		            
		            Query q = new Query();
		            q.setTabla("admin");
		            q.setAtributo("_id");
		            q.setOperador(Operador.igual);
		            q.setValor("1");
		            
		            Cursor c = Persistencia.selectJson(q);
		            JSON[] jsons = c.getJsons();
		            JSON jsonObj = jsons[0];
		            Map<String, Object> jsonMap = jsonObj.getJson();
		            
		            Admin a = gson.fromJson((String)jsonMap.get("json"), Admin.class);
		            
		            a.getNombre();
		            
		        }
		        catch (Exception e) {
		        	int a = 1;
		        }
		        return true;
		    }
		    
		    @Override
            protected void onPostExecute(Boolean ok) {

            }
		}.execute(null, null, null);
    }
}
