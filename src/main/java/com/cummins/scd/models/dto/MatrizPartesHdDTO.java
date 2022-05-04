package com.cummins.scd.models.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MatrizPartesHdDTO implements Serializable {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	
    private BigInteger idMatriz;
    private String nombre;
    private String noRevision;
    private BigInteger idTipo;
    private BigInteger idNs;
    private BigInteger idStatus;
    private String so;
    private String esn;
    private String cpl;
    private String creationDate;
    private String createdBy;
	private String lastUpdateDate;
	private String lastUpdateBy;
    private Character del;
	public BigInteger getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNoRevision() {
		return noRevision;
	}
	public void setNoRevision(String noRevision) {
		this.noRevision = noRevision;
	}
	public BigInteger getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(BigInteger idTipo) {
		this.idTipo = idTipo;
	}
	public BigInteger getIdNs() {
		return idNs;
	}
	public void setIdNs(BigInteger idNs) {
		this.idNs = idNs;
	}
	public BigInteger getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(BigInteger idStatus) {
		this.idStatus = idStatus;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public String getCpl() {
		return cpl;
	}
	public void setCpl(String cpl) {
		this.cpl = cpl;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
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
	@Override
	public String toString() {
		return "MatrizPartesHdDTO [idMatriz=" + idMatriz + ", nombre=" + nombre + ", noRevision=" + noRevision
				+ ", idTipo=" + idTipo + ", idNs=" + idNs + ", idStatus=" + idStatus + ", so=" + so + ", esn=" + esn
				+ ", cpl=" + cpl + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + "]";
	}
	
}
