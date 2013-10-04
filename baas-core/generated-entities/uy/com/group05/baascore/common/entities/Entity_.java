package uy.com.group05.baascore.common.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-01T23:48:36.492-0500")
@StaticMetamodel(Entity.class)
public class Entity_ {
	public static volatile SingularAttribute<Entity, Long> id;
	public static volatile SingularAttribute<Entity, String> name;
	public static volatile SingularAttribute<Entity, Application> application;
	public static volatile ListAttribute<Entity, Permission> permission;
}
