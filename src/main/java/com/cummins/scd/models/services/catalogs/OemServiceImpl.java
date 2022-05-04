package com.cummins.scd.models.services.catalogs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dao.IOemsDao;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OemServiceImpl implements IOemService {
	@Autowired
	IOemsDao oemsDao;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Oems> OemById(String oem) {
		List<Oems> listOem=(List<Oems> ) em.createQuery("SELECT c from Oems c where c.oem='"+oem+"' ").getResultList();
		
		return listOem;
		
	}
	
}
