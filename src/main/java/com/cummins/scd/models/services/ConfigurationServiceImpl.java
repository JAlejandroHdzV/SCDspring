package com.cummins.scd.models.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IPerfilesDao;
import com.cummins.scd.models.dao.IRelPerfilExcsDao;
import com.cummins.scd.models.dao.IRelPerfilMecanicos;
import com.cummins.scd.models.dao.IRelPerfilMotor;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMecanicos;
import com.cummins.scd.models.entity.RelPerfilMotor;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ConfigurationServiceImpl implements IConfigurationService{
	
	@Autowired
    private IPerfilesDao perfilesDao;
	@Autowired
    private IRelPerfilMotor perfilMotorDao;
	@Autowired
    private IRelPerfilExcsDao perfilExcsDao;
	@Autowired
    private IRelPerfilMecanicos perfilMecanicosDao;
	
	/*
    public Object findByCriteria(String catalogue, Object filter);
    */
    
    private ObjectMapper mapper = new ObjectMapper();
  
    Date date;

	@Override
    @Transactional(readOnly = true)
	public Object findAll(String catalogue) {
		switch(catalogue) {
		
		case "perfiles":
			return perfilesDao.findAll();
		case "perfilMotor":
			return perfilMotorDao.findAll();
		case "perfilExcs":
			return perfilExcsDao.findAll();
		case "perfilMecanicos":
			return perfilMecanicosDao.findAll();
		
		default:
			return null;
		}
		
	}

	@Override
    @Transactional(readOnly = true)
	public Object findById(String catalogue, String id) {
		switch(catalogue) {
		case "perfiles":                
            return perfilesDao.findOne(new BigInteger(id));
		case "perfilMotor":                
            return perfilMotorDao.findOne(new BigInteger(id));
		case "perfilExcs":                
            return perfilExcsDao.findOne(new BigInteger(id));
		case "perfilMecanicos":                
            return perfilMecanicosDao.findOne(new BigInteger(id));
				default:
					
					return null;
				
				}
			}

	@Override
	@Transactional
	public Object save(String catalogue, Object entity, String wwid) {
		date= new Date();
		switch(catalogue) {
		case "perfiles":  
            Perfiles perfil = mapper.convertValue(entity, Perfiles.class);
            
            Perfiles perfilFilter = new Perfiles();
            perfilFilter.setIdEvaluacion(perfil.getIdEvaluacion());
            perfilFilter.setSpCode(perfil.getSpCode());
            perfilFilter.setDel('N');
            
            
            List<Perfiles> perfilesList = findPerfilByCriteria(perfilFilter);
            /*if(!perfilesList.isEmpty()) {
            	perfil.setFronterizo(BigInteger.valueOf(10));
            }*/
            /*if(perfilesList.isEmpty()) {
            	perfil.setFronterizo(null);
            }else {
            	perfil.setFronterizo(perfilesList.get(0).getFronterizo());
            }*/
            
            perfil.setFronterizo((perfilesList.isEmpty())?null:perfilesList.get(0).getFronterizo());
                      
            perfil.setCreatedBy(wwid);
            perfil.setLastUpdateBy(wwid);
            perfil.setCreationDate(date);
            perfil.setLastUpdateDate(date);
            return perfilesDao.save(perfil);
		case "perfilMotor":  
			RelPerfilMotor perfilMotor = mapper.convertValue(entity, RelPerfilMotor.class);
            perfilMotor.setCreatedBy(wwid);
            perfilMotor.setLastUpdateBy(wwid);
            perfilMotor.setCreationDate(date);
            perfilMotor.setLastUpdateDate(date);
            return perfilMotorDao.save(perfilMotor);
		case "perfilExcs":  
			RelPerfilExcs perfilExcs = mapper.convertValue(entity, RelPerfilExcs.class);
            perfilExcs.setCreatedBy(wwid);
            perfilExcs.setLastUpdateBy(wwid);
            perfilExcs.setCreationDate(date);
            perfilExcs.setLastUpdateDate(date);
            return perfilExcsDao.save(perfilExcs);
		case "perfilMecanicos":  
			RelPerfilMecanicos perfilMecanicos = mapper.convertValue(entity, RelPerfilMecanicos.class);
            perfilMecanicos.setCreatedBy(wwid);
            perfilMecanicos.setLastUpdateBy(wwid);
            perfilMecanicos.setCreationDate(date);
            perfilMecanicos.setLastUpdateDate(date);
            return perfilMecanicosDao.save(perfilMecanicos);
				default:
					
					return null;
				
				}
	}

	@Override
	@Transactional
	public void delete(String catalogue, String id) {
	switch(catalogue) {
	case "perfiles":  
        perfilesDao.delete(new BigInteger(id));
	case "perfilMotor":  
        perfilesDao.delete(new BigInteger(id));
	case "perfilExcs":  
        perfilExcsDao.delete(new BigInteger(id));
        break;
	case "perfilMecanicos":  
        perfilMecanicosDao.delete(new BigInteger(id));
        break;
	default:
        break;
	
	}
		
	}

	@Override
	public Object findByCriteria(String catalogue, Object filter) {
		switch(catalogue) {
		case "perfiles":                
            return findPerfilByCriteria(filter);
		case "perfilMotor":                
            return findPerfilMotorByCriteria(filter);
		case "perfilExcs":                
            return findPerfilExcsByCriteria(filter);
		case "perfilMecanicos":                
            return findPerfilMecanicosByCriteria(filter);
				default:
					
					return null;
				
				}
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	//                                            Find By Criteria
	//----------------------------------------------------------------------------------------------------------------
	
	
	public List<Perfiles> findPerfilByCriteria(Object entityFilter){
        
	     Perfiles filter = mapper.convertValue(entityFilter, Perfiles.class);
     
       return perfilesDao.findAll( new  Specification<Perfiles>() {

			@Override
			public Predicate toPredicate(Root<Perfiles> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if(filter.getIdPerfil() != null) {
					predicates.add(cb.and(cb.equal(root.get("idPerfil"), filter.getIdPerfil())));
				}
				if(filter.getIdEvaluacion() != null) {
					predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
				}
				if(filter.getSpCode() != null) {
					predicates.add(cb.and(cb.equal(root.get("spCode"), filter.getSpCode())));
				}
				if(filter.getIdOem() != null) {
					predicates.add(cb.and(cb.equal(root.get("idOem"), filter.getIdOem())));
				}
				if(filter.getFronterizo() != null) {
					predicates.add(cb.and(cb.equal(root.get("fronterizo"), filter.getFronterizo())));
				}
				
				if(filter.getComentarios() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("comentarios")), "%" + filter.getComentarios().toUpperCase() + "%")));
               }
				if(filter.getNoBahias() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("noBahia")), "%" + filter.getNoBahias() + "%")));
               }
				if(filter.getNoMecPromotion() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("noMecPromotion")), "%" + filter.getNoMecPromotion() + "%")));
               }
				if(filter.getNoMecanicos() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("noMecanicos")), "%" + filter.getNoMecanicos() + "%")));
               }
				if(filter.getNoAyudantes() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("noAyudantes")), "%" + filter.getNoAyudantes() + "%")));
               }
				if(filter.getResultadoBloqueado() != null) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("resultadoBloqueado")),  filter.getNoAyudantes())));
               }

               if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
               }                
               if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
               }                
               if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
               }                
               if(filter.getLastUpdateBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
               }
               if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
               }				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "spCode"));
   }

	@Override
	public List<RelPerfilMotor> findPerfilMotorByCriteria(Object entityFilter){
        
	     RelPerfilMotor filter = mapper.convertValue(entityFilter, RelPerfilMotor.class);
    
      return perfilMotorDao.findAll( new  Specification<RelPerfilMotor>() {

    	  
    	
			@Override
			public Predicate toPredicate(Root<RelPerfilMotor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if(filter.getIdPerfil() != null) {
					predicates.add(cb.and(cb.equal(root.get("idPerfil"), filter.getIdPerfil())));
				}
				if(filter.getIdConfMotor() != null) {
					predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
				}
				if(filter.getCalificacion() != null) {
					predicates.add(cb.and(cb.equal(root.get("calificacion"), filter.getCalificacion())));
				}
				if(filter.getIdEstatus() != null) {
					predicates.add(cb.and(cb.equal(root.get("idEstatus"), filter.getIdEstatus())));
				}
				if(filter.getComentarios() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("comentarios")), "%" + filter.getComentarios().toUpperCase() + "%")));
              }
              if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
              }                
              if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
              }                
              if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
              }                
              if(filter.getLastUpdateBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
              }
              if(filter.getDel() != null) {
                  predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
              }				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "idPerfil"));
  }
	
	@Override
	public List<RelPerfilExcs> findPerfilExcsByCriteria(Object entityFilter){
        
	     RelPerfilExcs filter = mapper.convertValue(entityFilter, RelPerfilExcs.class);
    
      return perfilExcsDao.findAll( new  Specification<RelPerfilExcs>() {

    	  
    	
			@Override
			public Predicate toPredicate(Root<RelPerfilExcs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if(filter.getIdPerfil() != null) {
					predicates.add(cb.and(cb.equal(root.get("idPerfil"), filter.getIdPerfil())));
				}
				if(filter.getIdModulo() != null) {
					predicates.add(cb.and(cb.equal(root.get("idModulo"), filter.getIdModulo())));
				}
				if(filter.getIdSubmodulo() != null) {
					predicates.add(cb.and(cb.equal(root.get("idSubmodulo"), filter.getIdSubmodulo())));
				}
              if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
              }                
              if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
              }                
              if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
              }                
              if(filter.getLastUpdateBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
              }
              if(filter.getDel() != null) {
                  predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
              }				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "idPerfil"));
  }
	@Override
	public List<RelPerfilMecanicos> findPerfilMecanicosByCriteria(Object entityFilter){
        
	     RelPerfilMecanicos filter = mapper.convertValue(entityFilter, RelPerfilMecanicos.class);
    
      return perfilMecanicosDao.findAll( new  Specification<RelPerfilMecanicos>() {

    	  
    	
			@Override
			public Predicate toPredicate(Root<RelPerfilMecanicos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if(filter.getIdPerfil() != null) {
					predicates.add(cb.and(cb.equal(root.get("idPerfil"), filter.getIdPerfil())));
				}
				if(filter.getPromotionId() != null) {
					predicates.add(cb.and(cb.equal(root.get("promotionId"), filter.getPromotionId())));
				}
				if(filter.getNombre() != null) {
					predicates.add(cb.and(cb.like(root.get("nombre"), "%" + filter.getNombre().toUpperCase() + "%")));
				}
				if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
				}                
				if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
				}                
				if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
				}                
				if(filter.getLastUpdateBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
				}
				if(filter.getDel() != null) {
                  predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
				}				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "idPerfil"));
  }
	
	
}
