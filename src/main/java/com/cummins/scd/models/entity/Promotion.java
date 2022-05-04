package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;

import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.Pqs;


@Entity
@IdClass (PromotionId.class)
@Table(name = "ZMKT7352_DES_PROMOTION")


public class Promotion implements Serializable {
	/**
	 * 
	 */
    @Id
	private String promotionId;
    @Id
    private String spCode;
    @Id
    private String programId;
       

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "JOB_TITLE")
    private String jobTitle;
	@Column(name = "DATE_PASSED")
    private Date datePassed;
	@Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
	private String lastUpdateBy;

    
    @ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes code;
    @ManyToOne
    @JoinColumn(name="programId", insertable=false, updatable=false)
    private Pqs program;

    
	public SpCodes getCode() {
		return code;
	}

	public void setCode(SpCodes code) {
		this.code = code;
	}

	public Pqs getProgram() {
		return program;
	}

	public void setProgram(Pqs program) {
		this.program = program;
	}

	@Override
	public String toString() {
		return "Promotion ["
                + ", FirstName = " + firstName
                + ", MiddleName = " + middleName
                + ", LastName = " + lastName
                + ", JobTitle = " + jobTitle
                + ", DatePassed = " + datePassed
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
                + "]";
                
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getDatePassed() {
		return datePassed;
	}

	public void setDatePassed(Date datePassed) {
		this.datePassed = datePassed;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	
	

	

}

