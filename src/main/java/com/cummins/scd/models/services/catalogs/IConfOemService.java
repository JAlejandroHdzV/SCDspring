package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.ConfOem;

public interface IConfOemService {

	List<ConfOem> findConfOemByCriteria(Object entityFilter);

	ConfOem save(ConfOem oem);

	List<ConfOem> find(String Id);

	List<ConfOem> validaMotorOem(String Idoem, String IdEv, String Ns, String MotorName, String RangoName, String appName,
			String Lang);

}
