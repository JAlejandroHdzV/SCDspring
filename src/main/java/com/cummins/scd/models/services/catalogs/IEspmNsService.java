package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.dto.EspmNsDTO;
import com.cummins.scd.models.entity.ESPM_NS;

public interface IEspmNsService {

	List<EspmNsDTO> findESPMNSByCriteria(Object entityFilter, String idioma);

	List<ESPM_NS> espmNs(String motor, String rango, String aplicacion, String ns, String lang);

}
