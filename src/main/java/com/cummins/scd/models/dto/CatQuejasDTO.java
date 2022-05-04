package com.cummins.scd.models.dto;

import java.math.BigInteger;


public class CatQuejasDTO {
	private BigInteger idCatQuejas;   
    private String queja;
	public BigInteger getIdCatQuejas() {
		return idCatQuejas;
	}
	public void setIdCatQuejas(BigInteger idCatQuejas) {
		this.idCatQuejas = idCatQuejas;
	}
	public String getQueja() {
		return queja;
	}
	public void setQueja(String queja) {
		this.queja = queja;
	}
    
}
