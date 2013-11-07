package uy.trueques_beta.activity;

import java.util.List;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.VerTrueques.AdaptadorTrueque;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.negocio.Factory;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

	private AdaptadorOferta adaptador;
	private String mail;
	private ListView lstOfertas;
	private TextView lblVerOfertas;
	private Object[] ofertas;
	private VerOfertasPendientesListener listener;
	
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
		
		//Obtengo las ofertas pendientes
		ofertas = Factory.getTruequeCtrl().getOfertasPendientes(mail).toArray();
		int size= ofertas.length;
				
		lblVerOfertas = (TextView)getView().findViewById(R.id.LblVerOfertas);
		lblVerOfertas.setText(lblVerOfertas.getText().toString() +" ("+ size +")");
		 
		//trueques = Factory.getTruequeCtrl().getTrueques().toArray();
		this.adaptador = new AdaptadorOferta(this, R.layout.list_item_ofertas, ofertas);
		lstOfertas = (ListView)getView().findViewById(R.id.LstOfertas);
		lstOfertas.setAdapter(adaptador);
		
		lstOfertas.setOnItemClickListener(this);
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
	        listener.onOfertaSeleccionado(o.getIdOferta());
	    }
	}

	public interface VerOfertasPendientesListener {
	    void onOfertaSeleccionado(int idOferta);
	}
	
	public void setVerOfertasPendientesListener(VerOfertasPendientesListener listener) {
	    this.listener=listener;
	}
	
	//Creo la clase para el adaptador
	class AdaptadorOferta extends ArrayAdapter {
		 
	    Activity context;
		//Object[] ofers;
	 
	    public AdaptadorOferta(Fragment context, int textViewResourceId, Object[] objects) {
			super(context.getActivity(), textViewResourceId, objects);
			//this.ofers = objects;
			this.context = context.getActivity();
	    }
//		    AdaptadorTrueque(Activity context, int resource, Trueque[] objects) {
//	            super(context, R.layout.list_item_trueques, trueques);
//	            //this.trueques = Factory.getTruequeCtrl().getTrueques().toArray();
//	            this.context = context;
//	        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        	LayoutInflater inflater = context.getLayoutInflater();
	        View item = inflater.inflate(R.layout.list_item_ofertas, null);
	        
	        TextView itemTrueque = (TextView)item.findViewById(R.id.TextItemOferta);
	        itemTrueque.setText(((Oferta)ofertas[position]).getObjeto().getNombre() + 
	        		"   $"+ ((Oferta)ofertas[position]).getObjeto().getValor());
	 
//		        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//		        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
	 
	        return(item);
        }
	}

}
