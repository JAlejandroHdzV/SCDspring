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



public class ESPMId implements Serializable{
    private BigInteger idEspmotor;
    private BigInteger idMotor;
    private BigInteger idRango;
    private String idApp;

    public ESPMId(){

    }

    public ESPMId(BigInteger espm, BigInteger motor, BigInteger rango, String app){
        this.idEspmotor = espm;
        this.idMotor = motor;
        this.idRango=rango;
        this.idApp= app;
    }

    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setid( BigInteger c){
        this.idMotor = c;
    }
    public BigInteger getid(){
        return this.idMotor;
    }
    public void setIdRango( BigInteger c){
        this.idMotor = c;
    }
    public BigInteger getIdRango(){
        return this.idRango;
    }
    public void setIdApp( String c){
        this.idApp = c;
    }
    public String getIdApp(){
        return this.idApp;
    }

    @Override
    public int hashCode(){
        final int prime = 3;
        int result = 1;
        result = prime * (result + ( (this.idEspmotor == null ) ? 0 : this.idEspmotor.hashCode() ) + ( (this.idMotor == null ) ? 0 : this.idMotor.hashCode() )+ ( (this.idRango == null ) ? 0 : this.idRango.hashCode() )+ ( (this.idApp == null ) ? 0 : this.idApp.hashCode() ));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if( getClass() != obj.getClass()) return false;
        ESPMId other = (ESPMId) obj;
        if(this.idEspmotor == null){
            if(other.idEspmotor != null)
               return false;
        }
        else {
            if(this.idEspmotor != other.idEspmotor)
                return false;
        }
        if(this.idMotor == null){
            if(other.idMotor != null)
               return false;
        }
        else {
            if(this.idMotor != other.idMotor)
                return false;
        }
        if(this.idRango == null){
            if(other.idRango != null)
               return false;
        }
        else {
            if(this.idRango != other.idRango)
                return false;
        }
        if(this.idApp == null){
            if(other.idApp != null)
               return false;
        }
        else {
            if(this.idApp != other.idApp)
                return false;
        }
        return true;
        
    }


}