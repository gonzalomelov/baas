package uy.com.group05.baascore.common.entities;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-09-29T15:52:28.512-0300")
@StaticMetamodel(Application.class)
public class Application_ {
	public static volatile SingularAttribute<Application, Long> id;
	public static volatile SingularAttribute<Application, String> name;
	public static volatile ListAttribute<Application, User> users;
	public static volatile ListAttribute<Application, Role> roles;
	public static volatile ListAttribute<Application, Entity> entities;
	public static volatile SingularAttribute<Application, UUID> token;
	public static volatile ListAttribute<Application, Client> clients;
}
