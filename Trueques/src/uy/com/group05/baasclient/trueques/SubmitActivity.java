package uy.com.group05.baasclient.trueques;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.SDKFactory;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class SubmitActivity extends Activity {
    private TextView resultView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        
        resultView = (TextView) findViewById(R.id.submit_waiting);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void post(View view) {
		String entity = getIntent().getStringExtra("entity");
		
		String marca = ((EditText) findViewById(R.id.submit_marca)).getText().toString();
    	String anio = ((EditText) findViewById(R.id.submit_anio)).getText().toString();
		
    	new PostTask(this).execute(entity, marca, anio);
    }
    
    private class PostTask extends AsyncTask<String, Void, Boolean> {

		private Context context;
		
	    private PostTask(Context context) {
	        this.context = context.getApplicationContext();
	    }

		@Override
		protected Boolean doInBackground(String... args) {
			
			try
			{
				String entity = args[0];
				
				Trueque auto = new Trueque();
				auto.setMarca(args[0]);
				auto.setAnio(args[1]);
				
				boolean ok = SDKFactory.getAPIFacade(context).post(entity, auto, Trueque.class);
				
				return ok;
			}
			catch (UnsupportedEncodingException e) {
				return false;
			}
			catch (ClientProtocolException e) {
				return false;
			}
			catch (IOException e) {
				return false;
			}
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (!result) {
				resultView.setText("@string/register_unsuccessful");	
			} else {
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		}
		
		@Override
		protected void onPreExecute() {
			resultView.setText("@string/main_waiting");
		}
		
	}
}
