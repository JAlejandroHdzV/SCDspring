package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Anio;

public interface IAux_AnioDao extends CrudRepository<Aux_Anio, BigInteger>, JpaSpecificationExecutor<Aux_Anio>{

}