package uy.com.group05.baascore.common.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Operation.class)
public abstract class Operation_ {

	public static volatile SingularAttribute<Operation, Long> id;
	public static volatile SingularAttribute<Operation, String> name;

}

