package uy.com.group05.baasclient.sdk;

import android.app.Activity;
import android.content.Context;
import uy.com.group05.baasclient.sdk.impl.APIImpl;
import uy.com.group05.baasclient.sdk.impl.ClientImpl;

public class SDKFactory {
	public static APIFacade getAPIFacade(Context context) {
		return new APIImpl(context);
	}
	
	public static ClientFacade getClientFacade(Context context) {
		return new ClientImpl(context);
	}
	
	public static GCMService getGCMService(Activity act) {
		return new GCMService(act);
	}
}
