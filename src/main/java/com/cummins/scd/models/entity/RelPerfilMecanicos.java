package com.cummins.scd.models.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@IdClass (RelPerfilMecanicosId.class)
@Table(name = "ZMKT7352_DES_REL_PERFIL_MECS")
public class RelPerfilMecanicos implements Serializable{

	public RelPerfilMecanicos(BigInteger idPerfil, String promotionId) {
		super();
		this.idPerfil = idPerfil;
		this.promotionId = promotionId;
	}
	public RelPerfilMecanicos() {
		super();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		//---------------------------------------------------
		//					    Fields
		//---------------------------------------------------
		@Id
		private BigInteger idPerfil;
		@Id
		@Column(name = "PROMOTION_ID", nullable = false)
		private String promotionId;
		
		@Column(name = "NOMBRE", nullable = false)
		private String nombre;
		@Column(name = "CURSOS", nullable = false)
		private String cursos;
		@Column(name = "CREATION_DATE", nullable = false)
	    private Date creationDate;
	    @Column(name = "CREATED_BY", nullable = false)
	    private String createdBy;
	    @Column(name = "LAST_UPDATE_DATE", nullable = false)
		private Date lastUpdateDate;
	    @Column(name = "LAST_UPDATE_BY", nullable = false)
		private String lastUpdateBy;
		@Column(name = "DELETE_IND")
	    private Character del;
		
		@Transient
		 private String action;
		
		@ManyToOne
	    @JoinColumn(name="idPerfil", insertable=false, updatable=false)
	    private Perfiles per;

		public BigInteger getIdPerfil() {
			return idPerfil;
		}

		public void setIdPerfil(BigInteger idPerfil) {
			this.idPerfil = idPerfil;
		}

		public String getPromotionId() {
			return promotionId;
		}

		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCursos() {
			return cursos;
		}

		public void setCursos(String cursos) {
			this.cursos = cursos;
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

		public Perfiles getPer() {
			return per;
		}

		public void setPer(Perfiles per) {
			this.per = per;
		}
		@Override
		public String toString() {
			return "RelPerfilMecanicos [idPerfil=" + idPerfil + ", promotionId=" + promotionId + ", nombre=" + nombre
					+ ", cursos=" + cursos + ", creationDate=" + creationDate + ", createdBy=" + createdBy
					+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateBy=" + lastUpdateBy + ", del=" + del
					+ ", per=" + per + "]";
		}
		
		
		
		
		
		
}
