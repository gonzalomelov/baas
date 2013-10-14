package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.dal.dao.UserDao;

public class JpaUserDao extends JpaGenericDao<User> implements UserDao {
	
	public User readByEmail(String email) {
		TypedQuery<User> typedQuery = em.createQuery("select u from User u where u.email = :email", User.class);
		typedQuery.setParameter("email", email);
		
		List<User> users = typedQuery.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
}
