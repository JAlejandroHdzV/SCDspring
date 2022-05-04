
package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.AtributosMatricesHtas;

public interface IAtributosMatricesHtasDao extends CrudRepository<AtributosMatricesHtas, BigInteger>, JpaSpecificationExecutor<AtributosMatricesHtas>{

}