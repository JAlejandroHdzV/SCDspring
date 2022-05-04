package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_CAT_HERRAMIENTAS")

public class Tools implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
    @Id
	@Column(name = "NP")
    private String nP;
	@Column(name = "NP_ANTERIOR")
    private String nPAnterior;
    @Column(name = "DESC_ESPANOL")
    private String dSpanish;
    @Column(name = "DESC_INGLES")
    private String dEnglish;
    @Column(name = "DESC_PORTUGUES")
    private String dPortuguese;
    @Column(name = "CODIGO_VENTA")
    private String codigoVenta;
    @Column(name = "ESPECIFICACION")
    private byte[] especificacion;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_TYPE")
    private String fileType;
	@Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
	private String lastUpdatedBy;
	@Column(name = "DELETE_IND")
    private Character del;
    
	/*Getter and setters*/   
    
	@Transient
	 private String action;
	
    
	
	
    public String getnP() {
		return nP;
	}
	public void setnP(String nP) {
		this.nP = nP;
	}
	public String getnPAnterior() {
		return nPAnterior;
	}
	public void setnPAnterior(String nPAnterior) {
		this.nPAnterior = nPAnterior;
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
	public byte[] getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(byte[] especificacion) {
		this.especificacion = especificacion;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	@Override
	public String toString() {
		return "Herramienta [nP = " + nP
                + ", NParteAnterior = " + nPAnterior
                + ", Spanish = " + dSpanish
                + ", English = " + dEnglish
                + ", Portuguese = " + dPortuguese
                + ", CodigoVenta = " + codigoVenta
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdatedBy 
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