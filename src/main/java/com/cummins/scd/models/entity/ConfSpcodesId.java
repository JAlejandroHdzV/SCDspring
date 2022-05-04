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



public class ConfSpcodesId implements Serializable{
    private BigInteger idConfMotor;
    private String spCode;

    public ConfSpcodesId(){

    }

    public ConfSpcodesId(BigInteger confM, String sp){
        this.idConfMotor = confM;
        this.spCode = sp;
        
    }

    public void setidConfMotor (BigInteger e){
        this.idConfMotor = e;
    }
    public BigInteger getidConfMotor(){
        return this.idConfMotor;
    }

    public void setSpCode( String q){
        this.spCode = q;
    }
    public String getSpCode(){
        return this.spCode;
    }
    

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idConfMotor == null ) ? 0 : this.idConfMotor.hashCode() ) + ( (this.spCode == null ) ? 0 : this.spCode.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ConfSpcodesId other = (ConfSpcodesId) obj;
        if(this.idConfMotor == null){
            if(other.idConfMotor != null)
               return false;
        }
        else {
            if(this.idConfMotor != other.idConfMotor)
                return false;
        }
        if(this.spCode == null){
            if(other.spCode != null)
               return false;
        }
        else {
            if(this.spCode != other.spCode)
                return false;
        }
        
        return true;
        
    }


}