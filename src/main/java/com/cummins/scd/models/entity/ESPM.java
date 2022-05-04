package com.cummins.scd.models.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.ESPMId;
@Entity

@IdClass (ESPMId.class)
@Table(name = "ZMKT7352_DES_ESPM")

public class ESPM implements Serializable {
	
	//--------------------------------------------------------------------
	//						FIELDS ESPM
	//--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_ESPMOTOR")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_ESPMOTOR", sequenceName="ZMKT7352_SEC_ID_ESPMOTOR", allocationSize=1)
    private BigInteger idEspmotor;    
    @Id
    private BigInteger idMotor;
    @Id
    private BigInteger idRango;
    @Id
    private String idApp;
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
	
	//-------------------------------------------------------------
	//					       RELATIONS
	//-------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="idMotor", insertable=false, updatable=false)
    private MotorProducts motor;
    
    @ManyToOne
    @JoinColumn(name="idRango", insertable=false, updatable=false)
    private Ranks rango;
    
    @ManyToOne
    @JoinColumn(name="idApp", insertable=false, updatable=false)
    private Application app;
    
 
    //----------------------------------------------------------
    //						OVERLOAD CONSTRUCTORS
    //----------------------------------------------------------
    public ESPM(BigInteger idEspmotor, BigInteger idMotor, BigInteger idRango, String idApp, Date creationDate,
			String createdBy, Date lastUpdateDate, String lastUpdateBy, Character del) {
		this.idEspmotor = idEspmotor;
		this.idMotor = idMotor;
		this.idRango = idRango;
		this.idApp = idApp;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateBy = lastUpdateBy;
		this.del = del;
	}
    
    public ESPM() {
	}
    
    //------------------------------------------------------------
    //				        GETTERS AND SETTERS
    //------------------------------------------------------------
	public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setIdMotor( BigInteger c){
        this.idMotor = c;
    }
    public BigInteger getIdMotor(){
        return this.idMotor;
    }
    public void setIdRango( BigInteger c){
        this.idRango = c;
    }
    
	public BigInteger getIdRango(){
        return this.idRango;
    }
    public void setIdApp( String c){
        this.idApp = c;
    }
    public String getIdApp(){
        return this.idApp;
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
	public MotorProducts getMotor(){
        return this.motor;
    }
    public Ranks getRanks(){
        return this.rango;
    }
   
	public Application getApp(){
        return this.app;
    }
	
    
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "ESPM [idEspmotor=" + idEspmotor + ", idMotor=" + idMotor + ", idRango=" + idRango + ", idApp=" + idApp
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateBy=" + lastUpdateBy + ", del=" + del + ", action=" + action + ", motor=" + motor
				+ ", rango=" + rango + ", app=" + app + "]";
	}
	
}