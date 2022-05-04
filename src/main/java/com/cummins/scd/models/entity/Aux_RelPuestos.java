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


import com.cummins.scd.models.entity.Aux_RelPuestosId;


@Entity
@IdClass (Aux_RelPuestosId.class)
@Table(name = "ZMKT7352_DES_CAT_RELPUESTOS")


public class Aux_RelPuestos implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;   
    
    @Id
    
    private BigInteger idPuesto;    
    
    @Id
   
    private String titulo;   
    
   

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
    @JoinColumn(name="idPuesto", insertable=false, updatable=false)
    private Aux_Puestos puesto;
    // @ManyToOne(optional=false)
    // @JoinColumn(name="idRegion", referencedColumnName="id_region", updatable=false, insertable=false)
    // private Region region;

    // @ManyToOne
    // @JoinColumn(name="idPais", referencedColumnName="id_pais", updatable=false, insertable=false)  
    // private Country country;
    /*Getter and setters*/   
    
    
    //Region
    public BigInteger getId() {
		return this.idPuesto;
    }
    public void setId( BigInteger r) {
		this.idPuesto = r;
    }
    
    //Country
    public String getTitulo() {
		return this.titulo;
    }
    public void setTitulo( String c) {
		this.titulo = c;
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
    public Boolean isDeleted(){
        return (this.del == 'Y');
    }
    public Character getDelete(){
        return this.del;
        
    }
    public void setDelete(Boolean del){
        if(del)
            this.del = 'Y';
        else
            this.del = 'N';
    }
    public Aux_Puestos getPuesto(){
        return this.puesto;
    }
	@Override
	public String toString() {
		return "RelPuestos ["
				+ ", Id = " + idPuesto
                + ", Titulo = " + titulo
                + ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}


}

