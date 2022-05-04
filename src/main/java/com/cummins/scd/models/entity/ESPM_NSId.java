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



public class ESPM_NSId implements Serializable{
    private BigInteger idEspmotor;
    private BigInteger idNs;
    private BigInteger idPuesto;
    private String programId;

    public ESPM_NSId(){

    }

    public ESPM_NSId(BigInteger espm, BigInteger ns, BigInteger pues, String prog){
        this.idEspmotor = espm;
        this.idNs = ns;
        this.idPuesto=pues;
        this.programId= prog;
    }

    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setidNS( BigInteger c){
        this.idNs = c;
    }
    public BigInteger getidNS(){
        return this.idNs;
    }
    public void setIdPuesto( BigInteger c){
        this.idPuesto = c;
    }
    public BigInteger getIdPuesto(){
        return this.idPuesto;
    }
    public void setIdProgram( String c){
        this.programId = c;
    }
    public String getIdProgram(){
        return this.programId;
    }

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idEspmotor == null ) ? 0 : this.idEspmotor.hashCode() ) + ( (this.idNs == null ) ? 0 : this.idNs.hashCode() )+ ( (this.idPuesto == null ) ? 0 : this.idPuesto.hashCode() )+ ( (this.programId == null ) ? 0 : this.programId.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ESPM_NSId other = (ESPM_NSId) obj;
        if(this.idEspmotor == null){
            if(other.idEspmotor != null)
               return false;
        }
        else {
            if(this.idEspmotor != other.idEspmotor)
                return false;
        }
        if(this.idNs == null){
            if(other.idNs != null)
               return false;
        }
        else {
            if(this.idNs != other.idNs)
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
        if(this.programId == null){
            if(other.programId != null)
               return false;
        }
        else {
            if(this.programId != other.programId)
                return false;
        }
        return true;
        
    }


}