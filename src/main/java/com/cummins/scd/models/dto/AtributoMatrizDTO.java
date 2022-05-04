package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.Date;



public class AtributoMatrizDTO {
	private BigInteger idMatriz;    
    private BigInteger tipoMatriz; 
    private BigInteger tipoAtributo; 
    private String idText; 
    private BigInteger idNumber; 
    private Date creationDate;
    private String createdBy;
	private Date lastUpdateDate;
	private String lastUpdateBy;
    private Character del;
    private String nombre;
	public BigInteger getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}
	public BigInteger getTipoMatriz() {
		return tipoMatriz;
	}
	public void setTipoMatriz(BigInteger tipoMatriz) {
		this.tipoMatriz = tipoMatriz;
	}
	public BigInteger getTipoAtributo() {
		return tipoAtributo;
	}
	public void setTipoAtributo(BigInteger tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}
	public String getIdText() {
		return idText;
	}
	public void setIdText(String idText) {
		this.idText = idText;
	}
	public BigInteger getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(BigInteger idNumber) {
		this.idNumber = idNumber;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    
	
}
