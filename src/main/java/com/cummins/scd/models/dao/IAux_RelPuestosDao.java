package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_RelPuestos;

public interface IAux_RelPuestosDao extends CrudRepository<Aux_RelPuestos, BigInteger>, JpaSpecificationExecutor<Aux_RelPuestos>{

}