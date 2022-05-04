package com.cummins.scd.models.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class SpCodesDTO {
		//-------------------------------------------------------
		//					FIELDS
		//-------------------------------------------------------
	    private String spCode;
	    private String providerName;
	    private BigInteger idTipo;
	    private String responsibleBranchCode;
	    private String responsibleBranchName;
	    
	    
	  //-------------------------------------------------------
	  //				GETTERS AND SETTERS
	  //-------------------------------------------------------
	   
		public String getResponsibleBranchName() {
			return responsibleBranchName;
		}
		public void setResponsibleBranchName(String responsibleBranchName) {
			this.responsibleBranchName = responsibleBranchName;
		}
		private BigInteger idPais;
	    private BigInteger idOem;
		public String getSpCode() {
			return spCode;
		}
		public void setSpCode(String spCode) {
			this.spCode = spCode;
		}
		public String getProviderName() {
			return providerName;
		}
		public void setProviderName(String providerName) {
			this.providerName = providerName;
		}
		public BigInteger getIdTipo() {
			return idTipo;
		}
		public void setIdTipo(BigInteger idTipo) {
			this.idTipo = idTipo;
		}
		public String getResponsibleBranchCode() {
			return responsibleBranchCode;
		}
		public void setResponsibleBranchCode(String responsibleBranchCode) {
			this.responsibleBranchCode = responsibleBranchCode;
		}
		public BigInteger getIdPais() {
			return idPais;
		}
		public void setIdPais(BigInteger idPais) {
			this.idPais = idPais;
		}
		public BigInteger getIdOem() {
			return idOem;
		}
		public void setIdOem(BigInteger idOem) {
			this.idOem = idOem;
		}
		
		
		//-------------------------------------------------------
		//				 METHOD TO STRING
		//-------------------------------------------------------
		 @Override
			public String toString() {
				return "SpCodesDTO [spCode=" + spCode + ", providerName=" + providerName + ", idTipo=" + idTipo
						+ ", responsibleBranchCode=" + responsibleBranchCode + ", responsibleBranchName="
						+ responsibleBranchName + ", idPais=" + idPais + ", idOem=" + idOem + "]";
			}
	    
}
