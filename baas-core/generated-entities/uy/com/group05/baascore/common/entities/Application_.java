package uy.com.group05.baascore.common.entities;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-01T23:48:36.347-0500")
@StaticMetamodel(Application.class)
public class Application_ {
	public static volatile SingularAttribute<Application, Long> id;
	public static volatile SingularAttribute<Application, String> name;
	public static volatile SingularAttribute<Application, String> url;
	public static volatile SingularAttribute<Application, UUID> token;
	public static volatile ListAttribute<Application, User> users;
	public static volatile ListAttribute<Application, Client> clients;
	public static volatile ListAttribute<Application, Role> roles;
	public static volatile ListAttribute<Application, Entity> entities;
}
