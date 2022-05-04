package com.cummins.scd.models.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class MotorProductJoinESPM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private BigInteger idEspm;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getIdEspm() {
		return idEspm;
	}
	public void setIdEspm(BigInteger idEspm) {
		this.idEspm = idEspm;
	}
}
