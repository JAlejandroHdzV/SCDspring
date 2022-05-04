package com.cummins.scd.models.entity.auxiliar.tables;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZMKT7352_DES_CAT_ROLES")
public class Aux_Roles implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//-------------------------------------------------
	//					Fields
	//-------------------------------------------------
	@Id
	@Column(name = "ID_ROL")
	private BigInteger idRol;
	@Column(name = "ROL")
	private String rol;
	@Column(name = "INGLES")
	private String ingles;
	@Column(name = "PORTUGUES")
	private String portugues;
	@Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
	private String lastUpdateBy;
	@Column(name = "DELETE_IND")
    private Character del;
	
	
	//---------------------------------------
	//				Getters and Setters
	//---------------------------------------
	public BigInteger getIdRol() {
		return idRol;
	}
	public void setIdRol(BigInteger idRol) {
		this.idRol = idRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
