package uy.com.group05.baascore.bll.ejbs.interfaces;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;

@Local
public interface ClientManagementLocal {
	boolean validateClientCredentials(String email, String password);
}
