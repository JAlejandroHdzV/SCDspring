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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;



@Entity
@Table(name = "ZMKT7352_DES_CAT_REGION")


public class Region implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;
	@Id
    
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_REGION")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_REGION", sequenceName="ZMKT7352_SEC_ID_REGION", allocationSize=1)
    //private int user_id;
	@Column(name = "ID_REGION")
    private BigInteger idRegion; 
    
    

	@Column(name = "REGION")
    private String region;    
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
	
	@OneToMany
    @JoinColumn(name="idRegion")
    private List<CountryPerRegion> countriesPerRegion;
	  
    
	
	
    
    //Id Region
    public BigInteger getId() {
		return this.idRegion;
    }
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setId( BigInteger id) {
		this.idRegion = id;
    }
    
    //Region Name
	public String getName() {
		return this.region;
    }
    public void setName(String name){
        this.region = name;
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
    
	
	
	@Override
	public String toString() {
		return "Region [Id = " + idRegion
				+ ", Name = " + region
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}
	public Character getDel() {
		return del;
	}
	public void setDel(Character del) {
		this.del = del;
	}


}