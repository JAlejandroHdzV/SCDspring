package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;

public class QuejasDTO {
	private BigInteger idQueja;
    private String spCode;
    private Date fechaCaptura;
    private Date fechaQueja;
    private String cliente;
    private String contactoQueja;
    private BigInteger idCatQuejas;
    private String descQueja;
    private String segQueja;
    private String responsableSeg;
    private Date fechaCierre;
    private String compromiso;
    private String reporta;
    private String estatus;
    private String validoEval;
    
    private BigInteger idRegion;
    private String region;
    private String codigoDr;
    private String dr;
    private String oem;
    private String codigoPuntoServicio;
    private String puntoDeServicio;
    private String queja;
    
    
	public BigInteger getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(BigInteger idRegion) {
		this.idRegion = idRegion;
	}
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCodigoDr() {
		return codigoDr;
	}
	public void setCodigoDr(String codigoDr) {
		this.codigoDr = codigoDr;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public String getOem() {
		return oem;
	}
	public void setOem(String oem) {
		this.oem = oem;
	}
	public String getCodigoPuntoServicio() {
		return codigoPuntoServicio;
	}
	public void setCodigoPuntoServicio(String codigoPuntoServicio) {
		this.codigoPuntoServicio = codigoPuntoServicio;
	}
	public String getPuntoDeServicio() {
		return puntoDeServicio;
	}
	public void setPuntoDeServicio(String puntoDeServicio) {
		this.puntoDeServicio = puntoDeServicio;
	}
	public String getQueja() {
		return queja;
	}
	public void setQueja(String queja) {
		this.queja = queja;
	}
	@Override
	public String toString() {
		return "QuejasDTO [idQueja=" + idQueja + ", spCode=" + spCode + ", fechaCaptura=" + fechaCaptura
				+ ", fechaQueja=" + fechaQueja + ", cliente=" + cliente + ", contactoQueja=" + contactoQueja
				+ ", idCatQuejas=" + idCatQuejas + ", descQueja=" + descQueja + ", segQueja=" + segQueja
				+ ", responsableSeg=" + responsableSeg + ", fechaCierre=" + fechaCierre + ", compromiso=" + compromiso
				+ ", reporta=" + reporta + ", estatus=" + estatus + ", validoEval=" + validoEval + ", idRegion="
				+ idRegion + ", region=" + region + ", codigoDr=" + codigoDr + ", dr=" + dr + ", oem=" + oem
				+ ", codigoPuntoServicio=" + codigoPuntoServicio + ", puntoDeServicio=" + puntoDeServicio + ", queja="
				+ queja + "]";
	}
	
	
    
    
}
