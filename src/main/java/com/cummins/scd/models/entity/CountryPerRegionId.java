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



public class CountryPerRegionId implements Serializable{
    private BigInteger idRegion;
    private BigInteger idPais;

    public CountryPerRegionId(){

    }

    public CountryPerRegionId(BigInteger region, BigInteger country){
        this.idRegion = region;
        this.idPais = country;
    }

    public void setIdRegion( BigInteger r){
        this.idRegion = r;
    }
    public BigInteger getIdRegion(){
        return this.idRegion;
    }

    public void setIdCountry( BigInteger c){
        this.idPais = c;
    }
    public BigInteger getIdCountry(){
        return this.idPais;
    }

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idRegion == null ) ? 0 : this.idRegion.hashCode() ) + ( (this.idPais == null ) ? 0 : this.idPais.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        CountryPerRegionId other = (CountryPerRegionId) obj;
        if(this.idRegion == null){
            if(other.idRegion != null)
               return false;
        }
        else {
            if(this.idRegion != other.idRegion)
                return false;
        }
        if(this.idPais == null){
            if(other.idPais != null)
               return false;
        }
        else {
            if(this.idPais != other.idPais)
                return false;
        }
        return true;
        
    }


}