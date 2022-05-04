package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.apache.commons.io.IOUtils;

import com.cummins.scd.models.entity.SpCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ZMKT7352_DES_RESCATES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rescates implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    @Column(name = "FECHA_FALLA")
    private Date fechaFalla;
    @Column(name = "TIEMPO_RESPUESTA")
    private Double tiempoRespuesta;
    @Column(name = "TIEMPO_REPARACION")
    private Double tiempoReparacion;
    @Column(name = "TIEMPO_MUERTO")
    private Double tiempoMuerto;
    @Column(name = "MOTIVO")
    private String motivo;
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
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes codes;

	/*Getter and setters*/   

	
	
   public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    /*
    
    //SpCode
	public String getCode() {
		return this.spCode;
    }
	
	public void setCode(String o){
        this.spCode = o;
    }
		*/
	
    
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Date getFechaFalla() {
		return fechaFalla;
	}
	public void setFechaFalla(Date fechaFalla) {
		this.fechaFalla = fechaFalla;
	}
	
	
	
	public Double getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	public void setTiempoRespuesta(Double tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}
	public Double getTiempoReparacion() {
		return tiempoReparacion;
	}
	public void setTiempoReparacion(Double tiempoReparacion) {
		this.tiempoReparacion = tiempoReparacion;
	}
	public Double getTiempoMuerto() {
		return tiempoMuerto;
	}
	public void setTiempoMuerto(Double tiempoMuerto) {
		this.tiempoMuerto = tiempoMuerto;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
	
	
	
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public SpCodes getCodes() {
		return codes;
	}
	public void setCodes(SpCodes codes) {
		this.codes = codes;
	}
	@Override
	public String toString() {
		return "Rescates [Folio = " + folio
                + ", SpCode = " + spCode
                + ", FechaFalla = " + fechaFalla
                + ", Cliente = " + cliente
                + ", TiempoRespuesta = " + tiempoRespuesta
                + ", TiempoReparacion = " + tiempoReparacion
                + ", TiempoMuerto = " + tiempoMuerto
                + ", Motivo = " + motivo
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO") 
                + "]";
    }
}