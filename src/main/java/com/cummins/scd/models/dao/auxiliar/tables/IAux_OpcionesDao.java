package com.cummins.scd.models.dao.auxiliar.tables;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_Opciones;

public interface IAux_OpcionesDao extends CrudRepository<Aux_Opciones, BigInteger>, JpaSpecificationExecutor<Aux_Opciones> {

}
