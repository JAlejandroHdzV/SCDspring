package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dao.ISpCodeDao;
import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.dto.SpCodesDTO;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SpCodesService implements ISpCodesService {

	@Autowired
	ISpCodeDao spCodeDao;
	@Autowired
	ILoadInfoService loadInfo;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	//-----------------------------------------------------------------------
	//						GET SP CODE BY ID
	//-----------------------------------------------------------------------
	/*Return SPCODES by ID and DEL = 'N'*/
	@Override
	@Transactional(readOnly = true)
	public List<SpCodes> getSpCodeById( String id,BigInteger tipo) {
		return spCodeDao.findBySpCodeAndDelAndIdTipo(id, 'N',tipo);
	}
	
	//modulo productos por eval
	
		//-----------------------------------------------------------------------
		//			SPCODES VALIDOS   MODULE.- EVALUATION PRODUCTS
		//----------------------------------------------------------------------

		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		public List<SpCodesDTO> spCodesByWwid( String region, String wwid) {
			System.out.println("region: "+region+" wwid: "+wwid);
			List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
					"SELECT c from SpCodes c where c.del='N' And "
						+ " c.idTipo = '1' And "
						+ " c.idPais in (SELECT B.idPais from CountryPerRegion B "
										+ "Where B.idRegion in ('"+region+"') And B.del='N') "
						+ " And c.responsibleBranchCode in (SELECT d.spCode from UsersSpCodes d "
										+ " Where d.wwid='"+wwid+"' And d.del='N' )"
										+"ORDER BY c.spCode").getResultList();
			
			List<SpCodesDTO> listResponse= listSpcodesJoinSpcodes(listSpCodes);
			
			return listResponse;
			
			
		}
	
	
	
	/*
	 * 
	 *
	 
	 SELECT  A.SP_CODE, A.SERVICE_PROVIDER_NAME
FROM    ZMKT.ZMKT7352_DES_CAT_SPCODES A
WHERE   A.DELETE_IND = 'N'
        AND A.ID_TIPO = 1
        AND A.ID_PAIS IN
        (
            SELECT  C.ID_PAIS
            FROM    ZMKT.ZMKT7352_DES_CAT_PXR C
            WHERE   C.DELETE_IND = 'N'
                    AND C.ID_REGION = :p_id_region
        )
        AND A.RESPONSIBLE_BRANCH_CODE IN
        (
            SELECT  U.SP_CODE
            FROM    ZMKT.ZMKT7352_DES_USERS_SPCODES U
            WHERE   U.DELETE_IND = 'N'
                    AND U.WWID = TRIM(UPPER(:p_wwid))
        )
ORDER BY TO_NUMBER(A.SP_CODE)
*/
	
	
	
	//-----------------------------------------------------------------------
	//			SPCODES VALIDOS   MODULE.- EVALUATION PRODUCTS
	//----------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String> spCodesValidos( String region) {
		List<String> listSpCodes=(List<String> ) em.createQuery(
				"SELECT c.spCode from SpCodes c where c.del='N' And "
					+ " c.idTipo = '1' And "
					+ " c.idPais in (SELECT B.idPais from CountryPerRegion B "
									+ "Where B.idRegion='"+region+"' And B.del='N')"
									+"ORDER BY c.spCode").getResultList();
		
		/*
		 * SELECT A.SP_CODE
FROM    ZMKT.ZMKT7352_DES_CAT_SPCODES A
WHERE   A.DELETE_IND = 'N'
        AND A.ID_TIPO = 1
        AND A.ID_PAIS IN (SELECT B.ID_PAIS FROM ZMKT.ZMKT7352_DES_CAT_PXR B WHERE B.ID_REGION = :p_region AND B.DELETE_IND = 'N')

		 * 
		 * 
		 * 
		 * SELECT  A.SP_CODE, A.SERVICE_PROVIDER_NAME
FROM    ZMKT.ZMKT7352_DES_CAT_SPCODES A
WHERE   A.DELETE_IND = 'N'
        AND (A.ID_TIPO = 1 OR A.ID_TIPO = 2) 
        AND A.RESPONSIBLE_BRANCH_CODE = TRIM(UPPER(:p_sp_code))
ORDER BY TO_NUMBER(A.SP_CODE)

		 * */
		
		return listSpCodes;
		
		
	}
	
	//-----------------------------------------------------------------------
		//			SPCODES BY RESPONSIBLE_BRANCH_CODE
		//----------------------------------------------------------------------
		@Override
		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		public List<SpCodesDTO> spCodesByResponsibleBranchCode( String responsible) {
			List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
					"SELECT c from SpCodes c where c.del='N' And "
						+ " (c.idTipo = '1' or c.idTipo = '2') And "
						+ " c.responsibleBranchCode='"+responsible+"'"
						+" ORDER BY c.spCode").getResultList();
			
			List<SpCodesDTO> sp= listSpcodesJoinSpcodes(listSpCodes);
			
			/*
			
			 * 
			 * SELECT  A.SP_CODE, A.SERVICE_PROVIDER_NAME
	FROM    ZMKT.ZMKT7352_DES_CAT_SPCODES A
	WHERE   A.DELETE_IND = 'N'
	        AND (A.ID_TIPO = 1 OR A.ID_TIPO = 2) 
	        AND A.RESPONSIBLE_BRANCH_CODE = TRIM(UPPER(:p_sp_code))
	ORDER BY TO_NUMBER(A.SP_CODE)

			 * */
			
			return sp;
			
			
		}
	
	
	

	
	//---------------------------------------------------------------------------
	//							SPCODES BY REGIONS
	//---------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String> regionPorSpCodes( String spcodes) {
		List<String> listSpCodes=(List<String> ) em.createQuery(
				"SELECT c.idPais from SpCodes c where c.del='N' And "
					+ " c.idTipo = '1' And "
					+ " c.spCode in ("+spcodes+") And"
					+ " c.idPais in (SELECT B.idPais from CountryPerRegion B "
									+ "Where  B.del='N')").getResultList();
		
		/*List<String> Region=(List<String> ) em.createQuery(
				"SELECT B.idPais from CountryPerRegion B "
									+ "Where  B.del='N')").getResultList();*/
		
		return listSpCodes;
	}
	
	//----------------------------------------------------------
	//					GET SPCODES BY REGION
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<SpCodesDTO> getSpCodesByRegion(String IdRegion){
		List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
				"SELECT c from SpCodes c where c.del='N' And "
						+ " c.idTipo = '1' And "
						+ " c.idPais in (SELECT B.idPais from CountryPerRegion B "
										+ "Where B.idRegion IN ('"+IdRegion+"') And B.del='N')"
										+"ORDER BY c.spCode").getResultList()  ;
		List<SpCodesDTO> listResponse=listSpcodesJoinSpcodes(listSpCodes);
		return  listResponse;
	}
	
	//----------------------------------------------------------
		//					GET SPCODES BY REGION
		//----------------------------------------------------------
		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		@Override
		public List<SpCodesDTO> getDrSpCodesByRegion(String IdRegion){
			List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
					"SELECT c from SpCodes c where c.del='N' And "
							+ " c.idTipo = '2' And "
							+ " c.idPais in (SELECT B.idPais from CountryPerRegion B "
											+ "Where B.idRegion IN ('"+IdRegion+"') And B.del='N')"
											+"ORDER BY c.spCode").getResultList()  ;
			List<SpCodesDTO> listResponse=listSpcodesJoinSpcodes(listSpCodes);
			return  listResponse;
		}
	//------------------------------------------------------------------------------
	//			    QUERY TO GET SPCODES JOIN RESPONSIBLE BRANCH NAME BY LIST
	//------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<SpCodesDTO> spCodesJoinSpCodes() {
		List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
				"SELECT c from SpCodes c where c.del='N')").getResultList();
		List<SpCodesDTO> listSpCodesDto= listSpcodesJoinSpcodes(listSpCodes);
		return listSpCodesDto;
	}
	
	
	//-------------------------------------------------------------------
	//			    QUERY TO GET RESPONSIBLE BRANCH NAME
	//-------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<SpCodesDTO> spCodesJoinSpCodesbyId(String Id) {
		List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
				"SELECT c from SpCodes c where c.del='N' and c.spCode='"+Id+"')").getResultList();
		List<SpCodesDTO> listSpCodesDto= listSpcodesJoinSpcodes(listSpCodes);
		return listSpCodesDto;
	}
	
	//-------------------------------------------------------------------
	//			    GET SPCODES BY LIST DIST
	//-------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<SpCodesDTO> spCodesByListDist(String listDist) {
		System.out.println("Entra a buscar lista de spcodes por distribuidor: "+ listDist);
		System.out.println();
		String []arr=listDist.split(",");
		List<String>arrFinal=new ArrayList<>();
		if(listDist.split(",").length<2) {
			listDist="'"+listDist+"'";
		}
		
		for(String ar:arr) {
			String item="'"+ar+"'";
			arrFinal.add(item);
		}
		listDist=String.join(", ", arrFinal);
		System.out.println("Lista de Distribuidores: "+ listDist);
		List<SpCodes> listSpCodes=(List<SpCodes> ) em.createQuery(
				"SELECT c from SpCodes c where c.del='N' and c.responsibleBranchCode in("+listDist+")").getResultList();
		List<SpCodesDTO> listSpCodesDto= listSpcodesJoinSpcodes(listSpCodes);
		return listSpCodesDto;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<SpCodesDTO> listSpcodesJoinSpcodes(List<SpCodes> spCodes ){
		List<SpCodesDTO> listCP= new ArrayList<SpCodesDTO>();
		for(SpCodes sp: spCodes) {
			SpCodesDTO spDto= new SpCodesDTO();
			spDto.setIdOem(sp.getIdOem());
			spDto.setIdPais(sp.getIdPais());
			spDto.setIdTipo(sp.getTipo());
			spDto.setProviderName(sp.getProviderName());
			spDto.setResponsibleBranchCode(sp.getResponsibleBranchCode());
			List<SpCodes> responsibleName=(List<SpCodes> ) em.createQuery(
					"SELECT c from SpCodes c where c.del='N' and c.spCode='"+sp.getResponsibleBranchCode()+"' )").getResultList();
			spDto.setResponsibleBranchName(responsibleName.size()>0? responsibleName.get(0).getProviderName():"");
			spDto.setSpCode(sp.getSpCode());
			listCP.add(spDto);
		}
		return listCP;
	}
	
}
