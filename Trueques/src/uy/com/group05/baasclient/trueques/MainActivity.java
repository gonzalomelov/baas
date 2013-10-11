package uy.com.group05.baasclient.trueques;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView textView = (TextView)findViewById(R.id.main_text);
        textView.setText("Logged In!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private class ExecuteHelloWorldTask extends AsyncTask<String, Void, String> {
//    	@Override
//        protected String doInBackground(String... urls) {
//    		
//    		String ret = null;
//    
//    		ConnectivityManager connMgr = (ConnectivityManager) 
//	            getSystemService(Context.CONNECTIVITY_SERVICE);
//	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//	        if (networkInfo != null && networkInfo.isConnected()) {
//	        	new ExecuteHelloWorldTask().execute();
//	        } else {
//	            
//	        }
//    		
//    		return ret; 
//    		
//        }
//        
//        @Override
//        protected void onPostExecute(String result) {
//        	
//       }
//    }
}


