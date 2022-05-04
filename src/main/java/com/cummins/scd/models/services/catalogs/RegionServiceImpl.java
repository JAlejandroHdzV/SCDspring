package com.cummins.scd.models.services.catalogs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IRegionDao;
import com.cummins.scd.models.dao.ISpCodeDao;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	IRegionDao regionDao;
	@Autowired
	ILoadInfoService loadInfo;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Region> regionBySpcodesAndWwid(String wwid){
		
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Region> getAllRegionsByDel(){
		List<Region> reg= getRegionsByDelN();
		return reg;
	}
	
	@Transactional(readOnly = true)
	public List<Region> getRegionsByDelN(){
		List<Region> listRegion= regionDao.findAllByDelOrderByRegionAsc('N');
		return listRegion;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Region> getRegionsByWwid(String wwid){
		System.out.println("WWID IN REGIONS: "+wwid);
		List<Region> listRegion= (List<Region> ) em.createQuery(
				" SELECT D FROM Region D WHERE D.del='N' And D.idRegion IN "
				+ "("
						+ " SELECT A.idRegion FROM CountryPerRegion A WHERE A.del='N' And A.idPais IN "
							+ "("
								+ " SELECT B.idPais FROM SpCodes B WHERE B.del='N' And B.spCode IN "
									+ "("
										+ " SELECT C.spCode from UsersSpCodes C WHERE C.del='N' And C.wwid = '"+wwid.toUpperCase()+"'"
									+ ")"
							+ ")"
				 + ")").getResultList();
		return listRegion;
	}
}
