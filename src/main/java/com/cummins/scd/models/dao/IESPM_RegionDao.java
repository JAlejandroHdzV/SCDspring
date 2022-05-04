package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ESPM_Region;

public interface IESPM_RegionDao extends CrudRepository<ESPM_Region, BigInteger>, JpaSpecificationExecutor<ESPM_Region>{

}