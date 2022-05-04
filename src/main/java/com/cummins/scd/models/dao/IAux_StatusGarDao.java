package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_StatusGar;
public interface IAux_StatusGarDao extends CrudRepository<Aux_StatusGar, BigInteger>, JpaSpecificationExecutor<Aux_StatusGar>{

}