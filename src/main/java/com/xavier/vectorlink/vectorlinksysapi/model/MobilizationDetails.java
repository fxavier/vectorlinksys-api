package com.xavier.vectorlink.vectorlinksysapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mob_details")
public class MobilizationDetails implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date_mob")
	private LocalDate mobDate;
	
	@NotNull
	@Column(name = "house_holder_name")
	private String householderName;
	
	@NotNull
	@Column(name = "house_holder_id")
	private String householderId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "sentitized_structur")
	private MobilizationStatus mobilizationStatus;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "reason_not_sentitized")
	private ReasonNotSensitized reasonNotSensitized;
	
	
	@Column(name = "men_sentitized")
	private Long menSentitized;
	
	@Column(name = "women_sentitized")
	private Long women_sentitized;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "spray_acceptance")
	private SprayAcceptance sprayAcceptance;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "reason_not_accepted")
	private ReasonNotAccepted reasonNotAccepted;
	
	private String reference;
	
	@ManyToOne
	@JoinColumn(name = "mobilizer_id")
	private Mobilizer mobilizer;
	
	@ManyToOne
	@JoinColumn(name = "village_id")
	private Village village;
	
	@ManyToOne
	@JoinColumn(name = "iec_id")
	private IecAssistant iecAssistant;
	
	@Column(name = "verification_date")
	private LocalDate verificationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMobDate() {
		return mobDate;
	}

	public void setMobDate(LocalDate mobDate) {
		this.mobDate = mobDate;
	}

	public String getHouseholderName() {
		return householderName;
	}

	public void setHouseholderName(String householderName) {
		this.householderName = householderName;
	}

	public String getHouseholderId() {
		return householderId;
	}

	public void setHouseholderId(String householderId) {
		this.householderId = householderId;
	}

	public MobilizationStatus getMobilizationStatus() {
		return mobilizationStatus;
	}

	public void setMobilizationStatus(MobilizationStatus mobilizationStatus) {
		this.mobilizationStatus = mobilizationStatus;
	}

	public ReasonNotSensitized getReasonNotSensitized() {
		return reasonNotSensitized;
	}

	public void setReasonNotSensitized(ReasonNotSensitized reasonNotSensitized) {
		this.reasonNotSensitized = reasonNotSensitized;
	}

	public Long getMenSentitized() {
		return menSentitized;
	}

	public void setMenSentitized(Long menSentitized) {
		this.menSentitized = menSentitized;
	}

	public Long getWomen_sentitized() {
		return women_sentitized;
	}

	public void setWomen_sentitized(Long women_sentitized) {
		this.women_sentitized = women_sentitized;
	}

	public SprayAcceptance getSprayAcceptance() {
		return sprayAcceptance;
	}

	public void setSprayAcceptance(SprayAcceptance sprayAcceptance) {
		this.sprayAcceptance = sprayAcceptance;
	}

	public ReasonNotAccepted getReasonNotAccepted() {
		return reasonNotAccepted;
	}

	public void setReasonNotAccepted(ReasonNotAccepted reasonNotAccepted) {
		this.reasonNotAccepted = reasonNotAccepted;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Mobilizer getMobilizer() {
		return mobilizer;
	}

	public void setMobilizer(Mobilizer mobilizer) {
		this.mobilizer = mobilizer;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public IecAssistant getIecAssistant() {
		return iecAssistant;
	}

	public void setIecAssistant(IecAssistant iecAssistant) {
		this.iecAssistant = iecAssistant;
	}

	public LocalDate getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(LocalDate verificationDate) {
		this.verificationDate = verificationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobilizationDetails other = (MobilizationDetails) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
