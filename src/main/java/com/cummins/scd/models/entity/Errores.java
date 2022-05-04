package com.cummins.scd.models.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Errores {
	
	private String line;
	private String errorType;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_HEADER")
    private ReporteCargaMasiva header;
	
	
	public ReporteCargaMasiva obtenerHeader() {
		return header;
	}
	public void setHeader(ReporteCargaMasiva header) {
		this.header = header;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	

}
