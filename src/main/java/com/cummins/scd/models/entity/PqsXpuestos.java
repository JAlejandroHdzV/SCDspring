package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.cummins.scd.models.entity.Aux_Puestos;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.PqsXpuestosId;


@Entity
@IdClass (PqsXpuestosId.class)
@Table(name = "ZMKT7352_DES_CAT_PQSXPUESTOS")


public class PqsXpuestos implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;   
    
    @Id
    
    private String programId;    
    
    @Id
   
    private BigInteger idPuesto;   
    
   

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

    // @ManyToOne
    // @JoinColumn(name="programId", insertable=false, updatable=false)
    // private Pqs pqs;

    @ManyToOne
    @JoinColumn(name="idPuesto", insertable=false, updatable=false)
    private Aux_Puestos puesto;
    // @ManyToOne(optional=false)
    // @JoinColumn(name="idRegion", referencedColumnName="id_region", updatable=false, insertable=false)
    // private Region region;

    // @ManyToOne
    // @JoinColumn(name="idPais", referencedColumnName="id_pais", updatable=false, insertable=false)  
    // private Country country;
    /*Getter and setters*/   
    
    
    //Program
    public String getIdProgram() {
		return this.programId;
    }
    public void setIdProgram( String r) {
		this.programId = r;
    }
    
    //Puesto
    public BigInteger getIdPuesto() {
		return this.idPuesto;
    }
    public void setIdPuesto(BigInteger c) {
		this.idPuesto = c;
    }
    // //idCountryPerRegion
    // public BigInteger getIdCountryPerRegion(){
    //     return this.idCountryPerRegion;
    // }
    // public void gsetIdCountryPerRegion(BigInteger id){
    //     this.idCountryPerRegion = id;
    // }

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
    
    
    
    public Aux_Puestos getPuesto(){
        return this.puesto;
    }
    public Character getDel() {
		return del;
	}
	public void setDel(Character del) {
		this.del = del;
	}
	// public Pqs getPqs(){
    //     return this.pqs;
    // }
	@Override
	public String toString() {
		return "PqsXpuestos ["
				+ ", ProgramId = " + programId
                + ", IdPuesto = " + idPuesto
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}


}

