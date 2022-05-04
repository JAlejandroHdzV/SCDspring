package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.Comparator;
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

import com.cummins.scd.models.dao.IESPM_NSDao;
import com.cummins.scd.models.dto.EspmNsDTO;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EspmNsService implements IEspmNsService {

	@Autowired
	IESPM_NSDao espmNsDao;
	
private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional(readOnly = true)
	@Override
    public List<EspmNsDTO> findESPMNSByCriteria(Object entityFilter, String lang){
        
        ESPM_NS filter = mapper.convertValue(entityFilter, ESPM_NS.class);
     
        List<EspmNsDTO> response= new ArrayList<>();
        List<ESPM_NS> listESPM= espmNsDao.findAll( new  Specification<ESPM_NS>() {

           @Override
           public Predicate toPredicate(Root<ESPM_NS> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNs"), filter.getIdNs())));
                 }
                 if(filter.getIdPuesto() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getIdPuesto())));
                 }
                 if(filter.getIdProgram() != null) {
                    predicates.add(cb.and(cb.equal(root.get("programId"), filter.getIdProgram())));
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
        
        for(ESPM_NS ns:listESPM) {
        	
        	EspmNsDTO espmNS= new EspmNsDTO();
        	espmNS.setIdNs(ns.getIdNs());
        	switch(lang) {
      		case "es_ES":
      			espmNS.setName(ns.getNs().getServiceLevel());
      			break;
      		case "pt_BR":
      			espmNS.setName(ns.getNs().getPortuguese());
      			break;
      		case "en_US":
      			espmNS.setName(ns.getNs().getEnglish());
      			break;
      			
      		
      		}
        	Boolean existe=false;
        	for (EspmNsDTO exist: response) {
        		if(ns.getIdNs()==exist.getIdNs()) {
        			existe=true;
        			break;
        		}
        	}
        	if(existe==false)
        	response.add(espmNS);
        	
        }
        response.sort(Comparator.comparing(EspmNsDTO::getName));
        return response;
    }
	
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ESPM_NS> espmNs(String motor, String rango, String aplicacion, String ns, String  lang) {
		String queryRango="";
		String queryAplicacion="";
		String queryNS="";
		switch(lang) {
			case"es_ES":
				queryRango="SELECT b.idRank from Ranks b where b.rank='"+rango+"'";
				queryAplicacion="SELECT b.idApp from Application b where b.application='"+aplicacion+"'";
				queryNS="SELECT b.idServiceLevel from ServiceLevel b where b.serviceLevel='"+ns+"'";
				break;
			case"en_US":
				queryRango="SELECT b.idRank from Ranks b where b.english='"+rango+"'";
				queryAplicacion="SELECT b.idApp from Application b where b.english='"+aplicacion+"'";
				queryNS="SELECT b.idServiceLevel from ServiceLevel b where b.english='"+ns+"'";
				break;
			case"pt_BR":
				queryRango="SELECT b.idRank from Ranks b where b.portuguese='"+rango+"'";
				queryAplicacion="SELECT b.idApp from Application b where b.portuguese='"+aplicacion+"'";
				queryNS="SELECT b.idServiceLevel from ServiceLevel b where b.portuguese='"+ns+"'";
				break;
		
		}
		System.out.println("ns: "+ns+" motor: "+motor+" rango: "+rango+" aplicacion: "+aplicacion);
		List<ESPM_NS> listESPM=(List<ESPM_NS> ) em.createQuery(
				"SELECT c from ESPM_NS c  where c.idEspmotor=("
											+ " SELECT a.idEspmotor from ESPM a where a.del='N' "
														+ "And"
												+ " a.idMotor=(SELECT b.id from MotorProducts b where b.name='"+motor+"')"
														+ "And"
												+ " a.idRango=("+queryRango+")"
														+ "And"
												+ " a.idApp=("+queryAplicacion+")"
														
											+ ") And "
											+ " c.idNs=("+queryNS+")"
											+ "").getResultList();
		
		return listESPM;
		
	}
}
