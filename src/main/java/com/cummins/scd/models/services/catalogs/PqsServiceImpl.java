package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IPqsDao;
import com.cummins.scd.models.dao.IPqsXpuestosDao;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.PqsXpuestos;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PqsServiceImpl implements IPqsService {
	@Autowired
	IPqsDao pqsDAO;

	@Autowired
	IPqsXpuestosDao pqsXpuestosDAO;

	private ObjectMapper mapper = new ObjectMapper();

	@PersistenceContext
	private EntityManager em;

	// ---------------------------------------------------------------------------
	// 									PQS BY ID
	// ---------------------------------------------------------------------------

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Pqs> pqsById(String id) {
		List<Pqs> listPqs = (List<Pqs>) em.createQuery("SELECT c from Pqs c where c.programId='" + id + "'")
				.getResultList();
		return listPqs;
	}

	// ---------------------------------------------------------------------------
	// 								PQSXPUESTOS BY ID
	// ---------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<PqsXpuestos> PqsXpuestosByPqsId(String id) {
		List<PqsXpuestos> listPqs = (List<PqsXpuestos>) em
				.createQuery("SELECT c from PqsXpuestos c where c.programId='" + id + "'").getResultList();
		return listPqs;
	}

	// ---------------------------------------------------------------------------
	// 							DELETE PQS AND PQSXPUESTOS BY ID
	// ---------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Object deletePqsById(String id, String wwid) {
		System.out.println("ENTRA A DELETE PQS");
		Date date = new Date();
		List<Pqs> pqsExist = pqsById(id);
		System.out.println(pqsExist);
		Pqs pqsDel = new Pqs();
		if (pqsExist.size() > 0) {
			
				pqsDel.setCreatedBy(pqsExist.get(0).getCreatedBy());
				pqsDel.setCreationDate(pqsExist.get(0).getCreationDate());
				pqsDel.setId(pqsExist.get(0).getId());
				pqsDel.setNombre(pqsExist.get(0).getNombre());
				pqsDel.setDel('Y');
				pqsDel.setLastUpdateDate(date);
				pqsDel.setLastUpdatedBy(wwid);
				pqsDel.setPuestos(pqsExist.get(0).getPuestos());
			//pqsDAO.save(pqsDel);
			List<PqsXpuestos> pqsXpuestosExist = PqsXpuestosByPqsId(id);
			System.out.println("pqsxpuestos: "+pqsXpuestosExist);
			List<PqsXpuestos> responsePqsXpuestos = new ArrayList<PqsXpuestos>();
			
			Map<String, Object> response = new HashMap<>();
			for (PqsXpuestos pqsxp : pqsXpuestosExist) {
				PqsXpuestos newPqsxp= new PqsXpuestos();
							 newPqsxp.setCreatedBy(pqsxp.getCreatedBy());
							 newPqsxp.setCreationDate(pqsxp.getCreationDate());
							 newPqsxp.setIdProgram(pqsxp.getIdProgram());
							 newPqsxp.setIdPuesto(pqsxp.getIdPuesto());
							 newPqsxp.setDel('Y');
							 newPqsxp.setLastUpdateDate(date);
							 newPqsxp.setLastUpdatedBy(wwid);
							 System.out.println(newPqsxp);
				responsePqsXpuestos.add(pqsXpuestosDAO.save(newPqsxp));

			}
			//response.put("pqs", pqsDel);
			//response.put("pqsXpuestos", responsePqsXpuestos);
			System.out.println("La respuesta es: "+pqsDel);
			return pqsDel;

		} else {
			return null;
		}
	}

}
