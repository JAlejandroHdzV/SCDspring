package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_DRDLR;

public interface IAux_DRDLRDao extends CrudRepository<Aux_DRDLR, BigInteger>, JpaSpecificationExecutor<Aux_DRDLR>{

}