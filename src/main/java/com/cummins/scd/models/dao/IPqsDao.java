package com.cummins.scd.models.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Pqs;

public interface IPqsDao extends CrudRepository<Pqs, String>, JpaSpecificationExecutor<Pqs>{

}