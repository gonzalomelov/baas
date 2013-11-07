package uy.com.group05.baascore.common.entities;

import java.util.UUID;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ExternalClient.class)
public abstract class ExternalClient_ {

	public static volatile SingularAttribute<ExternalClient, Long> id;
	public static volatile SingularAttribute<ExternalClient, Application> application;
	public static volatile SingularAttribute<ExternalClient, UUID> accessToken;
	public static volatile SingularAttribute<ExternalClient, UUID> refreshToken;

}

