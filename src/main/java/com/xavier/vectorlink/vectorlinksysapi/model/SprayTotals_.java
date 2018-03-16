package com.xavier.vectorlink.vectorlinksysapi.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SprayTotals.class)
public abstract class SprayTotals_ {

	public static volatile SingularAttribute<SprayTotals, Long> insecticideFullReturned;
	public static volatile SingularAttribute<SprayTotals, Long> notSprayedStructures;
	public static volatile SingularAttribute<SprayTotals, Long> notSprayedWomen;
	public static volatile SingularAttribute<SprayTotals, Long> sprayedMan;
	public static volatile SingularAttribute<SprayTotals, Long> notSprayedPregnantWomen;
	public static volatile SingularAttribute<SprayTotals, Long> roomsSprayed;
	public static volatile SingularAttribute<SprayTotals, TeamLeader> teamLeader;
	public static volatile SingularAttribute<SprayTotals, LocalDate> sprayDate;
	public static volatile SingularAttribute<SprayTotals, Long> notSprayedChildren;
	public static volatile SingularAttribute<SprayTotals, SprayOperator> sprayOperator;
	public static volatile SingularAttribute<SprayTotals, String> reference;
	public static volatile SingularAttribute<SprayTotals, Long> notSprayedMen;
	public static volatile SingularAttribute<SprayTotals, Long> insecticideIssued;
	public static volatile SingularAttribute<SprayTotals, Long> sprayedWomen;
	public static volatile SingularAttribute<SprayTotals, Long> sprayedPregnantWomen;
	public static volatile SingularAttribute<SprayTotals, Long> sprayedChildren;
	public static volatile SingularAttribute<SprayTotals, Long> id;
	public static volatile SingularAttribute<SprayTotals, Long> roomsFound;
	public static volatile SingularAttribute<SprayTotals, Village> village;
	public static volatile SingularAttribute<SprayTotals, Long> insecticideEmptyReturned;
	public static volatile SingularAttribute<SprayTotals, Long> sprayedStructures;

}

