package uy.com.group05.baasclient.trueques;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import uy.com.group05.baasclient.sdk.GCMService;
import uy.com.group05.baasclient.sdk.SDKFactory;
import uy.com.group05.baasclient.sdk.entities.SimplePushChannelDTO;

public class SuscribirCanalPushActivity extends Activity {
    // Para los logs
    static final String TAG = "GCM Trueque";

    GCMService gcms;
    
    ArrayList<SimplePushChannelDTO> canales = new ArrayList<SimplePushChannelDTO>();
	ListAdapter boxAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribircanalpush);
        
        gcms = SDKFactory.getGCMService(this);
        
        fillData(this);
    }
    
    void fillData(final Activity act) {
	    new AsyncTask<Void, Void, Boolean>() {
	    	private final ProgressDialog dialog = new ProgressDialog(act);
    		@Override
    		protected void onPreExecute() {
    			this.dialog.setMessage("Cargando canales...");
    			this.dialog.setIndeterminate(true);
    			this.dialog.setCancelable(false);
    			this.dialog.show();
    		}
    		
		    @Override
		    protected Boolean doInBackground(Void... params) {
				try {
					List<SimplePushChannelDTO> listaCanales = gcms.getPushChannels();
					for (SimplePushChannelDTO canal : listaCanales) {
						Log.i("TAG", "Agregando canal: " + canal.getName());
			    		canales.add(canal);
			    	}
					return true;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		    }
		    
		    @Override
            protected void onPostExecute(Boolean ok) {
		    	if (this.dialog.isShowing()) {
		    		this.dialog.dismiss();
		    	}
		    	
		    	boxAdapter = new ListAdapter(act, canales);

			    ListView lvMain = (ListView) findViewById(R.id.lvMain);
			    lvMain.setAdapter(boxAdapter);
            }
		}.execute(null, null, null);
	  }
    
    public void actualizarSuscripciones(View view) {
    	final Activity act = (Activity) view.getContext();
    	new AsyncTask<Void, Void, Boolean>() {
    		private final ProgressDialog dialog = new ProgressDialog(act);
    		@Override
    		protected void onPreExecute() {
    			this.dialog.setMessage("Suscribiendo a canales...");
    			this.dialog.setIndeterminate(true);
    			this.dialog.show();
    		}
    		
		    @Override
		    protected Boolean doInBackground(Void... params) {
		    	for (SimplePushChannelDTO canal : boxAdapter.getBox()) {
					try {
						gcms.subscribeToPushChannel(canal.getName());
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
		    	}
		    	return true;
		    }
		    
		    @Override
            protected void onPostExecute(Boolean ok) {
		    	if (this.dialog.isShowing()) {
		    		this.dialog.dismiss();
		    	}
		    	Toast.makeText(act, "Suscripciones realizadas", Toast.LENGTH_LONG).show();
		    	act.finish();
            }
		}.execute(null, null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gcms.checkPlayServices();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

