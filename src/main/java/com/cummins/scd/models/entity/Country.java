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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;



@Entity
@Table(name = "ZMKT7352_DES_CAT_PAISES")


public class Country implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "ID_PAIS")
    private BigInteger idCountry; 
    
    
	@Column(name = "PAIS")
    private String name;    
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
	
	
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@OneToMany
    @JoinColumn(name="idPais")
    private List<CountryPerRegion> countriesPerRegion;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendario")  
	// 	List<DetalleCalendario> detalles;
	
	//  @Transient
	//  private String action;
	 
	//  @Transient
	//  private Date fechaInicial;

	//  @Transient
	//  private Date fechaInicialPeriodo;
     
    /*Getter and setters*/   
    
    
	
    public List<CountryPerRegion> obtenerCountriesPerRegion() {
		return countriesPerRegion;
	}
	public void setCountriesPerRegion(List<CountryPerRegion> countriesPerRegion) {//fue modificado el 24//11/2021 checar si no truenan otrso servicios
		this.countriesPerRegion = countriesPerRegion;
	}
	
	/*public List<CountryPerRegion> getCountriesPerRegion() {
		return countriesPerRegion;
	}*/
	//Id Pais
    public BigInteger getId() {
		return this.idCountry;
    }
    public void setId( BigInteger id) {
		this.idCountry = id;
    }
    
    //Country Name
	public String getName() {
		return this.name;
    }
    public void setName(String name){
        this.name = name;
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
   
	
	
	@Override
	public String toString() {
		return "Country [Id = " + idCountry
				+ ", Name = " + name
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}
	public Character getDel() {
		return del;
	}
	public void setDel(Character del) {
		this.del = del;
	}


}