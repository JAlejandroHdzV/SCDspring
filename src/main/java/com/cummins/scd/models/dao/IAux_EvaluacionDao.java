package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Evaluacion;

public interface IAux_EvaluacionDao extends CrudRepository<Aux_Evaluacion, BigInteger>, JpaSpecificationExecutor<Aux_Evaluacion>{

}