package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "ZMKT7352_DES_QUEJAS_NOT")
public class Quejas_Not implements Serializable {
	 	/**
	 * author Alejandro Hernandez - Transom Group
	 */
	private static final long serialVersionUID = 1L;
	
	//---------------------------------------------
	//				    COLUMNS
	//---------------------------------------------
		@Id  
	    @Column(name = "ID_QUEJA_NOT")
	    private BigInteger idQuejaNot;
	 	@Column(name = "MAIL_ING")
	    private String mailIng;
	 	@Column(name = "MAIL_ESP")
	    private String mailEsp; 
	 	@Column(name = "MAIL_POR")
	    private String mailPor; 
	 	@Column(name = "CREATION_DATE")
	    private Date creationDate;
	    @Column(name = "CREATED_BY")
	    private String createdBy;
	    @Column(name = "LAST_UPDATE_DATE")
		private Date lastUpdateDate;
	    @Column(name = "LAST_UPDATE_BY")
		private String lastUpdateBy;
		
	//---------------------------------------------
	//			 GETTERS AND SETTERS
	//---------------------------------------------
	    public BigInteger getIdQuejaNot() {
			return idQuejaNot;
		}
		public void setIdQuejaNot(BigInteger idQuejaNot) {
			this.idQuejaNot = idQuejaNot;
		}
		public String getMailIng() {
			return mailIng;
		}
		public void setMailIng(String mailIng) {
			this.mailIng = mailIng;
		}
		public String getMailEsp() {
			return mailEsp;
		}
		public void setMailEsp(String mailEsp) {
			this.mailEsp = mailEsp;
		}
		public String getMailPor() {
			return mailPor;
		}
		public void setMailPor(String mailPor) {
			this.mailPor = mailPor;
		}
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Date getLastUpdateDate() {
			return lastUpdateDate;
		}
		public void setLastUpdateDate(Date lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
		public String getLastUpdateBy() {
			return lastUpdateBy;
		}
		public void setLastUpdateBy(String lastUpdateBy) {
			this.lastUpdateBy = lastUpdateBy;
		}
		
	   
	//------------------------------------------
	//				TO STRING METHOD
	//------------------------------------------
		@Override
		public String toString() {
			return "Quejas_Not [idQuejaNot=" + idQuejaNot + ", mailIng=" + mailIng + ", mailEsp=" + mailEsp
					+ ", mailPor=" + mailPor + ", creationDate=" + creationDate + ", createdBy=" + createdBy
					+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + "]";
		}
		
	    

}
