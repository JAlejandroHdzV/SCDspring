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
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizHtasDetId;
@Entity
@IdClass (MatrizHtasDetId.class)
@Table(name = "ZMKT7352_DES_MATRIZ_HTAS_DET")
public class MatrizHtasDet implements Serializable {
	/**
	 * 
     * 
	 */
		 
	//------------------------------------------------
	//				      FIELDS
	//------------------------------------------------
    @Id 
    private BigInteger idMatriz;    
    
    @Id
   
    private String np;


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
    
	@ManyToOne
    @JoinColumn(name ="np",insertable=false,  updatable=false)
    private Aux_Htas partes;
	/*@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "idMatriz", insertable=false, updatable=false)
    private MatrizHtasHd matriz;*/
	
	public Aux_Htas getPartes() {
		return partes;
	}

	public void setPartes(Aux_Htas partes) {
		this.partes = partes;
	}

	@Transient
	private String action;

	
	//------------------------------------------------
	//				     GETTERS AND SETTERS
	//------------------------------------------------
	public BigInteger getIdMatriz() {
		return idMatriz;
	}

	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}

	public String getNp() {
		return np;
	}

	public void setNp(String np) {
		this.np = np;
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

	/*public MatrizHtasHd getMatriz() {
		return matriz;
	}

	public void setMatriz(MatrizHtasHd matriz) {
		this.matriz = matriz;
	}*/

	public String obtenerAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "MatrizHtasDet [idMatriz=" + idMatriz + ", np=" + np + ", ponderacion=" + ponderacion + ", qty=" + qty
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", matriz="  + ", action=" + action + "]";
	}
	
	
  
	
	
    /*
    public void setIdMatriz( BigInteger r){
        this.idMatriz = r;
    }
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public BigInteger getIdMatriz(){
        return this.idMatriz;
    }

    public void setNP( String c){
        this.np = c;
    }
    public String getNP(){
        return this.np;
    }
    public void setPonderacion( float c){
        this.ponderacion = c;
    }
    public float getPonderacion(){
        return this.ponderacion;
    }
    
    public void setQty( BigInteger c){
        this.qty = c;
    }
    public BigInteger getQty(){
        return this.qty;
    }
    public Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }
    public void setCreatedBy(String user){
        this.createdBy = user;
    }

    public Date getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(Date date){
        this.lastUpdateDate = date;
    }
    public String getLastUpdatedBy(){
        return this.lastUpdateBy;
    }
    public void setLastUpdatedBy(String user){
        this.lastUpdateBy = user;
    }
    public Character getDel(){
        return this.del;
    }
    public void setDel(Character del){
        this.del = del;
        
    }
	
	
	/*@Override
	public String toString() {
		return "MatrizPartesDet [IdMatriz = " + idMatriz
                + ", NP = " + np
                + ", Ponderacion = " + ponderacion
                + ", Qty = " + qty
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}*/
}