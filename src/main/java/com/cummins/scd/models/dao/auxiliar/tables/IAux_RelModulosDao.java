package com.cummins.scd.models.dao.auxiliar.tables;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_RelModulos;

public interface IAux_RelModulosDao extends CrudRepository<Aux_RelModulos, BigInteger>, JpaSpecificationExecutor<Aux_RelModulos> {
//
}
