package uy.com.group05.baassdk.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
 
public class AuthenticatorService extends Service {

	private Authenticator mAuthenticator; 
	
	@Override
	public void onCreate() {
		super.onCreate();
		mAuthenticator = new Authenticator(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mAuthenticator.getIBinder();
	}

}
