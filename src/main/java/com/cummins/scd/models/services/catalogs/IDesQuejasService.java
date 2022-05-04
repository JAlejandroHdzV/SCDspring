package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import com.cummins.scd.models.dto.QuejasDTO;
import com.cummins.scd.models.entity.Des_Quejas;

public interface IDesQuejasService {
	
	Des_Quejas getQuejasById(BigInteger id);
	
	Des_Quejas save(String wwid,Des_Quejas entity);
	
	Des_Quejas update(String wwid,Des_Quejas entity);

	//List<Des_Quejas> getAllQuejas(String wwid);

	List<QuejasDTO> getAllQuejas(String wwid, String lang);

	//List<QuejasDTO> getQuejasByList(String lang, String listQjas);

	List<QuejasDTO> getQuejasBySpCodes(String lang, String listSpCodes);

	Boolean deleteQuejaById(BigInteger id);

	QuejasDTO getQuejaDtoById(BigInteger id, String lang);


	List<QuejasDTO> getQuejasByList(String lang, String listQjas, String listDist, String listSpCodes,
			String listRegions, String wwid);

}
