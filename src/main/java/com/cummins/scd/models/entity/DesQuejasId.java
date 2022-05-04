package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;


public class DesQuejasId implements Serializable {

	/**
	 * Author: ALEJANDRO HERNANDEZ VEGA TRANSOM - GROUP
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger idCatQuejas;
    private String spCode;
    private BigInteger idQueja;
	public BigInteger getIdCatQuejas() {
		return idCatQuejas;
	}
	public void setIdCatQuejas(BigInteger idCatQueja) {
		this.idCatQuejas = idCatQueja;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public BigInteger getIdQueja() {
		return idQueja;
	}
	public void setIdQueja(BigInteger idQueja) {
		this.idQueja = idQueja;
	}

    
}
