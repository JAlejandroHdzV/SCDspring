package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.List;

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

import com.cummins.scd.models.dao.IConfOemDao;
import com.cummins.scd.models.entity.ConfOem;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ConfOemService implements IConfOemService {

	 @Autowired
	    private IConfOemDao confoemDao;
	 
	 @PersistenceContext
		private EntityManager em;
	 
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
    public List<ConfOem> findConfOemByCriteria(Object entityFilter){
        
        ConfOem filter = mapper.convertValue(entityFilter, ConfOem.class);
     
       return confoemDao.findAll( new  Specification<ConfOem>() {

           @Override
           public Predicate toPredicate(Root<ConfOem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
            if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getIdOem() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idOem")),filter.getIdOem())));
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
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfMotor"));
    }
	
	@Override
	public ConfOem save(ConfOem oem) {
		return confoemDao.save(oem);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfOem> find(String Id) {
		
		List<ConfOem> listOem=(List<ConfOem>) em.createQuery("SELECT c from ConfOem c "
															+ "where c.idConfMotor='"+Id+"'"
																	+ "And c.del='N'").getResultList();
		
		return listOem;
		
	}
	
	//Query, valida si un motor, es válido para un OEM.
	
	@Transactional(readOnly = true)
	@Override
	public List<ConfOem> validaMotorOem(String Idoem,String IdEv,String Ns,String MotorName,String RangoName,String appName, String Lang){
		String queryApp="";
		String queryRango="";
		String queryNS="";
		switch(Lang) {
		case"es_ES":
			queryNS= " AND NS.serviceLevel = '" + Ns + "'";
			queryRango=" AND R.rank = '" + RangoName + "'";
			queryApp=" AND A.application = '" + appName + "'";
			break;
		case"en_US":
			queryNS= " AND NS.english = '" + Ns + "'";
			queryRango=" AND R.english = '" + RangoName + "'";
			queryApp=" AND A.english = '" + appName + "'";
					
		    break;
		case"pt_BR":
			queryNS= " AND NS.portuguese = '" + Ns + "'";
			queryRango=" AND R.portuguese = '" + RangoName + "'";
			queryApp=" AND A.portuguese = '" + appName + "'";
			
			break;

		
		}
		
		System.out.println("IdOem: "+ Idoem+ " Evaluacion: "+IdEv+" Nivel: "+Ns
				+ " Motor: "+MotorName+" Rango: "+RangoName+" App: "+appName);
		
		List<ConfOem> list=(List<ConfOem>) em.createQuery(
				"SELECT O "
				+ " FROM ConfOem O"
				+ " WHERE O.del = 'N'"
				+ " AND O.idOem = '" + Idoem + "'" 
				+ " AND O.idConfMotor IN"
				+ " ("
				+ " SELECT  CM.idConfMotor"
				+ " FROM    ConfMotor CM"
				+ " WHERE   CM.del = 'N'"
				+ "        AND CM.idEvaluacion = '" + IdEv + "'"
				+ "        AND idNs = "
				+ "        ("
				+ "            SELECT  NS.idServiceLevel"
				+ "            FROM    ServiceLevel NS"
				+ "            WHERE   NS.del = 'N'"
				+ queryNS
				+ ")"
				+ "        AND CM.idEspmotor =  "
				+ "("
				+ " SELECT  ESPM.idEspmotor"
				+ " FROM    ESPM ESPM"
				+ " WHERE   ESPM.del = 'N'"
				+ "        AND ESPM.idMotor ="
				+ "("
				+ " SELECT  M.id"
				+ " FROM    MotorProducts M"
				+ " WHERE   M.del = 'N'"
				+ "        AND M.name = '" + MotorName + "'"
				+ ")"
				+ " AND ESPM.idRango ="
				+ "("
				+ " SELECT  R.idRank"
				+ " FROM    Ranks R"
				+ " WHERE   R.del = 'N'"
				+ queryRango
				+ ")"
				+ " AND ESPM.idApp ="
				+ "("
				+ " SELECT  A.idApp"
				+ " FROM    Application A"
				+ " WHERE   A.del = 'N'"
				+ queryApp
				+ ")"
				+ ")"
				+ ")").getResultList();
		System.out.println(list);
		return  list;
	}
}
