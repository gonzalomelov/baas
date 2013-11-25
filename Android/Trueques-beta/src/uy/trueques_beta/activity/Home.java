package uy.trueques_beta.activity;

import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.CrearTrueque.CrearTruequeListener;
import uy.trueques_beta.activity.VerOfertasPendientes.VerOfertasPendientesListener;
import uy.trueques_beta.activity.VerRSS.VerRSSListener;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.activity.VerTruequesHechos.VerTruequesHechosListener;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.RSS;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.fragment.Fragment1;
import uy.trueques_beta.negocio.Factory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class Home extends Activity implements VerTruequesListener, CrearTruequeListener, VerOfertasPendientesListener, VerTruequesHechosListener, VerRSSListener{

	private String[] opcionesMenu;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	public int[] imagenesMenu;
	private ActionBarDrawerToggle conmutador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//Inicializo GCM
		SDKFactory.getGCMService(this);
		
		//+++ DRAWER
		opcionesMenu = new String[] {"Perfil", "Publicar Trueque", "Ver Trueques", "Ofertas Pendientes", "Trueques Realizados", "Ver RSS","Cerrar Sesión"};
		imagenesMenu= new int[] {R.drawable.ic_perfil, R.drawable.ic_crear_trueque, R.drawable.ic_ver_trueques,R.drawable.ic_ver_ofertas,R.drawable.ic_trueques_ok, R.drawable.ic_rss3, R.drawable.ic_cerrar};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
 
        //+++Conmutador
        conmutador = new ActionBarDrawerToggle(this, this.drawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name){
        	
        };
        //+++
        drawerList.setAdapter(new /*ArrayAdapter<String>*/HomeAdapter(getActionBar().getThemedContext(),
        	    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ?
        	    		R.layout.list_item_home
        	    /*android.R.layout.simple_list_item_activated_1*/ :
        	    android.R.layout.simple_list_item_1, opcionesMenu));
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
	                    fragment = new VerTruequesHechos();
	                    ((VerTruequesHechos)fragment).setVerTruequesHechosListener(Home.this);
	                    break;
	                case 5:
	                    fragment = new VerRSS();
	                    ((VerRSS)fragment).setVerRSSListener(Home.this);
	                    break;
	                case 6:
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
	        		editor.remove("isBloqueado");
	        		editor.commit();
	        		// Voy al inicio (Main)
	        		Intent intent = new Intent(Home.this, Main.class);
	        		//intent.putExtra("mail", mail);
	        		startActivity(intent);
	        		finish();
	            }
	        }
	    });
		String from=null;
		Intent intent = this.getIntent();
		if(intent.getExtras()!=null)
			from=intent.getExtras().getString("from", "nada");
		if(from==null || !from.equals("VistaTrueque")){
			//Inicio en Perfil
			FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, new Perfil()).commit();
	        drawerList.setItemChecked(0, true);
	        getActionBar().setTitle(opcionesMenu[0]);
		}else{
			//Voy a OfertasPendientes
			android.app.Fragment fragment= new VerOfertasPendientes();
            ((VerOfertasPendientes)fragment).setVerOfertasPendientesListener(Home.this);
			FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	        drawerList.setItemChecked(3, true);
	        getActionBar().setTitle(opcionesMenu[3]);
		}
		
        SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
        if(prefs.getBoolean("PrimeraVez", true)){
	        //Inicio con Drawer abierto
			drawerLayout.openDrawer(drawerList);
	        
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("PrimeraVez", false);
			editor.commit();
        }
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		drawerLayout.setDrawerListener(conmutador);
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
		drawerLayout.openDrawer(drawerList);
		Toast.makeText(this, "Para navegar usa el menú", Toast.LENGTH_SHORT).show();
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
	public void onTruequeSeleccionado(Trueque t){//int idTrueque) {
		//IR a VistaTrueque con IdTrueque
		Intent intent = new Intent(Home.this, VistaTrueque.class);
		//intent.putExtra("idTrueque", (int)idTrueque);
		intent.putExtra("Trueque", t.toJson());
		startActivity(intent);
	}



	@Override
	public void onOfertaSeleccionado(Oferta o) {
		//IR a VistaOfertaPend con idOferta
		Intent intent = new Intent(Home.this, VistaOfertaPend.class);
		intent.putExtra("Oferta", o.toJson());
		startActivity(intent);
	}

	@Override
	public void onTruequeHechoSeleccionado(Trueque t) {
		//IR a VistaTrueque con IdTrueque
		Intent intent = new Intent(Home.this, VistaTruequeHecho.class);
		//intent.putExtra("idTrueque", (int)idTrueque);
		intent.putExtra("Trueque", t.toJson());
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (conmutador.onOptionsItemSelected(item)){
			return true;
		}
		switch (item.getItemId()) {
        case R.id.action_settings:
            startActivity(new Intent(Home.this, Configuracion.class));
        	return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		conmutador.syncState();
	}



	@Override
	public void onRSSSeleccionado(RSS r) {
		// TODO Auto-generated method stub
		
	}
}
