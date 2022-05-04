package com.cummins.scd.models.dto;

import java.math.BigInteger;

public class EvaluacionesDTO {
	private BigInteger idEvaluacion;
	private String anio;
	private String revision;
    private String tEvaluacion;
    private String name;
    private String region;
    private String idRegion;
    private String status;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(BigInteger idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String gettEvaluacion() {
		return tEvaluacion;
	}
	public void settEvaluacion(String tEvaluacion) {
		this.tEvaluacion = tEvaluacion;
	}
    
    
}
