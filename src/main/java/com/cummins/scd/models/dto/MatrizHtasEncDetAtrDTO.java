package com.cummins.scd.models.dto;

import java.util.List;

import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.Region;

public class MatrizHtasEncDetAtrDTO {
	MatrizHtasHd matriz;
	List<MatrizHtasDet> detalles;
	List<AtributoMatrizDTO> atributos;
	Region region;
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public MatrizHtasHd getMatriz() {
		return matriz;
	}
	public void setMatriz(MatrizHtasHd matriz) {
		this.matriz = matriz;
	}
	public List<MatrizHtasDet> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<MatrizHtasDet> detalles) {
		this.detalles = detalles;
	}
	public List<AtributoMatrizDTO> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoMatrizDTO> atributos) {
		this.atributos = atributos;
	}
	
	
	
}
