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



public class PqsXpuestosId implements Serializable{
    private String programId;
    private BigInteger idPuesto;

    public PqsXpuestosId(){

    }

    public PqsXpuestosId(String prog, BigInteger p){
        this.programId = prog;
        this.idPuesto = p;
    }

    public void setIdProgram( String r){
        this.programId = r;
    }
    public String getIdProgram(){
        return this.programId;
    }

    public void setIdPuesto( BigInteger c){
        this.idPuesto = c;
    }
    public BigInteger getIdPuesto(){
        return this.idPuesto;
    }

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.programId == null ) ? 0 : this.programId.hashCode() ) + ( (this.idPuesto == null ) ? 0 : this.idPuesto.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        PqsXpuestosId other = (PqsXpuestosId) obj;
        if(this.programId == null){
            if(other.programId != null)
               return false;
        }
        else {
            if(this.programId != other.programId)
                return false;
        }
        if(this.idPuesto == null){
            if(other.idPuesto != null)
               return false;
        }
        else {
            if(this.idPuesto != other.idPuesto)
                return false;
        }
        return true;
        
    }


}