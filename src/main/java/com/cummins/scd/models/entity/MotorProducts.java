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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
//import org.apache.commons.io.IOUtils;

@Entity
@Table(name = "ZMKT7352_DES_CAT_MOTORES")

public class MotorProducts implements Serializable {
	/**
	 * 
	 */
		 
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZMKT7352_SEC_ID_NOMBRE_MOT")
    @SequenceGenerator(name="ZMKT7352_SEC_ID_NOMBRE_MOT", sequenceName="ZMKT7352_SEC_ID_NOMBRE_MOT", allocationSize=1)
    //private int user_id;
	@Column(name = "ID")
    private BigInteger id;
	@Column(name = "NOMBRE")
    private String name;
    @Column(name = "NOMBRE_COMERCIAL")
    private String comercialName;
    private BigInteger idCategoria;
    @Lob
    // @Column(name = "PORTADA")
    @Column(name = "LOGO")
    private byte[] coverPage;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_TYPE")
    private String fileType;
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
    @JoinColumn(name="idCategoria", insertable=false, updatable=false)
    private Category category;
	/*Getter and setters*/   
    @Transient
	private String action;
	
	
	public String obtenerAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    //Id 
    public BigInteger getId() {
		return this.id;
    }
    public void setId( BigInteger id) {
		this.id = id;
    }
    
    //Name
	public String getName() {
		return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    //Comercial Name
	public String getComercialName() {
		return this.comercialName;
    }
    public void setComercialName(String name){
        this.comercialName = name;
    }
    public BigInteger getIdCategoria() {
		return this.idCategoria;
    }
    public void setIdCategoria(BigInteger ic){
        this.idCategoria = ic;
    }
    //coverPage
	public byte[] getCoverPage() {
		return this.coverPage;
    }
    public void setCoverPage(byte[] cover){
        this.coverPage = cover;
    }
    //File name
    public String getFileName() {
		return this.fileName;
    }
    public void setFileName(String fn){
        this.fileName = fn;
    }
    //File Type
    public String getFileType() {
		return this.fileType;
    }
    public void setFileType(String ft){
        this.fileType = ft;
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
	public Category getCategory(){
        return this.category;        
    }
    
	
	@Override
	public String toString() {
		return "MotorProducts [Id = " + id
                + ", Name = " + name
                + ", Comercial Name = " + comercialName
                + ", Cover Page = " + coverPage
				+ ", Creation Date = " + creationDate
				+ ", Created by = " + createdBy
				+ ", Last Update Date = " + lastUpdateDate 
				+ ", Last Updated By = " + lastUpdateBy 
				+ ", Deleted = " + ((del == 'Y') ? "YES" : "NO")
                + "]";
                
		
	}


}