package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Tipo;

public interface IAux_TipoDao extends CrudRepository<Aux_Tipo, BigInteger>, JpaSpecificationExecutor<Aux_Tipo>{

}