package com.cummins.scd.models.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccessControlServiceImpl implements IAccessControlService{

	@Autowired
    private IUsersDao usersDao;
	
	/*
    public Object findByCriteria(String catalogue, Object filter);
    */
    
    private ObjectMapper mapper = new ObjectMapper();
  
    Date date;

	@Override
    @Transactional(readOnly = true)
	public Object findAll(String catalogue) {
		switch(catalogue) {
		
		case "users":
			return usersDao.findAll();
		
		default:
			return null;
		}
		
	}

	@Override
    @Transactional(readOnly = true)
	public Object findById(String catalogue, String id) {
		switch(catalogue) {
		case "users":                
            return usersDao.findOne(new String(id));
				default:
					
					return null;
				
				}
			}

	@Override
	@Transactional
	public Object save(String catalogue, Object entity, String wwid) {
		date= new Date();
		switch(catalogue) {
		case "users":  
            Users user = mapper.convertValue(entity, Users.class);
            user.setCreatedBy(wwid);
            user.setLastUpdateBy(wwid);
            user.setCreationDate(date);
            user.setLastUpdateDate(date);
            return usersDao.save(user);
				default:
					
					return null;
				
				}
	}

	@Override
	@Transactional
	public void delete(String catalogue, String id) {
	switch(catalogue) {
	case "users":  
        usersDao.delete(new String(id));
	default:
        break;
	
	}
		
	}

	@Override
	public Object findByCriteria(String catalogue, Object filter) {
		switch(catalogue) {
			case "users":                
				return findUsersByCriteria(filter);
				
				default:
					
					return null;
				
				}
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	//                                            Find By Criteria
	//----------------------------------------------------------------------------------------------------------------
	
	
	public List<Users> findUsersByCriteria(Object entityFilter){
        
	     Users filter = mapper.convertValue(entityFilter, Users.class);
     
       return usersDao.findAll( new  Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if(filter.getWwid() != null) {
					predicates.add(cb.and(cb.equal(root.get("wwid"), filter.getWwid())));
				}
				if(filter.getEmail() != null) {
					predicates.add(cb.and(cb.equal(root.get("email"), filter.getEmail())));
				}
				if(filter.getNombre() != null) {
					predicates.add(cb.and(cb.like(root.get("nombre"), "%" + filter.getNombre().toUpperCase() + "%")));
				}
				if(filter.getIdRol() != null) {
					predicates.add(cb.and(cb.equal(root.get("idRol"), filter.getIdRol())));
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
			
		}, new Sort(Sort.Direction.ASC, "wwid"));
   }
}
