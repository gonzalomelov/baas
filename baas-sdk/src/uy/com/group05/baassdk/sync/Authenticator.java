package uy.com.group05.baassdk.sync;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
 
public class Authenticator extends AbstractAccountAuthenticator{

	public Authenticator(Context context) {
		super(context);
	}
	
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse arg0, String arg1,
			String arg2, String[] arg3, Bundle arg4)
			throws NetworkErrorException {
		return null;
	}

	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse arg0,
			Account arg1, Bundle arg2) throws NetworkErrorException {
		return null;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse arg0, String arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse arg0, Account arg1,
			String arg2, Bundle arg3) throws NetworkErrorException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getAuthTokenLabel(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse arg0, Account arg1,
			String[] arg2) throws NetworkErrorException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse arg0,
			Account arg1, String arg2, Bundle arg3)
			throws NetworkErrorException {
		throw new UnsupportedOperationException();
	}

}
