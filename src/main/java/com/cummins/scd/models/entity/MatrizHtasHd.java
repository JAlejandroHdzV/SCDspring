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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;


import com.cummins.scd.models.entity.ServiceLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.cummins.scd.models.entity.Aux_StatusMatriz;


@Entity
@Table(name = "ZMKT7352_DES_MATRIZ_HTAS_HD")

public class MatrizHtasHd implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_DES_MATRIZ_HTAS_SEQ")
    @SequenceGenerator(name="ZMKT7352_DES_MATRIZ_HTAS_SEQ", sequenceName="ZMKT7352_DES_MATRIZ_HTAS_SEQ")//, allocationSize=1)
	@Column(name = "ID_MATRIZ")
    private BigInteger idMatriz;
	@Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NO_REVISION")
    private String noRevision;
    //@Column(name = "ID_NS")
    private BigInteger idNs;
    //@Column(name = "ID_STATUS")
    private BigInteger idStatus;
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
	
    
    @ManyToOne
    @JoinColumn(name="idNs", insertable=false, updatable=false)
    private ServiceLevel ns;
    
    @ManyToOne
    @JoinColumn(name="idStatus", insertable=false, updatable=false)
    private Aux_StatusMatriz status;
    
    
	public BigInteger getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(BigInteger idMatriz) {
		this.idMatriz = idMatriz;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNoRevision() {
		return noRevision;
	}
	public void setNoRevision(String noRevision) {
		this.noRevision = noRevision;
	}
	public BigInteger getIdNs() {
		return idNs;
	}
	public void setIdNs(BigInteger idNs) {
		this.idNs = idNs;
	}
	public BigInteger getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(BigInteger idStatus) {
		this.idStatus = idStatus;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ServiceLevel getNs() {
		return ns;
	}
	public void setNs(ServiceLevel ns) {
		this.ns = ns;
	}
	public Aux_StatusMatriz getStatus() {
		return status;
	}
	public void setStatus(Aux_StatusMatriz status) {
		this.status = status;
	}
    
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "matriz")  
   	List<MatrizHtasDet> detalles;*/
    

	/*Getter and setters*/   
    
    
    
    
    /*public BigInteger getIdMatriz() {
		return this.idMatriz;
    }
    

	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setIdMatriz(BigInteger id) {
		this.idMatriz = id;
    }
    
	public String getNombre() {
		return this.nombre;
    }
    public void setNombre(String o){
        this.nombre = o;
    }
    public String getNoRevision() {
		return this.noRevision;
    }
    public void setNoRevision(String o){
        this.noRevision = o;
    }
    
    public BigInteger getIdNs() {
		return this.idNs;
    }
    public void setIdNs(BigInteger o){
        this.idNs = o;
    }
    public BigInteger getIdStatus() {
        return this.idStatus;
    }
    public void setIdStatus(BigInteger o){
        this.idStatus = o;
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
    
    public ServiceLevel getNs(){
        return this.ns;
    }
    public Aux_StatusMatriz getStatus(){
        return this.status;
    }

	@Override
	public String toString() {
		return "MatrizHtasHd [idMatriz=" + idMatriz + ", nombre=" + nombre + ", noRevision=" + noRevision + ", idNs="
				+ idNs + ", idStatus=" + idStatus + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", action="
				+ action + ", ns=" + ns + ", status=" + status + ", detalles=" + "]";
	}*/
	
    
}