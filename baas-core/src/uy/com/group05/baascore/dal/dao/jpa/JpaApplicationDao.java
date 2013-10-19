package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.ExternalApplication;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.dal.dao.ApplicationDao;

@Stateless
public class JpaApplicationDao extends JpaGenericDao<Application> implements ApplicationDao {
	
	
	public Application readById(long appId) {
		/*TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a WHERE a.id = :appId", Application.class);
		query.setParameter("appId", appId);
		 
		List<Application> applications = query.getResultList();
		
		if (applications.isEmpty()) {
			return null;
		}*/
		
		Application a = em.find(Application.class, appId);//applications.get(0);
		
		if(a !=null){
			a.getUsers().size();
			a.getClients().size();
			a.getEntities().size();
			a.getRoles().size();
		}
		
		return a;
	}
	
	public Application readByName(String name) {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a WHERE a.name = :name", Application.class);
		query.setParameter("name", name);
		 
		List<Application> applications = query.getResultList();
		
		if (applications.isEmpty()) {
			return null;
		}
		
		Application a = applications.get(0);
		
		a.getClients().size();
		a.getEntities().size();
		a.getRoles().size();
		
		return a;
	}
	
	public List<Application> readFromUser(long userId) {
		TypedQuery<Application> query =
				em.createQuery("select distinct a "
						+ "from Application a inner join a.users b "
						+ "where b.id = :userId", Application.class);
		
		query.setParameter("userId", userId);
		
		List<Application> applications = query.getResultList();
		
		return applications;
	}
	
	public Application readFromApiClientId(UUID apiClientId) {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a WHERE a.apiClientId = :apiClientId", Application.class);
		query.setParameter("apiClientId", apiClientId);
		
		List<Application> applications = query.getResultList();
		
		if (applications.isEmpty()) {
			return null;
		}
		
		Application a = applications.get(0);
		
		a.getClients().size();
		a.getEntities().size();
		a.getRoles().size();
		
		return a;
	}
	
	public ExternalApplication readAssociatedExternalApplication(long appId, long externalAppId) {
		TypedQuery<ExternalApplication> query =
				em.createQuery("select a from ExternalApplication a"
						+ "inner join a.applications b"
						+ "where a.id = :appId and b.id == :externalAppId", ExternalApplication.class);
		
		List<ExternalApplication> externalApplications = query.getResultList();
		
		return externalApplications.isEmpty() ? null : externalApplications.get(0);
	}
	
	public List<Role> readExternalClientsAppRole(long appId) {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a WHERE a.id = :appId", Application.class);
		query.setParameter("appId", appId);
		
		List<Application> applications = query.getResultList();
		
		return applications.isEmpty() ? null : applications.get(0).getExternalClientsRoles();
	}
}
