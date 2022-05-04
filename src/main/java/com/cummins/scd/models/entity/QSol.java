package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.cummins.scd.models.entity.QSolId;


@Entity

@Table(name = "ZMKT7352_DES_CAT_ASKQSOL")


public class QSol implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;   
  
    @Id  
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZMKT7352_DES_CAT_ASKQSOL_SEQ")
    @SequenceGenerator(name="ZMKT7352_DES_CAT_ASKQSOL_SEQ", sequenceName="ZMKT7352_DES_CAT_ASKQSOL_SEQ", allocationSize=1)
    @Column(name = "ID_ASKQSOL")
    private BigInteger idAskqsol;
    
    private BigInteger idEvaluacion;    
    
    
    @Column(name = "PREGUNTA")
    private String pregunta;
    @Column(name = "INGLES")
    private String english;
    @Column(name = "PORTUGUES")
    private String portuguese;
    @Column(name = "PONDERACION")
    private float ponderacion;
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
    @Transient
    private String nombreRegion;
    
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idEvaluacion", insertable=false, updatable=false)
    private Evaluaciones ev;
    // @ManyToOne(optional=false)
    // @JoinColumn(name="idRegion", referencedColumnName="id_region", updatable=false, insertable=false)
    // private Region region;

    // @ManyToOne
    // @JoinColumn(name="idPais", referencedColumnName="id_pais", updatable=false, insertable=false)  
    // private Country country;
    /*Getter and setters*/   
    
    
    
    public Character getDel() {
		return del;
	}
	
	

	public BigInteger getIdAskqsol() {
		return idAskqsol;
	}



	public void setIdAskqsol(BigInteger idAskqsol) {
		this.idAskqsol = idAskqsol;
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
	
    public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	//Id Evaluacion
    public BigInteger getIdEvaluacion() {
		return this.idEvaluacion;
    }
   public void setIdEvaluacion( BigInteger r) {
		this.idEvaluacion = r;
    }
    
    //Questions
    public String getPregunta() {
		return this.pregunta;
    }
    public void setPregunta( String c) {
		this.pregunta = c;
    }
    //Ponderacion
    public float getPonderacion() {
		return this.ponderacion;
    }
    public void setPonderacion( float r) {
		this.ponderacion = r;
    }
     //English
     public String getEnglish() {
		return this.english;
    }
    public void setEnglish(String e){
        this.english = e;
    }
  
    //Portuguese
    public String getPortuguese() {
		return this.portuguese;
    }
    public void setPortuguese(String p){
        this.portuguese = p;
    }
   
    //Creation Date
    public Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    //Created By
    public String getCreatedBy(){
        return this.createdBy;
    }
    public void setCreatedBy(String user){
        this.createdBy = user;
    }

    //Last Updated Date
    public Date getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(Date date){
        this.lastUpdateDate = date;
    }

    //Last updated By
    public String getLastUpdatedBy(){
        return this.lastUpdateBy;
    }
    public void setLastUpdatedBy(String user){
        this.lastUpdateBy = user;
    }

    //Deleted
    
    public Evaluaciones obtenerEvaluacion(){
        return this.ev;
    }
    
	@Override
	public String toString() {
		return "QSol ["
				+ ", IdEvaluacion = " + idEvaluacion
                + ", Pregunta = " + pregunta
                + ", Ponderacion = " + ponderacion
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}


}

