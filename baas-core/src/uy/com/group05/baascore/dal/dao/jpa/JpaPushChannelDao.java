package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.dal.dao.PushChannelDao;

@Stateless
public class JpaPushChannelDao extends JpaGenericDao<PushChannel> implements PushChannelDao {

	public PushChannel readById(long pushChannelId) {
		
		PushChannel a = em.find(PushChannel.class, pushChannelId);//applications.get(0);
		
		if(a !=null){
			a.getClients().size();
			a.getEntities().size();
		}
		
		return a;
	}
	
	@Override
	public PushChannel readByName(long appId, String name) {
		TypedQuery<PushChannel> query = em.createQuery("SELECT c FROM PushChannel c WHERE c.application.id = :appId AND c.name = :name", PushChannel.class);
		query.setParameter("appId", appId);
		query.setParameter("name", name);
		
		List<PushChannel> pushChannels = query.getResultList();
		
		return pushChannels.isEmpty() ? null : pushChannels.get(0);
	}

	@Override
	public List<PushChannel> readAllFromApp(long appId) {
		TypedQuery<PushChannel> query = em.createQuery("SELECT c FROM PushChannel c WHERE c.application.id = :appId", PushChannel.class);
		return query.getResultList();
	}

	@Override
	public List<PushChannel> readAllFromClient(long appId, long clientId) {
		TypedQuery<PushChannel> query =
				em.createQuery("select distinct r "
						+ "from PushChannel c inner join c.clients r "
						+ "where c.id = :clientId and "
						+ "c.application.id = :appId", PushChannel.class);
		
		query.setParameter("appId", appId);
		query.setParameter("clientId", clientId);
		
		return query.getResultList();
	}

	@Override
	public List<Client> readAllFromCanal(long appId, long canalId) {
		TypedQuery<Client> query =
				em.createQuery("select distinct c "
						+ "from Client c inner join c.pushChannels p "
						+ "where p.id = :canalId and "
						+ "c.application.id = :appId", Client.class);
		
		query.setParameter("appId", appId);
		query.setParameter("canalId", canalId);
		
		return query.getResultList();
	}

}
