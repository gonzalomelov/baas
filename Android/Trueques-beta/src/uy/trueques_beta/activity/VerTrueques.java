package uy.trueques_beta.activity;

import java.util.ArrayList;
import java.util.List;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.CrearTrueque.CrearTruequeTask;
//import uy.trueques_beta.activity.Home.AdaptadorObjetos;
import uy.trueques_beta.entities.Objeto;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
	private int size;
	private VerTruequeTask mAuthTask = null;
	private ProgressDialog pd;
			
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
		if(mAuthTask==null){
			pd = ProgressDialog.show(VerTrueques.this.getActivity(),"Ver Trueques","Cargando Trueques",true,false,null);
			mAuthTask = new VerTruequeTask();
			mAuthTask.execute((Void) null);
		}
		
//		trueques = Factory.getTruequeCtrl().getTruequesActivos(this.getActivity()).toArray();
//		int size=trueques.length;
		
		size=0;
		lblVerTrueques = (TextView)getView().findViewById(R.id.LblVerTrueques);
		lblVerTrueques.setText(lblVerTrueques.getText().toString());
		 
//		this.adaptador = new AdaptadorTrueque(this, R.layout.list_item_trueques, trueques);
		lstTrueques = (ListView)getView().findViewById(R.id.LstTrueques);
//		lstTrueques.setAdapter(adaptador);
		
		lstTrueques.setOnItemClickListener(this);
		
	}


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
            listener.onTruequeSeleccionado(t);//.getIdTrueque());
        }
	}
	
	public interface VerTruequesListener {
        void onTruequeSeleccionado(Trueque t);//int idTrueque);
    }
 
    public void setVerTruequesListener(VerTruequesListener listener) {
        this.listener=listener;
    }

    public class VerTruequeTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			
			size = 0;
			List<Trueque> ts =null;
			ts = Factory.getTruequeCtrl().getTruequesActivos(VerTrueques.this.getActivity());
			if(ts!=null){
				trueques=ts.toArray();
				size=trueques.length;
				return true;
			}
			else
				return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);
			if(pd!=null & pd.isShowing())
				pd.dismiss();

			if (success) {
				Log.i("[VerTrueques]:", "EXITO!");
				adaptador = new AdaptadorTrueque(VerTrueques.this, R.layout.list_item_trueques, trueques);
				lstTrueques.setAdapter(adaptador);
				lblVerTrueques.setText(lblVerTrueques.getText().toString() +" ("+ size +")");
		    	
			} else {
				Log.i("[VerTrueques]:", "ERROR");
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
			if(pd!=null & pd.isShowing())
				pd.dismiss();
		}
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
	        
	        //NOMBRE OBJETO
	        TextView objeto = (TextView)item.findViewById(R.id.Objeto);
	        objeto.setText(((Trueque)trueques[position]).getObjeto().getNombre());
	        //PRECIO
	        TextView precio = (TextView)item.findViewById(R.id.Precio);
	        precio.setText("$"+((Trueque)trueques[position]).getObjeto().getValor());
	        //DUENIO
	        TextView duenio = (TextView)item.findViewById(R.id.Duenio);
	        duenio.setText("Publicado por: "+((Trueque)trueques[position]).getUsuario());
	        //IMAGEN
	        ImageView img = (ImageView)item.findViewById(R.id.imgTrueque);
	        img.setImageBitmap(((Trueque)trueques[position]).getImagen());
	        
//	        TextView itemTrueque = (TextView)item.findViewById(R.id.TextItemTrueque);
//	        itemTrueque.setText(((Trueque)trueques[position]).getObjeto().getNombre() + 
//	        		"   $"+ ((Trueque)trueques[position]).getObjeto().getValor());
	 
//	        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//	        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
	 
	        return(item);
        }
	}


}
