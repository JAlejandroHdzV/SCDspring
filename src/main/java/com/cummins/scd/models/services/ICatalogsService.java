package com.cummins.scd.models.services;
// import com.cummins.payroll.suite.models.entity.DiaFestivo;

import java.util.Date;
import java.util.List;
import java.math.BigInteger;

import org.springframework.web.multipart.MultipartFile;


import com.cummins.scd.global.FileStorageException;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.Aux_Anio;
import com.cummins.scd.models.entity.Aux_Evaluacion;
import com.cummins.scd.models.entity.Aux_Revisiones;
import com.cummins.scd.models.entity.Aux_Status;
import com.cummins.scd.models.entity.Category;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Tools;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;

// import com.cummins.payroll.suite.models.entity.DetalleCalendario;

public interface ICatalogsService {
	
	//----------------------------------------------------
	//										  CRUD METHODS
	//----------------------------------------------------

    public Object findAll(String catalogue);
	
	public Object findById(String catalogue, String id);
	
	public Object save(String catalogue, Object entity,String wwid);
	
	public void delete(String catalogue, String id);
	
	
	//----------------------------------------------------
	//										FILTER METHODS
	//----------------------------------------------------
	
	//public Object findByCriteria(String catalogue, Object filter);
	public Object findByCriteria(String catalogue, Object filter, String lang, Boolean flag);
	public Object findExisting(String catalogue, Object filter);
	
	
	//----------------------------------------------------
	//										  FILE METHODS
	//----------------------------------------------------
	public Oems storeFileOem(BigInteger id,String oem, String english,String portuguese, MultipartFile file, Date creationDate,String createdBy,Date lastUpdateDate, String lastUpdateBy, Character delete) throws FileStorageException;

	public MotorProducts storeFileMotorProducts(BigInteger id,String name, String comercialName, BigInteger idCategoria, MultipartFile file, Date creationDate,String createdBy,Date lastUpdateDate, String lastUpdateBy, Character delete) throws FileStorageException;
	
	public Tools storeFileTools(String np,String npAnterior,String spanish,String english,String portuguese,String codigoVenta,MultipartFile file, String wwid, Character delete) throws FileStorageException;
    
	public Oems getFileOem(String fileId);

	public MotorProducts getFileMotorProducts(String fileId);

	public Tools getFileTools(String np);
	
	//------------------------------------------------------
	//				 				FIND BY CRITERIA METHODS
	//------------------------------------------------------
	//List<Evaluaciones> findEvaluacionesByCriteria(Object entityFilter);
	List<Aux_Roles> findRolesByCriteria(Object entityFilter);
	//List<ServiceLevel> findServiceLevelByCriteria(Object entityFilter);
	//List<Aux_Evaluacion> findEvaluacionByCriteria(Object entityFilter);
	//List<Aux_Revisiones> findRevisionesByCriteria(Object entityFilter);
	List<Aux_Anio> findAnioByCriteria(Object entityFilter);
	//List<Aux_Status> findStatusByCriteria(Object entityFilter);
	//List<Category> findCategoryByCriteria(Object entityFilter);
	
	//------------------------------------------------------
	//											FIND BY NAME
	//------------------------------------------------------
	Country findCountryByName(Object entityFilter);
	Oems findOemsByName(Object entityFilter);
	Application findApplicationByName(Object entityFilter);
	ServiceLevel findServiceLevelByName(Object entityFilter);
	MotorProducts findMotorProductsByName(Object entityFilter);
	Ranks findRankByName(Object entityFilter);
	
	
	//------------------------------------------------------
	//										FIND BY EXISTING
	//------------------------------------------------------
	List<Tools> findToolExisting(Object entityFilter);

	List<Category> findCategoryByCriteria(Object entityFilter, String lang, Boolean flag);

	List<ServiceLevel> findServiceLevelByCriteria(Object entityFilter, String lang, Boolean flag);

	List<Aux_Status> findStatusByCriteria(Object entityFilter, String lang, Boolean flag);

	List<Aux_Revisiones> findRevisionesByCriteria(Object entityFilter, String lang, Boolean flag);

	List<Aux_Evaluacion> findEvaluacionByCriteria(Object entityFilter, String lang, Boolean flag);

	List<Evaluaciones> findEvaluacionesByCriteria(Object entityFilter, String lang, Boolean flag);

	

	



	


	

	
	
	
	
	

	

	

	

	

	

}