package com.cummins.scd.models.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.LicenciasQsol;

public interface ILicenciasQsolDao extends CrudRepository<LicenciasQsol, String>, JpaSpecificationExecutor<LicenciasQsol>{

}