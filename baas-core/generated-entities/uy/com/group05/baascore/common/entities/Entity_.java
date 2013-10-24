package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Entity.class)
public abstract class Entity_ {

	public static volatile SingularAttribute<Entity, Long> id;
	public static volatile SingularAttribute<Entity, Application> application;
	public static volatile SingularAttribute<Entity, String> name;
	public static volatile ListAttribute<Entity, Permission> permission;

}

