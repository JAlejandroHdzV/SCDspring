package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.entity.MatrizHtasHd;

public interface IMatrizHtasService {

	List<MatrizDTO> matricesValidas(Object entityFilter);


	List<MatrizHtasHd> matricesValidasCargaMasiva(String ns, String dist, String mod, String oem, String matriz);

}
