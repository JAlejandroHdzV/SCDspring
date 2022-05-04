package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.controllers.catalogs.DesQuejasController;
import com.cummins.scd.models.dao.ICountryPerRegionDao;
import com.cummins.scd.models.dao.IDesQuejasDao;
import com.cummins.scd.models.dao.IQuejasDao;
import com.cummins.scd.models.dao.IRegionDao;
import com.cummins.scd.models.dao.ISpCodeDao;
import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.dto.QuejasDTO;
import com.cummins.scd.models.entity.Aux_Partes;
import com.cummins.scd.models.entity.Des_Quejas;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DesQuejasServiceImpl implements IDesQuejasService {
	@Autowired
	IDesQuejasDao quejasDao;
	@Autowired
	IUsersDao usersDao;
	
	@Autowired
	ISpCodeDao spCodeDao;
	
	@Autowired
	IQuejasDao catQuejasDao;
	
	@Autowired
	IRegionDao regionDao;
	
	@Autowired
	ICountryPerRegionDao pxrDao;
	
    private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
    private DataSource dataSource;
	
	private static final Logger logger = Logger.getLogger(DesQuejasServiceImpl.class);

	
	// ----------------------------------------------------------
	// GET ALL QUEJAS BY DEL='N'
    // ----------------------------------------------------------
    @SuppressWarnings("unchecked")
    //@Transactional(readOnly = true)
    @Override
    public List<QuejasDTO> getAllQuejas(String wwid, String lang)
    {
    	
    	logger.info("Enter the getAllQuejas query method");
	    try {
	    	
		    Users user= usersDao.findOne(wwid);
		    if (user!= null) {
		    	if(user.getTodas()=='0') {
		    		logger.info("User doesn't have all spcodes");
				    List<Des_Quejas> listQuejas = (List<Des_Quejas>) em
				    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
					        .createQuery("SELECT c FROM Des_Quejas c 	WHERE c.spCode IN ("
					        				+ "SELECT b.spCode FROM SpCodes b WHERE b.del='N'AND b.responsibleBranchCode IN("
					        					+ "SELECT a.spCode FROM UsersSpCodes a WHERE a.del='N' And a.wwid='"+wwid+"'"
					        					+ ")"
					        			+ ") ")
						    .getResultList();
				    //System.out.println("listQueja: "+listQuejas);
				    listQuejas.sort(Comparator.comparing(Des_Quejas::getIdQueja));
				    List<QuejasDTO> lstQueDto=convertQuejasDTO(listQuejas, lang);
				    //System.out.println("DTO: "+lstQueDto);
				    return lstQueDto;
		    	}else {
		    		logger.info("User have all spcodes");
		    		List<Des_Quejas> listQuejas = (List<Des_Quejas>) em
				    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
					        .createQuery("SELECT c FROM Des_Quejas c")// 	WHERE c.spCode IN ("
					        				//+ "SELECT b.spCode FROM SpCodes b WHERE b.del='N') ")
						    .getResultList();
		    		//System.out.println("listQueja: "+listQuejas);
				    listQuejas.sort(Comparator.comparing(Des_Quejas::getIdQueja));
				    List<QuejasDTO> lstQueDto=convertQuejasDTO(listQuejas, lang);
				    //System.out.println("DTO: "+lstQueDto);
				    return lstQueDto;
		    	}
		    }else {
		    	logger.info("There is no complaint list");
		    	return null;
		    }
	    }catch(Exception e) {
	    	logger.error(e.getMessage());
	    	return null;
	    }finally {
	    	em.close();
	    }
	    
	    
    }
    //-----------------------------------------------------------
    // GET ALL QUEJAS BY FILTERS
    // ----------------------------------------------------------
    @SuppressWarnings("unchecked")
	
    //@Transactional(readOnly = true)
    @Override
    public List<QuejasDTO> getQuejasByList( String lang, String listQjas,String listDist,String listSpCodes,String listRegions, String wwid)
    {
    	
    	logger.info("Enter the getQuejasByList query method");
    	List<Des_Quejas> listQuejas= null;
	    try {
	    	Users user=usersDao.findOne(wwid);
	    	//----------------------------------------------------------------------------------
	    	//						      	FILTER COMPLAINT BY DIST
	    	//----------------------------------------------------------------------------------
	    	if(!listQjas.equals("") && listQjas.length()>0 && !listQjas.equals("null")) {
	    		System.out.println("Entra a query por lista de quejas: "+listQjas);
	    		String spCodesRelation="";
	    		
	    		if(!listSpCodes.equals("") && listSpCodes.length()>0 && !listSpCodes.equals("null")) {
	    			spCodesRelation=listSpCodes;
	    		}else if(!listDist.equals("") && listDist.length()>0&&!listDist.equals("null") ){
	    			String []arr=listDist.split(",");
		    		List<String>arrFinal=new ArrayList<>();
		    		if(listDist.split(",").length<2) {
		    			listDist="'"+listDist+"'";
		    		}
		    		
		    		for(String ar:arr) {
		    			String item="'"+ar+"'";
		    			arrFinal.add(item);
		    		}
		    		listDist=String.join(", ", arrFinal);
		    		
		    		spCodesRelation=" SELECT DISTINCT E.spCode FROM SpCodes E WHERE E.del='N' And E.responsibleBranchCode IN " + 
                            " ( " + listDist +" ) ";
	    		}else {
	    			spCodesRelation=" SELECT DISTINCT E.spCode FROM SpCodes E WHERE E.del='N' And E.responsibleBranchCode IN " + 
                            " ( " + 
                        	" SELECT DISTINCT A.spCode FROM UsersSpCodes A WHERE " + 
                        			" A.wwid= '"+wwid+"' AND A.del='N' AND A.spCode IN " + 
                        				" ( " + 
                        				  	" SELECT DISTINCT C.spCode FROM SpCodes C WHERE C.del='N' And C.idPais IN " + 
                        				  	" ( " + 
                        				  		" SELECT DISTINCT D.idPais FROM CountryPerRegion D WHERE D.del='N' And D.idRegion IN("+listRegions+") " + 
											" ) " + 
										" ) " + 
						" ) ";
	    		}
	    		
	    		if(user.getTodas().equals('0')) {
	    		listQuejas = (List<Des_Quejas>) em
			    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
				        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
				        								      " B.idCatQuejas IN("+listQjas+") " + 
				        									  " AND " + 
				        		                              " B.spCode IN " + 
				        		                              " ( " + 
				        		                              spCodesRelation
				        		                               + 
				        									  " )")
					    .getResultList();
	    		}else {
	    			listQuejas = (List<Des_Quejas>) em
				    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
					        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
					        								      " B.idCatQuejas IN("+listQjas+") " + 
					        									  " AND " + 
					        		                              " B.spCode IN " + 
					        		                              " ( " + 
        		                                    				  	" SELECT DISTINCT C.spCode FROM SpCodes C WHERE C.del='N' And C.idPais IN " + 
        		                                    				  	" ( " + 
        		                                    				  		" SELECT DISTINCT D.idPais FROM CountryPerRegion D WHERE D.del='N' And D.idRegion IN("+listRegions+") " + 
        																" ) " + 
					        									  " )")
						    .getResultList();
	    			
	    		}
	    		
	    		
	    	}
	    	//----------------------------------------------------------------------------------
	    	//						      	FILTER COMPLAINT BY SPCODE
	    	//----------------------------------------------------------------------------------
	    	else if(!listSpCodes.equals("") && listSpCodes.length()>0 && !listSpCodes.equals("null")) {
	    		System.out.println("Entra a query por lista de spcodes: "+listSpCodes);
	    		listQuejas = (List<Des_Quejas>) em
			    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
				        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
				        		                              " B.spCode IN " + 
				        		                              " ( " + listSpCodes+ 
				        									  " )")
					    .getResultList();
	    		
	    		
	    		
	    	}
	    	//----------------------------------------------------------------------------------
	    	//						      	FILTER COMPLAINT BY DIST
	    	//----------------------------------------------------------------------------------
	    	else if(!listDist.equals("") && listDist.length()>0&&!listDist.equals("null")) {
	    		System.out.println("Entra a query por lista de distribuidores: "+listDist);
	    		System.out.println("Total de datos en la consulta: "+listDist.split(",").length);
	    		String []arr=listDist.split(",");
	    		List<String>arrFinal=new ArrayList<>();
	    		if(listDist.split(",").length<2) {
	    			listDist="'"+listDist+"'";
	    		}
	    		
	    		for(String ar:arr) {
	    			String item="'"+ar+"'";
	    			arrFinal.add(item);
	    		}
	    		listDist=String.join(", ", arrFinal);
	    		listQuejas = (List<Des_Quejas>) em
			    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
				        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
				        		                              " B.spCode IN " + 
				        		                              " ( " + 
				        		                              " SELECT DISTINCT E.spCode FROM SpCodes E WHERE E.del='N' And E.responsibleBranchCode IN " + 
				        		                                    " ( " + listDist+
				        											" ) " + 
				        									  " )")
					    .getResultList();
	    		
	    	}else if(!listRegions.equals("") && listRegions.length()>0 && !listRegions.equals("null")) {
	    		System.out.println("Entra a query por lista de regiones: "+listRegions);

    			System.out.println("getTodas"+user.getTodas());
	    		if(user.getTodas().equals('0')) {
		    		listQuejas = (List<Des_Quejas>) em
				    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
					        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
					        		                              " B.spCode IN " + 
					        		                              " ( " + 
					        		                              " SELECT DISTINCT E.spCode FROM SpCodes E WHERE E.del='N' And E.responsibleBranchCode IN " + 
					        		                                    " ( " + 
					        		                                    	" SELECT DISTINCT A.spCode FROM UsersSpCodes A WHERE " + 
					        		                                    			" A.wwid= '"+wwid+"' AND A.del='N' AND A.spCode IN " + 
					        		                                    				" ( " + 
					        		                                    				  	" SELECT DISTINCT C.spCode FROM SpCodes C WHERE C.del='N' And C.idPais IN " + 
					        		                                    				  	" ( " + 
					        		                                    				  		" SELECT DISTINCT D.idPais FROM CountryPerRegion D WHERE D.del='N' And D.idRegion IN("+listRegions+") " + 
					        																" ) " + 
					        															" ) " + 
					        											" ) " + 
					        									  " )")
						    .getResultList();
		    		}else {
		    			listQuejas = (List<Des_Quejas>) em
					    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
						        .createQuery(" SELECT B FROM Des_Quejas B WHERE " + 
						        		                              " B.spCode IN " + 
						        		                              " ( " + 
	        		                                    				  	" SELECT DISTINCT C.spCode FROM SpCodes C WHERE C.del='N' And C.idPais IN " + 
	        		                                    				  	" ( " + 
	        		                                    				  		" SELECT DISTINCT D.idPais FROM CountryPerRegion D WHERE D.del='N' And D.idRegion IN("+listRegions+") " + 
	        																" ) " + 
						        									  " )")
							    .getResultList();
		    			
		    		}
	    		
	    		
	    	}
		    System.out.println("listQueja: "+listQuejas);
		    listQuejas.sort(Comparator.comparing(Des_Quejas::getIdQueja));
		    List<QuejasDTO> lstQueDto=convertQuejasDTO(listQuejas, lang);
		    System.out.println("DTO: "+lstQueDto);
		    return lstQueDto;
	    }catch(Exception e) {
	    	logger.error(e.getMessage());
	    	return null;
	    }finally {
	    	em.close();
	    }
	    
	    
    }
    
    @SuppressWarnings("unchecked")
    //--------------------------------------------------------------
	// GET ALL QUEJAS BY SPCODES
    // ----------------------------------------------------------
    //@Transactional(readOnly = true)
    @Override
    public List<QuejasDTO> getQuejasBySpCodes( String lang, String listSpCodes)
    {
    	
    	logger.info("Enter the getQuejasBySpCodes query method");
	    try {
		    List<Des_Quejas> listQuejas = (List<Des_Quejas>) em
		    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
			        .createQuery("SELECT c FROM Des_Quejas c 	WHERE c.spCode IN ('"+listSpCodes
			        			+ "') ")
				    .getResultList();
		    System.out.println("listQueja: "+listQuejas);
		    listQuejas.sort(Comparator.comparing(Des_Quejas::getIdQueja));
		    List<QuejasDTO> lstQueDto=convertQuejasDTO(listQuejas, lang);
		    System.out.println("DTO: "+lstQueDto);
		    return lstQueDto;
	    }catch(Exception e) {
	    	logger.error(e.getMessage());
	    	return null;
	    }finally {
	    	em.close();
	    }
	    
	    
    }


	@Override
	public Des_Quejas getQuejasById(BigInteger id) {
		try {
		return quejasDao.findOne(id);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}finally {
			em.close();
		}
	}
	
	@Override
	public Boolean deleteQuejaById(BigInteger id) {
		try {
			quejasDao.delete(id);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			em.close();
			
		}
	}

	//----------------------------------------------------------
    // UPDATE COMPLAINT
    //----------------------------------------------------------
	@Override
	public Des_Quejas update(String wwid, Des_Quejas entity) {
		Des_Quejas exist= getQuejasById(entity.getIdQueja());
  		System.out.println(entity);
  		try {
	  		if(exist!=null) {
	  		    Date date= new Date();
	  		    entity.setFechaCaptura(exist.getFechaCaptura());
	  		    entity.setCreatedBy(exist.getCreatedBy());
	  		    entity.setCreationDate(exist.getCreationDate());
	  		    entity.setLastUpdateBy(wwid);
	  		    entity.setLastUpdateDate(date);
	  	 	    return quejasDao.save(entity);
	  		} else {
	  		    return null;
	  		}
  		} catch(Exception e) {
  			logger.error(e.getMessage());
  			return null;
  		} finally {
  			em.close();
  		}
	}
	
	
	//----------------------------------------------------------
    // SAVE COMPLAINT
    //----------------------------------------------------------
  	@Override
  	public Des_Quejas save(String wwid, Des_Quejas entity)
  	{ 
  		/*Des_Quejas exist= getQuejasById(entity.getIdQueja().toString());
  		System.out.println(entity);
  		if(exist==null) {*/
  		    Date date= new Date();
  		    entity.setFechaCaptura(date);
  		    entity.setCreatedBy(wwid);
  		    entity.setLastUpdateBy(wwid);
  		    entity.setCreationDate(date);
  		    entity.setLastUpdateDate(date);
  	 	    return quejasDao.save(entity);
  		/*} else {
  		    return null;
  		}*/
  	}
  	
  	//----------------------------------------------------------
    // GET COMPLAINT DTO BY ID
    //----------------------------------------------------------
  	@Override
  	public QuejasDTO getQuejaDtoById(BigInteger id, String lang)
  	{ 
  		List<Des_Quejas> qList= new ArrayList<Des_Quejas>();
  		Des_Quejas queja=quejasDao.findOne(id);
  		if(queja!=null) {
  			qList.add(queja);
  			List<QuejasDTO> quejasResult=convertQuejasDTO(qList,lang);
  			if(quejasResult!=null) {
  				return quejasResult.get(0);
  			}else {
  				return null;
  			}
  		}else {
  			
  			return null;
  		}
  	}
  	
  	//----------------------------------------------------------
    // CONVERT COMPLAINT DTO BY LIST
    //----------------------------------------------------------
  	@SuppressWarnings({ "unchecked" })
	private List<QuejasDTO> convertQuejasDTO(List<Des_Quejas> listQuejas, String lang){
  		List<QuejasDTO> list= new ArrayList<QuejasDTO>();
  		for(Des_Quejas q:listQuejas) {
  			SpCodes sp=spCodeDao.findOne(q.getSpCode());
  			SpCodes dr=spCodeDao.findOne(sp.getResponsibleBranchCode());
  			Quejas que=catQuejasDao.findOne(q.getIdCatQuejas());
  			
  			List<Region> listRegion = (List<Region>) em
		    		//select * from ZMKT7352_DES_QUEJAS c where c.SP_CODE in(select b.SP_CODE from ZMKT7352_DES_CAT_SPCODES b where b.SP_CODE in(select a.SP_CODE from ZMKT7352_DES_USERS_SPCODES a where a.WWID='RQ897'))
			        .createQuery(" SELECT c FROM Region c WHERE c.del='N' And c.idRegion IN ("
			        					+ " SELECT d.idRegion FROM CountryPerRegion d WHERE d.del='N' And d.idPais IN ( "+sp.getCountry().getId()+"  )  ) ")
				    .getResultList();
  			
  			
  			QuejasDTO quejasDTO= new QuejasDTO();
  			quejasDTO.setIdQueja(q.getIdQueja());
  			quejasDTO.setSpCode(q.getSpCode());
  			quejasDTO.setCliente(q.getCliente());
  			quejasDTO.setContactoQueja(q.getContactoQueja());
  			quejasDTO.setIdCatQuejas(q.getIdCatQuejas());
  			quejasDTO.setDescQueja(q.getDescQueja());
  			quejasDTO.setSegQueja(q.getSegQueja());
  			quejasDTO.setResponsableSeg(q.getResponsableSeg());
  			quejasDTO.setCompromiso(q.getCompromiso());
  			quejasDTO.setReporta(q.getReporta());
  			quejasDTO.setEstatus(q.getEstatus());
  			quejasDTO.setValidoEval(q.getValidoEval());
  			quejasDTO.setIdRegion(listRegion.get(0).getId());
  			quejasDTO.setRegion(listRegion.get(0).getName());
  			quejasDTO.setCodigoDr(dr.getSpCode());
  			quejasDTO.setDr(dr.getProviderName());
  			quejasDTO.setCodigoPuntoServicio(sp.getSpCode());
  			quejasDTO.setPuntoDeServicio(sp.getProviderName());
  			quejasDTO.setFechaCaptura(q.getFechaCaptura());
	  		quejasDTO.setFechaQueja(q.getFechaQueja());
	  		quejasDTO.setFechaCierre(q.getFechaCierre());
  			switch(lang) {
  			case"es_ES":
  				quejasDTO.setOem(dr.getOEM().getOem());
  				quejasDTO.setQueja(que.getQueja());
	        	break;
	        case"en_US":
	        	quejasDTO.setOem(dr.getOEM().getEnglish());
	        	quejasDTO.setQueja(que.getEnglish());
	        	break;
	        	
	        case"pt_BR":
	        	quejasDTO.setOem(dr.getOEM().getPortuguese());
	        	quejasDTO.setQueja(que.getPortuguese());
	        	break;
	        	
	        default:
	        	break;
        }
  			list.add(quejasDTO);
  		}
  		
  		return list;
  	}
}
