package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.QuejasId;


@Entity
@Table(name = "ZMKT7352_DES_CAT_QUEJAS")
public class Quejas implements Serializable {
		 
	private static final long serialVersionUID = 1L;   
	//---------------------------------------------------
    //				  FIELDS QUEJAS
    //---------------------------------------------------
    
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_DES_CAT_QUEJAS_SEQ1")
    @SequenceGenerator(name="ZMKT7352_DES_CAT_QUEJAS_SEQ1", sequenceName="ZMKT7352_DES_CAT_QUEJAS_SEQ1", allocationSize=20)
    private BigInteger idCatQuejas;   
    
    private BigInteger idEvaluacion;
    
    @Column(name = "QUEJA")
    private String queja;
    
    @Column(name = "INGLES")
    private String english;
    @Column(name = "PORTUGUES")
    private String portuguese;
    @Column(name = "PONDERACION")
    private Double ponderacion;
    
    private BigInteger idTipo;
    
	@Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
	private String lastUpdateBy;
    
    @Transient
	private String action;
	

	@Column(name = "DELETE_IND")
    private Character del;
    
    @ManyToOne
    @JoinColumn(name="idEvaluacion", insertable=false, updatable=false)
    private Evaluaciones ev;
    
    @ManyToOne
    @JoinColumn(name="idTipo", insertable=false, updatable=false)
    private Aux_TipoQ at;
    // @ManyToOne(optional=false)
    // @JoinColumn(name="idRegion", referencedColumnName="id_region", updatable=false, insertable=false)
    // private Region region;

    
    
    
    //---------------------------------------------------
    //				GETTERS AND SETTERS
    //---------------------------------------------------
    
	public BigInteger getIdCatQueja() {
		return idCatQuejas;
	}

	public void setIdCatQueja(BigInteger idCatQueja) {
		this.idCatQuejas = idCatQueja;
	}

	public BigInteger getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(BigInteger idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getQueja() {
		return queja;
	}

	public void setQueja(String queja) {
		this.queja = queja;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getPortuguese() {
		return portuguese;
	}

	public void setPortuguese(String portuguese) {
		this.portuguese = portuguese;
	}

	public Double getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Double ponderacion) {
		this.ponderacion = ponderacion;
	}

	public BigInteger getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(BigInteger idTipo) {
		this.idTipo = idTipo;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Character getDel() {
		return del;
	}

	public void setDel(Character del) {
		this.del = del;
	}

	public Evaluaciones getEv() {
		return ev;
	}

	public void setEv(Evaluaciones ev) {
		this.ev = ev;
	}

	public Aux_TipoQ getAt() {
		return at;
	}

	public void setAt(Aux_TipoQ at) {
		this.at = at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    // @ManyToOne
    // @JoinColumn(name="idPais", referencedColumnName="id_pais", updatable=false, insertable=false)  
    // private Country country;
    /*Getter and setters*/   
    
    
    //Id Evaluacion
    
    


}

