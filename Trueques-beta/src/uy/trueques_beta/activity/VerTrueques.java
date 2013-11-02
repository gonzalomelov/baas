package uy.trueques_beta.activity;

import java.util.ArrayList;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
//import uy.trueques_beta.activity.Home.AdaptadorObjetos;
import uy.trueques_beta.entities.Objeto;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;


public class VerTrueques extends Fragment implements AdapterView.OnItemClickListener{//extends Activity implements AdapterView.OnItemClickListener {

	private AdaptadorTrueque adaptador;
	private String mail;
	private ListView lstTrueques;
	private TextView lblVerTrueques;
	private Object[] trueques;
	private VerTruequesListener listener;
			
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_ver_trueques, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_ver_trueques);
		
		//Obtengo datos del usuario
        SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
//		Bundle bundle = this.getIntent().getExtras();
//		this.mail = bundle.getString("mail");
		//***Pruba
		int size=Factory.getTruequeCtrl().getTrueques().size();
		
		lblVerTrueques = (TextView)getView().findViewById(R.id.LblVerTrueques);
		lblVerTrueques.setText(lblVerTrueques.getText().toString() +" ("+ size +")");
		 
		trueques = Factory.getTruequeCtrl().getTrueques().toArray();
		this.adaptador = new AdaptadorTrueque(this, R.layout.list_item_trueques, trueques);
		lstTrueques = (ListView)getView().findViewById(R.id.LstTrueques);
		lstTrueques.setAdapter(adaptador);
		
		lstTrueques.setOnItemClickListener(this);
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.ver_trueques, menu);
//		return true;
//	}
	
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		super.onBackPressed();
//	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		Trueque t =((Trueque)adapterView.getItemAtPosition(position));//.getAdapter().getItem(position));
//		Intent intent = new Intent(VerTrueques.this, VistaTrueque.class);
//        intent.putExtra("idTrueque", (int)t.getIdTrueque());
//        intent.putExtra("mail", mail);
//		startActivity(intent);
		
		//AVISO AL ACTIVITY
		if (listener!=null) {
            listener.onTruequeSeleccionado(t.getIdTrueque());
        }
	}
	
	public interface VerTruequesListener {
        void onTruequeSeleccionado(int idTrueque);
    }
 
    public void setVerTruequesListener(VerTruequesListener listener) {
        this.listener=listener;
    }
	
	
	//Creo la clase para el adaptador
	class AdaptadorTrueque extends ArrayAdapter {
		 
	    //Activity context;
	    Activity context;
		//Object[] trueques;
	 
	    public AdaptadorTrueque(Fragment context, int textViewResourceId, Object[] objects) {
			super(context.getActivity(), textViewResourceId, objects);
			//this.trueques = objects;
			this.context = context.getActivity();
	    }
//	    AdaptadorTrueque(Activity context, int resource, Trueque[] objects) {
//            super(context, R.layout.list_item_trueques, trueques);
//            //this.trueques = Factory.getTruequeCtrl().getTrueques().toArray();
//            this.context = context;
//        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
	        LayoutInflater inflater = context.getLayoutInflater();//getSystemService(Context.LAYOUT_INFLATER_SERVICE);//
	        View item = inflater.inflate(R.layout.list_item_trueques, null);
	        
	        TextView itemTrueque = (TextView)item.findViewById(R.id.TextItemTrueque);
	        itemTrueque.setText(((Trueque)trueques[position]).getObjeto().getNombre() + 
	        		"   $"+ ((Trueque)trueques[position]).getObjeto().getValor());
	 
//	        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//	        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
	 
	        return(item);
        }
	}


}
