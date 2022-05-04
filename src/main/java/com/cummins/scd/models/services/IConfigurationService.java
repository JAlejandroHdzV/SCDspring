package com.cummins.scd.models.services;

import java.util.List;

import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMecanicos;
import com.cummins.scd.models.entity.RelPerfilMotor;

public interface IConfigurationService {

	 public Object findAll(String catalogue);
		
		public Object findById(String catalogue, String id);
		
		public Object save(String catalogue, Object entity,String wwid);
		
		public void delete(String catalogue, String id);
		
		public Object findByCriteria(String catalogue, Object filter);

		List<RelPerfilMotor> findPerfilMotorByCriteria(Object entityFilter);

		List<RelPerfilExcs> findPerfilExcsByCriteria(Object entityFilter);

		List<RelPerfilMecanicos> findPerfilMecanicosByCriteria(Object entityFilter);
	
	

}
