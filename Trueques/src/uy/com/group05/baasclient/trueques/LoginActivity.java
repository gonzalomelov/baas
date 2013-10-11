package uy.com.group05.baasclient.trueques;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.SDKFactory;
import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
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

public class LoginActivity extends Activity {

	private static final String TAG = "LoginActivity";
	
	public static final String LOGIN_EMAIL = "uy.com.group05.baasclient.trueques.LOGIN_EMAIL";
	public static final String LOGIN_PASSWORD = "uy.com.group05.baasclient.trueques.LOGIN_PASSWORD";
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		textView = (TextView) findViewById(R.id.login_state);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void login(View view) {
		String email = ((EditText)findViewById(R.id.login_email)).getText().toString();
		String password = ((EditText)findViewById(R.id.login_password)).getText().toString();
		
		ConnectivityManager connMgr = (ConnectivityManager) 
	        getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	new LoginTask(this).execute(email, password);
	    } else {
	    	
	    	textView.setText("Try again!");
	    }
		
		
	} 
	
	private class LoginTask extends AsyncTask<String, Void, Boolean> {

		private Context context;
		
	    private LoginTask(Context context) {
	        this.context = context.getApplicationContext();
	    }
		
		@Override
		protected void onPreExecute() {
			textView.setText("Executing");
		}

		@Override
		protected Boolean doInBackground(String... args) {
			String email = args[0];
			String password = args[1];
			
			ClientAuthenticationDTO auten ;
			
			try
			{
				auten = SDKFactory.getClientFacade().authenticate(context, email, password);
				
				return auten.isOk();
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
		protected void onPostExecute(Boolean b) {
			if(!b) {
				textView.setText("Try again!");
			} else {
		        Intent intent = new Intent(context, MainActivity.class);
		        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		}
		
	}
}
