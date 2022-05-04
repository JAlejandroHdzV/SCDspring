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

import com.cummins.scd.models.entity.Oems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.cummins.scd.models.entity.ConfOemId;
import com.cummins.scd.models.entity.ConfMotor;

@Entity
@IdClass (ConfOemId.class)
@Table(name = "ZMKT7352_DES_CONF_OEM")


public class ConfOem implements Serializable {
	/**
	 * 
	 */
    
    @Id
    private BigInteger idConfMotor;    
    @Id
    private BigInteger idOem;
    
      

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
    

    
    public Character getDel() {
		return del;
	}

	public void setDel(Character del) {
		this.del = del;
	}

	@ManyToOne
    @JoinColumn(name="idConfMotor", insertable=false, updatable=false)
    @JsonIgnoreProperties
    private ConfMotor confM;

    @ManyToOne
    @JoinColumn(name="idOem", insertable=false, updatable=false)
    @JsonIgnoreProperties
    private Oems oem;

	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}

	public void setIdConfMotor(BigInteger idConfMotor) {
		this.idConfMotor = idConfMotor;
	}

	public BigInteger getIdOem() {
		return idOem;
	}

	public void setIdOem(BigInteger idOem) {
		this.idOem = idOem;
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

	public ConfMotor getConfM() {
		return confM;
	}

	public void setConfM(ConfMotor confM) {
		this.confM = confM;
	}

	public Oems getOem() {
		return oem;
	}

	public void setOem(Oems oem) {
		this.oem = oem;
	}
   
    // @ManyToOne(optional=false)
    // @JoinColumn(name="idRegion", referencedColumnName="id_region", updatable=false, insertable=false)
    // private Region region;

    // @ManyToOne
    // @JoinColumn(name="idPais", referencedColumnName="id_pais", updatable=false, insertable=false)  
    // private Country country;
    /*Getter and setters*/   
    
    
    


}

