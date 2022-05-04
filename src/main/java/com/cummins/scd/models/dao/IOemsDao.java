package com.cummins.scd.models.dao;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Oems;

public interface IOemsDao extends CrudRepository<Oems, BigInteger>, JpaSpecificationExecutor<Oems>{



}