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
//import org.apache.commons.io.IOUtils;



public class ConfOemId implements Serializable{
    private BigInteger idConfMotor;
    private BigInteger idOem;

    public ConfOemId(){

    }

    public ConfOemId(BigInteger confM, BigInteger sp){
        this.idConfMotor = confM;
        this.idOem = sp;
        
    }

    public void setConfMotor (BigInteger e){
        this.idConfMotor = e;
    }
    public BigInteger getidConfMotor(){
        return this.idConfMotor;
    }

    public void setidOem(BigInteger q){
        this.idOem = q;
    }
    public BigInteger getidOem(){
        return this.idOem;
    }
    

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idConfMotor == null ) ? 0 : this.idConfMotor.hashCode() ) + ( (this.idOem == null ) ? 0 : this.idOem.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ConfOemId other = (ConfOemId) obj;
        if(this.idConfMotor == null){
            if(other.idConfMotor != null)
               return false;
        }
        else {
            if(this.idConfMotor != other.idConfMotor)
                return false;
        }
        if(this.idOem == null){
            if(other.idOem != null)
               return false;
        }
        else {
            if(this.idOem != other.idOem)
                return false;
        }
        
        return true;
        
    }


}