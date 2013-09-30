package uy.com.group05.baascore.common.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-09-29T15:41:21.781-0500")
@StaticMetamodel(Permission.class)
public class Permission_ {
	public static volatile SingularAttribute<Permission, Long> id;
	public static volatile SingularAttribute<Permission, Application> application;
	public static volatile SingularAttribute<Permission, Entity> entity;
	public static volatile SingularAttribute<Permission, Role> role;
	public static volatile SingularAttribute<Permission, Operation> operation;
}
