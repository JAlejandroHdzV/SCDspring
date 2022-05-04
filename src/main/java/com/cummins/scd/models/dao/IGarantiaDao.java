package com.cummins.scd.models.dao;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cummins.scd.models.entity.Garantia;

public interface IGarantiaDao extends CrudRepository<Garantia, String>, JpaSpecificationExecutor<Garantia> {
}