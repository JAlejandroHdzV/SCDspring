package com.cummins.scd.models.dto;

import java.util.List;

import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MatrizHtasHd;



public class MatrizHtasEncDetDTO {
	MatrizHtasHd matriz;
	List<MatrizHtasDet> detalles;
	List<AtributosMatricesHtas> atributos;
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
	public List<AtributosMatricesHtas> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributosMatricesHtas> atributos) {
		this.atributos = atributos;
	}
	
	
}
