package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.MatrizPartesDetId;
@Entity
@IdClass (MatrizPartesDetId.class)
@Table(name = "ZMKT7352_DES_MATRIZ_PARTES_DET")
public class MatrizPartesDet implements Serializable {
	/**
	 * 
     * 
	 */
		 
	
    
    //private BigInteger idMatriz;    
    
    @Id
   
    private String np;
    
    @Id
    
    private BigInteger idMatriz; 


    @Column(name = "PONDERACION")
    private float ponderacion;
    @Column(name = "QTY")
    private BigInteger qty;
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

    public Aux_Partes getPartes() {
		return partes;
	}

	public void setPartes(Aux_Partes partes) {
		this.partes = partes;
	}

	//fetch= FetchType.LAZY 
	@ManyToOne
    @JoinColumn(name ="np",insertable=false,  updatable=false)
    private Aux_Partes partes;

	public String getNp() {
		return np;
	}

	public void setNp(String np) {
		this.np = np;
	}

	public BigInteger getIdMatriz() {
		return idMatriz;
	}

	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}

	public float getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(float ponderacion) {
		this.ponderacion = ponderacion;
	}

	public BigInteger getQty() {
		return qty;
	}

	public void setQty(BigInteger qty) {
		this.qty = qty;
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

	/*public MatrizPartesHd getMatriz() {
		return matriz;
	}

	public void setMatriz(MatrizPartesHd matriz) {
		this.matriz = matriz;
	}*/

	@Override
	public String toString() {
		return "MatrizPartesDet [np=" + np + ", idMatriz=" + idMatriz + ", ponderacion=" + ponderacion + ", qty=" + qty
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", action=" + action + ", matriz=" +  "]";
	}
  
	/*Getter and setters*/   
    
	
	
    
}