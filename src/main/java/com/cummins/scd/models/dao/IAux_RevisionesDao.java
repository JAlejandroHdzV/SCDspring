package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Revisiones;

public interface IAux_RevisionesDao extends CrudRepository<Aux_Revisiones, BigInteger>, JpaSpecificationExecutor<Aux_Revisiones>{

}