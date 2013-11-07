package uy.com.group05.baascore.sl.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Application_;
import uy.com.group05.baascore.sl.entitiesws.ApplicationEntity;
import uy.com.group05.baascore.sl.entitiesws.ClientEntity;
import uy.com.group05.baascore.sl.services.rest.IntegracionRest;

public class IntegracionImpl implements IntegracionRest {

	@Inject
	AppManagementLocal appManagementLocal;
	
	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	@Override
	public List<ApplicationEntity> getAplicaciones(){
		List<ApplicationEntity> lista = new ArrayList<ApplicationEntity>();
		
		List<Application> aplicaciones = appManagementLocal.getAllApplications();
		
		for (Application app : aplicaciones) {
			ApplicationEntity entity = new ApplicationEntity();
			entity.setId(app.getId());
			entity.setName(app.getName());
			lista.add(entity);
		}
		
		
		
		
		return lista;
	}

	@Override
	public ClientEntity loguearUsuarioFinalApp(long idApp, String email,
			String password) {
		
		return clientManagementLocal.authenticateExternal(idApp, email, password);
	}

}
