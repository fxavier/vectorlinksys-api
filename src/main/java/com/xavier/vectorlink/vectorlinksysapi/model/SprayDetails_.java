package com.xavier.vectorlink.vectorlinksysapi.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SprayDetails.class)
public abstract class SprayDetails_ {

	public static volatile SingularAttribute<SprayDetails, Long> notSprayedWomen;
	public static volatile SingularAttribute<SprayDetails, Long> sprayedMan;
	public static volatile SingularAttribute<SprayDetails, Long> notSprayedPregnantWomen;
	public static volatile SingularAttribute<SprayDetails, Long> roomsSprayed;
	public static volatile SingularAttribute<SprayDetails, TeamLeader> teamLeader;
	public static volatile SingularAttribute<SprayDetails, LocalDate> sprayDate;
	public static volatile SingularAttribute<SprayDetails, Long> notSprayedChildren;
	public static volatile SingularAttribute<SprayDetails, SprayOperator> sprayOperator;
	public static volatile SingularAttribute<SprayDetails, String> reference;
	public static volatile SingularAttribute<SprayDetails, SprayStatus> sprayStatus;
	public static volatile SingularAttribute<SprayDetails, Long> notSprayedMen;
	public static volatile SingularAttribute<SprayDetails, String> houseHoldName;
	public static volatile SingularAttribute<SprayDetails, String> houseHoldId;
	public static volatile SingularAttribute<SprayDetails, Long> sprayedWomen;
	public static volatile SingularAttribute<SprayDetails, Long> sprayedPregnantWomen;
	public static volatile SingularAttribute<SprayDetails, Long> sprayedChildren;
	public static volatile SingularAttribute<SprayDetails, Long> id;
	public static volatile SingularAttribute<SprayDetails, Gender> genderInterviwedPerson;
	public static volatile SingularAttribute<SprayDetails, Long> roomsFound;
	public static volatile SingularAttribute<SprayDetails, Village> village;
	public static volatile SingularAttribute<SprayDetails, ReasonNotSprayed> reasonNotSprayed;

}

