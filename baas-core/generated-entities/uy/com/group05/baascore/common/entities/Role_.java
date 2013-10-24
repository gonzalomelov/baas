package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, Application> application;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, Permission> permissions;

}

