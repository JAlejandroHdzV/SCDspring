
package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ConfOem;

public interface IConfOemDao extends CrudRepository<ConfOem, BigInteger>, JpaSpecificationExecutor<ConfOem>{

}