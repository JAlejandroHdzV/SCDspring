package com.cummins.scd.models.dao.auxiliar.tables;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;

public interface IAux_RolesDao extends CrudRepository<Aux_Roles, BigInteger>, JpaSpecificationExecutor<Aux_Roles> {

}
