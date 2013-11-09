package uy.com.group05.baassdk;

import uy.com.group05.baassdk.impl.APIClientImpl;
import uy.com.group05.baassdk.impl.ClientImpl;
import android.app.Activity;
import android.content.Context;

public class SDKFactory {
	public static APIFacade getAPIFacade(Context context) {
		return new APIClientImpl(context);
	}
	
	public static ClientFacade getClientFacade(Context context) {
		return new ClientImpl(context);
	}
	
	public static GCMService getGCMService(Activity act) {
		return new GCMService(act);
	}
}
