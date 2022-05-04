package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;



public class MatrizPartesDetId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger idMatriz;
    private String np;


    public MatrizPartesDetId(){

    }

    public MatrizPartesDetId(BigInteger mat, String n){
        this.idMatriz = mat;
        this.np = n;

    }

    public void setIdMatriz( BigInteger r){
        this.idMatriz = r;
    }
    public BigInteger getIdMatriz(){
        return this.idMatriz;
    }

    public void setNP( String c){
        this.np = c;
    }
    public String getNP(){
        return this.np;
    }
    

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idMatriz == null ) ? 0 : this.idMatriz.hashCode() + ( (this.np == null ) ? 0 : this.np.hashCode())));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        MatrizPartesDetId other = (MatrizPartesDetId) obj;
        if(this.idMatriz == null){
            if(other.idMatriz != null)
               return false;
        }
        else {
            if(this.idMatriz != other.idMatriz)
                return false;
        }
        if(this.np == null){
            if(other.np != null)
               return false;
        }
        else {
            if(this.np != other.np)
                return false;
        }
        return true;
        
    }


}