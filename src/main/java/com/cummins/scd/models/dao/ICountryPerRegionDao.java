package com.cummins.scd.models.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.CountryPerRegion;

public interface ICountryPerRegionDao extends CrudRepository<CountryPerRegion, BigInteger>, JpaSpecificationExecutor<CountryPerRegion>{

}