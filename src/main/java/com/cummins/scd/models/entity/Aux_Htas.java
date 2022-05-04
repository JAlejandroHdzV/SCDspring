package com.cummins.scd.models.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ZMKT7352_DES_CAT_HERRAMIENTAS")
public class Aux_Htas {

	@Id
	@Column(name = "NP")
    private String np;
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
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Lob
    @Column(name = "ESPECIFICACION")
    private byte[] especificacion;
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
	public byte[] getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(byte[] especificacion) {
		this.especificacion = especificacion;
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
		return "Aux_Htas [np=" + np + ", nPAnterior=" + nPAnterior + ", dSpanish=" + dSpanish + ", dEnglish=" + dEnglish
				+ ", dPortuguese=" + dPortuguese + ", codigoVenta=" + codigoVenta + ", fileName=" + fileName
				+ ", fileType=" + fileType + ", especificacion=" + Arrays.toString(especificacion) + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy="
				+ lastUpdateBy + ", del=" + del + "]";
	}
	
	
	
}
