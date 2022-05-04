package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_StatusEv;

@Entity
@IdClass (RelPerfilMotorId.class)
@Table(name = "ZMKT7352_DES_REL_PERFIL_MOTOR")
public class RelPerfilMotor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private BigInteger idPerfil;
	@Id
	private BigInteger idConfMotor;
	
	@Column(name = "CALIFICACION", nullable = true)
	private BigInteger calificacion;
	
	//@Column(name = "ID_STATUS")
	private BigInteger idStatus;
	
	@Column(name = "COMENTARIOS", nullable = true)
	private String comentarios;
	
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
    
	@Transient
	 private String action;
	
	
	
	@ManyToOne
    @JoinColumn(name="idPerfil", insertable=false, updatable=false)
    private Perfiles pr;
	
	@ManyToOne
    @JoinColumn(name="idConfMotor", insertable=false, updatable=false)
    private ConfMotor cm;
	
	@ManyToOne
    @JoinColumn(name="idStatus", insertable=false, updatable=false)
    private Aux_StatusEv status;

	public BigInteger getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}

	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}

	public void setIdConfMotor(BigInteger idConfMotor) {
		this.idConfMotor = idConfMotor;
	}

	public BigInteger getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(BigInteger calificacion) {
		this.calificacion = calificacion;
	}

	public BigInteger getIdEstatus() {
		return idStatus;
	}

	public void setIdEstatus(BigInteger idEstatus) {
		this.idStatus = idEstatus;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Perfiles getPr() {
		return pr;
	}

	public void setPr(Perfiles pr) {
		this.pr = pr;
	}

	public ConfMotor getCm() {
		return cm;
	}

	public void setCm(ConfMotor cm) {
		this.cm = cm;
	}

	public Aux_StatusEv getStatus() {
		return status;
	}

	public void setStatus(Aux_StatusEv status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RelPerfilMotor [idPerfil=" + idPerfil + ", idConfMotor=" + idConfMotor + ", calificacion="
				+ calificacion + ", idEstatus=" + idStatus + ", comentarios=" + comentarios + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy="
				+ lastUpdateBy + ", del=" + del + ", pr=" + pr + ", cm=" + cm + ", status=" + status + "]";
	}



	
	

	
	
	

	
}
