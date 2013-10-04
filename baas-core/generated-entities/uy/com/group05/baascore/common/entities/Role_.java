package uy.com.group05.baascore.common.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-01T23:48:36.498-0500")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, Application> application;
	public static volatile ListAttribute<Role, Permission> permissions;
}
