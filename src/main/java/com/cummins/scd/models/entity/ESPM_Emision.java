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
import com.cummins.scd.models.entity.Emissions;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.ESPM_EmisionId;
@Entity
@IdClass (ESPM_EmisionId.class)
@Table(name = "ZMKT7352_DES_ESPM_EMISION")

public class ESPM_Emision implements Serializable {
	
	//-----------------------------------------------
	//			   FIELDS ESPM_EMISION
	//-----------------------------------------------
    @Id
    private BigInteger idEspmotor;   
    @Id
    private BigInteger idEmision;
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
    
    //------------------------------------------------
    //			    OVERLOAD CONSTRUCTORS
    //------------------------------------------------
	public ESPM_Emision(BigInteger idEspmotor, BigInteger idEmision, Date creationDate, String createdBy,
			Date lastUpdateDate, String lastUpdateBy, Character del) {
		this.idEspmotor = idEspmotor;
		this.idEmision = idEmision;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateBy = lastUpdateBy;
		this.del = del;
	}
	public ESPM_Emision() {
		
	}

	//------------------------------------------------
    //				     RELATIONS
    //------------------------------------------------
    @ManyToOne
    @JoinColumn(name="idEmision", insertable=false, updatable=false)
    private Emissions em;
    
    
    
    //-----------------------------------------------   
    //				GETTERS AND SETTERS
    //-----------------------------------------------
    
    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setIdEmision( BigInteger c){
        this.idEmision = c;
    }
    public BigInteger getIdEmision(){
        return this.idEmision;
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
    public Character getDel(){
        return this.del;
    }
    public void setDel(Character del){
        this.del = del;
        
    }
	public Emissions getEmmision(){
        return this.em;
    }
    
	
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	//-------------------------------------------------------
	//				   		METHOD TO STRING
	//-------------------------------------------------------
	@Override
	public String toString() {
		return "ESPM_Emision [idEspmotor=" + idEspmotor + ", idEmision=" + idEmision + ", creationDate=" + creationDate
				+ ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy
				+ ", del=" + del + ", action=" + action + ", em=" + em + "]";
	}
	
}