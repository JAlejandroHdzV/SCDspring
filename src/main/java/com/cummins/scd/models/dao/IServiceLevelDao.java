package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ServiceLevel;

public interface IServiceLevelDao extends CrudRepository<ServiceLevel, BigInteger>, JpaSpecificationExecutor<ServiceLevel>{

}