package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Status;

public interface IAux_StatusDao extends CrudRepository<Aux_Status, BigInteger>, JpaSpecificationExecutor<Aux_Status>{

}