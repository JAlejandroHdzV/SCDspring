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
import com.cummins.scd.models.entity.Aux_Anio;
import com.cummins.scd.models.entity.Aux_Evaluacion;
import com.cummins.scd.models.entity.Aux_Revisiones;
import com.cummins.scd.models.entity.Aux_Status;
import com.cummins.scd.models.entity.Region;

@Entity
@Table(name = "ZMKT7352_DES_CONF_EV")

public class Evaluaciones implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_EVALUACION")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_EVALUACION", sequenceName="ZMKT7352_SEC_ID_EVALUACION", allocationSize=1)
    //private int user_id;
	@Column(name = "ID_EVALUACION")
    private BigInteger idEvaluacion;
	private BigInteger idRegion;
    private BigInteger idTevaluacion;
    private BigInteger idRevision;
    private BigInteger idAnio;
    private BigInteger idStatus;
    
    
    
    @Column(name = "FEC_INI_EVAL")
    private Date initDate;
    @Column(name = "FEC_FIN_EVAL")
    private Date endDate;
    @Column(name = "TXT_01")
    private String text1;
    @Column(name = "TXT_02")
    private String text2;
    @Column(name = "TXT_03")
    private String text3;
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
	@Transient
	private String nombreRegion;
 
  
    @ManyToOne
    @JoinColumn(name="idRegion", insertable=false, updatable=false)
    private Region region;

    @ManyToOne
    @JoinColumn(name="idRevision", insertable=false, updatable=false)
    private Aux_Revisiones revision;

    public void setRevision(Aux_Revisiones revision) {
		this.revision = revision;
	}


	public void setStatus(Aux_Status status) {
		this.status = status;
	}


	@ManyToOne
    @JoinColumn(name="idAnio", insertable=false, updatable=false)  
    private Aux_Anio anio; 

    @ManyToOne
    @JoinColumn(name="idStatus", insertable=false, updatable=false)
    private Aux_Status status;
    
    @ManyToOne
    @JoinColumn(name="idTevaluacion", insertable=false, updatable=false)  
    private Aux_Evaluacion tipoEvaluacion; 
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ev")  
   	List<QSol> detalles;
    
    
    
	public List<QSol> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<QSol> detalles) {
		this.detalles = detalles;
	}
	public String obtenerNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	/*Getter and setters*/   
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    //Id 
    public BigInteger getId() {
		return this.idEvaluacion;
    }
   
	public void setId( BigInteger id) {
		this.idEvaluacion = id;
    }
    //Region Id
    public BigInteger getIdRegion() {
		return this.idRegion;
    }
    public void setIdRegion( BigInteger id) {
		this.idRegion = id;
    }
    public Region getRegion()
    {
        return this.region;
    }
   
    //Tipo de evaluacion Id
    public BigInteger getIdTEvaluacion() {
		return this.idTevaluacion;
    }
    public void setIdTEvaluacion( BigInteger id) {
		this.idTevaluacion = id;
    }
    public Aux_Evaluacion getTEvaluacion()
    {
        return this.tipoEvaluacion;
    }
    
    //Revision Id
    public BigInteger getIdRevision() {
		return this.idRevision;
    }
    public void setIdRevision( BigInteger id) {
		this.idRevision = id;
    }
    public Aux_Revisiones getRevisiones()
    {
        return this.revision;
    }
    // Año Id
    public BigInteger getIdAnio() {
		return this.idAnio;
    }
    public void setIdAnio( BigInteger id) {
		this.idAnio = id;
    }
    public Aux_Anio getAnio(){
      return this.anio;
    }
    
    // Status Id
    public BigInteger getIdStatus() {
		return this.idStatus;
    }
    public void setIdStatus( BigInteger id) {
		this.idStatus = id;
    }
    public Aux_Status getStatus(){
      return this.status;
    }
    
    // Fecha inicial y final de evaluacion
    public Date getInitDate(){
        return this.initDate;
    }
    public void setInitDate(Date date){
        this.initDate = date;
    }

    public Date getEndDate(){
        return this.endDate;
    }
    public void setEndDate(Date date){
        this.endDate = date;
    }
    // text1 TÍTULO DEL CERTIFICADO
    public String getText1() {
		return this.text1;
    }
    public void setText1(String o){
        this.text1 = o;
    }
    //text 2 TÍTULO MOTORES AUTORIZADOS
    public String getText2() {
		return this.text2;
    }
    public void setText2(String o){
        this.text2 = o;
    }
    // text3 TÍTULO VIGENCIA
    public String getText3() {
		return this.text3;
    }
    public void setText3(String o){
        this.text3 = o;
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
    // public Boolean isDeleted(){
    //     return (this.del == 'Y');
    // }
    public Character getDel(){
        return this.del;
    }
    public void setDel(Character del){
        this.del = del;
        
    }
	
	
	@Override
	public String toString() {
		return "Evaluaciones [Id = " + idEvaluacion
                + ", IdRegion = " + idRegion
                + ", IdTEvaluacion = " + idTevaluacion
                + ", IdRevision = " + idRevision
                + ", IdAnio = " + idAnio
                + ", IdStatus = " + idStatus
                + ", InitDate = " + initDate
                + ", EndDate = " + endDate
                + ", Text1 = " + text1
                + ", Text2 = " + text2
                + ", Text3 = " + text3
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}
}