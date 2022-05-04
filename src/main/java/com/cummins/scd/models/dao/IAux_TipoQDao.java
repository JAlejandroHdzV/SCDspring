package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_TipoQ;

public interface IAux_TipoQDao extends CrudRepository<Aux_TipoQ, BigInteger>, JpaSpecificationExecutor<Aux_TipoQ>{

}