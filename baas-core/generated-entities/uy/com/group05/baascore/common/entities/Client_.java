package uy.com.group05.baascore.common.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-09-29T19:04:26.526-0500")
@StaticMetamodel(Client.class)
public class Client_ {
	public static volatile SingularAttribute<Client, Long> id;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, String> username;
	public static volatile SingularAttribute<Client, String> password;
	public static volatile SingularAttribute<Client, String> email;
	public static volatile SingularAttribute<Client, String> lastname;
	public static volatile SingularAttribute<Client, Boolean> loggedIn;
	public static volatile ListAttribute<Client, Role> roles;
	public static volatile SingularAttribute<Client, Application> application;
}
