package com.cummins.scd.models.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.SpCodes;

public interface ISpCodeDao extends CrudRepository<SpCodes, String>, JpaSpecificationExecutor<SpCodes>{
 public List<SpCodes> findBySpCodeAndDelAndIdTipo(String spCode, Character ch, BigInteger tipo);
 
}