package com.cummins.scd.models.dao.auxiliar.tables;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_Modulos;

public interface IAux_ModulosDao extends CrudRepository<Aux_Modulos, BigInteger>, JpaSpecificationExecutor<Aux_Modulos> {

}
