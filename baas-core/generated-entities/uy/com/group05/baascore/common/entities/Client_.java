package uy.com.group05.baascore.common.entities;

import java.util.UUID;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, Long> id;
	public static volatile SingularAttribute<Client, Application> application;
	public static volatile SingularAttribute<Client, UUID> accessToken;
	public static volatile SingularAttribute<Client, String> email;
	public static volatile SingularAttribute<Client, String> gcm_regId;
	public static volatile ListAttribute<Client, Role> roles;
	public static volatile ListAttribute<Client, PushChannel> pushChannels;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, String> lastname;
	public static volatile SingularAttribute<Client, Boolean> loggedIn;
	public static volatile SingularAttribute<Client, UUID> refreshToken;
	public static volatile SingularAttribute<Client, String> password;

}

