
package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ConfMotor;

public interface IConfMotorDao extends CrudRepository<ConfMotor, BigInteger>, JpaSpecificationExecutor<ConfMotor>{

}