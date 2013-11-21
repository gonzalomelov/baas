package uy.trueques_beta.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.sync.BaasProviderObserver;
import uy.com.group05.baassdk.sync.Constants;
import uy.com.group05.baassdk.*;
import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.negocio.Factory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity {

	private Context context;
	
    private long back_pressed;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = getApplicationContext();
		
		MyApplication myApplication = (MyApplication)(context.getApplicationContext());
		
		List<String> entities = new ArrayList<String>();
		entities.add("Admin");
		entities.add("Objeto");
		entities.add("Oferta");
		entities.add("Trueque");
		entities.add("Usuario");
		
		myApplication.init(this, entities); 

        //+++
        Factory.getObjetoCtrl();
        Factory.getUsuarioCtrl();
        //+++++
        
        //COMPRUEBO SI EL USUARIO YA ESTA LOGEADO
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
        String mail = prefs.getString("mail", "");
        if(!mail.equals("") && Factory.getUsuarioCtrl().getUsuario(this, mail)!=null){ 
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
}
