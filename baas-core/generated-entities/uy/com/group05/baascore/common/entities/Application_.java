package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Application.class)
public abstract class Application_ {

	public static volatile SingularAttribute<Application, Long> id;
	public static volatile ListAttribute<Application, User> users;
	public static volatile ListAttribute<Application, Role> roles;
	public static volatile ListAttribute<Application, PushChannel> pushChannels;
	public static volatile SingularAttribute<Application, String> name;
	public static volatile SingularAttribute<Application, String> apiClientId;
	public static volatile ListAttribute<Application, Entity> entities;
	public static volatile SingularAttribute<Application, String> apiClientSecret;
	public static volatile ListAttribute<Application, Client> clients;

}

