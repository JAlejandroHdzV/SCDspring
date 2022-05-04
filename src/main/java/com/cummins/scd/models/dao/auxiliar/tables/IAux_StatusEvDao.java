package com.cummins.scd.models.dao.auxiliar.tables;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_StatusEv;

public interface IAux_StatusEvDao extends CrudRepository<Aux_StatusEv, BigInteger>, JpaSpecificationExecutor<Aux_StatusEv> {

}
