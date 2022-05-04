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
import com.cummins.scd.models.entity.ESPM_RegionId;
@Entity
@IdClass (ESPM_RegionId.class)
@Table(name = "ZMKT7352_DES_ESPM_REGION")

public class ESPM_Region implements Serializable {
	
	//-------------------------------------------
	//			   FIELDS ESPM_REGION
	//-------------------------------------------
    @Id
    private BigInteger idEspmotor;    
    @Id
    private BigInteger idRegion;
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
	
    //-------------------------------------------
  	//			   		RELATIONS
  	//-------------------------------------------
    @ManyToOne
    @JoinColumn(name="idRegion", insertable=false, updatable=false)
    private Region reg;
    
    
    //-------------------------------------------
  	//			   OVERLOAD CONSTRUCTORS
  	//-------------------------------------------
    public ESPM_Region(BigInteger idEspmotor, BigInteger idRegion, Date creationDate, String createdBy,
			Date lastUpdateDate, String lastUpdateBy, Character del) {
		this.idEspmotor = idEspmotor;
		this.idRegion = idRegion;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateBy = lastUpdateBy;
		this.del = del;
	}
    public ESPM_Region() {
	}

	//-------------------------------------------
  	//			   GETTERS AND SETTERS
  	//-------------------------------------------
    
    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
   
	public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setIdRegion( BigInteger c){
        this.idRegion = c;
    }
    public BigInteger getIdRegion(){
        return this.idRegion;
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
    
    
	
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Region getReg() {
		return reg;
	}
	public void setReg(Region reg) {
		this.reg = reg;
	}
	@Override
	public String toString() {
		return "ESPM_Region [idEspmotor=" + idEspmotor + ", idRegion=" + idRegion + ", creationDate=" + creationDate
				+ ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy
				+ ", del=" + del + ", action=" + action + ", reg=" + reg + "]";
	}
	
}