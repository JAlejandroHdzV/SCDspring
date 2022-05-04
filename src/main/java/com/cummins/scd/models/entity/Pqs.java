package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.apache.commons.io.IOUtils;

//import com.cummins.scd.models.entity.PqsXpuestos;



@Entity
@Table(name = "ZMKT7352_DES_CAT_PQS")


public class Pqs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */  
    @Id
    @Column(name = "PROGRAM_ID")
    private String programId;
    @Column(name = "NOMBRE")
    private String nombre;
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
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "programId")
    private List<PqsXpuestos> puestos;
    
	
	@Transient
	private String action;
	
	
    
    public List<PqsXpuestos> getPuestos() {
		return puestos;
	}
	public void setPuestos(List<PqsXpuestos> puestos) {
		this.puestos = puestos;
	}
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	//Id Evaluacion
    public String getId() {
		return this.programId;
    }
    public void setId(String r) {
		this.programId = r;
    }
    
    //Questions
    public String getNombre() {
		return this.nombre;
    }
    public void setNombre(String c) {
		this.nombre = c;
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

    //Deleted
  
    public Character getDel(){
        return this.del;
    }
    public void setDel(Character del){
        this.del= del;
    }
	@Override
	public String toString() {
		return "Pqs [programId=" + programId + ", nombre=" + nombre + ", creationDate=" + creationDate + ", createdBy="
				+ createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del
				+ ", puestos=" + puestos + ", action=" + action + "]";
	}
    
    
    
	


}

