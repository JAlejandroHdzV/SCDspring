package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_CAT_ANIOS")

public class Aux_Anio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
		 
    @Id
    //private int user_id;
	@Column(name = "ID_ANIO")
    private BigInteger idanio;
	@Column(name = "ANIO")
    private String anio;
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
    
	/*Getter and setters*/   
    
    
    //Id 
    public BigInteger getId() {
		return this.idanio;
    }
    public void setId( BigInteger id) {
		this.idanio = id;
    }
    
    //Rank
	public String getAnio() {
		return this.anio;
    }
    public void setAnio(String o){
        this.anio = o;
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
	
	
	@Override
	public String toString() {
		return "Anio [Id = " + idanio
                + ", anio = " + anio
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}
}