package com.cummins.scd.models.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_CC;

public interface IAux_CCDao extends CrudRepository<Aux_CC, String>, JpaSpecificationExecutor<Aux_CC>{

}