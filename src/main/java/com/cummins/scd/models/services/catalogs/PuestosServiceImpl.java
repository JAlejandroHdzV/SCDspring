package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IAux_PuestosDao;
import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.entity.Aux_Puestos;
import com.cummins.scd.models.entity.Aux_RelPuestos;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PuestosServiceImpl implements IPuestosService {
	@Autowired
	IAux_PuestosDao puestosDao;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Object puestosActivos() {
		List<String> listPuestos=(List<String> ) em.createQuery(
				"SELECT Distinct c.titulo from Aux_RelPuestos c  where c.del='N' order by c.titulo ").getResultList();
		/*List<Map<String, Object>> response = new ArrayList<Map<String,Object>>();
		for(String str: listPuestos) {
			Map<String, Object> res = new HashMap<>();
			res.put("puesto", str);
			response.add(res);
		}*/
		Map<String, Object> response = new HashMap<>();
		response.put("puestos", listPuestos);
		return response;
		
	}

}
