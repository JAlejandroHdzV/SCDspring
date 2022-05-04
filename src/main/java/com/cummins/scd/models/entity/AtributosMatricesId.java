package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
//import org.apache.commons.io.IOUtils;



public class AtributosMatricesId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private BigInteger idMatriz;
    private BigInteger tipoMatriz;
    private BigInteger tipoAtributo;
    private String idText;
    private BigInteger idNumber;
	public BigInteger getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}
	public BigInteger getTipoMatriz() {
		return tipoMatriz;
	}
	public void setTipoMatriz(BigInteger tipoMatriz) {
		this.tipoMatriz = tipoMatriz;
	}
	public BigInteger getTipoAtributo() {
		return tipoAtributo;
	}
	public void setTipoAtributo(BigInteger tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}
	public String getIdText() {
		return idText;
	}
	public void setIdText(String idText) {
		this.idText = idText;
	}
	public BigInteger getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(BigInteger idNumber) {
		this.idNumber = idNumber;
	}

}