package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ESPM_NS;

public interface IESPM_NSDao extends CrudRepository<ESPM_NS, BigInteger>, JpaSpecificationExecutor<ESPM_NS>{

}