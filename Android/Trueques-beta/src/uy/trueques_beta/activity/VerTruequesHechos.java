package uy.trueques_beta.activity;

import java.util.List;

import uy.trueques_beta.R;
import uy.trueques_beta.activity.VerTrueques.AdaptadorTrueque;
import uy.trueques_beta.activity.VerTrueques.VerTruequeTask;
import uy.trueques_beta.activity.VerTrueques.VerTruequesListener;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.negocio.Factory;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VerTruequesHechos extends Fragment implements AdapterView.OnItemClickListener{

	private AdaptadorTrueque adaptador;
	private String mail;
	private ListView lstTrueques;
	private TextView lblVerTrueques;
	private Object[] trueques;
	private VerTruequesHechosListener listener;
	private int size;
	private VerTruequeTask mAuthTask = null;
			
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_ver_trueques_hechos, container, false);
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
			mAuthTask = new VerTruequeTask();
			mAuthTask.execute((Void) null);
		}
		
		size=0;
//		trueques = Factory.getTruequeCtrl().getTruequesUsuario(this.getActivity(), this.mail).toArray();
//		int size=this.trueques.length;
		
		lblVerTrueques = (TextView)getView().findViewById(R.id.LblVerTrueques);
		lblVerTrueques.setText("Trueques realizados: " + size);
		 
		
		//this.adaptador = new AdaptadorTrueque(this, R.layout.list_item_trueques, trueques);
		lstTrueques = (ListView)getView().findViewById(R.id.LstTrueques);
		//lstTrueques.setAdapter(adaptador);
		
		lstTrueques.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		Trueque t =((Trueque)adapterView.getItemAtPosition(position));
		//AVISO AL ACTIVITY
		if (listener!=null) {
            listener.onTruequeHechoSeleccionado(t);//.getIdTrueque());
        }
	}
	
	public interface VerTruequesHechosListener {
        void onTruequeHechoSeleccionado(Trueque t);//int idTrueque);
    }
 
    public void setVerTruequesHechosListener(VerTruequesHechosListener listener) {
        this.listener=listener;
    }
	
    public class VerTruequeTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			
			size = 0;
			List<Trueque> ts =null;
			ts = Factory.getTruequeCtrl().getTruequesHechosUsuario(VerTruequesHechos.this.getActivity(), mail);
			//ts = Factory.getTruequeCtrl().getTruequesActivos(VerTrueques.this.getActivity());
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

			if (success) {
				Log.i("[VerTruequesHechos]:", "EXITO!");
				adaptador = new AdaptadorTrueque(VerTruequesHechos.this, R.layout.list_item_trueques, trueques);
				lstTrueques.setAdapter(adaptador);
				lblVerTrueques.setText("Trueques realizados: " + size);
		    	
			} else {
				Log.i("[VerTruequesHechos]:", "ERROR");
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
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
 
        public View getView(int position, View convertView, ViewGroup parent) {
	        LayoutInflater inflater = context.getLayoutInflater();//getSystemService(Context.LAYOUT_INFLATER_SERVICE);//
	        View item = inflater.inflate(R.layout.list_item_trueques, null);
	        
	        Trueque t = (Trueque) trueques[position];
	        //NOMBRE OBJETO
	        TextView objeto = (TextView)item.findViewById(R.id.Objeto);
	        objeto.setText("Cambiaste " + t.getObjeto().getNombre() +" por " 
	        		+ t.getGanadora().getObjeto().getNombre());
	        //PRECIO
	        TextView precio = (TextView)item.findViewById(R.id.Precio);
	        precio.setVisibility(View.GONE);;
	        //DUENIO
	        TextView fecha = (TextView)item.findViewById(R.id.Duenio);
	        fecha.setText("El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
	        //IMAGEN
	        ImageView img = (ImageView)item.findViewById(R.id.imgTrueque);
	        img.setImageBitmap(((Trueque)trueques[position]).getImagen());
	        
//	        TextView itemTrueque = (TextView)item.findViewById(R.id.Objeto);//TextItemTrueque);
//	        Trueque t = (Trueque) trueques[position];
//	        itemTrueque.setText("Cambiaste " + t.getObjeto().getNombre() +" por " 
//	        		+ t.getGanadora().getObjeto().getNombre()+ "\n"
//	        		/*+ "El día " + t.getFechaFin().toGMTString());*/
//	        		+ "El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
//	        TextView lblDescObj = (TextView)item.findViewById(R.id.LblDescObj);
//	        lblDescObj.setText(((Objeto) objs[position]).getDescripcion());
	 
	        return(item);
        }
	}
}


