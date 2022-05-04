package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.SpCodes;

public class ConfMotorDTO {

	//--------------------------------------------------
	//					ATTRIBUTES
	//--------------------------------------------------
	private BigInteger idConfMotor;
    private BigInteger idEvaluacion;   
    private BigInteger idEspmotor;
    private BigInteger idNs;
    private BigInteger idMatrizPartes;
    private BigInteger idMatrizHtas;
    private BigInteger mecReq;
    private Character del;
    private String[] sp;
    private BigInteger[] oem;
    private MatrizDTO matPartes;
    private MatrizDTO matHtas;
    private ServiceLevel ns;
    
    //---------------------------------------------------
    //              OVERLOAD CONSTRUCTOR
    //---------------------------------------------------
    public ConfMotorDTO(BigInteger idConfMotor, BigInteger idEvaluacion, BigInteger idEspmotor, BigInteger idNs,
			BigInteger idMatrizPartes, BigInteger idMatrizHtas, BigInteger mecReq, Character del, String[] sp,
			BigInteger[] oem, MatrizDTO matPartes, MatrizDTO matHtas, ServiceLevel ns) {
		this.idConfMotor = idConfMotor;
		this.idEvaluacion = idEvaluacion;
		this.idEspmotor = idEspmotor;
		this.idNs = idNs;
		this.idMatrizPartes = idMatrizPartes;
		this.idMatrizHtas = idMatrizHtas;
		this.mecReq = mecReq;
		this.del = del;
		this.sp = sp;
		this.oem = oem;
		this.matPartes = matPartes;
		this.matHtas = matHtas;
		this.ns = ns;
	}
    public ConfMotorDTO() {
    	
	}
	//-------------------------------------------------
    //				GETTERS AND SETTERS
    //-------------------------------------------------
    public Character getDel() {
		return del;
	}
	public void setDel(Character del) {
		this.del = del;
	}
	public BigInteger getIdConfMotor() {
		return idConfMotor;
	}
	public void setIdConfMotor(BigInteger idConfMotor) {
		this.idConfMotor = idConfMotor;
	}
	public BigInteger getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(BigInteger idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public BigInteger getIdEspmotor() {
		return idEspmotor;
	}
	public void setIdEspmotor(BigInteger idEspmotor) {
		this.idEspmotor = idEspmotor;
	}
	public BigInteger getIdNs() {
		return idNs;
	}
	public void setIdNs(BigInteger idNs) {
		this.idNs = idNs;
	}
	public BigInteger getIdMatrizPartes() {
		return idMatrizPartes;
	}
	public void setIdMatrizPartes(BigInteger idMatrizPartes) {
		this.idMatrizPartes = idMatrizPartes;
	}
	public BigInteger getIdMatrizHtas() {
		return idMatrizHtas;
	}
	public void setIdMatrizHtas(BigInteger idMatrizHtas) {
		this.idMatrizHtas = idMatrizHtas;
	}
	public BigInteger getMecReq() {
		return mecReq;
	}
	public void setMecReq(BigInteger mecReq) {
		this.mecReq = mecReq;
	}
	public String[] getSp() {
		return sp;
	}
	public void setSp(String[] sp) {
		this.sp = sp;
	}
	public BigInteger[] getOem() {
		return oem;
	}
	public void setOem(BigInteger[] oem) {
		this.oem = oem;
	}
	public MatrizDTO getMatPartes() {
		return matPartes;
	}
	public void setMatPartes(MatrizDTO matPartes) {
		this.matPartes = matPartes;
	}
	public MatrizDTO getMatHtas() {
		return matHtas;
	}
	public void setMatHtas(MatrizDTO matHtas) {
		this.matHtas = matHtas;
	}
	public ServiceLevel getNs() {
		return ns;
	}
	public void setNs(ServiceLevel ns) {
		this.ns = ns;
	}
    
    
    
    
	
    
    
    
}
