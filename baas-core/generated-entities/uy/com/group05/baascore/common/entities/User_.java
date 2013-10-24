package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> fbId;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile ListAttribute<User, Application> applications;
	public static volatile SingularAttribute<User, Boolean> loggedIn;
	public static volatile SingularAttribute<User, String> password;

}

