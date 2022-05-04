package com.cummins.scd.models.services.catalogs;




import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IESPMDao;
import com.cummins.scd.models.dao.IESPM_EmisionDao;
import com.cummins.scd.models.dao.IESPM_NSDao;
import com.cummins.scd.models.dao.IESPM_RegionDao;
import com.cummins.scd.models.dao.IServiceLevelDao;
import com.cummins.scd.models.dto.ConfMotorOemsSpDTO;
import com.cummins.scd.models.dto.ESPMNsRegEmDTO;
import com.cummins.scd.models.dto.EspmDTO;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_Emision;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.ESPM_Region;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;

@Service
public class EspmService implements IEspmService {

	@Autowired
	IESPMDao espmDao;
	@Autowired
	ILoadInfoService loadService;
	
	@Autowired
	IESPM_RegionDao regionService;
	
	@Autowired
	IESPM_NSDao nsService;
	
	@Autowired
	IESPM_EmisionDao emisionService;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ESPM> todosESPM() {
		
		return em.createQuery("SELECT c,r from ESPM c, ESPM_Region r where c.del='N' and c.idEspmotor=r.idEspmotor order by c.idEspmotor ").getResultList();
	}
	
	 //select B.NOMBRE FROM  ZMKT7352_DES_ESPM A join ZMKT7352_DES_CAT_MOTORES B ON A.ID_MOTOR=B.ID  where ID_ESPMOTOR=10
			 
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MotorProducts> EspmJoinMotorProductsName(String Id) {
		
		return em.createQuery("SELECT B from ESPM A, MotorProducts B where  A.idEspmotor=B.id and A.idEspmotor='"+Id+"'").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ESPM> espmxregion(){
		
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<ESPM> criteria= builder.createQuery(ESPM.class);
		Root<ESPM> root=criteria.from(ESPM.class);
		
		criteria.select(root.get("idMotor")).where(builder.and(builder.equal(root.get("del"),'N')));
		
		List<ESPM> list= (List<ESPM>) em.createQuery(criteria).getResultList();
		return  list;
		
	}
	
	@Override
	@Transactional(readOnly = true)
    public List<EspmDTO> findAll(String idioma ) {
        
		List<EspmDTO> response= new ArrayList<>();
          List<ESPM> listEspm=(List<ESPM>) espmDao.findAll();
                
          for(ESPM espm : listEspm) {
        	  EspmDTO espmDto= new EspmDTO();
        	  espmDto.setMotor(espm.getMotor().getName());
        	  espmDto.setCreatedBy(espm.getCreatedBy());
        	  espmDto.setCreationDate(espm.getCreationDate());
        	  espmDto.setDel(espm.getDel());
        	  espmDto.setIdMotor(espm.getIdMotor());
        	  espmDto.setLastUpdateDate(espm.getLastUpdateDate());
        	  espmDto.setLastUpdateBy(espm.getLastUpdatedBy());
        	  espmDto.setIdRango(espm.getIdRango());
        	  espmDto.setIdEspmotor(espm.getIdESPM());
        	  espmDto.setIdApp(espm.getApp().getId().toString());
        	  
      		switch(idioma) {
      		case "Español":
      			espmDto.setApp(espm.getApp().getApplication());
      			espmDto.setRango(espm.getRanks().getRank());
      			break;
      		case "Portugues":
      			espmDto.setApp(espm.getApp().getPortuguese());
      			espmDto.setRango(espm.getRanks().getPortuguese());
      			break;
      		case "Ingles":
      			espmDto.setApp(espm.getApp().getEnglish());
      			espmDto.setRango(espm.getRanks().getEnglish());
      			break;
      			
      		
      		}
      		ESPM_Region filterRegion= new ESPM_Region();
  	    	filterRegion.setIdESPM(espm.getIdESPM());
  	    	filterRegion.setDel('N');
  	    	System.out.println(filterRegion);
  	    	List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) loadService.findESPMRegionByCriteria(filterRegion);
  	    	/*List<ESPM_Region> listaESPMxRegion= regionService.findAll(new  Specification<ESPM_Region>() {

                @Override
                public Predicate toPredicate(Root<ESPM_Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();
			
                     return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            },new Sort(Sort.Direction.ASC, "idRegion"));*/
  	    	espmDto.setRegion(listaESPMxRegion);
      		
      		response.add(espmDto);
        	  
        	  
          }
          
           
        return response;
    }
	
@Override	
public List<EspmDTO> findESPMByCriteria(Object entityFilter,String idioma){
        
        ESPM filter = mapper.convertValue(entityFilter, ESPM.class);
        List<EspmDTO> response= new ArrayList<>();
       List<ESPM> listEspm= espmDao.findAll( new  Specification<ESPM>() {

           @Override
           public Predicate toPredicate(Root<ESPM> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdMotor() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idMotor"), filter.getIdMotor())));
                 }
                 if(filter.getIdRango() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRango"), filter.getIdRango())));
                 }
                 if(filter.getIdApp() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idApp"), filter.getIdApp())));
                 }	
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
       
       for(ESPM espm : listEspm) {
     	  EspmDTO espmDto= new EspmDTO();
     	  espmDto.setMotor(espm.getMotor().getName());
     	  espmDto.setCreatedBy(espm.getCreatedBy());
     	  espmDto.setCreationDate(espm.getCreationDate());
     	  espmDto.setDel(espm.getDel());
     	  espmDto.setIdMotor(espm.getIdMotor());
     	  espmDto.setLastUpdateDate(espm.getLastUpdateDate());
     	  espmDto.setLastUpdateBy(espm.getLastUpdatedBy());
     	  espmDto.setIdRango(espm.getIdRango());
     	  espmDto.setIdEspmotor(espm.getIdESPM());
     	  espmDto.setIdApp(espm.getApp().getId().toString());
     	 System.out.println(idioma);
   		switch(idioma) {
   		
   		case "en_US":
   			espmDto.setApp(espm.getApp().getEnglish());
   			espmDto.setRango(espm.getRanks().getEnglish());
   			break;
   		case "pt_BR":
   			espmDto.setApp(espm.getApp().getPortuguese());
   			espmDto.setRango(espm.getRanks().getPortuguese());
   			break;
   		default:
   			espmDto.setApp(espm.getApp().getApplication());
   			espmDto.setRango(espm.getRanks().getRank());
   			break;
   		}
   		ESPM_Region filterRegion= new ESPM_Region();
	    	filterRegion.setIdESPM(espm.getIdESPM());
	    	filterRegion.setDel('N');
	    	System.out.println(filterRegion);
	    	List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) loadService.findESPMRegionByCriteria(filterRegion);
	    	/*List<ESPM_Region> listaESPMxRegion= regionService.findAll(new  Specification<ESPM_Region>() {

             @Override
             public Predicate toPredicate(Root<ESPM_Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 List<Predicate> predicates = new ArrayList<>();
			
                  return cb.and(predicates.toArray(new Predicate[predicates.size()]));
             }
         },new Sort(Sort.Direction.ASC, "idRegion"));*/
	    	espmDto.setRegion(listaESPMxRegion);
   		
   		response.add(espmDto);
     	  
     	  
       }
       
        
     return response;
    }



	//--------------------------------------------------------------------------------
	//							      SAVE ESPM
	//--------------------------------------------------------------------------------

	
	@Override
	public Object save(Object entity, String wwid, String id) {
		Date date = new Date();
		JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
		//System.out.println(actualObj1);
		JsonNode espm=actualObj1.get("espm");
		BigInteger idMotor=new BigInteger(espm.get("idMotor").toString().replaceAll("\"", ""));
		BigInteger idRango= new BigInteger(espm.get("idRango").toString().replaceAll("\"", ""));
		String idApp= espm.get("idApp").toString().replaceAll("\"", "");
		String[] idEmision=espm.get("idEmision").toString().replaceAll("\"", "").split(",");
	    String[] idRegion=espm.get("idRegion").toString().replaceAll("\"", "").split(",");
	    JsonNode espmNs=actualObj1.get("espmNs");
	    BigInteger idEspm;
		/*if(id!= null) {
			idEspm=new BigInteger(id);
		}else {
			idEspm= null;
		}*/
		idEspm=id!=null? new BigInteger(id): null;
		ESPM newEspm= new ESPM(idEspm,idMotor,idRango,idApp,date,wwid,date,wwid,'N' );
		//System.out.println(newEspm);
		ESPM saveEspm=espmDao.save(newEspm);
		List <ESPM_Emision> emision= new ArrayList<ESPM_Emision>();
		List <ESPM_Region>  region= new ArrayList<ESPM_Region>();
		List <ESPM_NS> nivel= new ArrayList<ESPM_NS>();
		
		for(String e:idEmision) {
			ESPM_Emision em= new ESPM_Emision(saveEspm.getIdESPM(), new BigInteger(e), date, wwid, date, wwid, 'N');
			System.out.println("Objeto Emision: "+em);
			emision.add(emisionService.save(em));
		}
		
		for(String r: idRegion) {
			ESPM_Region reg= new ESPM_Region(saveEspm.getIdESPM(), new BigInteger(r), date, wwid, date, wwid, 'N');
			System.out.println("Objeto Region: "+reg);
			region.add(regionService.save(reg));
		}
		for(JsonNode node: espmNs) {
			BigInteger idNs= new BigInteger(node.get("ns").toString().replaceAll("\"", ""));
			String pqMec= node.get("pqMec").toString().replaceAll("\"", "");
			String pqJefe= node.get("pqJefe").toString().replaceAll("\"", "");
			String pqGarantias= node.get("pqGarantias").toString().replaceAll("\"", "");
			String pqGerente= node.get("pqGerente").toString().replaceAll("\"", "");
			ESPM_NS mec= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("4"), pqMec, date, wwid, date, wwid, 'N');
			nivel.add(nsService.save(mec));
			ESPM_NS jefe= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("2"), pqJefe, date, wwid, date, wwid, 'N');
			nivel.add(nsService.save(jefe));
			ESPM_NS garantia= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("1"), pqGarantias, date, wwid, date, wwid, 'N');
			nivel.add(nsService.save(garantia));
			ESPM_NS gerente= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("3"), pqGerente, date, wwid, date, wwid, 'N');
			nivel.add(nsService.save(gerente));
			
		}
		//System.out.println(emision);
		//System.out.println(region);
		
		ESPMNsRegEmDTO response= new ESPMNsRegEmDTO(saveEspm, emision, region, nivel);
		
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object delete(String id, String wwid) {
		System.out.println("Entra a actualizar el id" + id);
		Date date= new Date();
		//BigInteger Id=new BigInteger(id);
		System.out.println("big integer" + id);
		//ESPM exist= espmDao.findOne(Id);
		ESPM exist = (ESPM) em.createQuery("SELECT e from ESPM e where e.idEspmotor='"+id+"'").getResultList().get(0);
		exist.setDel('Y');
		exist.setLastUpdateDate(date);
		exist.setLastUpdatedBy(wwid);
		System.out.println(exist);
		ESPM updateEspm= espmDao.save(exist);
		List<ESPM_NS> responseNs= new ArrayList<ESPM_NS>();
		List<ESPM_Region> responseRegion= new ArrayList<ESPM_Region>();
		List<ESPM_Emision> responseEmision= new ArrayList<ESPM_Emision>();
		if(updateEspm!= null) {
		List<ESPM_NS> listEspmNs= em.createQuery("SELECT e from ESPM_NS e where  e.idEspmotor='"+exist.getIdESPM()+"'").getResultList();
		List<ESPM_Emision> listEspmEmision= em.createQuery("SELECT e from ESPM_Emision e where  e.idEspmotor='"+exist.getIdESPM()+"'").getResultList();
		List<ESPM_Region> listEspmRegion= em.createQuery("SELECT e from ESPM_Region e where e.idEspmotor='"+exist.getIdESPM()+"'").getResultList();
		for(ESPM_NS ns:listEspmNs) {
			ns.setLastUpdateDate(date);
			ns.setLastUpdatedBy(wwid);
			ns.setDel('Y');
			responseNs.add(nsService.save(ns));
			
		}
		for(ESPM_Emision em1:listEspmEmision) {
			em1.setLastUpdateDate(date);
			em1.setLastUpdatedBy(wwid);
			em1.setDel('Y');
			responseEmision.add(emisionService.save(em1));	
					
		}
		for(ESPM_Region reg:listEspmRegion) {
			reg.setLastUpdateDate(date);
			reg.setLastUpdatedBy(wwid);
			reg.setDel('Y');
			responseRegion.add(regionService.save(reg));
		}
		
		}
		ESPMNsRegEmDTO response= new ESPMNsRegEmDTO(updateEspm, responseEmision, responseRegion, responseNs);
		return response;
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object update(Object entity, String wwid) {
		JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
		//System.out.println(actualObj1);
		JsonNode espm=actualObj1.get("espm");
		String idEspm=espm.get("idESPM").toString().replaceAll("\"", "");
		System.out.println(idEspm);
		Object delete= delete(idEspm, wwid);
		Object response= updateEspm(entity, wwid, idEspm);
		return response;
	}
	
	
	
	

	@SuppressWarnings("unchecked")
	public Object updateEspm(Object entity, String wwid, String id) {
		Date date = new Date();
		JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
		System.out.println(actualObj1);
		JsonNode espm=actualObj1.get("espm");
		BigInteger idMotor=new BigInteger(espm.get("idMotor").toString().replaceAll("\"", ""));
		BigInteger idRango= new BigInteger(espm.get("idRango").toString().replaceAll("\"", ""));
		String idApp= espm.get("idApp").toString().replaceAll("\"", "");
		String[] idEmision=espm.get("idEmision").toString().replaceAll("\"", "").split(",");
	    String[] idRegion=espm.get("idRegion").toString().replaceAll("\"", "").split(",");
	    JsonNode espmNs=actualObj1.get("espmNs");
	    BigInteger idEspm;
	    ESPM newEspm;
		idEspm=id!=null? new BigInteger(id): null;
		ESPM exist = (ESPM) em.createQuery("SELECT e from ESPM e where e.idEspmotor='"+id+"'").getResultList().get(0);
		if(exist!= null) {
			
			newEspm= new ESPM(exist.getIdESPM(),exist.getIdMotor(),exist.getIdRango(),exist.getIdApp(),exist.getCreationDate(),exist.getCreatedBy(),date,wwid,'N' );
		}else {
			newEspm= new ESPM(idEspm,idMotor,idRango,idApp,date,wwid,date,wwid,'N' );
		}
		
		System.out.println(newEspm);
		ESPM saveEspm=espmDao.save(newEspm);
		List <ESPM_Emision> emision= new ArrayList<ESPM_Emision>();
		List <ESPM_Region>  region= new ArrayList<ESPM_Region>();
		List <ESPM_NS> nivel= new ArrayList<ESPM_NS>();
		
		for(String e:idEmision) {
			/*public ESPM_Emision(BigInteger idEspmotor, BigInteger idEmision, Date creationDate, String createdBy,
			Date lastUpdateDate, String lastUpdateBy, Character del)*/
			BigInteger idEm=new BigInteger(e);
			ESPM_Emision et=(ESPM_Emision) em.createQuery("SELECT e from ESPM_Emision  e where e.idEspmotor='"+id+"' and e.idEmision='"+idEm+"'").getResultList().get(0);
			ESPM_Emision em;
			if(et!= null) {
			em= new ESPM_Emision(et.getIdESPM(), et.getIdEmision(), et.getCreationDate(), et.getCreatedBy(), date, wwid, 'N');
			}else {
			em= new ESPM_Emision(saveEspm.getIdESPM(), new BigInteger(e), date, wwid, date, wwid, 'N');
				
				
			}
			System.out.println("Objeto Emision: "+em);
			emision.add(emisionService.save(em));
		}
		System.out.println(idRegion);
		for(String r: idRegion) {
			BigInteger idReg=new BigInteger(r);
			List<ESPM_Region> et=(List<ESPM_Region>) em.createQuery("SELECT e from ESPM_Region  e where  e.idEspmotor='"+id+"' and e.idRegion='"+idReg+"'").getResultList();
			ESPM_Region reg;
			/*public ESPM_Region(BigInteger idEspmotor, BigInteger idRegion, Date creationDate, String createdBy,
			Date lastUpdateDate, String lastUpdateBy, Character del) {*/
			if(et.size()>0) {
				
				reg= new ESPM_Region(et.get(0).getIdESPM(), et.get(0).getIdRegion(), et.get(0).getCreationDate(), et.get(0).getCreatedBy(), date, wwid, 'N');
			}else {
				reg= new ESPM_Region(saveEspm.getIdESPM(), new BigInteger(r), date, wwid, date, wwid, 'N');
				
			}
			System.out.println("Objeto Region: "+reg);
			region.add(regionService.save(reg));
		}
		for(JsonNode node: espmNs) {
			BigInteger idNs= new BigInteger(node.get("ns").toString().replaceAll("\"", ""));
			String pqMec= node.get("pqMec").toString().replaceAll("\"", "");
			String pqJefe= node.get("pqJefe").toString().replaceAll("\"", "");
			String pqGarantias= node.get("pqGarantias").toString().replaceAll("\"", "");
			String pqGerente= node.get("pqGerente").toString().replaceAll("\"", "");
			/*
			 * private BigInteger idEspmotor;  
    @Id
    private BigInteger idNs;
    @Id
    private BigInteger idPuesto;
    @Id
    private String programId;
			 * */
			List<ESPM_NS> existMec=(List<ESPM_NS>) em.createQuery("SELECT e from ESPM_NS  e "
																+ "where "
																+ " e.idEspmotor='"+id+"' "
																+ " and e.idNs='"+idNs+"' "
																+ " and e.idPuesto='4'"
																+ " and e.programId='"+pqMec+"'").getResultList();
			System.out.println("Mecanico "+existMec);
			List<ESPM_NS> existJefe=(List<ESPM_NS>) em.createQuery("SELECT e from ESPM_NS  e "
																+ "where  "
																+ "  e.idEspmotor='"+id+"' "
																+ " and e.idNs='"+idNs+"' "
																+ " and e.idPuesto='2'"
																+ " and e.programId='"+pqJefe+"'").getResultList();
			System.out.println("Jefe "+existJefe);
			List<ESPM_NS> existGarantia=(List<ESPM_NS>) em.createQuery("SELECT e from ESPM_NS  e "
																+ "where  "
																+ "  e.idEspmotor='"+id+"' "
																+ " and e.idNs='"+idNs+"' "
																+ " and e.idPuesto='1'"
																+ " and e.programId='"+pqGarantias+"'").getResultList();
			System.out.println("Garantia "+existGarantia);
			List<ESPM_NS> existGerente=(List<ESPM_NS>) em.createQuery("SELECT e from ESPM_NS  e "
																+ "where "
																+ "  e.idEspmotor='"+id+"' "
																+ " and e.idNs='"+idNs+"' "
																+ " and e.idPuesto='3'"
																+ " and e.programId='"+pqGerente+"'").getResultList();
			System.out.println("Gerente "+existGerente);
			ESPM_NS mec, jefe, garantia, gerente;
			System.out.println("hasta aqui todo bien");
			if(existMec.size()>0) {
				mec= new ESPM_NS(existMec.get(0).getIdESPM(), existMec.get(0).getIdNs(), existMec.get(0).getIdPuesto() , existMec.get(0).getIdProgram(), existMec.get(0).getCreationDate(), existMec.get(0).getCreatedBy(), date, wwid, 'N');
			}else {
				mec= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("4"), pqMec, date, wwid, date, wwid, 'N');
			}
				
			if(existJefe.size()>0) {
				jefe= new ESPM_NS(existJefe.get(0).getIdESPM(), existJefe.get(0).getIdNs(), existJefe.get(0).getIdPuesto() , existJefe.get(0).getIdProgram(), existJefe.get(0).getCreationDate(), existJefe.get(0).getCreatedBy(), date, wwid, 'N');
			}else {
				jefe= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("2"), pqJefe, date, wwid, date, wwid, 'N');
			}
			
			if(existGarantia.size()>0) {
				garantia= new ESPM_NS(existGarantia.get(0).getIdESPM(), existGarantia.get(0).getIdNs(), existGarantia.get(0).getIdPuesto() , existGarantia.get(0).getIdProgram(), existGarantia.get(0).getCreationDate(), existGarantia.get(0).getCreatedBy(), date, wwid, 'N');
			}else {
				garantia= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("1"), pqGarantias, date, wwid, date, wwid, 'N');
			}
			
			if(existGerente.size()>0) {
				gerente= new ESPM_NS(existGerente.get(0).getIdESPM(), existGerente.get(0).getIdNs(), existGerente.get(0).getIdPuesto() , existGerente.get(0).getIdProgram(), existGerente.get(0).getCreationDate(), existGerente.get(0).getCreatedBy(), date, wwid, 'N');
			}else {
				gerente= new ESPM_NS(saveEspm.getIdESPM(), idNs, new BigInteger("3"), pqGerente, date, wwid, date, wwid, 'N');
			}
			nivel.add(nsService.save(mec));
			nivel.add(nsService.save(jefe));
			nivel.add(nsService.save(garantia));
			nivel.add(nsService.save(gerente));
		}
		//System.out.println(emision);
		//System.out.println(region);
		
		ESPMNsRegEmDTO response= new ESPMNsRegEmDTO(saveEspm, emision, region, nivel);
		
		return response;
	}

	
}
