package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.List;

import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.ServiceLevel;

public class ConfMotorDTO2 {
	private BigInteger idConfMotor;
    private BigInteger idEvaluacion;   
    private BigInteger idEspmotor;
    private String nameEspmotor;
    private BigInteger idNs;
    private BigInteger idMatrizPartes;
    private BigInteger idMatrizHtas;
    private BigInteger mecReq;
    private Character del;
    private List<SpCodesDTO> sp;
    private List<ConfOem> oem;
    private MatrizDTO matPartes;
    private MatrizDTO matHtas;
    private ServiceLevel ns;
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
	public String getNameEspmotor() {
		return nameEspmotor;
	}
	public void setNameEspmotor(String nameEspmotor) {
		this.nameEspmotor = nameEspmotor;
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
	public Character getDel() {
		return del;
	}
	public void setDel(Character del) {
		this.del = del;
	}
	public List<SpCodesDTO> getSp() {
		return sp;
	}
	public void setSp(List<SpCodesDTO> sp) {
		this.sp = sp;
	}
	public List<ConfOem> getOem() {
		return oem;
	}
	public void setOem(List<ConfOem> oem) {
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
