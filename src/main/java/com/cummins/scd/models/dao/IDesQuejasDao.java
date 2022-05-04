package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Des_Quejas;


public interface IDesQuejasDao extends CrudRepository<Des_Quejas, BigInteger>, JpaSpecificationExecutor<Des_Quejas> {

}
