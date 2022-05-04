package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Puestos;

public interface IAux_PuestosDao extends CrudRepository<Aux_Puestos, BigInteger>, JpaSpecificationExecutor<Aux_Puestos>{

}