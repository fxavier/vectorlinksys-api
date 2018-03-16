package com.xavier.vectorlink.vectorlinksysapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Village.class)
public abstract class Village_ {

	public static volatile SingularAttribute<Village, String> name;
	public static volatile SingularAttribute<Village, Locality> locality;
	public static volatile SingularAttribute<Village, Boolean> active;
	public static volatile SingularAttribute<Village, Long> id;
	public static volatile SingularAttribute<Village, Long> revisits;

}

