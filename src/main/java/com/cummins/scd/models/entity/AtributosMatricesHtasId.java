package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
//import org.apache.commons.io.IOUtils;



public class AtributosMatricesHtasId implements Serializable{
    /**
	 * 
	 */
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
    

    /*
    public AtributosMatricesHtasId(){

    }

    public AtributosMatricesHtasId(BigInteger id, BigInteger tipoM, BigInteger tipoA, String text, BigInteger num ){
        this.idMatriz = id;
        this.tipoMatriz = tipoM;
        this.tipoAtributo = tipoA;
        this.idText = text;
        this.idNumber = num;
    }

    public void setIdMatriz( BigInteger r){
        this.idMatriz = r;
    }
    public BigInteger getIdMatriz(){
        return this.idMatriz;
    }

    public void setTipoMatriz( BigInteger c){
        this.tipoMatriz = c;
    }
    public BigInteger getTipoMatriz(){
        return this.tipoMatriz;
    }
    public void setTipoAtributo( BigInteger c){
        this.tipoAtributo = c;
    }
    public BigInteger getTipoAtributo(){
        return this.tipoAtributo;
    }
    public void setIdText( String c){
        this.idText = c;
    }
    public String getIdText(){
        return this.idText;
    }
    public void setIdNumber( BigInteger c){
        this.idNumber = c;
    }
    public BigInteger getIdNumber(){
        return this.idNumber;
    }


    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idMatriz == null ) ? 0 : this.idMatriz.hashCode() ) + ( (this.tipoMatriz == null ) ? 0 : this.tipoMatriz.hashCode() ) + ( (this.tipoAtributo == null ) ? 0 : this.tipoAtributo.hashCode() ) + ( (this.idText == null ) ? 0 : this.idText.hashCode() ) + ( (this.idNumber == null ) ? 0 : this.idNumber.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        AtributosMatricesHtasId other = (AtributosMatricesHtasId) obj;
        if(this.idMatriz == null){
            if(other.idMatriz != null)
               return false;
        }
        else {
            if(this.idMatriz != other.idMatriz)
                return false;
        }
        if(this.tipoMatriz == null){
            if(other.tipoMatriz != null)
               return false;
        }
        else {
            if(this.tipoMatriz != other.tipoMatriz)
                return false;
        }
        if(this.tipoAtributo == null){
            if(other.tipoAtributo != null)
               return false;
        }
        else {
            if(this.tipoAtributo != other.tipoAtributo)
                return false;
        }
        if(this.idText == null){
            if(other.idText != null)
               return false;
        }
        else {
            if(this.idText != other.idText)
                return false;
        }
        if(this.idNumber == null){
            if(other.idNumber != null)
               return false;
        }
        else {
            if(this.idNumber != other.idNumber)
                return false;
        }
        return true;
        
    }*/


}