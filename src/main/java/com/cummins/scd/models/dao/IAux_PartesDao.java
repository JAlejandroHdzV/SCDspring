package com.cummins.scd.models.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Partes;

public interface IAux_PartesDao extends CrudRepository<Aux_Partes, String>, JpaSpecificationExecutor<Aux_Partes>{

}