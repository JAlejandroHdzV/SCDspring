package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;

@Entity
@Table(name = "ZMKT7352_DES_USERS")
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------
	//					Fields
	//-----------------------------------------
	@Id
	@Column(name = "WWID", nullable = false)
	private String wwid;
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "EMAIL", nullable = true)
	private String email;
	
	private BigInteger idRol;
	
	@Column(name = "TODAS", nullable = false)
	private Character todas;
	
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
	
	
	//------------------------------------------------
	//				      Relations 
	//------------------------------------------------
	@ManyToOne
    @JoinColumn(name="idRol", insertable=false, updatable=false)
    private Aux_Roles rol;

	
	
	//------------------------------------------------
	//				 Getters and Setters
	//------------------------------------------------
	public String getWwid() {
		return wwid;
	}

	public void setWwid(String wwid) {
		this.wwid = wwid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigInteger getIdRol() {
		return idRol;
	}

	public void setIdRol(BigInteger idRol) {
		this.idRol = idRol;
	}

	public Character getTodas() {
		return todas;
	}

	public void setTodas(Character todas) {
		this.todas = todas;
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

	public String obtenerAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Aux_Roles getRol() {
		return rol;
	}

	public void setRol(Aux_Roles rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Users [wwid=" + wwid + ", nombre=" + nombre + ", email=" + email + ", idRol=" + idRol + ", todas="
				+ todas + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", rol=" + rol + "]";
	}
	
	
	
	
}
