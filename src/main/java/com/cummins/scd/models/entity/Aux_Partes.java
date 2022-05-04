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
@Table(name = "ZMKT7352_DES_CAT_PARTES")

public class Aux_Partes implements Serializable {
	/**
	 * 
	 */
    @Id
	@Column(name = "NP")
    private String np;
	@Column(name = "NP_ANTERIOR")
    private String nPAnterior;
    @Column(name = "NP_EQUIVALENTE")
    private String nPEquivalente;
    @Column(name = "DESC_ESPANOL")
    private String dSpanish;
    @Column(name = "DESC_INGLES")
    private String dEnglish;
    @Column(name = "DESC_PORTUGUES")
    private String dPortuguese;
    @Column(name = "CODIGO_VENTA")
    private String codigoVenta;
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
	
	
	
	public String getNp() {
		return np;
	}
	public void setNp(String np) {
		this.np = np;
	}
	public String getnPAnterior() {
		return nPAnterior;
	}
	public void setnPAnterior(String nPAnterior) {
		this.nPAnterior = nPAnterior;
	}
	public String getnPEquivalente() {
		return nPEquivalente;
	}
	public void setnPEquivalente(String nPEquivalente) {
		this.nPEquivalente = nPEquivalente;
	}
	public String getdSpanish() {
		return dSpanish;
	}
	public void setdSpanish(String dSpanish) {
		this.dSpanish = dSpanish;
	}
	public String getdEnglish() {
		return dEnglish;
	}
	public void setdEnglish(String dEnglish) {
		this.dEnglish = dEnglish;
	}
	public String getdPortuguese() {
		return dPortuguese;
	}
	public void setdPortuguese(String dPortuguese) {
		this.dPortuguese = dPortuguese;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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
		return "Aux_Partes [nP=" + np + ", nPAnterior=" + nPAnterior + ", nPEquivalente=" + nPEquivalente
				+ ", dSpanish=" + dSpanish + ", dEnglish=" + dEnglish + ", dPortuguese=" + dPortuguese
				+ ", codigoVenta=" + codigoVenta + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + "]";
	}
    
	  
    
   /* 
    public String getNP() {
		return this.nP;
    }
    public void setNP( String id) {
		this.nP = id;
    }
    
	public String getNPAnterior() {
		return this.nPAnterior;
    }
    public void setNPAnterior(String o){
        this.nPAnterior = o;
    }
    public String getNPEquivalente() {
		return this.nPEquivalente;
    }
    public void setNPEquivalente(String o){
        this.nPEquivalente = o;
    }
    
    public String getDSpanish() {
		return this.dSpanish;
    }
    public void setDSpanish(String e){
        this.dSpanish = e;
    }
    public String getEnglish() {
		return this.dEnglish;
    }
    public void setEnglish(String e){
        this.dEnglish = e;
    }
  
    public String getPortuguese() {
		return this.dPortuguese;
    }
    public void setPortuguese(String p){
        this.dPortuguese = p;
    }
    
    public String getCodigoVenta()
    {
            return this.codigoVenta;

    }
    public void setCodigoVenta(String c)
    {
        this.codigoVenta = c;
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
		return "Herramienta [nP = " + nP
                + ", NParteAnterior = " + nPAnterior
                + ", NParteAnterior = " + nPEquivalente
                + ", Spanish = " + dSpanish
                + ", English = " + dEnglish
                + ", Portuguese = " + dPortuguese
                + ", CodigoVenta = " + codigoVenta
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
    }*/
}