package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.dto.ConfMotorDTO;
import com.cummins.scd.models.dto.ConfMotorDTO2;
import com.cummins.scd.models.entity.ConfMotor;

public interface IConfMotorService {


	//List<ConfMotorDTO> confMotor();

	//List<ConfMotorDTO> confMotor(String id);

	Object save(Object entity, String wwid);

	Object delete(String id, String wwid);

	Object update(Object entity, String wwid);

	List<ConfMotorDTO2> confMotor2(String id);

	List<ConfMotor> getConfMotor(String IdEv);

	List<ConfMotor> getConfMotorByNsEspmEV(String idNs, String idESPM, String IdEv);

	List<ConfMotorDTO> confMotor(String id, String lang);

}
