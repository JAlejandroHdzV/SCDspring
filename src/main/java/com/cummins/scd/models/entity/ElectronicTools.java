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


@Entity
@Table(name = "ZMKT7352_DES_LICENCIAS_INSITE")


public class ElectronicTools implements Serializable {
	/**
	 * 
	 */  
    @Id
    @Column(name = "PCID_TOOLINSTANCE")
    private String idTool;
   // @Column(name = "SP_CODE")
    private String spCode;
    @Column(name = "NP")
    private String numeroParte;
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
    private SpCodes spcode;
    
    
    
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	//Id Tool
    public String getId() {
		return this.idTool;
    }
    public void setId(String r) {
		this.idTool = r;
    }
    
    //sp code
    public String getSPCode() {
		return this.spCode;
    }
    public void setSPCode(String c) {
		this.spCode = c;
    }
    //numero de parte
    public String getNP() {
		return this.numeroParte;
    }
    public void setNP(String c) {
		this.numeroParte = c;
    }
    //Fecha registro
    public Date getFechaReg(){
        return this.fechaRegistro;
    }
    public void setFechaReg(Date date){
        this.fechaRegistro = date;
    }
    //Fecha registro
    public Date getFechaExp(){
        return this.fechaExpiracion;
    }
    public void setFechaExp(Date date){
        this.fechaExpiracion = date;
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
        
            this.del= del;
    
    }
    
    public SpCodes getCodes(){
        return this.spcode;
    }
	@Override
	public String toString() {
		return "HerramientasElectronicas ["
				+ ", Id = " + idTool
                + ", SpCode = " + spCode
                + ", NumeroParte = " + numeroParte
                + ", Fecha Registro = " + fechaRegistro
                + ", Fecha Expiracion = " + fechaExpiracion
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del== 'Y') ? "YES" : "NO") 
                + "]";
                
		
	}


}

