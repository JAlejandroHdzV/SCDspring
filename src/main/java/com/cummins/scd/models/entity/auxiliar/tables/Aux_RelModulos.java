package com.cummins.scd.models.entity.auxiliar.tables;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.cummins.scd.models.entity.Aux_RelModulosId;

@Entity
@IdClass (Aux_RelModulosId.class)
@Table(name = "ZMKT7352_DES_CAT_REL_MODULOS")
public class Aux_RelModulos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_MODULO")
    private BigInteger idModulo;
	@Id
	@Column(name = "ID_SUBMODULO")
    private BigInteger idSubmodulo;
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
	public BigInteger getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(BigInteger idModulo) {
		this.idModulo = idModulo;
	}
	public BigInteger getIdSubmodulo() {
		return idSubmodulo;
	}
	public void setIdSubmodulo(BigInteger idSubmodulo) {
		this.idSubmodulo = idSubmodulo;
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
