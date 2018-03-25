package com.xavier.vectorlink.vectorlinksysapi.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MobilizationDetails.class)
public abstract class MobilizationDetails_ {

	public static volatile SingularAttribute<MobilizationDetails, Long> menSentitized;
	public static volatile SingularAttribute<MobilizationDetails, Long> womenSentitized;
	public static volatile SingularAttribute<MobilizationDetails, MobilizationStatus> mobilizationStatus;
	public static volatile SingularAttribute<MobilizationDetails, String> householderId;
	public static volatile SingularAttribute<MobilizationDetails, SprayAcceptance> sprayAcceptance;
	public static volatile SingularAttribute<MobilizationDetails, LocalDate> verificationDate;
	public static volatile SingularAttribute<MobilizationDetails, String> reference;
	public static volatile SingularAttribute<MobilizationDetails, ReasonNotSensitized> reasonNotSensitized;
	public static volatile SingularAttribute<MobilizationDetails, String> ArthmeticVerified;
	public static volatile SingularAttribute<MobilizationDetails, LocalDate> mobDate;
	public static volatile SingularAttribute<MobilizationDetails, Mobilizer> mobilizer;
	public static volatile SingularAttribute<MobilizationDetails, ReasonNotAccepted> reasonNotAccepted;
	public static volatile SingularAttribute<MobilizationDetails, Long> id;
	public static volatile SingularAttribute<MobilizationDetails, String> householderName;
	public static volatile SingularAttribute<MobilizationDetails, Village> village;
	public static volatile SingularAttribute<MobilizationDetails, String> PhysicalVerified;
	public static volatile SingularAttribute<MobilizationDetails, IecAssistant> iecAssistant;

}

