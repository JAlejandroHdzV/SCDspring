package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_StatusMatriz;

public interface IAux_StatusMatrizDao extends CrudRepository<Aux_StatusMatriz, BigInteger>, JpaSpecificationExecutor<Aux_StatusMatriz>{

}