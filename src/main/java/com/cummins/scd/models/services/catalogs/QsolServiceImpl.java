package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
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

import com.cummins.scd.models.dao.IQSolDao;
import com.cummins.scd.models.entity.QSol;
import com.cummins.scd.models.entity.Quejas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QsolServiceImpl implements IQsolService {
	@Autowired
	IQSolDao qsolDao;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
		//----------------------------------------------------------
		//					SAVE RECORD QSOL BY LIST
		//----------------------------------------------------------
		@Override
		public Object save(String wwid, Object entity)
		{ 
			System.out.println("La entidad es:"+entity);
			JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
			System.out.println(actualObj1);
			Date date = new Date();
			List<QSol> listaQSol = new ArrayList<QSol>();
			String id=actualObj1.get(0).get("idEvaluacion").toString().replaceAll("\"", "");
			List<QSol>ListaQsolExist=getQsolByIdEv(id);
			System.out.println("Existe la lista: "+ListaQsolExist);
			if(ListaQsolExist.size()<1) {
			for (Object q : actualObj1) {
				QSol qsol= new QSol();
				qsol = mapper.convertValue(q, QSol.class);
				qsol.setDel('N');
				qsol.setCreationDate(date);
				qsol.setCreatedBy(wwid);
			 	qsol.setLastUpdateDate(date);
			 	qsol.setLastUpdatedBy(wwid);
				listaQSol.add(qsol);
				qsolDao.save(qsol);	
			}
			return listaQSol;
			}else {
				System.out.println("Ya existe una lista guardada");
				return "1";
			}
		
		}
		
		//---------------------------------------------------------------------
		//					GET ALL QSOL BY DEL N AND EVALUACION
		//--------------------------------------------------------------------
		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		@Override
		public List<QSol> getQsolByIdEv(String id){
			List<QSol> listQuejas=(List<QSol> ) em.createQuery(
					"SELECT c from QSol c where c.del='N' AND c.idEvaluacion= " + id ).getResultList();
			return  listQuejas;
		}
		
		//---------------------------------------------------------------------
		//					DELETE ALL QSOL BY EVALUACION ID
		//--------------------------------------------------------------------
		@Override
		public List<QSol> deleteAll(String id, String wwid) {
			List<QSol>ListaQsol=getQsolByIdEv(id);
			System.out.println(ListaQsol);
			List<QSol>UpdatedList = new ArrayList<QSol>();
			Date date = new Date();
			for(QSol q: ListaQsol)
			{
				QSol qsol= new QSol();
				qsol=mapper.convertValue(q, QSol.class);
				qsol.setDel('Y');
				qsol.setLastUpdateDate(date);
				qsol.setLastUpdatedBy(wwid);
				UpdatedList.add( qsolDao.save(qsol));
			}
			return UpdatedList;
		}
		
		//---------------------------------------------------------------------
		//					UPDATE QSOL
		//--------------------------------------------------------------------
		@Override
		public List<QSol> update(Object entity, String wwid) {
		System.out.println(entity);
		 	Date date = new Date();
		 	List <QSol> qsolList= new ArrayList<QSol>();
		 	JsonNode object= mapper.convertValue(entity, JsonNode.class);
		 	String id=object.get(0).get("idEvaluacion").toString().replaceAll("\"", "");
		 	deleteAll(id, wwid);
		 	for (JsonNode jn: object) {
		 		QSol q= mapper.convertValue(jn, QSol.class);
		 		if(q.getIdAskqsol()!=null) {
		 		QSol exist=qsolDao.findOne(q.getIdAskqsol());
		 		
		 		if(exist!= null) {
		 			q.setCreatedBy(exist.getCreatedBy());
		 			q.setCreationDate(exist.getCreationDate());
		 		}else {
		 			q.setCreationDate(date);
		 			q.setCreatedBy(wwid);
		 			
		 		}
		 		}else {
		 			q.setCreationDate(date);
		 			q.setCreatedBy(wwid);
		 			
		 		}
		 		q.setDel('N');
		 		q.setLastUpdateDate(date);
		 		q.setLastUpdatedBy(wwid);
		 		qsolList.add(qsolDao.save(q));
		 	}
			return qsolList;
		
		}
}
