package uy.trueques_beta.activity;

import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.VerOfertasPendientes.AdaptadorOferta;
import uy.trueques_beta.activity.VerTrueques.AdaptadorTrueque;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VerUsuarios extends Activity implements AdapterView.OnItemClickListener{
	private AdaptadorUsuario adaptador;
	private String mail;
	private ListView lstUsuarios;
	private TextView lblUsuarios;
	private Object[] usuarios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_usuarios);
		
		//Inicializo GCM
		SDKFactory.getGCMService(this);
		
		//Obtengo datos del usuario
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
//		Bundle bundle = this.getIntent().getExtras();
//		this.mail = bundle.getString("mail");
		//***Pruba
		usuarios = Factory.getUsuarioCtrl().getUsuarios(this).toArray();
		int size=usuarios.length;
		
		lblUsuarios = (TextView)findViewById(R.id.LblUsuarios);
		lblUsuarios.setText(lblUsuarios.getText().toString() +" ("+ size +")");
		 
		this.adaptador = new AdaptadorUsuario(this, R.layout.list_item_usuarios, usuarios);
		lstUsuarios = (ListView)findViewById(R.id.LstUsuarios);
		lstUsuarios.setAdapter(adaptador);
		
		lstUsuarios.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_usuarios, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		Usuario u =((Usuario)adapterView.getItemAtPosition(position));//.getAdapter().getItem(position));
		//Voy a VistaUsuario
		Intent intent = new Intent(VerUsuarios.this, VistaUsuario.class);
        intent.putExtra("mailUsuario", u.getMail());
		startActivity(intent);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	        case R.id.action_cerrar:
	        	//BORRO USUARIO
	        	SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
	        	SharedPreferences.Editor editor = prefs.edit();
	        	editor.remove("mail");
	        	editor.commit();
	        	// Voy al inicio (Main)
	        	Intent intent = new Intent(VerUsuarios.this, Main.class);
	        	//intent.putExtra("mail", mail);
	        	startActivity(intent);
	        	finish();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}

	
		//Creo la clase para el adaptador
		class AdaptadorUsuario extends ArrayAdapter {
		    Activity context;
		 
		    public AdaptadorUsuario(Activity context, int textViewResourceId, Object[] objects) {
				super(context, textViewResourceId, objects);
				//this.trueques = objects;
				this.context = context;
		    }
	 
	        public View getView(int position, View convertView, ViewGroup parent) {
		        LayoutInflater inflater = context.getLayoutInflater();//getSystemService(Context.LAYOUT_INFLATER_SERVICE);//
		        View item = inflater.inflate(R.layout.list_item_usuarios, null);
		        
		        TextView nombre = (TextView)item.findViewById(R.id.Nombre);
		        if(((Usuario)usuarios[position]).isBloqueado())
		        	nombre.setText("Nombre: "+((Usuario)usuarios[position]).getNombre()+"  [B]");
		        else
		        	nombre.setText("Nombre: "+((Usuario)usuarios[position]).getNombre());
		        TextView mail = (TextView)item.findViewById(R.id.Email);
		        mail.setText("Email: "+((Usuario)usuarios[position]).getMail());
		 
//		        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//		        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
		 
		        return(item);
	        }
		}

}
