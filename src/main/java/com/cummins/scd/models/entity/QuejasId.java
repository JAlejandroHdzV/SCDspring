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



public class QuejasId implements Serializable{
    private BigInteger idEvaluacion;
    private String queja;

    public QuejasId(){

    }

    public QuejasId(BigInteger idEv, String s){
        this.idEvaluacion = idEv;
        this.queja = s;
    }

    public void setIdEvaluacion( BigInteger e){
        this.idEvaluacion = e;
    }
    public BigInteger getIdEvaluacion(){
        return this.idEvaluacion;
    }

    public void setQueja( String q){
        this.queja = q;
    }
    public String getQueja(){
        return this.queja;
    }
  

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idEvaluacion == null ) ? 0 : this.idEvaluacion.hashCode() ) + ( (this.queja == null ) ? 0 : this.queja.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        QuejasId other = (QuejasId) obj;
        if(this.idEvaluacion == null){
            if(other.idEvaluacion != null)
               return false;
        }
        else {
            if(this.idEvaluacion != other.idEvaluacion)
                return false;
        }
        if(this.queja == null){
            if(other.queja != null)
               return false;
        }
        else {
            if(this.queja != other.queja)
                return false;
        }
       
        return true;
        
    }


}