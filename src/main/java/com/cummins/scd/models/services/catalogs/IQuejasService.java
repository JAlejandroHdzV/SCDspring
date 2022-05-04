package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.dto.CatQuejasDTO;
import com.cummins.scd.models.entity.Quejas;

public interface IQuejasService {

	Object save(String wwid, Object entity);

	Quejas delete(String id, String wwid);

	Quejas update(Quejas entity, String wwid);

	List<Quejas> getQuejasByDel(String id);

	List<Quejas> deleteAll(String id, String wwid);

	List<CatQuejasDTO> getByEvAndRegion(String listRegion, String lang);

}
