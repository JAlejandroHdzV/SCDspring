package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;


public class RelPerfilExcsId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//---------------------------------------------
	//                   fields
	//---------------------------------------------
	private BigInteger idPerfil;
	private BigInteger idModulo;
	private BigInteger idSubmodulo;
	private BigInteger idConfMotor;
	
	//---------------------------------------------
	//             Getters and Setters
	//---------------------------------------------
	
	public BigInteger getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}
	public BigInteger getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(BigInteger idModulo) {
		this.idModulo = idModulo;
	}
	public BigInteger getIdSubmodulo() {
		return idSubmodulo;
	}
	public void setIdSubmodulo(BigInteger idSubmodulo) {
		this.idSubmodulo = idSubmodulo;
	}
	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}
	public void setIdConfMotor(BigInteger idConfMotor) {
		this.idConfMotor = idConfMotor;
	}


}
