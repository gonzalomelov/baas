package uy.com.group05.baascore.common.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-09-26T15:57:45.414-0500")
@StaticMetamodel(Application.class)
public class Application_ {
	public static volatile SingularAttribute<Application, Long> id;
	public static volatile SingularAttribute<Application, String> name;
	public static volatile ListAttribute<Application, User> users;
}
