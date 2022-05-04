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



public class MatrizHtasDetId implements Serializable{
    private BigInteger idMatriz;
    private String np;


    public MatrizHtasDetId(){

    }

    public MatrizHtasDetId(BigInteger mat, String n){
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
        MatrizHtasDetId other = (MatrizHtasDetId) obj;
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