package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.QSol;

public interface IQsolService {

	Object save(String wwid, Object entity);

	List<QSol> getQsolByIdEv(String id);

	List<QSol> deleteAll(String id, String wwid);

	List<QSol> update(Object entity, String wwid);

}
