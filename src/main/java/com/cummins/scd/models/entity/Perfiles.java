package com.cummins.scd.models.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "ZMKT7352_DES_PERFILES")
public class Perfiles {
	
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_PERFIL")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_PERFIL", sequenceName="ZMKT7352_SEC_ID_PERFIL", allocationSize=1)
	@Column(name = "ID_PERFIL")
    private BigInteger idPerfil;
	
	private BigInteger idEvaluacion;
	private String spCode;
	private BigInteger idOem;
	
	@Column(name = "FRONTERIZO", nullable = true)
	private BigInteger fronterizo;
	
	@Column(name = "NO_BAHIAS", nullable = true)
    private BigInteger noBahias;
	
	@Column(name = "NO_MEC_PROMOTION")
    private BigInteger noMecPromotion;
	
	@Column(name = "NO_MECANICOS")
    private BigInteger noMecanicos;
	
	@Column(name = "NO_AYUDANTES")
    private BigInteger noAyudantes;
	
	@Column(name = "COMENTARIOS")
    private String  comentarios;
	
	@Column(name = "RESULTADO_BLOQUEADO", nullable = true)
    private Character resultadoBloqueado;
	
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
    @JoinColumn(name="idEvaluacion", insertable=false, updatable=false)
    private Evaluaciones ev;
	
	@ManyToOne
    @JoinColumn(name="spCode", insertable=false, updatable=false)
    private SpCodes sp;
	
	@ManyToOne
    @JoinColumn(name="idOem", insertable=false, updatable=false)
    private Oems ido;

	public BigInteger getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(BigInteger idPerfil) {
		this.idPerfil = idPerfil;
	}

	public BigInteger getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(BigInteger idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public BigInteger getIdOem() {
		return idOem;
	}

	public void setIdOem(BigInteger idOem) {
		this.idOem = idOem;
	}

	public BigInteger getFronterizo() {
		return fronterizo;
	}

	public void setFronterizo(BigInteger fronterizo) {
		this.fronterizo = fronterizo;
	}

	public BigInteger getNoBahias() {
		return noBahias;
	}

	public void setNoBahias(BigInteger noBahias) {
		this.noBahias = noBahias;
	}

	public BigInteger getNoMecPromotion() {
		return noMecPromotion;
	}

	public void setNoMecPromotion(BigInteger noMecPromotion) {
		this.noMecPromotion = noMecPromotion;
	}

	public BigInteger getNoMecanicos() {
		return noMecanicos;
	}

	public void setNoMecanicos(BigInteger noMecanicos) {
		this.noMecanicos = noMecanicos;
	}

	public BigInteger getNoAyudantes() {
		return noAyudantes;
	}

	public void setNoAyudantes(BigInteger noAyudantes) {
		this.noAyudantes = noAyudantes;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Character getResultadoBloqueado() {
		return resultadoBloqueado;
	}

	public void setResultadoBloqueado(Character resultadoBloqueado) {
		this.resultadoBloqueado = resultadoBloqueado;
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

	public Evaluaciones getEv() {
		return ev;
	}

	public void setEv(Evaluaciones ev) {
		this.ev = ev;
	}

	public SpCodes getSp() {
		return sp;
	}

	public void setSp(SpCodes sp) {
		this.sp = sp;
	}

	public Oems getIdo() {
		return ido;
	}

	public void setIdo(Oems ido) {
		this.ido = ido;
	}

	@Override
	public String toString() {
		return "Perfiles [idPerfil=" + idPerfil + ", idEvaluacion=" + idEvaluacion + ", spCode=" + spCode + ", idOem="
				+ idOem + ", fronterizo=" + fronterizo + ", noBahias=" + noBahias + ", noMecPromotion=" + noMecPromotion
				+ ", noMecanicos=" + noMecanicos + ", noAyudantes=" + noAyudantes + ", comentarios=" + comentarios
				+ ", resultadoBloqueado=" + resultadoBloqueado + ", creationDate=" + creationDate + ", createdBy="
				+ createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del
				+ ", action=" + action + ", ev=" + ev + ", sp=" + sp + ", ido=" + ido + "]";
	}

	

	
	
	
	
	
	
	

}
