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
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.Aux_Puestos;
import com.cummins.scd.models.entity.ESPM_NSId;
@Entity
@IdClass (ESPM_NSId.class)
@Table(name = "ZMKT7352_DES_ESPM_NS_PQ")

public class ESPM_NS implements Serializable {
	
	//-------------------------------------------
	//			    FIELDS ESPM_NS_PQ
	//-------------------------------------------
    @Id 
    private BigInteger idEspmotor;  
    @Id
    private BigInteger idNs;
    @Id
    private BigInteger idPuesto;
    @Id
    private String programId;
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
	
    
    //-------------------------------------------
  	//			    OVERLOAD CONSTRUCTORS
  	//-------------------------------------------
    
    public ESPM_NS(BigInteger idEspmotor, BigInteger idNs, BigInteger idPuesto, String programId, Date creationDate,
			String createdBy, Date lastUpdateDate, String lastUpdateBy, Character del) {
		this.idEspmotor = idEspmotor;
		this.idNs = idNs;
		this.idPuesto = idPuesto;
		this.programId = programId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateBy = lastUpdateBy;
		this.del = del;
	}
    
    public ESPM_NS() {
	}
	//-------------------------------------------
  	//			       RELATIONS
  	//-------------------------------------------
    @ManyToOne
    @JoinColumn(name="idNs", insertable=false, updatable=false)
    private ServiceLevel ns;
    
    @ManyToOne
    @JoinColumn(name="idPuesto", insertable=false, updatable=false)
    private Aux_Puestos puesto;
    
    @ManyToOne
    @JoinColumn(name="programId", insertable=false, updatable=false)
    private Pqs program;
    
    //-------------------------------------------
  	//			    FIELDS ESPM_NS_PQ
  	//-------------------------------------------
    public void setIdESPM( BigInteger r){
        this.idEspmotor = r;
    }
    public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public BigInteger getIdESPM(){
        return this.idEspmotor;
    }

    public void setIdNs( BigInteger c){
        this.idNs = c;
    }
    public BigInteger getIdNs(){
        return this.idNs;
    }
    public void setIdPuesto( BigInteger c){
        this.idPuesto = c;
    }
    public BigInteger getIdPuesto(){
        return this.idPuesto;
    }
    public void setIdProgram( String c){
        this.programId = c;
    }
    public String getIdProgram(){
        return this.programId;
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
	public ServiceLevel getNs(){
        return this.ns;
    }
    public Aux_Puestos getPuestos(){
        return this.puesto;
    }
    
	
	
	public Pqs getProgram() {
		return program;
	}
	public void setProgram(Pqs program) {
		this.program = program;
	}
	@Override
	public String toString() {
		return "ESPM [IdEspMotor = " + idEspmotor
                + ", IdNs = " + idNs
                + ", IdPuesto = " + idPuesto
                + ", IdProgram = " + programId
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
	}
}