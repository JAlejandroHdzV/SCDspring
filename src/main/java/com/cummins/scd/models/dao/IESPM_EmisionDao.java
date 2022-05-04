package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ESPM_Emision;

public interface IESPM_EmisionDao extends CrudRepository<ESPM_Emision, BigInteger>, JpaSpecificationExecutor<ESPM_Emision>{

}