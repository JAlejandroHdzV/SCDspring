package com.cummins.scd.models.services.catalogs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cummins.scd.models.dto.EspmDTO;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.MotorProducts;

public interface IEspmService {

	//Object findAll();

	List<EspmDTO> findAll(String idioma);

	List<EspmDTO> findESPMByCriteria(Object entityFilter, String idioma);

	List<ESPM> todosESPM();

	List<ESPM> espmxregion();


	Object delete(String id, String wwid);

	Object save(Object entity, String wwid, String id);

	Object update(Object entity, String wwid);

	List<MotorProducts> EspmJoinMotorProductsName(String Id);

	
}
