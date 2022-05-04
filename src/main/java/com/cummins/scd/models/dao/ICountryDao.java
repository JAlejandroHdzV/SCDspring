package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Country;

public interface ICountryDao extends CrudRepository<Country, BigInteger>, JpaSpecificationExecutor<Country>{

}