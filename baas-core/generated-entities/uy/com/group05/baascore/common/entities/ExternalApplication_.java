package uy.com.group05.baascore.common.entities;

import java.util.UUID;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ExternalApplication.class)
public abstract class ExternalApplication_ {

	public static volatile SingularAttribute<ExternalApplication, Long> id;
	public static volatile SingularAttribute<ExternalApplication, Long> externalAppId;
	public static volatile SingularAttribute<ExternalApplication, String> name;
	public static volatile ListAttribute<ExternalApplication, Application> applications;
	public static volatile SingularAttribute<ExternalApplication, UUID> clientId;

}

