package com.cummins.scd.models.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.ElectronicTools;

public interface IElectronicToolsDao extends CrudRepository<ElectronicTools, String>, JpaSpecificationExecutor<ElectronicTools>{

}