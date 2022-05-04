package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;



public class ESPM_RegionId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger idEspmotor;
    private BigInteger idRegion;


    public ESPM_RegionId(){

    }

    public ESPM_RegionId(BigInteger espm, BigInteger em){
        this.idEspmotor = espm;
        this.idRegion = em;
     
    }

    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setidRegion( BigInteger c){
        this.idRegion = c;
    }
    public BigInteger getidRegion(){
        return this.idRegion;
    }
   
    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idEspmotor == null ) ? 0 : this.idEspmotor.hashCode() ) + ( (this.idRegion == null ) ? 0 : this.idRegion.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ESPM_RegionId other = (ESPM_RegionId) obj;
        if(this.idEspmotor == null){
            if(other.idEspmotor != null)
               return false;
        }
        else {
            if(this.idEspmotor != other.idEspmotor)
                return false;
        }
        if(this.idRegion == null){
            if(other.idRegion != null)
               return false;
        }
        else {
            if(this.idRegion != other.idRegion)
                return false;
        }
       
        return true;
        
    }


}