package uy.com.group05.baasclient.sdk;

import uy.com.group05.baasclient.sdk.impl.APIImpl;
import uy.com.group05.baasclient.sdk.impl.ClientImpl;

public class SDKFactory {
	public static APIFacade getAPIFacade() {
		return new APIImpl();
	}
	
	public static ClientFacade getClientFacade() {
		return new ClientImpl();
	}
}
