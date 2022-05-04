package com.cummins.scd.models.dto;

import java.util.List;


import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.Region;

public class MatrizPartesEncDetAtrDTO {
	MatrizPartesHd matriz;
	List<MatrizPartesDet> detalles;
	List<AtributoMatrizDTO> atributos;
	Region region;
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public MatrizPartesHd getMatriz() {
		return matriz;
	}
	public void setMatriz(MatrizPartesHd matriz) {
		this.matriz = matriz;
	}
	public List<MatrizPartesDet> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<MatrizPartesDet> detalles) {
		this.detalles = detalles;
	}
	public List<AtributoMatrizDTO> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoMatrizDTO> atributos) {
		this.atributos = atributos;
	}
	
}
