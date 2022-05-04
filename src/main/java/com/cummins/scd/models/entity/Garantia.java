package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_GARANTIAS")

public class Garantia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
		 
	@Id
    @Column(name = "FOLIO")
	private String folio;
	
	//Relations
    private String idStatus;
    private String spCode;
    private String codigoCta;
    
    @Column(name = "ESN")
    private String esn;
    @Column(name = "MONTO_RECLAMADO")
    private Double montoReclamado;
    @Column(name = "MONTO_PAGADO")
    private Double montoPagado;
    @Column(name = "FECHA_RECLAMO")
    private Date fechaReclamo;
    @Column(name = "FECHA_FALLA")
    private Date fechaFalla;
    @Column(name = "FECHA_FIN_REPARACION")
    private Date fechaFinReparacion;
    @Column(name = "FECHA_RECHAZO")
    private Date fechaRechazo;
    @Column(name = "FECHA_SOLUCION")
    private Date fechaSolucion;
    @Column(name = "DIAS_REPARACION")
    private Double diasReparacion;
    @Column(name = "TOTAL_SRTS")
    private Double totalSrts;
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
    @JoinColumn(name="idStatus", insertable=false, updatable=false)
    private Aux_StatusGar status;

    @ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes spcode;

    @ManyToOne
    @JoinColumn(name="codigoCta", insertable=false, updatable=false)  
    private Aux_CC codigo; 
	/*Getter and setters*/   
    
    
    
    
    //Id 
    public String getFolio() {
		return this.folio;
    }
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setFolio( String id) {
		this.folio = id;
    }
    //Status
	public String getStatus() {
		return this.idStatus;
    }
    public void setStatus(String o){
        this.idStatus = o;
    }

    //SPCode
    public String getSpCode() {
		return this.spCode;
    }
    public void setSpCode(String e){
        this.spCode = e;
    }
    //codigoCta
    public String getCodigoCta()
    {
        return this.codigoCta;
    }
    public void setCodigoCta(String e){
        this.codigoCta = e;
    }
    //ESN
    public String getEsn() {
		return this.esn;
    }
    public void setEsn(String p){
        this.esn = p;
    }
  //Monto reclamado
    public Double getMontoReclamado() {
		return this.montoReclamado;
    }
    public void setMontoReclamado(Double m){
        this.montoReclamado = m;
    }
    //Monto Pagado
    public Double getMontoPagado() {
		return this.montoPagado;
    }
    public void setMontoPagado(Double ft){
        this.montoPagado = ft;
    }
    //Fecha Reclamo
	public Date getFechaReclamo() {
		return this.fechaReclamo;
    }
    public void setFechaReclamo(Date l){
        this.fechaReclamo = l;
    }
        //Fecha Falla
	public Date getFechaFalla() {
		return this.fechaFalla;
    }
    public void setFechaFalla(Date l){
        this.fechaFalla = l;
    }
    //Fecha Fin Reparacion
	public Date getFechaFinReparacion() {
		return this.fechaFinReparacion;
    }
    public void setFechaFinReparacion(Date l){
        this.fechaFinReparacion = l;
    }
     //Fecha Fin Reparacion
	public Date getFechaRechazo() {
		return this.fechaRechazo;
    }
    public void setFechaRechazo(Date l){
        this.fechaRechazo = l;
    }
    //Fecha Fin Reparacion
	public Date getFechaSolucion() {
		return this.fechaSolucion;
    }
    public void setFechSolucion(Date l){
        this.fechaSolucion = l;
    }
    //Dias Reparacion
	public Double getDiasReparacion() {
		return this.diasReparacion;
    }
    public void setDiasReparacion(Double l){
        this.diasReparacion = l;
    }
    //Total
	public Double getTotal() {
		return this.totalSrts;
    }
    public void setTotal(Double l){
        this.totalSrts = l;
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

    public Aux_StatusGar getStatusGar(){
        return this.status;
    }
    public SpCodes getCodes(){
        return this.spcode;
    }
    public Aux_CC getCodigo(){
        return this.codigo;
    }
  
	
	@Override
	public String toString() {
		return "Garantias [Id = " + folio
                + ", Status = " + idStatus
                + ", spCode = " + spCode
                + ", codigoCta = " + codigoCta
                + ", ESN = " + esn
                + ", montoReclamado = " + montoReclamado
                + ", montoPagado = " + montoPagado
                + ", fechaReclamo = " + fechaReclamo
                + ", fechaFalla = " + fechaFalla
                + ", fechaFinReparacion = " + fechaFinReparacion
                + ", fechaRechazo = " + fechaRechazo
                + ", fechaSolucion = " + fechaSolucion
                + ", diasReparacion = " + diasReparacion
                + ", totalSrts = " + totalSrts
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}
}