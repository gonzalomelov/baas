package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PushChannel.class)
public abstract class PushChannel_ {

	public static volatile SingularAttribute<PushChannel, Long> id;
	public static volatile SingularAttribute<PushChannel, Application> application;
	public static volatile SingularAttribute<PushChannel, String> name;
	public static volatile ListAttribute<PushChannel, Client> clients;

}

