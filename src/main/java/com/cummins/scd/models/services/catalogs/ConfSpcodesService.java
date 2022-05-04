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

import com.cummins.scd.models.dao.IConfSpcodesDao;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConfSpcodesService implements IConfSpcodesService {
	@Autowired
    private IConfSpcodesDao confspcodesDao;
	
	@PersistenceContext
	private EntityManager em;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
    public List<ConfSpcodes> findConfSpcodesByCriteria(Object entityFilter){
        
        ConfSpcodes filter = mapper.convertValue(entityFilter, ConfSpcodes.class);
     
       return confspcodesDao.findAll( new  Specification<ConfSpcodes>() {

           @Override
           public Predicate toPredicate(Root<ConfSpcodes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getSpCode() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSpCode().toUpperCase() + "%")));
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
	public ConfSpcodes save(ConfSpcodes sp) {
		return confspcodesDao.save(sp);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfSpcodes> find(String Id) {
		
		List<ConfSpcodes> listSpCodes=(List<ConfSpcodes>) em.createQuery("SELECT c from ConfSpcodes c "
																			+ "where c.idConfMotor='"+Id+"'"
																				+ "And c.del='N'").getResultList();
		
		return listSpCodes;
		
	}
	
	//valida si un motor, es válido para un Distribuidor
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<ConfSpcodes> validaMotorDistribuidor(String spcode,String IdEv,String Ns,String MotorName,String RangoName,String appName, String Lang){
		System.out.println("SpCode: "+spcode+" Evaluacion: "+IdEv+" NivelServicio: "+Ns+" Motor: "+MotorName+" Rango: "+RangoName+" App: "+appName+"Lang: "+Lang);
		
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
		
		System.out.println("SpCode: "+ spcode+ " Evaluacion: "+IdEv+" Nivel: "+Ns
				+ " Motor: "+MotorName+" Rango: "+RangoName+" App: "+appName);
		List<ConfSpcodes>list=(List<ConfSpcodes>) em.createQuery(
				"SELECT SP "
				+ " FROM ConfSpcodes SP"
				+ " WHERE SP.del = 'N'"
				+ " AND SP.spCode = '" + spcode + "'" 
				+ " AND SP.idConfMotor IN"
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
		//ConfSpcodes cspc= new ConfSpcodes();
		//cspc= mapper.convertValue(list, ConfSpcodes.class);
		//System.out.println(cspc);
		return  list;
	}
	
}
