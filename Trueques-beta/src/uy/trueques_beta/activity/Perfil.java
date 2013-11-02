package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil extends Fragment {//Activity {

	private String mail;
	private TextView lblNombre;
	private TextView lblEmail;
	private TextView lblRealizados;
	private TextView lblPublicados;
	private TextView lblAceptados;
//	private String[] opcionesMenu;
//	private DrawerLayout drawerLayout;
//	private ListView drawerList;
	private PerfilListener listener;
//	private static long back_pressed;
	
	 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_perfil);
		
		//Obtengo el mail del usuario
//		Bundle bundle = this.getIntent().getExtras();
//		this.mail = bundle.getString("mail");
		SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		Usuario u = Factory.getUsuarioCtrl().getUsuario(mail);
		if(u==null){
			Toast.makeText(this.getActivity().getBaseContext(), "U = NULL", Toast.LENGTH_SHORT).show();
			//finish();
		}
		
		//Seteo los datos del usuario
		lblNombre = (TextView)getView().findViewById(R.id.LblNombre);
		lblNombre.setText(u.getNombre());
		lblEmail = (TextView)getView().findViewById(R.id.LblEmail);
		lblEmail.setText(u.getMail());
		
		lblRealizados = (TextView)getView().findViewById(R.id.LblRealizados);
		lblRealizados.setText(String.valueOf(u.getRealizados()));
		lblAceptados = (TextView)getView().findViewById(R.id.LblAceptados);
		lblAceptados.setText(String.valueOf(u.getAceptados()));
		lblPublicados = (TextView)getView().findViewById(R.id.LblPublicados);
		lblPublicados.setText(String.valueOf(u.getPublicados()));
		
//		//Envio a CrearObjeto
//		getView().findViewById(R.id.BtnCrearTrueque).setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						crearTrueque();
//					}
//				});
//		//Envio a CrearObjeto
//		getView().findViewById(R.id.BtnVerTrueques).setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						verTrueques();
//					}
//				});
//		//Envio a CrearObjeto
//		getView().findViewById(R.id.BtnVerOferPend).setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						verOferPend();
//					}
//				});
//		//CERRAR SESION E ENVIAR AL INICIO
//		getView().findViewById(R.id.BtnCerrarSesion).setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View view) {
//						cerrarSesion();
//					}
//				});
	}
	
	public interface PerfilListener {
        void onPerfilSeleccionado(String accion);
    }
 
    public void setPerfilListener(PerfilListener listener) {
        this.listener=listener;
    }

//	public void crearTrueque(){
//		// Voy a crear un Trueque
//		Intent intent = new Intent(Perfil.this, CrearTrueque.class);
//		intent.putExtra("mail", mail);
//		//intent.putExtra("idUsuario", );
//		startActivity(intent);
//	}
//	public void verTrueques(){
//		// Voy a verTrueques
//		Intent intent = new Intent(Perfil.this, VerTrueques.class);
//		intent.putExtra("mail", mail);
//		startActivity(intent);
//	}
//	public void verOferPend(){
//		// Voy a verOfertasPendientes
//		Intent intent = new Intent(Perfil.this, VerOfertasPendientes.class);
//		intent.putExtra("mail", mail);
//		startActivity(intent);
//	}
//	public void cerrarSesion(){
//		//BORRO USUARIO
//		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
//		SharedPreferences.Editor editor = prefs.edit();
//		editor.remove("mail");
//		editor.commit();
////		// Voy al inicio (Main)
////		Intent intent = new Intent(Perfil.this, Main.class);
////		//intent.putExtra("mail", mail);
////		startActivity(intent);
//		finish();
//	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.perfil, menu);
//		return true;
//	}
	
//	@Override
//	public void onBackPressed()
//	{
////	        if (back_pressed + 2000 > System.currentTimeMillis()){ 
////	        	super.onBackPressed();
////	        	finish();
////	        	//System.exit(0);
////			}
////	        else 
////	        	Toast.makeText(getBaseContext(), "Presiona de nuevo para salir", Toast.LENGTH_SHORT).show();
////	        back_pressed = System.currentTimeMillis();
//	}

}
