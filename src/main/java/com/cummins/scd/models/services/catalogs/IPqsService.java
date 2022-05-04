package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.PqsXpuestos;

public interface IPqsService {

	List<PqsXpuestos> PqsXpuestosByPqsId(String id);

	List<Pqs> pqsById(String id);

	Object deletePqsById(String id, String wwid);


}
