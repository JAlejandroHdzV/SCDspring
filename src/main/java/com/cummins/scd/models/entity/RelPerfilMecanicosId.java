package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;


public class RelPerfilMecanicosId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//----------------------------------------------
	//					Fields
	//----------------------------------------------
	
	private BigInteger idPerfil;
	private String promotionId;
	
	//----------------------------------------------
	//				Getters and setters
	//----------------------------------------------
	public BigInteger getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

}
