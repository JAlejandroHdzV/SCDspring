package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;



public class ESPM_EmisionId implements Serializable{
    private BigInteger idEspmotor;
    private BigInteger idEmision;


    public ESPM_EmisionId(){

    }

    public ESPM_EmisionId(BigInteger espm, BigInteger em){
        this.idEspmotor = espm;
        this.idEmision = em;
     
    }

    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setidEmision( BigInteger c){
        this.idEmision = c;
    }
    public BigInteger getidEmision(){
        return this.idEmision;
    }
   
    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idEspmotor == null ) ? 0 : this.idEspmotor.hashCode() ) + ( (this.idEmision == null ) ? 0 : this.idEmision.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ESPM_EmisionId other = (ESPM_EmisionId) obj;
        if(this.idEspmotor == null){
            if(other.idEspmotor != null)
               return false;
        }
        else {
            if(this.idEspmotor != other.idEspmotor)
                return false;
        }
        if(this.idEmision == null){
            if(other.idEmision != null)
               return false;
        }
        else {
            if(this.idEmision != other.idEmision)
                return false;
        }
       
        return true;
        
    }


}