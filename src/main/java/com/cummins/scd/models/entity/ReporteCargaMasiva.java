package com.cummins.scd.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;


public class ReporteCargaMasiva {
	
	int registrosEliminados;
	int registrosNuevos;
	int registrosActualizados;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")  
	List<Errores> errores;
	
	
	
	
	public List<Errores> getErrores() {
		return errores;
	}
	public void setErrores(List<Errores> errores) {
		this.errores = errores;
	}
	public int getRegistrosEliminados() {
		return registrosEliminados;
	}
	public void setRegistrosEliminados(int registrosEliminados) {
		this.registrosEliminados = registrosEliminados;
	}
	public int getRegistrosNuevos() {
		return registrosNuevos;
	}
	public void setRegistrosNuevos(int registrosNuevos) {
		this.registrosNuevos = registrosNuevos;
	}
	public int getRegistrosActualizados() {
		return registrosActualizados;
	}
	public void setRegistrosActualizados(int registrosActualizados) {
		this.registrosActualizados = registrosActualizados;
	}
	
	
	
	

}
