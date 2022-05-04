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

import com.cummins.scd.models.entity.SpCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ZMKT7352_DES_LICENCIAS_QSOL")
@JsonIgnoreProperties(ignoreUnknown = true ) 

public class LicenciasQsol implements Serializable {
	/**
	 * 
	 */  
    @Id
    // @Column(name = "SP_CODE")
    private String spCode;
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;
    @Column(name = "FECHA_EXPIRACION")
    private Date fechaExpiracion;
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
	String action;
	
	
    @ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes spCodeObj;
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    //SP code
    public String getSpCode() {
		return this.spCode;
    }
    public void setSpCode(String r) {
		this.spCode = r;
    }
    
    //Fecha registro
    public Date getFechaRegistro() {
		return this.fechaRegistro;
    }
    public void setFechaRegistro(Date c) {
		this.fechaRegistro = c;
    }
    public Date getFechaExpiracion() {
		return this.fechaExpiracion;
    }
    public void setFechaExpiracion(Date c) {
		this.fechaExpiracion = c;
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
   
    public Character getDel(){
        return this.del;
        
    }
    public void setDel(Character del){
       
            this.del = del;
   
    }
    
    public SpCodes getSpCodeObj(){
        return this.spCodeObj;
    }
    
	@Override
	public String toString() {
		return "LicenciaQsol ["
				+ ", SpCode = " + spCode
                + ", fechaRegistro = " + fechaRegistro
                + ", fechaExpiracion = " + fechaExpiracion
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO") 
                + "]";
                
		
	}


}

