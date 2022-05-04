package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatrizHtasService implements IMatrizHtasService {
	@Autowired
	IMatrizPartesHdDao matrizHtasHdDao;
	@Autowired
	ILoadInfoService loadInfo;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MatrizHtasHd> matricesValidasCargaMasiva(String ns, String dist, String mod, String oem, String matriz) {
		System.out.println("ns: "+ns+"mod: "+mod+"oem: "+oem+" matriz: "+matriz+" dist: "+dist);
		List<MatrizHtasHd> listMatriz=(List<MatrizHtasHd> ) em.createQuery(
				"SELECT c from MatrizHtasHd c  "
						+ "where c.del='N' And "
						+ " c.nombre= '"+matriz+"' And "
						+ " c.idStatus = '1' "
						+"And c.idNs= (SELECT e.idServiceLevel FROM ServiceLevel e where e.serviceLevel='"+ns+"')"
							+ "And c.idMatriz in"
							+ "(select d.idMatriz from AtributosMatricesHtas d "
							+ "where d.tipoMatriz='2' And d.tipoAtributo='1' And d.idText in ("+dist+") ) "
								+ "And c.idMatriz in"
								+ "(select d.idMatriz from AtributosMatricesHtas d "
								+ "where d.tipoMatriz='2' And d.tipoAtributo='2' And d.idNumber in ("+mod+") ) "
									+ "And c.idMatriz in"
									+ "(select d.idMatriz from AtributosMatricesHtas d "
									+ "where d.tipoMatriz='2' And d.tipoAtributo='3' And d.idNumber in ("+oem+") ) "
										+ "").getResultList();
		
		return listMatriz;
		
	}
	
	
	
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MatrizDTO> matricesValidas(Object entityFilter) {
		try {
		JsonNode actualObj1 = null;
		actualObj1 = mapper.convertValue(entityFilter, JsonNode.class);
		//String tipoMatriz=actualObj1.get("tipoMatriz").toString().replaceAll("\"", "");
		String ns=actualObj1.get("ns").toString().replaceAll("\"", "");
		String dist=(actualObj1.get("distribuidor").toString().replaceAll("\"", ""));
		System.out.println(dist);
        String mod=(actualObj1.get("modProductos").toString().replaceAll("\"", ""));
        System.out.println(mod);
        String oem=(actualObj1.get("oems").toString().replaceAll("\"", ""));
        System.out.println(oem);
		List<MatrizHtasHd> listMatriz=(List<MatrizHtasHd> ) em.createQuery(
				"SELECT c from MatrizHtasHd c  "
						+ "where c.del='N' And "
						+ " c.idStatus = '1' "
						+"And c.idNs='"+ns+"'"
							+ "And c.idMatriz in"
							+ "(select d.idMatriz from AtributosMatricesHtas d "
							+ "where d.tipoMatriz='2' And d.tipoAtributo='1' And d.idText in ("+dist+") ) "
								+ "And c.idMatriz in"
								+ "(select d.idMatriz from AtributosMatricesHtas d "
								+ "where d.tipoMatriz='2' And d.tipoAtributo='2' And d.idNumber in ("+mod+") ) "
									+ "And c.idMatriz in"
									+ "(select d.idMatriz from AtributosMatricesHtas d "
									+ "where d.tipoMatriz='2' And d.tipoAtributo='3' And d.idNumber in ("+oem+") ) "
										+ "").getResultList();
		List<MatrizDTO> response= new ArrayList<>();
		 for(MatrizHtasHd matriz: listMatriz) {
			 MatrizDTO newMatriz= new MatrizDTO();
			 newMatriz.setIdMatriz(matriz.getIdMatriz().toString());
			 newMatriz.setNombre(matriz.getNombre());
			 newMatriz.setNoRevision(matriz.getNombre());
			 response.add(newMatriz);
		 }
		
		
		return response;
		}catch(Exception e) {
			return null;
		}
	}
}
