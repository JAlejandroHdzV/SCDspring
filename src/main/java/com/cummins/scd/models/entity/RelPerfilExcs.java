package com.cummins.scd.models.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_Modulos;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_RelModulos;

@Entity
@IdClass (RelPerfilExcsId.class)
@Table(name = "ZMKT7352_DES_REL_PERFIL_EXCS")
public class RelPerfilExcs {

	//--------------------------------------------------
	//					Fields
	//--------------------------------------------------
	@Id
	private BigInteger idPerfil;
	@Id
	private BigInteger idModulo;
	@Id
	private BigInteger idSubmodulo;
	@Id
	private BigInteger idConfMotor;
	
	
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
	
	//-----------------------------------------------------------------------
	//					Create constructor to filter record
	//-----------------------------------------------------------------------
	 public RelPerfilExcs(BigInteger idPerfil, BigInteger idModulo, BigInteger idSubmodulo,BigInteger idConfMotor) {
			super();
			this.idPerfil = idPerfil;
			this.idModulo = idModulo;
			this.idSubmodulo = idSubmodulo;
			this.idConfMotor= idConfMotor;
	}
	 public RelPerfilExcs() {
			super();
	}
	
	 
	 
	 //-----------------------------------------------------------------------------------
	 //					       Relation to table DES_PERFILES
	 //-----------------------------------------------------------------------------------
	@ManyToOne
    @JoinColumn(name="idPerfil", insertable=false, updatable=false)
    private Perfiles per;
    
	//-----------------------------------------------------------------------------------
	//					      Relation to table DES_REL_MODULOS
	//-----------------------------------------------------------------------------------
	@ManyToOne
        @JoinColumn(name="idModulo", referencedColumnName="ID_MODULO",insertable=false, updatable=false)
        
    private Aux_Modulos idMod;
	
	//-----------------------------------------------------------------------------------
	//					      Relation to table DES_REL_MODULOS
	//-----------------------------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name="idSubmodulo", referencedColumnName="ID_MODULO",insertable=false, updatable=false)
    private Aux_Modulos idSub;
	
	//-----------------------------------------------------------------------------------
	//					      Relation to table DES_CONF_MOTOR
	//-----------------------------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name="idConfMotor", referencedColumnName="ID_CONF_MOTOR",insertable=false, updatable=false)
    private ConfMotor idconfmotor;
	//-----------------------------------------------
	//				Getters and Setters
	//-----------------------------------------------
	
	public BigInteger getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}

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
	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}

	public void setIdConfMotor(BigInteger idConfM) {
		this.idConfMotor = idConfM;
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
	
	@Override
	public String toString() {
		return "RelPerfilExcs [idPerfil=" + idPerfil + ", idModulo=" + idModulo + ", idSubmodulo=" + idSubmodulo
				+ ", idConfMotor=" + idConfMotor + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", per="
				+ per + ", idMod=" + idMod + ", idSub=" + idSub + ", idconfmotor=" + idconfmotor + "]";
	}

	
	
}
