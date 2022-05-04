package com.cummins.scd.models.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Region;

public interface IRegionDao extends CrudRepository<Region, BigInteger>, JpaSpecificationExecutor<Region>{

	List<Region> findAllByDel(Character ch);

	List<Region> findAllByDelOrderByRegionAsc(Character ch);
}