package com.cummins.scd.models.dao;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import com.cummins.scd.models.entity.Quejas;

public interface IQuejasDao extends CrudRepository<Quejas, BigInteger>, JpaSpecificationExecutor<Quejas>{

}