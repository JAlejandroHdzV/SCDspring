package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cummins.scd.models.entity.ESPM_Region;


public class EspmDTO {

private BigInteger idEspmotor;    
private BigInteger idMotor;
private BigInteger idRango;
private String idApp;
private Date creationDate;
private String createdBy;
private Date lastUpdateDate;
private String lastUpdateBy;
private Character del;
private String motor;
private String Rango;
private String app;
private List<ESPM_Region> region;


public List<ESPM_Region> getRegion() {
	return region;
}
public void setRegion(List<ESPM_Region> region) {
	this.region = region;
}

public BigInteger getIdEspmotor() {
	return idEspmotor;
}
public void setIdEspmotor(BigInteger idEspmotor) {
	this.idEspmotor = idEspmotor;
}
public BigInteger getIdMotor() {
	return idMotor;
}
public void setIdMotor(BigInteger idMotor) {
	this.idMotor = idMotor;
}
public BigInteger getIdRango() {
	return idRango;
}
public void setIdRango(BigInteger idRango) {
	this.idRango = idRango;
}
public String getIdApp() {
	return idApp;
}
public void setIdApp(String idApp) {
	this.idApp = idApp;
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
public String getMotor() {
	return motor;
}
public void setMotor(String motor) {
	this.motor = motor;
}
public String getRango() {
	return Rango;
}
public void setRango(String rango) {
	Rango = rango;
}
public String getApp() {
	return app;
}
public void setApp(String app) {
	this.app = app;
}



    
}
