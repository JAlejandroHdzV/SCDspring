package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IQuejasDao;
import com.cummins.scd.models.dto.CatQuejasDTO;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.Quejas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class QuejasServiceImpl implements IQuejasService{
	@Autowired
	IQuejasDao quejasDao;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	
	//----------------------------------------------------------
	//					SAVE RECORD QUEJAS
	//----------------------------------------------------------
	@Override
	public Object save(String wwid, Object entity)
	{ 
		System.out.println(entity);
		JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
		System.out.println(actualObj1);
		Date date = new Date();
		String id=actualObj1.get(0).get("idEvaluacion").toString().replaceAll("\"", "");
		List<Quejas> listaQuejasExist = getQuejasByDel(id);
		if(listaQuejasExist.size()<1) {
		List<Quejas> listaQuejas = new ArrayList<Quejas>();
		for (Object q : actualObj1) {
			Quejas queja= new Quejas();
			queja = mapper.convertValue(q, Quejas.class);
			queja.setDel('N');
			queja.setCreationDate(date);
			queja.setCreatedBy(wwid);
		 	queja.setLastUpdateDate(date);
		 	queja.setLastUpdateBy(wwid);
			listaQuejas.add(queja);
			quejasDao.save(queja);	
		}
		/*
	 	Date date = new Date();
	 	
	 	q.setCreationDate(date);
	 	q.setCreatedBy(wwid);
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
	 	return quejasDao.save(q);
	 	*/
		return listaQuejas;
		}else {
			System.out.println("Ya existen registros con ese id de queja");
			return "1";
		}
	
	}
		
	
	//---------------------------------------------------------------------
	//					FIND QUEJAS BY CRITERIA
	//--------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Quejas> findQuejasByCriteria(Object entityFilter){
	        
	        Quejas filter = mapper.convertValue(entityFilter, Quejas.class);
	     
	       return quejasDao.findAll( new  Specification<Quejas>() {
	
	           @Override
	           public Predicate toPredicate(Root<Quejas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	               List<Predicate> predicates = new ArrayList<>();
	               
	                if(filter.getIdEvaluacion() != null) {
	                   predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
	                }				
	                if(filter.getQueja() != null) {
	                   predicates.add(cb.and(cb.like(cb.upper(root.get("queja")), "%" + filter.getQueja().toUpperCase() + "%")));
	                }
	                if(filter.getPonderacion() != null) {
	                    predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
	                 }
	                 if(filter.getIdTipo() != null) {
	                    predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getIdTipo())));
	                 }
	                if(filter.getEnglish() != null) {
	                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
	                }
	                if(filter.getPortuguese() != null) {
	                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
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
	                if(filter.getLastUpdateBy() != null ) {
	                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
	                }
	                if(filter.getDel() != null) {
	                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
	                }				
	                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	           }
	           
	       }, new Sort(Sort.Direction.ASC, "queja"));
	    }
	
	//---------------------------------------------------------------------
	//					GET ALL QUEJAS BY N AND EVALUACION
	//--------------------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Quejas> getQuejasByDel(String id){
	try {	
		List<Quejas> listQuejas=(List<Quejas> ) em.createQuery(
				"SELECT c from Quejas c where c.del='N' AND c.idEvaluacion= " + id ).getResultList();
		return  listQuejas;
	}catch(Exception e) {
		
		return null;
	}finally {
		em.close();
	}
	}
	
	
	//---------------------------------------------------------------------
	//					DELETE QUEJAS BY ID
	//--------------------------------------------------------------------
	
	@Override
	public Quejas delete(String id, String wwid) {
		Quejas q=quejasDao.findOne(new BigInteger(id));
		Date date = new Date();
		q.setDel('Y');
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
	 	return quejasDao.save(q);
		
	}
	//---------------------------------------------------------------------
	//					DELETE ALL QUEJAS BY EVALUACION ID
	//--------------------------------------------------------------------
	

	@Override
	public List<Quejas> deleteAll(String id, String wwid) {
		List<Quejas>ListaQuejas=getQuejasByDel(id);
		System.out.println(ListaQuejas);
		List<Quejas>UpdatedList = new ArrayList<Quejas>();
		Date date = new Date();
		for(Quejas q: ListaQuejas)
		{
			Quejas queja= new Quejas();
			queja=mapper.convertValue(q, Quejas.class);
			queja.setDel('Y');
			queja.setLastUpdateDate(date);
			queja.setLastUpdateBy(wwid);
			UpdatedList.add( quejasDao.save(queja));
		}
		return UpdatedList;
		
	}
	
	//---------------------------------------------------------------------
	//					UPDATE QUEJAS
	//--------------------------------------------------------------------
	@Override
	public Quejas update(Quejas entity, String wwid) {
		System.out.println(entity.getIdCatQueja());
		Quejas q=quejasDao.findOne(new BigInteger(entity.getIdCatQueja().toString()));
		System.out.println(q);
	 	Date date = new Date();
	 	q.setIdEvaluacion(entity.getIdEvaluacion());
	 	q.setQueja(entity.getQueja());
	 	q.setEnglish(entity.getEnglish());
	 	q.setPortuguese(entity.getPortuguese());
	 	q.setPonderacion(entity.getPonderacion());
	 	q.setIdTipo(entity.getIdTipo());
	 	q.setDel('N');
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
		return quejasDao.save(q);
	
	}
	
	//---------------------------------------------------------------------
		
		//					UPDATE QUEJAS
		//--------------------------------------------------------------------
		@Override
		@SuppressWarnings("unchecked")
		public List<CatQuejasDTO> getByEvAndRegion(String listRegion, String lang) {
			try {
				List<Quejas> listQuejas=(List<Quejas> ) em.createQuery(
						"SELECT c from Quejas c where c.del='N' AND c.idEvaluacion in(SELECT b from Evaluaciones b where b.idRegion in(" + listRegion+"))" ).getResultList();
				List<CatQuejasDTO> lst=getCatQuejasDtoList(listQuejas, lang);
				return  lst;
			}catch(Exception e){
				System.out.println(e.getMessage());
				return null;
			}finally {
				em.close();
			}
		
		}
		
		private List<CatQuejasDTO> getCatQuejasDtoList(List<Quejas> listQuejas, String lang){
			List<CatQuejasDTO> listQ=new ArrayList<CatQuejasDTO>();
			for(Quejas q: listQuejas) {
				CatQuejasDTO newCatQuejasDTO =new CatQuejasDTO();
				newCatQuejasDTO.setIdCatQuejas(q.getIdCatQueja());
				switch(lang) {
	  			case"es_ES":
	  				newCatQuejasDTO.setQueja(q.getQueja());
		        	break;
		        case"en_US":
		        	newCatQuejasDTO.setQueja(q.getEnglish());
		        	break;
		        	
		        case"pt_BR":
		        	newCatQuejasDTO.setQueja(q.getPortuguese());
		        	break;
		        	
		        default:
		        	break;
	        }
				listQ.add(newCatQuejasDTO);
			}
			return listQ;
		}
}