package com.cummins.scd.models.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cummins.scd.models.entity.NoDisponible;

public interface INoDisponibleDao extends CrudRepository<NoDisponible, String>, JpaSpecificationExecutor<NoDisponible>, PagingAndSortingRepository<NoDisponible, String> {

	

   

}