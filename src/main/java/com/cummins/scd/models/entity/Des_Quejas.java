package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ZMKT7352_DES_QUEJAS")
public class Des_Quejas implements Serializable {

	/**
	 * author: ALEJANDRO HERNANDEZ - TRANSOM-GROUP - 2021
	 */
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_QUEJA")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_QUEJA", sequenceName="ZMKT7352_SEC_ID_QUEJA", allocationSize=1)
	@Column(name = "ID_QUEJA")
	private BigInteger idQueja;
	@Column(name = "SP_CODE")
    private String spCode;
	@Column(name = "FECHA_CAPTURA", nullable = true)
    private Date fechaCaptura;
	@Column(name = "FECHA_QUEJA", nullable = true)
    private Date fechaQueja;
	@Column(name = "CLIENTE", nullable = true)
    private String cliente;
	@Column(name = "CONTACTO_QUEJA", nullable = true)
    private String contactoQueja;
	@Column(name = "ID_CAT_QUEJAS", nullable = true)
    private BigInteger idCatQuejas;
	@Column(name = "DESC_QUEJA", nullable = true)
    private String descQueja;
	@Column(name = "SEG_QUEJA", nullable = true)
    private String segQueja;
	@Column(name = "RESPONSABLE_SEG", nullable = true)
    private String responsableSeg;
	@Column(name = "FECHA_CIERRE", nullable = true)
    private Date fechaCierre;
	@Column(name = "COMPROMISO", nullable = true)
    private String compromiso;
	@Column(name = "REPORTA", nullable = true)
    private String reporta;
	@Column(name = "ESTATUS", nullable = true)
    private String estatus;//--
	@Column(name = "VALIDO_EVAL", nullable = true)
    private String validoEval;//--
	
	@Column(name = "FILE_NAME_1",nullable = true)
    private String fileName1;
	@Column(name = "FILE_TYPE_1", nullable = true)
    private String fileType1;
	@Lob
	@Column(name = "FILE_CONTENT_1", nullable = true)
    private byte[] fileContent1;
	
	@Column(name = "FILE_NAME_2", nullable = true)
    private String fileName2;
	@Column(name = "FILE_TYPE_2", nullable= true)
    private String fileType2;
	@Lob
	@Column(name = "FILE_CONTENT_2", nullable = true)
    private byte[] fileContent2;
	
	@Column(name = "FILE_NAME_3", nullable=true)
    private String fileName3;
	@Column(name = "FILE_TYPE_3", nullable = true)
    private String fileType3;
	@Lob
	@Column(name = "FILE_CONTENT_3", nullable= true)
    private byte[] fileContent3;
	
	@Column(name = "FILE_NAME_4", nullable = true)
    private String fileName4;
	@Column(name = "FILE_TYPE_4", nullable = true)
    private String fileType4;
	@Lob
	@Column(name = "FILE_CONTENT_4", nullable = true)
    private byte[] fileContent4;
	
	@Column(name = "FILE_NAME_5", nullable = true)
    private String fileName5;
	@Column(name = "FILE_TYPE_5", nullable = true)
    private String fileType5;
	@Lob
	@Column(name = "FILE_CONTENT_5", nullable = true)
    private byte[] fileContent5;
	
	@Column(name = "CREATION_DATE")
    private Date creationDate;
	@Column(name = "CREATED_BY")
    private String createdBy;
	@Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
	@Column(name = "LAST_UPDATE_BY")
	
	
	
	
	
	private String lastUpdateBy;
	public BigInteger getIdQueja() {
		return idQueja;
	}
	public void setIdQueja(BigInteger idQueja) {
		this.idQueja = idQueja;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public Date getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	public Date getFechaQueja() {
		return fechaQueja;
	}
	public void setFechaQueja(Date fechaQueja) {
		this.fechaQueja = fechaQueja;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getContactoQueja() {
		return contactoQueja;
	}
	public void setContactoQueja(String contactoQueja) {
		this.contactoQueja = contactoQueja;
	}
	public BigInteger getIdCatQuejas() {
		return idCatQuejas;
	}
	public void setIdCatQuejas(BigInteger idCatQuejas) {
		this.idCatQuejas = idCatQuejas;
	}
	public String getDescQueja() {
		return descQueja;
	}
	public void setDescQueja(String descQueja) {
		this.descQueja = descQueja;
	}
	public String getSegQueja() {
		return segQueja;
	}
	public void setSegQueja(String segQueja) {
		this.segQueja = segQueja;
	}
	public String getResponsableSeg() {
		return responsableSeg;
	}
	public void setResponsableSeg(String responsableSeg) {
		this.responsableSeg = responsableSeg;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}
	public String getReporta() {
		return reporta;
	}
	public void setReporta(String reporta) {
		this.reporta = reporta;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getValidoEval() {
		return validoEval;
	}
	public void setValidoEval(String validoEval) {
		this.validoEval = validoEval;
	}
	public String getFileName1() {
		return fileName1;
	}
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	public String getFileType1() {
		return fileType1;
	}
	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
	}
	public byte[] getFileContent1() {
		return fileContent1;
	}
	public void setFileContent1(byte[] fileContent1) {
		this.fileContent1 = fileContent1;
	}
	public String getFileName2() {
		return fileName2;
	}
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	public String getFileType2() {
		return fileType2;
	}
	public void setFileType2(String fileType2) {
		this.fileType2 = fileType2;
	}
	public byte[] getFileContent2() {
		return fileContent2;
	}
	public void setFileContent2(byte[] fileContent2) {
		this.fileContent2 = fileContent2;
	}
	public String getFileName3() {
		return fileName3;
	}
	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}
	public String getFileType3() {
		return fileType3;
	}
	public void setFileType3(String fileType3) {
		this.fileType3 = fileType3;
	}
	public byte[] getFileContent3() {
		return fileContent3;
	}
	public void setFileContent3(byte[] fileContent3) {
		this.fileContent3 = fileContent3;
	}
	public String getFileName4() {
		return fileName4;
	}
	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}
	public String getFileType4() {
		return fileType4;
	}
	public void setFileType4(String fileType4) {
		this.fileType4 = fileType4;
	}
	public byte[] getFileContent4() {
		return fileContent4;
	}
	public void setFileContent4(byte[] fileContent4) {
		this.fileContent4 = fileContent4;
	}
	public String getFileName5() {
		return fileName5;
	}
	public void setFileName5(String fileName5) {
		this.fileName5 = fileName5;
	}
	public String getFileType5() {
		return fileType5;
	}
	public void setFileType5(String fileType5) {
		this.fileType5 = fileType5;
	}
	public byte[] getFileContent5() {
		return fileContent5;
	}
	public void setFileContent5(byte[] fileContent5) {
		this.fileContent5 = fileContent5;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Des_Quejas [idQueja=" + idQueja + ", spCode=" + spCode + ", fechaCaptura=" + fechaCaptura
				+ ", fechaQueja=" + fechaQueja + ", cliente=" + cliente + ", contactoQueja=" + contactoQueja
				+ ", idCatQuejas=" + idCatQuejas + ", descQueja=" + descQueja + ", segQueja=" + segQueja
				+ ", responsableSeg=" + responsableSeg + ", fechaCierre=" + fechaCierre + ", compromiso=" + compromiso
				+ ", reporta=" + reporta + ", estatus=" + estatus + ", validoEval=" + validoEval + ", fileName1="
				+ fileName1 + ", fileType1=" + fileType1 + ", fileContent1=" + Arrays.toString(fileContent1)
				+ ", fileName2=" + fileName2 + ", fileType2=" + fileType2 + ", fileContent2="
				+ Arrays.toString(fileContent2) + ", fileName3=" + fileName3 + ", fileType3=" + fileType3
				+ ", fileContent3=" + Arrays.toString(fileContent3) + ", fileName4=" + fileName4 + ", fileType4="
				+ fileType4 + ", fileContent4=" + Arrays.toString(fileContent4) + ", fileName5=" + fileName5
				+ ", fileType5=" + fileType5 + ", fileContent5=" + Arrays.toString(fileContent5) + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy="
				+ lastUpdateBy + "]";
	}
}
