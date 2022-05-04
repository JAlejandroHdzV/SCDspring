package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class RelPerfilMotorId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger idPerfil;
	private BigInteger idConfMotor;
	
	
	public BigInteger getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}
	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}
	public void setIdConfMotor(BigInteger idConfMotor) {
		this.idConfMotor = idConfMotor;
	}
}
