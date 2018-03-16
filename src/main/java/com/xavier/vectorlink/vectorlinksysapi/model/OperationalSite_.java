package com.xavier.vectorlink.vectorlinksysapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OperationalSite.class)
public abstract class OperationalSite_ {

	public static volatile SingularAttribute<OperationalSite, District> district;
	public static volatile SingularAttribute<OperationalSite, String> name;
	public static volatile SingularAttribute<OperationalSite, Boolean> active;
	public static volatile SingularAttribute<OperationalSite, Long> id;
	public static volatile SingularAttribute<OperationalSite, Long> target;

}

