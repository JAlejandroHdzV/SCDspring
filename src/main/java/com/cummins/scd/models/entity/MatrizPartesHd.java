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


import com.cummins.scd.models.entity.Aux_Tipo;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.Aux_StatusMatriz;


@Entity
@Table(name = "ZMKT7352_DES_MATRIZ_PARTES_HD")

public class MatrizPartesHd implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_DES_MATRIZ_PARTES_SEQ")
    @SequenceGenerator(name="ZMKT7352_DES_MATRIZ_PARTES_SEQ", sequenceName="ZMKT7352_DES_MATRIZ_PARTES_SEQ")//, allocationSize=11800)
	@Column(name = "ID_MATRIZ")
    private BigInteger idMatriz;
	@Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NO_REVISION")
    private String noRevision;
    //@Column(name = "ID_TIPO") // 
    private BigInteger idTipo;
    //@Column(name = "ID_NS")
    private BigInteger idNs;
    //@Column(name = "ID_STATUS")
    private BigInteger idStatus;
    @Column(name = "SO")
    private String so;
    @Column(name = "ESN")
    private String esn;
    @Column(name = "CPL")
    private String cpl;
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
    @JoinColumn(name="idTipo", insertable=false, updatable=false)
    private Aux_Tipo tipo;
    @ManyToOne
    @JoinColumn(name="idNs", insertable=false, updatable=false)
    private ServiceLevel ns;
    @ManyToOne
    @JoinColumn(name="idStatus", insertable=false, updatable=false)
    private Aux_StatusMatriz status;
   
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "matriz")  
	//List<MatrizPartesDet> detalles;

    
    
    
	/*Getter and setters*/   

   /* public List<MatrizPartesDet> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<MatrizPartesDet> detalles) {
		this.detalles = detalles;
	}*/

	//Action
    public String obtenerAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

    
    
    //Numero Id
    public BigInteger getIdMatriz() {
		return this.idMatriz;
    }
   
	
	public void setIdMatriz(BigInteger id) {
		this.idMatriz = id;
    }
    
    //nombre
	public String getNombre() {
		return this.nombre;
    }
    public void setNombre(String o){
        this.nombre = o;
    }
    //No Revision
    public String getNoRevision() {
		return this.noRevision;
    }
    public void setNoRevision(String o){
        this.noRevision = o;
    }
    
    //id tipo
   
    public void setTipo(Aux_Tipo tipo) {
		this.tipo = tipo;
	}
    public Aux_Tipo getTipo(){
        return this.tipo;
    }
    
    //id Ns
    public BigInteger getIdNs() {
		return this.idNs;
    }
   
	public void setIdNs(BigInteger o){
        this.idNs = o;
    }
    //id Ns
    public BigInteger getIdStatus() {
        return this.idStatus;
    }
    public void setIdStatus(BigInteger o){
        this.idStatus = o;
    }
    //SO
    public String getSo() {
		return this.so;
    }
    public void setSo(String e){
        this.so = e;
    }
    //ESN
    public String getESN() {
		return this.esn;
    }
    public void setESN(String e){
        this.esn = e;
    }
    //cpl
    public String getCPL() {
		return this.cpl;
    }
    public void setCPL(String e){
        this.cpl = e;
    }

    //Creation Date
    public Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    //Created By
    public String getCreatedBy(){
        return this.createdBy;
    }
    public void setCreatedBy(String user){
        this.createdBy = user;
    }

    //Last Updated Date
    public Date getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(Date date){
        this.lastUpdateDate = date;
    }

    //Last updated By
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
    
    //Id tipo
    public BigInteger getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(BigInteger idTipo) {
		this.idTipo = idTipo;
	}

    
    
	public Aux_StatusMatriz getStatus() {
		return status;
	}

	
	public void setStatus(Aux_StatusMatriz status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MatrizPartesHd [idMatriz=" + idMatriz + ", nombre=" + nombre + ", noRevision=" + noRevision
				+ ", idTipo=" + idTipo + ", idNs=" + idNs + ", idStatus=" + idStatus + ", so=" + so + ", esn=" + esn
				+ ", cpl=" + cpl + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", action=" + action + ", tipo="
				+ tipo + ", ns=" + ns + ", status=" + status + "]";
	}
	
	

	
	
}