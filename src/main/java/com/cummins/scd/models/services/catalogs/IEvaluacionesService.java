package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.dto.EvaluacionesDTO;
import com.cummins.scd.models.entity.Evaluaciones;

public interface IEvaluacionesService {
	

	List<EvaluacionesDTO> findEvaluacionesByCriteria(Object entityFilter, String idioma);

	List<EvaluacionesDTO> findEv(String idioma);


	List<EvaluacionesDTO> findEvaluationsByCatalog(String catalog, String lang);

	List<Evaluaciones> listEvByRegion(String Regiones);

	//List<EvaluacionesDTO> findEvaluationsByCatalog(String catalog, String lang, Boolean flag);

}
