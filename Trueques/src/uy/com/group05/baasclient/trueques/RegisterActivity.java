package uy.com.group05.baasclient.trueques;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.SDKFactory;
import uy.com.group05.baasclient.sdk.entities.ClientRegistrationDTO;
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

public class RegisterActivity extends Activity {

	private TextView registerWaiting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		registerWaiting = (TextView) findViewById(R.id.register_waiting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void register(View view) {
		String email = ((EditText) findViewById(R.id.register_email)).getText().toString();
		String password = ((EditText) findViewById(R.id.register_password)).getText().toString();
		String name = ((EditText) findViewById(R.id.register_name)).getText().toString();
		String lastname = ((EditText) findViewById(R.id.register_lastname)).getText().toString();
		
		ConnectivityManager connMgr = (ConnectivityManager) 
		        getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	new RegisterTask(this).execute(email, password, name, lastname);
	    } else {
	    	
	    	registerWaiting.setText("@string/register_unsuccessful");
	    }
		
		
	}
	
	private class RegisterTask extends AsyncTask<String, Void, Boolean> {

		private Context context;
		
		private RegisterTask(Context context) {
			this.context = context.getApplicationContext();
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			String email = params[0];
			String password = params[1];
			String name = params[2];
			String lastname = params[3];
			
			ClientRegistrationDTO clientRegistration;
			
			try {
				clientRegistration = SDKFactory.getClientFacade().register(context, email, password, name, lastname);
				return clientRegistration.isOk();
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
				registerWaiting.setText("@string/register_unsuccessful");	
			} else {
				Intent intent = new Intent(context, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		}

		@Override
		protected void onPreExecute() {
			registerWaiting.setText("@string/register_waiting");
		}
		
	}
}
