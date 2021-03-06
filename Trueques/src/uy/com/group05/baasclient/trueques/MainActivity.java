package uy.com.group05.baasclient.trueques;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends Activity {    
    private TableLayout entitiesTable;
    private TextView resultView;
    private TextView entityTextView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        entitiesTable = (TableLayout) findViewById(R.id.main_tableLayout);
        resultView = (TextView) findViewById(R.id.main_waiting);
        entityTextView = (TextView) findViewById(R.id.main_entity);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void get(View view) {
    	String entity = entityTextView.getText().toString();
    	
    	new GetTask(this).execute(entity);
    }
    
    public void post(View view) {
    	String entity = entityTextView.getText().toString();
    	
    	Intent intent = new Intent(this, SubmitActivity.class);
    	intent.putExtra("entity", entity);
    	
    	startActivity(intent);
    }
    

	public void suscribirCanalesPush(View view) {
		Intent intent = new Intent(this, SuscribirCanalPushActivity.class);
		startActivity(intent);
	}
	
	public void enviarMensajePush(View view) {
		Intent intent = new Intent(this, EnviarMensajeCanalPushActivity.class);
		startActivity(intent);
	}
    
    private class GetTask extends AsyncTask<String, Void, String> {

		private Context context;
		
	    private GetTask(Context context) {
	        this.context = context.getApplicationContext();
	    }

		@Override
		protected String doInBackground(String... args) {
			
			try
			{
				String entity = args[0];
				String query = "";
				
				String json = SDKFactory.getAPIFacade(context).get(entity, query);
				
				return json;
			}
			catch (UnsupportedEncodingException e) {
				return "";
			}
			catch (ClientProtocolException e) {
				return "";
			}
			catch (IOException e) {
				return "";
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			//resultView.setText("@string/main_unsuccessful");
			
			Gson gson = new Gson();
			
			Trueque[] autosArray = gson.fromJson(result, Trueque[].class);
			
			List<Trueque> autos = autosArray != null && autosArray.length > 0 ? Arrays.asList(autosArray) : new ArrayList<Trueque>();
			
			if (!autos.isEmpty()) {
				for (Trueque auto : autos) {
					TableRow tr = new TableRow(context);
					tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					
					TextView value = new TextView(context);
					value.setText(auto.getMarca() + " - " + auto.getAnio());
					
					tr.addView(value);
					
					entitiesTable.addView(tr);
				}	
			} else {
				resultView.setText("No hay autos");
			}
			
			
			resultView.setText("");
			
		}
		
		@Override
		protected void onPreExecute() {
			resultView.setText("@string/main_waiting");
		}
		
	}
    
}


