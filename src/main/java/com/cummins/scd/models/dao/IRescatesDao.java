package com.cummins.scd.models.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Rescates;

public interface IRescatesDao extends CrudRepository<Rescates, String>, JpaSpecificationExecutor<Rescates>{

}