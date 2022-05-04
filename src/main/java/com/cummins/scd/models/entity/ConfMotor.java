package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.MatrizHtasHd;

@Entity
@Table(name = "ZMKT7352_DES_CONF_MOTOR")

public class ConfMotor implements Serializable {
	/**
	 * 
	 */
		 
	
    private static final long serialVersionUID = 1L;
    
    //------------------------------------------------
    //					FIELDS
    //------------------------------------------------
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_DES_CONF_MOTOR_SEQ")
    @SequenceGenerator(name="ZMKT7352_DES_CONF_MOTOR_SEQ", sequenceName="ZMKT7352_DES_CONF_MOTOR_SEQ", allocationSize=50)
    @Column(name = "ID_CONF_MOTOR")
    private BigInteger idConfMotor;    
   

    
    @Column(name = "ID_ESPMOTOR")
    private BigInteger idEspmotor;
    

    private BigInteger idNs;
    private BigInteger idMatrizPartes;
    private BigInteger idMatrizHtas;
    private BigInteger idEvaluacion;
     
    @Column(name = "MEC_REQ")
    private BigInteger mecReq;
     
  
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
	
	
	//----------------------------------------------------
	//					RELATIONS
	//----------------------------------------------------
    
    @ManyToOne
    @JoinColumn(name="idEvaluacion", insertable=false, updatable=false)
    private Evaluaciones ev;
    
    @ManyToOne
    @JoinColumn(name="idNs", insertable=false, updatable=false)
    private ServiceLevel ns;

    @ManyToOne
    @JoinColumn(name="idMatrizPartes", insertable=false, updatable=false)
    private MatrizPartesHd mpartesHd;

    @ManyToOne
    @JoinColumn(name="idMatrizHtas", insertable=false, updatable=false)
    private MatrizHtasHd mhtasHd;
    
  
    
    
	//-------------------------------------------------------
    //                   GETTERS AND SETTERS
    //-------------------------------------------------------
    
    
  
    
	public void setIdConfMotor(BigInteger r){
        this.idConfMotor = r;
    }
    
	public BigInteger getIdConfMotor(){
        return this.idConfMotor;
    }
	
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public BigInteger getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(BigInteger idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public void setEv(Evaluaciones ev) {
		this.ev = ev;
	}
	public Evaluaciones getEv() {
		return ev;
	}

	public void setIdESPM( BigInteger c){
        this.idEspmotor = c;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }
     //id Ns
     public BigInteger getIdNs() {
		return this.idNs;
    }
    public void setIdNs(BigInteger o){
        this.idNs = o;
    }
     //Numero Id parts
     public BigInteger getIdMatrizPartes() {
		return this.idMatrizPartes;
    }
    public void setIdMatrizPartes(BigInteger id) {
		this.idMatrizPartes = id;
    }
        //Numero Id Htas
    public BigInteger getIdMatrizHtas() {
        return this.idMatrizHtas;
    }
    public void setIdMatrizHtas(BigInteger id) {
        this.idMatrizHtas = id;
    }
    //Numero Mec req
    public BigInteger getMecReq() {
        return this.mecReq;
    }
    public void setMecReq(BigInteger id) {
        this.mecReq = id;
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
    // public Boolean isDeleted(){
    //     return (this.del == 'Y');
    // }
    public Character getDel(){
        return this.del;
    }
    public void setDel(Character del){
        this.del = del;
        
    }
    
    
	
   
	/*public ESPM getEspMotor(){
        return this.espmotor;
    }*/
    public ServiceLevel getNS(){
        return this.ns;
    }
    public MatrizPartesHd getMatrizPartes()
    {
        return this.mpartesHd;
    }
    public MatrizHtasHd getMatrizHtas()
    {
        return this.mhtasHd;
    }
	@Override
	public String toString() {
		return "ConfMotor [IdConfMotor = " + idConfMotor
                + ", IdEvaluacion = " + idEvaluacion
                + ", IdESPM = " + idEspmotor
                + ", IdNS = " + idNs
                + ", IdMatrizPartes = " + idMatrizPartes
                + ", IdMatrizHtas = " + idMatrizHtas
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}
}