package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.CrearTrueque.CrearTruequeListener;
import uy.trueques_beta.activity.VerOfertasPendientes.VerOfertasPendientesListener;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.fragment.Fragment1;
import uy.trueques_beta.negocio.Factory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class Home extends Activity implements VerTruequesListener, CrearTruequeListener, VerOfertasPendientesListener{

	private String[] opcionesMenu;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	public int[] imagenesMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//+++ DRAWER
		opcionesMenu = new String[] {"Perfil", "Publicar Trueque", "Ver Trueques", "Ofertas Pendientes", "Cerrar Sesión"};
		imagenesMenu= new int[] {R.drawable.ic_perfil, R.drawable.ic_crear_trueque, R.drawable.ic_ver_trueques,R.drawable.ic_ver_ofertas, R.drawable.ic_cerrar};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
 
        drawerList.setAdapter(new /*ArrayAdapter<String>*/HomeAdapter(getActionBar().getThemedContext(),
        	    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ?
        	    		R.layout.list_item_home
        	    /*android.R.layout.simple_list_item_activated_1*/ :
        	    android.R.layout.simple_list_item_1, opcionesMenu));
        
//		        drawerList.setAdapter(new ArrayAdapter<String>(
//		                getActionBar().getThemedContext(),
//		            android.R.layout.simple_list_item_1, opcionesMenu));
        //+++++
		//++++
		drawerList.setOnItemClickListener(new OnItemClickListener() {
	        private String tituloSeccion;

			@Override
	        public void onItemClick(AdapterView parent, View view, int position, long id) {
	 
	            android.app.Fragment fragment = null;
	            fragment = new Fragment1();
	 
	            boolean cerrar= false;
	            switch (position) {
	                case 0:
	                    fragment = new Perfil();//new Perfil();
	                    
	                    break;
	                case 1:
	                    fragment = new CrearTrueque();
	                    ((CrearTrueque)fragment).setCrearTruequeListener(Home.this);
	                    break;
	                case 2:
	                    fragment = new VerTrueques();
	                    ((VerTrueques)fragment).setVerTruequesListener(Home.this);
	                    break;
	                case 3:
	                    fragment = new VerOfertasPendientes();
	                    ((VerOfertasPendientes)fragment).setVerOfertasPendientesListener(Home.this);
	                    break;
	                case 4:
	                    cerrar = true;
	                    break;
	            }
	            
	            if(!cerrar){
	            FragmentManager fragmentManager = getFragmentManager();
	 
	            fragmentManager.beginTransaction()
	                .replace(R.id.content_frame, fragment)
	                .commit();
	 
	            drawerList.setItemChecked(position, true);
	 
	            tituloSeccion = opcionesMenu[position];
	            getActionBar().setTitle(tituloSeccion);
	 
	            drawerLayout.closeDrawer(drawerList);
	            }
	            else{
	            	//BORRO USUARIO
	        		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
	        		SharedPreferences.Editor editor = prefs.edit();
	        		editor.remove("mail");
	        		editor.commit();
	        		// Voy al inicio (Main)
	        		Intent intent = new Intent(Home.this, Main.class);
	        		//intent.putExtra("mail", mail);
	        		startActivity(intent);
	        		finish();
	            }
	        }
	    });
		
		//Inicio en Perfil
		FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Perfil()).commit();
        drawerList.setItemChecked(0, true);
        getActionBar().setTitle(opcionesMenu[0]);
		//Inicio con Drawer abierto
		drawerLayout.openDrawer(drawerList);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}


	@Override
	public void onTruequeCreado() {
		//IR a Perfil
		Toast.makeText(this, "Trueque creado con exito", Toast.LENGTH_LONG).show();
		// Voy Perfil
		FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Perfil()).commit();
        drawerList.setItemChecked(0, true);
        getActionBar().setTitle(opcionesMenu[0]);
	}



	@Override
	public void onTruequeSeleccionado(int idTrueque) {
		//IR a VistaTrueque con IdTrueque
		Intent intent = new Intent(Home.this, VistaTrueque.class);
		intent.putExtra("idTrueque", (int)idTrueque);
		startActivity(intent);
	}



	@Override
	public void onOfertaSeleccionado(int idOferta) {
		//IR a VistaOfertaPend con idOferta
		Intent intent = new Intent(Home.this, VistaOfertaPend.class);
		intent.putExtra("idOferta", (int)idOferta);
		startActivity(intent);
	}

	
	//Creo la clase para el adaptador
	class HomeAdapter extends ArrayAdapter {
		 
	    Context context;
		//Object[] ofers;
	 
	    public HomeAdapter(Context context, int textViewResourceId, Object[] objects) {
			super(context, textViewResourceId, objects);
			//this.ofers = objects;
			this.context = context;
	    }
//			    AdaptadorTrueque(Activity context, int resource, Trueque[] objects) {
//		            super(context, R.layout.list_item_trueques, trueques);
//		            //this.trueques = Factory.getTruequeCtrl().getTrueques().toArray();
//		            this.context = context;
//		        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        	LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //LayoutInflater inflater = context.getLayoutInflater();
	        View item = inflater.inflate(R.layout.list_item_home, null);
	        
	        TextView itemText = (TextView)item.findViewById(R.id.text);
	        itemText.setText(opcionesMenu[position]);
	        
	        ImageView itemImage = (ImageView)item.findViewById(R.id.icon);
	        itemImage.setImageResource(imagenesMenu[position]);
	        
	        //if(Factory.getUsuarioCtrl().getUsuario(this.toString()mail))
	 
//			        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//			        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
	 
	        return(item);
        }
	}
}
