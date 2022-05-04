package com.cummins.scd.models.dao.auxiliar.tables;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Aux_Htas;

public interface IAux_HtasDao extends CrudRepository<Aux_Htas, String>, JpaSpecificationExecutor<Aux_Htas> {

}
