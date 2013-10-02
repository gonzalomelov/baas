package uy.com.group05.baascore.sl.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;
import uy.com.group05.baascore.sl.services.soap.ApplicationServices;

public class ApplicationServicesImpl implements ApplicationServices{

	@Inject
	Mapper mapper;
	
	@Inject
	AppManagementLocal appManagementLocal;
	
	public List<ApplicationDTO> listApplications(long idUser) 
			throws 
				UserNotRegisteredException{
		List<Application> listApps = appManagementLocal.listApplications(idUser);
		//mapper.
		return new ArrayList<ApplicationDTO>();
	}


	@Override
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws 
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException, MongoDBAlreadyExistsException {
		return appManagementLocal.createApplication(idUser, nombreApp, rolesStr, entidadesStr);
	}
	
	
	public boolean existsApplication(String nombre){
		return appManagementLocal.existsApplication(nombre);
	}

}
