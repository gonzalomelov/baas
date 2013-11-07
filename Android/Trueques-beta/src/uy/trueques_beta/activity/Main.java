package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.negocio.Factory;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity {

    private long back_pressed;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //+++
        Factory.getObjetoCtrl();
        Factory.getUsuarioCtrl();
        //+++++
        
        //COMPRUEBO SI EL USUARIO YA ESTA LOGEADO
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
        String mail = prefs.getString("mail", "");
        if(!mail.equals("") && Factory.getUsuarioCtrl().getUsuario(mail)!=null){ 
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
