package com.cummins.scd.models.services.catalogs;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.entity.MatrizPartesHd;


public interface IMatrizPartesHdService {

	List<MatrizPartesHd> findMatrizPartesHdByCriteria(Object entityFilter);

	List<MatrizPartesHd> partsMatrixExistConf(Object entityFilter, String idioma);


	List<MatrizDTO> matricesValidas(Object entityFilter);

	List<MatrizPartesHd> matricesValidasCargaMasiva(String ns, String dist, String mod, String oem, String matriz);

}
