package uy.com.group05.baascore.sl.services.impl;

import javax.ejb.EJB;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.sl.services.rest.PushRest;

public class PushRestImpl implements PushRest {

	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	public String helloWorld() {
		return "hello";
	}
}
