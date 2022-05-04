package com.cummins.scd.models.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@IdClass (UsersSpCodesId.class)
@Table(name = "ZMKT7352_DES_USERS_SPCODES")
public class UsersSpCodes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//---------------------------------------------------
	//					    Fields
	//---------------------------------------------------
	@Id
	private String wwid;
	@Id
	private String spCode;
	
	
	@Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY", nullable = false)
	private String lastUpdateBy;
	@Column(name = "DELETE_IND")
    private Character del;
	
	@Transient
	 private String action;
	
	@ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes spcode;

	public String getWwid() {
		return wwid;
	}

	public void setWwid(String wwid) {
		this.wwid = wwid;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
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

	public Character getDel() {
		return del;
	}

	public void setDel(Character del) {
		this.del = del;
	}

	public SpCodes getSpcode() {
		return spcode;
	}

	public void setSpcode(SpCodes spcode) {
		this.spcode = spcode;
	}

	
	@Override
	public String toString() {
		return "UsersSpCodes [wwid=" + wwid + ", spCode=" + spCode + ", creationDate=" + creationDate + ", createdBy="
				+ createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del
				+ ", spcode=" + spcode + "]";
	}

}
