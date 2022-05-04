package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ZMKT7352_DES_CONF_EVAL")

public class ConfEval implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_DES_CONF_EVAL_SEQ")
    @SequenceGenerator(name="ZMKT7352_DES_CONF_EVAL_SEQ", sequenceName="ZMKT7352_DES_CONF_EVAL_SEQ", allocationSize=1)
    //private int user_id;
	@Column(name = "ID_CONF_EVAL")
    private long idConfEval;
	@Column(name = "ID_EVALUACION")
    private long idEvaluacion;
	@Column(name = "ID_EVALUACION_ANT")
    private long idEvaluacionAnt;
    
    

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
	public long getIdConfEval() {
		return idConfEval;
	}
	public void setIdConfEval(long idConfEval) {
		this.idConfEval = idConfEval;
	}
	public long getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public long getIdEvaluacionAnt() {
		return idEvaluacionAnt;
	}
	public void setIdEvaluacionAnt(long idEvaluacionAnt) {
		this.idEvaluacionAnt = idEvaluacionAnt;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
 
 

}
