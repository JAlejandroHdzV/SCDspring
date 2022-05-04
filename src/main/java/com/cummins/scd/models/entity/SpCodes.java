package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.apache.commons.io.IOUtils;

import org.hibernate.annotations.ForeignKey;

import com.cummins.scd.models.entity.Aux_DRDLR;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.Oems;

@Entity
@Table(name = "ZMKT7352_DES_CAT_SPCODES")
@JsonIgnoreProperties(ignoreUnknown = true ) 
public class SpCodes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
    @Id
	@Column(name = "SP_CODE")
    private String spCode;
	@Column(name = "SERVICE_PROVIDER_NAME")
    private String providerName;
    //@Column(name = "ID_TIPO")
    private BigInteger idTipo;
    @Column(name = "RESPONSIBLE_BRANCH_CODE")
    private String responsibleBranchCode;
    //@Column(name = "ID_PAIS")
    private BigInteger idPais;
    //@Column(name = "ID_OEM")
    private BigInteger idOem;
    @Column(name = "ADD1")
    private String add;
    @Column(name = "CITY_NAME")
    private String city;
    @Column(name = "ISO_3166")
    private String iso;
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

    @ManyToOne
    @JoinColumn(name="idTipo", insertable=false, updatable=false)
    private Aux_DRDLR tipo;

    @ManyToOne
    @JoinColumn(name="idPais", insertable=false, updatable=false)
    private Country pais;

    @ManyToOne
    @JoinColumn(name="idOem", insertable=false, updatable=false)
    private Oems oem;
    
	/*Getter and setters*/   
    
    
    public String obtenerAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
    
    //Numero sp code
    public String getSpCode() {
		return this.spCode;
    }
   
	public void setSpCode( String id) {
		this.spCode = id;
    }
    
    //provider name
	public String getProviderName() {
		return this.providerName;
    }
    public void setProviderName(String o){
        this.providerName = o;
    }
    //Tipo
    public BigInteger getTipo() {
		return this.idTipo;
    }
    public void setTipo(BigInteger o){
        this.idTipo = o;
    }
    
    //Responsible branch code
    public String getResponsibleBranchCode() {
		return this.responsibleBranchCode;
    }
    public void setResponsibleBranchCode(String o){
        this.responsibleBranchCode = o;
    }
    //Pais
    public BigInteger getIdPais() {
		return this.idPais;
    }
    public void setIdPais(BigInteger p){
        this.idPais = p;
    }
    //OEM
    public BigInteger getIdOem() {
        return this.idOem;
    }
    public void setIdOem(BigInteger p){
        this.idOem = p;
    }
    //Direccion
    public String getAdd() {
        return this.add;
    }
    public void setAdd(String a){
        this.add = a;
    }
    //City
    public String getCity() {
		return this.city;
    }
    public void setCity(String e){
        this.city = e;
    }
    //ISO
    public String getIso() {
		return this.iso;
    }
    public void setIso(String e){
        this.iso = e;
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

   

    public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
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
    public Aux_DRDLR getDRDLR(){
        return this.tipo;
    }
    public Country getCountry(){
        return this.pais;
    }
    public Oems getOEM(){
        return this.oem;
    }
	@Override
	public String toString() {
		return "SPCode [SpCode = " + spCode
                + ", ServiceProvider = " + providerName
                + ", idTipo = " + idTipo
                + ", ResponsibleBranchCode = " + responsibleBranchCode
                + ", idPais = " + idPais
                + ", idOem = " + idOem
                + ", Address = " + add
                + ", City = " + city
                + ", Iso = " + iso
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
    }
}