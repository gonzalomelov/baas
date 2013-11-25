package uy.trueques_beta.activity;

import java.util.List;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.VerTrueques.AdaptadorTrueque;
import uy.trueques_beta.activity.VerTrueques.VerTruequeTask;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VerOfertasPendientes extends Fragment implements AdapterView.OnItemClickListener {//extends Activity implements AdapterView.OnItemClickListener {
	private VerOfertasPendientesTask mAuthTask = null;

	private AdaptadorOferta adaptador;
	private String mail;
	private ListView lstOfertas;
	private TextView lblVerOfertas;
	private Object[] ofertas;
	private VerOfertasPendientesListener listener;
	int size;
	private ProgressDialog pd;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_ver_ofertas_pendientes, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_ver_ofertas_pendientes);
		
		//Obtengo datos del usuario
        SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		if(mAuthTask==null){
			pd = ProgressDialog.show(VerOfertasPendientes.this.getActivity(),"Ofertas Pendientes","Cargando Ofertas",true,false,null);
			mAuthTask = new VerOfertasPendientesTask();
			mAuthTask.execute((Void) null);
		}
		
//		//Obtengo las ofertas pendientes
//		ofertas = Factory.getTruequeCtrl().getOfertasPendientes(mail).toArray();
//		int size= ofertas.length;
//				
//		lblVerOfertas = (TextView)getView().findViewById(R.id.LblVerOfertas);
//		lblVerOfertas.setText(lblVerOfertas.getText().toString() +" ("+ size +")");
		 
		//trueques = Factory.getTruequeCtrl().getTrueques().toArray();
//		this.adaptador = new AdaptadorOferta(this, R.layout.list_item_ofertas, ofertas);
//		lstOfertas = (ListView)getView().findViewById(R.id.LstOfertas);
//		lstOfertas.setAdapter(adaptador);
//		
//		lstOfertas.setOnItemClickListener(this);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.ver_ofertas_pendientes, menu);
//		return true;
//	}
	
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		Oferta o =((Oferta)adapterView.getItemAtPosition(position));//.getAdapter().getItem(position));
//		Intent intent = new Intent(VerOfertasPendientes.this, VistaOfertaPend.class);
//        intent.putExtra("idOferta", (int)o.getIdOferta());
//        intent.putExtra("mail", mail);
//		startActivity(intent);
//	}
		//AVISO AL ACTIVITY
		if (listener!=null) {
	        listener.onOfertaSeleccionado(o);
	    }
	}

	public interface VerOfertasPendientesListener {
	    void onOfertaSeleccionado(Oferta o);
	}
	
	public void setVerOfertasPendientesListener(VerOfertasPendientesListener listener) {
	    this.listener=listener;
	}
	
	//Creo la clase para el adaptador
	class AdaptadorOferta extends ArrayAdapter {
		 
	    Activity context;
	 
	    public AdaptadorOferta(Fragment context, int textViewResourceId, Object[] objects) {
			super(context.getActivity(), textViewResourceId, objects);
			//this.ofers = objects;
			this.context = context.getActivity();
	    }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        	LayoutInflater inflater = context.getLayoutInflater();
        	View item = inflater.inflate(R.layout.list_item_trueques, null);

	        Oferta ofer= ((Oferta)ofertas[position]);
	        //String nomTrueque= Factory.getTruequeCtrl().getTrueque(ofer.getIdTrueque()).getObjeto().getNombre();
	        //NOMBRE OBJETO
	        TextView objeto = (TextView)item.findViewById(R.id.Objeto);
	        objeto.setText(ofer.getObjeto().getNombre());//+" por "+nomTrueque);
	        //PRECIO
	        TextView precio = (TextView)item.findViewById(R.id.Precio);
	        precio.setText("$"+ofer.getObjeto().getValor());
	        //DUENIO
	        TextView duenio = (TextView)item.findViewById(R.id.Duenio);
	        duenio.setText("Ofrecido por: "+ofer.getUsuario());
	        //IMAGEN
	        ImageView img = (ImageView)item.findViewById(R.id.imgTrueque);
	        img.setImageBitmap(ofer.getImagen());
	        
	        return(item);
        }
	}

	public class VerOfertasPendientesTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			
			try{
				size=0;
				List<Oferta> ofs; 
				ofs=Factory.getTruequeCtrl().getOfertasPendientes(VerOfertasPendientes.this.getActivity(), mail);
				if(ofs!=null){
					ofertas=ofs.toArray();
					size=ofertas.length;
					return true;
				}
				else
					return false;
			}catch(Exception e){
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);
			if(pd!=null & pd.isShowing())
				pd.dismiss();

			if (success) {
				Log.i("[VerOfertasPendientes]:", "EXITO!");
				//Obtengo las ofertas pendientes
				
				//size= ofertas.length;
						
				lblVerOfertas = (TextView)getView().findViewById(R.id.LblVerOfertas);
				lblVerOfertas.setText(lblVerOfertas.getText().toString() +" ("+ size +")");
				adaptador = new AdaptadorOferta(VerOfertasPendientes.this, R.layout.list_item_ofertas, ofertas);
				lstOfertas = (ListView)getView().findViewById(R.id.LstOfertas);
				lstOfertas.setAdapter(adaptador);
				
				lstOfertas.setOnItemClickListener(VerOfertasPendientes.this);
		    	
			} else {
				Log.i("[VerOfertasPendientes]:", "ERROR");
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			if(pd!=null & pd.isShowing())
				pd.dismiss();
			//showProgress(false);
		}
	}
	

}
