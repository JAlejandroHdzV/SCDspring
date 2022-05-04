package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.List;

import com.cummins.scd.models.dto.SpCodesDTO;
import com.cummins.scd.models.entity.SpCodes;

public interface ISpCodesService {

	List<String> spCodesValidos(String region);


	List<SpCodesDTO> spCodesJoinSpCodes();

	List<SpCodesDTO> spCodesJoinSpCodesbyId(String Id);



	List<String> regionPorSpCodes(String spcodes);


	List<SpCodesDTO> getSpCodesByRegion(String IdRegion);


	List<SpCodesDTO> spCodesByResponsibleBranchCode(String responsible);


	List<SpCodesDTO> spCodesByWwid(String region, String wwid);


	List<SpCodes> getSpCodeById(String id, BigInteger tipo);


	List<SpCodesDTO> spCodesByListDist(String listDist);


	List<SpCodesDTO> getDrSpCodesByRegion(String IdRegion);


}
