package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_CAT_STATUS_MATRIZ")

public class Aux_StatusMatriz implements Serializable {
	/**
	 * 
	 */
		 
	@Id
    //private int user_id;
	@Column(name = "ID_STATUS")
    private BigInteger idStatus;
	@Column(name = "STATUS")
    private String status;
    @Column(name = "INGLES")
    private String english;
    @Column(name = "PORTUGUES")
    private String portuguese;
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
	
	
	//----------------------------------------------
	//				Getters and Setters
	//----------------------------------------------
	public BigInteger getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(BigInteger idStatus) {
		this.idStatus = idStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Aux_StatusMatriz [idStatus=" + idStatus + ", status=" + status + ", english=" + english
				+ ", portuguese=" + portuguese + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + "]";
	}
    
	   
    
    
    
    /*public BigInteger getId() {
		return this.idStatus;
    }
    public void setId( BigInteger id) {
		this.idStatus = id;
    }
    
  
	public String getStatus() {
		return this.status;
    }
    public void setStatus(String o){
        this.status = o;
    }

    public String getEnglish() {
		return this.english;
    }
    public void setRank(String e){
        this.english = e;
    }
  
    
    public String getPortuguese() {
		return this.portuguese;
    }
    public void setPortuguese(String p){
        this.portuguese = p;
    }
     
   
    public Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    
    public String getCreatedBy(){
        return this.createdBy;
    }
    public void setCreatedBy(String user){
        this.createdBy = user;
    }

    
    public Date getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(Date date){
        this.lastUpdateDate = date;
    }

    
    public String getLastUpdatedBy(){
        return this.lastUpdateBy;
    }
    public void setLastUpdatedBy(String user){
        this.lastUpdateBy = user;
    }

    public Character getDelete(){
        return this.del;
    }
    public void setDelete(Character del){
        this.del = del;
        
    }
	
	
	@Override
	public String toString() {
		return "Tipo [Id = " + idStatus
                + ", Status = " + status
                + ", English = " + english
                + ", Portuguese = " + portuguese
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}*/
}