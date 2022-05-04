package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.MatrizPartesDet;

public interface IMatrizPartesDetDao extends CrudRepository<MatrizPartesDet, BigInteger>, JpaSpecificationExecutor<MatrizPartesDet>{

}