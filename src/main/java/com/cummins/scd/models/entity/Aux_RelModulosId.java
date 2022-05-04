package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;



public class Aux_RelModulosId implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger idModulo;
    private BigInteger idSubmodulo;
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
	
	
    
}
