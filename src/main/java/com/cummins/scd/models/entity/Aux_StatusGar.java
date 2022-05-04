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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_CAT_STATUS_GAR")

public class Aux_StatusGar implements Serializable {
	/**
	 * 
	 */
		 
    @Id
    //private int user_id;
	@Column(name = "ID_STATUS")
    private BigInteger idstatus;
	@Column(name = "STATUS")
    private String status;
    @Column(name = "INGLES")
    private String english;
    @Column(name = "PORTUGUES")
    private String portuguese;
	@Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
	private String lastUpdateBy;
	@Column(name = "DELETE_IND")
    private Character del;
    
	@Transient
	private String action;
	/*Getter and setters*/   
    
    
    
	
	@Override
	public String toString() {
		return "StatusGar [Id = " + idstatus
                + ", Status = " + status
                + ", English = " + english
                + ", Portuguese = " + portuguese
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}




	public BigInteger getIdstatus() {
		return idstatus;
	}




	public void setIdstatus(BigInteger idstatus) {
		this.idstatus = idstatus;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getEnglish() {
		return english;
	}




	public void setEnglish(String english) {
		this.english = english;
	}




	public String getPortuguese() {
		return portuguese;
	}




	public void setPortuguese(String portuguese) {
		this.portuguese = portuguese;
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




	public Character getDel() {
		return del;
	}




	public void setDel(Character del) {
		this.del = del;
	}




	public String obtenerAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}
}