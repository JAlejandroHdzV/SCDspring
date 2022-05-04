package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
//import org.apache.commons.io.IOUtils;

import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.AtributosMatricesId;


@Entity
@IdClass (AtributosMatricesId.class)
@Table(name = "ZMKT7352_DES_ATT_MATRICES")


public class AtributosMatrices implements Serializable {
	 
private static final long serialVersionUID = 1L;   


//-------------------------------------------------------
//						Columns
//-------------------------------------------------------

@Id

private BigInteger idMatriz;    

@Id

private BigInteger tipoMatriz; 

@Id

private BigInteger tipoAtributo; 

@Id

private String idText; 

@Id

private BigInteger idNumber; 



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




//----------------------------------------------------
// 				Getters and setters
//----------------------------------------------------
public BigInteger getIdMatriz() {
	return idMatriz;
}
public void setIdMatriz(BigInteger idMatriz) {
	this.idMatriz = idMatriz;
}
public BigInteger getTipoMatriz() {
	return tipoMatriz;
}
public void setTipoMatriz(BigInteger tipoMatriz) {
	this.tipoMatriz = tipoMatriz;
}
public BigInteger getTipoAtributo() {
	return tipoAtributo;
}
public void setTipoAtributo(BigInteger tipoAtributo) {
	this.tipoAtributo = tipoAtributo;
}
public String getIdText() {
	return idText;
}
public void setIdText(String idText) {
	this.idText = idText;
}
public BigInteger getIdNumber() {
	return idNumber;
}
public void setIdNumber(BigInteger idNumber) {
	this.idNumber = idNumber;
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
@Override
public String toString() {
	return "AtributosMatricesHtas [idMatriz=" + idMatriz + ", tipoMatriz=" + tipoMatriz + ", tipoAtributo="
			+ tipoAtributo + ", idText=" + idText + ", idNumber=" + idNumber + ", creationDate=" + creationDate
			+ ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy
			+ ", del=" + del + "]";
}

/*@ManyToOne
@JoinColumn(name="idMatriz", insertable=false, updatable=false)
private MatrizPartesHd matrizHd;




public MatrizPartesHd getMatrizHd() {
	return matrizHd;
}
public void setMatrizHd(MatrizPartesHd matrizHd) {
	this.matrizHd = matrizHd;
}*/


    



}

