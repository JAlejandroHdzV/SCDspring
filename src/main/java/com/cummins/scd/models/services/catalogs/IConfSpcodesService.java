package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.ConfSpcodes;

public interface IConfSpcodesService {

	List<ConfSpcodes> findConfSpcodesByCriteria(Object entityFilter);

	ConfSpcodes save(ConfSpcodes sp);

	List<ConfSpcodes> find(String Id);

	List<ConfSpcodes> validaMotorDistribuidor(String spcode, String IdEv, String Ns, String MotorName, String RangoName,
			String appName, String Lang);

}
