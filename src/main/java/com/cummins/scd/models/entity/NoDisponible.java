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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;

import com.cummins.scd.models.entity.SpCodes;


@Entity
@Table(name = "ZMKT7352_DES_NODISP")

public class NoDisponible implements Serializable {
	/**
	 * 
	 */
    @Id
	@Column(name = "FOLIO")
    private String folio;
	//@Column(name = "SP_CODE")
    private String spCode;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "RAZON")
    private String razon;
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
	@Transient
	private String filter;
	@Transient
	private String size;
	@Transient
	private String page;
	
	
	
    @ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes codes;

	/*Getter and setters*/   
    
    
    
    
    //Numero folio
    public String getFolio() {
		return this.folio;
    }
    public String obtenerFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String obtenerSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String obtenerPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setFolio(String folio) {
		this.folio = folio;
    }
    
    //SpCode
	public String getCode() {
		return this.spCode;
    }
    public void setCode(String o){
        this.spCode = o;
    }
    //Fecha falla
    public Date getFecha() {
		return this.fecha;
    }
    public void setFecha(Date o){
        this.fecha = o;
    }
    
    //Cliente
    public String getCliente() {
		return this.cliente;
    }
    public void setCliente(String o){
        this.cliente = o;
    }
    //Motivo
    public String getRazon() {
		return this.razon;
    }
    public void setRazon(String e){
        this.razon = e;
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
    public SpCodes getSPCodes(){
        return this.codes;
    }
    
    
   
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "NoDisponible [Folio = " + folio
                + ", SpCode = " + spCode
                + ", Fecha = " + fecha
                + ", Cliente = " + cliente
                + ", Razon = " + razon
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
    }
}