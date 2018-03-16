package com.xavier.vectorlink.vectorlinksysapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locality.class)
public abstract class Locality_ {

	public static volatile SingularAttribute<Locality, OperationalSite> operationalSite;
	public static volatile SingularAttribute<Locality, String> name;
	public static volatile SingularAttribute<Locality, Boolean> active;
	public static volatile SingularAttribute<Locality, Long> id;

}

