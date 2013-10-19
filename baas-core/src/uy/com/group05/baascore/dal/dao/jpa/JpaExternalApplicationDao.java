package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.ExternalApplication;
import uy.com.group05.baascore.dal.dao.ExternalApplicationDao;

@Stateless
public class JpaExternalApplicationDao extends JpaGenericDao<ExternalApplication> implements ExternalApplicationDao {


}
