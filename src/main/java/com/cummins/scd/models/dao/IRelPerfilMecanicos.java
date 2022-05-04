package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


import com.cummins.scd.models.entity.RelPerfilMecanicos;

public interface IRelPerfilMecanicos extends CrudRepository<RelPerfilMecanicos, BigInteger>, JpaSpecificationExecutor<RelPerfilMecanicos> {

}
