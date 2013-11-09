package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SingularAttribute<Permission, Long> id;
	public static volatile SingularAttribute<Permission, Operation> operation;
	public static volatile SingularAttribute<Permission, Application> application;
	public static volatile SingularAttribute<Permission, Entity> entity;
	public static volatile SingularAttribute<Permission, Role> role;

}

