package com.cummins.scd.models.entity.auxiliar.tables;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZMKT7352_DES_CAT_STATUS_EV")
public class Aux_StatusEv {
	
	//------------------------------------------
	//				   Fields
	//------------------------------------------
	@Id
	@Column(name = "ID_STATUS", nullable = false)           
	private BigInteger idStatus;
	@Column(name = "STATUS", nullable = false)
	private String status;
	@Column(name = "INGLES", nullable = false)
	private String ingles;
	@Column(name = "PORTUGUES", nullable = false)
	private String portugues;
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
	
	//-------------------------------------------------
	//              Getters and Setters
	//-------------------------------------------------
	public BigInteger getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(BigInteger idStatus) {
		this.idStatus = idStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIngles() {
		return ingles;
	}
	public void setIngles(String ingles) {
		this.ingles = ingles;
	}
	public String getPortugues() {
		return portugues;
	}
	public void setPortugues(String portugues) {
		this.portugues = portugues;
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

}