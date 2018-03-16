package com.xavier.vectorlink.vectorlinksysapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Province.class)
public abstract class Province_ {

	public static volatile SingularAttribute<Province, Country> country;
	public static volatile SingularAttribute<Province, String> name;
	public static volatile SingularAttribute<Province, Boolean> active;
	public static volatile SingularAttribute<Province, Long> id;

}

