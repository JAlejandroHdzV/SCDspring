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



public class Aux_RelPuestosId implements Serializable{
    private BigInteger idPuesto;
    private String titulo;

    public Aux_RelPuestosId(){

    }

    public Aux_RelPuestosId(BigInteger p, String t){
        this.idPuesto = p;
        this.titulo = t;
    }

    public void setIdPuesto( BigInteger r){
        this.idPuesto = r;
    }
    public BigInteger getIdRegion(){
        return this.idPuesto;
    }

    public void setTitulo( String c){
        this.titulo = c;
    }
    public String getTitulo(){
        return this.titulo;
    }

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idPuesto == null ) ? 0 : this.idPuesto.hashCode() ) + ( (this.titulo == null ) ? 0 : this.titulo.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        Aux_RelPuestosId other = (Aux_RelPuestosId) obj;
        if(this.idPuesto == null){
            if(other.idPuesto != null)
               return false;
        }
        else {
            if(this.idPuesto != other.idPuesto)
                return false;
        }
        if(this.titulo == null){
            if(other.titulo != null)
               return false;
        }
        else {
            if(this.titulo != other.titulo)
                return false;
        }
        return true;
        
    }


}