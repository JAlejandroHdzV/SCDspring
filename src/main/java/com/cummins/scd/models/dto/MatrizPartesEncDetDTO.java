package com.cummins.scd.models.dto;

import java.util.List;

import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.MatrizPartesHd;

public class MatrizPartesEncDetDTO {
	
		MatrizPartesHd matriz;
		List<MatrizPartesDet> detalles;
		List<AtributosMatricesHtas> atributos;
		
		public List<AtributosMatricesHtas> getAtributos() {
			return atributos;
		}
		public void setAtributos(List<AtributosMatricesHtas> atributos) {
			this.atributos = atributos;
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
}




