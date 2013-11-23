package uy.trueques_beta.activity;

import java.util.List;

import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.entities.RSS;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//public class VerRSS extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_ver_rss);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.ver_rs, menu);
//		return true;
//	}
//
//}

public class VerRSS extends Fragment implements AdapterView.OnItemClickListener{

	private AdaptadorRSS adaptador;
	private String mail;
	private ListView lstRSS;
	private TextView lblVerRSS;
	private Object[] rss;
	private VerRSSListener listener;
	private int size;
	private VerRSSTask mAuthTask = null;
			
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_ver_rss, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
		
		//Obtengo datos del usuario
        SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		if(mAuthTask==null){
			mAuthTask = new VerRSSTask();
			mAuthTask.execute((Void) null);
		}
		
		
		size=0;
		lblVerRSS = (TextView)getView().findViewById(R.id.LblVerRSS);
		lblVerRSS.setText(lblVerRSS.getText().toString());
		 
//		this.adaptador = new AdaptadorTrueque(this, R.layout.list_item_trueques, trueques);
		lstRSS = (ListView)getView().findViewById(R.id.LstRSS);
//		lstTrueques.setAdapter(adaptador);
		
		lstRSS.setOnItemClickListener(this);
		
	}


	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		RSS r =((RSS)adapterView.getItemAtPosition(position));//.getAdapter().getItem(position));
//		Intent intent = new Intent(VerTrueques.this, VistaTrueque.class);
//        intent.putExtra("idTrueque", (int)t.getIdTrueque());
//        intent.putExtra("mail", mail);
//		startActivity(intent);
		
		//AVISO AL ACTIVITY
		if (listener!=null) {
            listener.onRSSSeleccionado(r);//.getIdTrueque());
        }
	}
	
	public interface VerRSSListener {
        void onRSSSeleccionado(RSS r);//int idTrueque);
    }
 
    public void setVerRSSListener(VerRSSListener listener) {
        this.listener=listener;
    }

    public class VerRSSTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			
			size = 0;
			List<RSS> ts =null;
			ts = Factory.getRSSCtrl().getListaRSS(VerRSS.this.getActivity());
			if(ts!=null){
				rss=ts.toArray();
				size=rss.length;
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
				Log.i("[VerRSS]:", "EXITO!");
				adaptador = new AdaptadorRSS(VerRSS.this, R.layout.list_item_rss, rss);
				lstRSS.setAdapter(adaptador);
				lblVerRSS.setText(lblVerRSS.getText().toString() +" ("+ size +")");
		    	
			} else {
				Log.i("[VerTrueques]:", "ERROR");
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
		}
	}
    
    
	
	//Creo la clase para el adaptador
	class AdaptadorRSS extends ArrayAdapter {
		 
	    //Activity context;
	    Activity context;
		//Object[] trueques;
	 
	    public AdaptadorRSS(Fragment context, int textViewResourceId, Object[] objects) {
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
	        View item = inflater.inflate(R.layout.list_item_rss, null);
	        
	        //NOMBRE OBJETO
	        TextView titulo = (TextView)item.findViewById(R.id.TituloRSS);
	        titulo.setText(((RSS)rss[position]).getTitulo());
	        //PRECIO
	        TextView desc = (TextView)item.findViewById(R.id.DescRSS);
	        desc.setText("$"+((RSS)rss[position]).getMensaje());
	        
	 
	        return(item);
        }
	}


}

