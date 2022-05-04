package com.cummins.scd.models.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ESPM;

public interface IESPMDao extends CrudRepository<ESPM, BigInteger>, JpaSpecificationExecutor<ESPM>{

	@Query(value="SELECT e FROM ESPM e")
	List<ESPM> espmMotor();
	
	
}