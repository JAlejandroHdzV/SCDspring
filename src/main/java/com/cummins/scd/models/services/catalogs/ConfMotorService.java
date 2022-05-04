package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IConfMotorDao;
import com.cummins.scd.models.dao.IConfOemDao;
import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dao.IOemsDao;
import com.cummins.scd.models.dto.ConfMotorDTO;
import com.cummins.scd.models.dto.ConfMotorDTO2;
import com.cummins.scd.models.dto.ConfMotorOemsSpDTO;
import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.dto.SpCodesDTO;
import com.cummins.scd.models.entity.ConfEval;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;

@Service
public class ConfMotorService implements IConfMotorService {
	@Autowired
	IConfMotorDao confMotorDao;
	
	@Autowired
	IConfOemService oemService;
	
	@Autowired
	IEspmService espmService;
	
	@Autowired
	IConfSpcodesService spcodesService;
	@Autowired
	ILoadInfoService loadInfo;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	public ConfMotor getConfByPerfil(String idEvaluacion)
	{
		ConfMotor confMotor=(ConfMotor) em.createQuery(
				"SELECT cm from ConfMotor cm  "
						+ "where cm.del='N' And "
						+ " cm.idEvaluacion= '"+ idEvaluacion + "'"
						+"And cm.idNs= (SELECT sl.idServiceLevel FROM ServiceLevel sl where sl.del='N'"
						+ "AND trim(upper(sl.serviceLevel))=trim(upper())"
							
							+ "And c.idMatriz in"
							+ "(select d.idMatriz from AtributosMatricesHtas d "
							+ "where d.tipoMatriz='2' And d.tipoAtributo='1' And d.idText in ("+") ) "
								+ "And c.idMatriz in"
								+ "(select d.idMatriz from AtributosMatricesHtas d "
								+ "where d.tipoMatriz='2' And d.tipoAtributo='2' And d.idNumber in ("+") ) "
									+ "And c.idMatriz in"
									+ "(select d.idMatriz from AtributosMatricesHtas d "
									+ "where d.tipoMatriz='2' And d.tipoAtributo='3' And d.idNumber in ("+") ) "
										+ "").getSingleResult();
		
		return confMotor;
		
	}
	    //--------------------------------------------------------------------------
		//	GET LIST CONF MOTOR By ID EVALUACION
		//--------------------------------------------------------------------------
		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		@Override
		public List<ConfMotor> getConfMotor(String IdEv){
			List<ConfMotor> listConfMotor=(List<ConfMotor> ) em.createQuery(
					"SELECT  CF1  FROM  ConfMotor CF1"
					+ " WHERE   CF1.del = 'N'"
					+ "	AND CF1.idEvaluacion = "  + IdEv).getResultList();
			return  listConfMotor;
		}
	    //--------------------------------------------------------------------------
		//	GET LIST CONF MOTOR By IdNs, IdESPM, idEv
		//--------------------------------------------------------------------------
		@Transactional(readOnly = true)
		@SuppressWarnings("unchecked")
		@Override
		public List<ConfMotor> getConfMotorByNsEspmEV(String idNs,String idESPM,String IdEv){
			List<ConfMotor> listConfMotor=(List<ConfMotor> ) em.createQuery(
					"SELECT  CF1  FROM  ConfMotor CF1"
					+ " WHERE   CF1.del = 'N'"
					+ " AND CF1.idNs = '" + idNs + "'"
					+ " AND CF1.idEspmotor = '" + idESPM + "'"
					+ "	AND CF1.idEvaluacion = "  + IdEv).getResultList();
			return  listConfMotor;
		}
	/*private BigInteger idEspmotor;
    

    private BigInteger idNs;
    private BigInteger idMatrizPartes;
    private BigInteger idMatrizHtas;
    private BigInteger idEvaluacion;*/
	
	
	
	/*SELECT  CM.ID_CONF_MOTOR
FROM    ZMKT.ZMKT7352_DES_CONF_MOTOR CM    *************************************** confMotor
WHERE   CM.DELETE_IND = 'N'
        AND CM.ID_EVALUACION = :p_id_evaluacion
        AND ID_NS = 
        (
            SELECT  NS.ID_NS
            FROM    ZMKT.ZMKT7352_DES_CAT_NSERVICIO NS      **********************  serviceLevel
            WHERE   NS.DELETE_IND = 'N'
                    AND trim(upper(NS.NSERVICIO)) = trim(upper(:p_columna_e))
        )
        AND CM.ID_ESPMOTOR =          
(
SELECT  ESPM.ID_ESPMOTOR
FROM    ZMKT.ZMKT7352_DES_ESPM ESPM      ****************************************    ESPM
WHERE   ESPM.DELETE_IND = 'N'
        AND ESPM.ID_MOTOR =
(
SELECT  M.ID
FROM    ZMKT.ZMKT7352_DES_CAT_MOTORES M *****************************************    Motor products
WHERE   M.DELETE_IND = 'N'
        AND M.NOMBRE = :p_columna_b
)        
AND ESPM.ID_RANGO =
(
     
SELECT  R.ID_RANGO
FROM    ZMKT.ZMKT7352_DES_CAT_RANGO R   *******************************************   Ranks
WHERE   R.DELETE_IND = 'N'
        AND R.RANGO = :p_columna_c        
)
AND ESPM.ID_APP = 
(        
        SELECT  A.ID_APP
        FROM    ZMKT.ZMKT7352_DES_CAT_APLICACION A   *******************************  Application
        WHERE   A.DELETE_IND = 'N'
                AND A.APLICACION = :p_columna_d
)
) */
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ConfMotorDTO2> confMotor2(String id) {
		List<ConfMotor> listConfMotor=(List<ConfMotor> ) em.createQuery(
				"SELECT c from ConfMotor c  where c.del='N' And c.idEvaluacion='"+id+"'").getResultList();
		List<ConfMotorDTO2> response= new ArrayList<ConfMotorDTO2>();
		for (ConfMotor conf: listConfMotor) {
			System.out.println(conf);
		List<MotorProducts> motorExist= espmService.EspmJoinMotorProductsName(conf.getIdESPM().toString());
		System.out.println(motorExist);
		 ConfMotorDTO2 confDto= new ConfMotorDTO2();
		 confDto.setIdConfMotor(conf.getIdConfMotor());
		 confDto.setIdEspmotor(conf.getIdESPM());
		 confDto.setNameEspmotor(motorExist.size()>0?motorExist.get(0).getName():"");
		 confDto.setIdMatrizHtas(conf.getIdMatrizHtas());
		 confDto.setIdMatrizPartes(conf.getIdMatrizPartes());
		 confDto.setIdEvaluacion(conf.getIdEvaluacion());
		 confDto.setMecReq(conf.getMecReq());
		 confDto.setIdNs(conf.getIdNs());
		 confDto.setNs(conf.getNS());
		 confDto.setDel(conf.getDel());
		 
		 MatrizDTO partes= new MatrizDTO();
		 partes.setIdMatriz(conf.getMatrizPartes().getIdMatriz().toString());
		 partes.setNombre(conf.getMatrizPartes().getNombre());
		 partes.setNoRevision(conf.getMatrizPartes().getNoRevision());
		 
		 MatrizDTO Htas= new MatrizDTO();
		 Htas.setIdMatriz(conf.getMatrizHtas().getIdMatriz().toString());
		 Htas.setNombre(conf.getMatrizHtas().getNombre());
		 Htas.setNoRevision(conf.getMatrizHtas().getNoRevision());
		 confDto.setMatPartes(partes);
		 confDto.setMatHtas(Htas);
		 
		 ConfOem filterOem= new ConfOem();
		 filterOem.setIdConfMotor(conf.getIdConfMotor());
		 
		 ConfSpcodes filterSpcodes= new ConfSpcodes();
		 filterSpcodes.setIdConfMotor(conf.getIdConfMotor());
		 
		 List<ConfOem> oOems= oemService.findConfOemByCriteria(filterOem);
		 System.out.println(oOems);
		 List<ConfSpcodes> oSpcodes= spcodesService.findConfSpcodesByCriteria(filterSpcodes);
		 List<SpCodesDTO> listSpcodes= new ArrayList<SpCodesDTO>();
		 for(ConfSpcodes sp: oSpcodes) {
			 SpCodesDTO newSp= new SpCodesDTO();
			 newSp.setIdOem(sp.getCode().getIdOem());
			 newSp.setIdPais(sp.getCode().getIdPais());
			 newSp.setIdTipo(sp.getCode().getTipo());
			 newSp.setProviderName(sp.getCode().getProviderName());
			 newSp.setResponsibleBranchCode(sp.getCode().getResponsibleBranchCode());
			 newSp.setSpCode(sp.getCode().getSpCode());
			 listSpcodes.add(newSp);
			 
		 }
		 
		 //SpCodesDTO spc= listSpcodes.stream().filter(e->e.getIdOem()==BigInteger.valueOf(1)).findFirst().get();
		
		 confDto.setSp(listSpcodes);
		 confDto.setOem(oOems);
		 
		 response.add(confDto);
		 
	 }
		
		return response;
	}
	
	
	
	
	//----------------------------------------------------------------------------------
	//				              GET CONF MOTOR BY DEL = N
	//----------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ConfMotorDTO> confMotor(String id, String lang) {
		List<ConfMotor> listConfMotor=(List<ConfMotor> ) em.createQuery(
				"SELECT c from ConfMotor c  where c.del='N' And c.idEvaluacion='"+id+"' order by idConfMotor asc").getResultList();
		System.out.println(listConfMotor);
		List<ConfMotorDTO> response= new ArrayList<ConfMotorDTO>();
		for (ConfMotor conf: listConfMotor) {
			System.out.println(conf);
		 ConfMotorDTO confDto= new ConfMotorDTO();
		 confDto.setIdConfMotor(conf.getIdConfMotor());
		 confDto.setIdEspmotor(conf.getIdESPM());
		 confDto.setIdMatrizHtas(conf.getIdMatrizHtas());
		 confDto.setIdMatrizPartes(conf.getIdMatrizPartes());
		 confDto.setIdEvaluacion(conf.getIdEvaluacion());
		 confDto.setMecReq(conf.getMecReq());
		 confDto.setIdNs(conf.getIdNs());
		 switch(lang) {
	        case"en_US":
	        		conf.getNS().setServiceLevel(conf.getNS().getEnglish());
	        	break;
	        	
	        case"pt_BR":
	        		conf.getNS().setServiceLevel(conf.getNS().getPortuguese());
	        	break;
	        	
	        default:
	        		conf.getNS().setServiceLevel(conf.getNS().getServiceLevel());
	        	break;
		 }
		 
		 
		 confDto.setNs(conf.getNS());
		 confDto.setDel(conf.getDel());
		 
		 MatrizDTO partes= new MatrizDTO();
		 partes.setIdMatriz(conf.getMatrizPartes().getIdMatriz().toString());
		 partes.setNombre(conf.getMatrizPartes().getNombre());
		 partes.setNoRevision(conf.getMatrizPartes().getNoRevision());
		 
		 MatrizDTO Htas= new MatrizDTO();
		 Htas.setIdMatriz(conf.getMatrizHtas().getIdMatriz().toString());
		 Htas.setNombre(conf.getMatrizHtas().getNombre());
		 Htas.setNoRevision(conf.getMatrizHtas().getNoRevision());
		 confDto.setMatPartes(partes);
		 confDto.setMatHtas(Htas);
		 
		 ConfOem filterOem= new ConfOem();
		 filterOem.setIdConfMotor(conf.getIdConfMotor());
		 
		 ConfSpcodes filterSpcodes= new ConfSpcodes();
		 filterSpcodes.setIdConfMotor(conf.getIdConfMotor());
		 
		 List<ConfOem> oOems= oemService.findConfOemByCriteria(filterOem);
		 List<ConfSpcodes> oSpcodes= spcodesService.findConfSpcodesByCriteria(filterSpcodes);
		 String sp[] = new String[oSpcodes.size()];
		 BigInteger oem[] = new BigInteger[oOems.size()];
		 int cont=0;
		 for(ConfOem el:oOems) {
			 oem[cont]=el.getOem().getId();
			 cont++;
		 }
		 cont=0;
		 for(ConfSpcodes el:oSpcodes) {
			 sp[cont]=el.getCode().getSpCode();
			 cont++;
		 }
		
		 confDto.setSp(sp);
		 confDto.setOem(oem);
		 
		 response.add(confDto);
		 
	 }
		//response.sort(Comparator.comparing(ConfMotorDTO::getIdConfMotor));
		return response;
	}


	//--------------------------------------------------------------------------------
	//							      SAVE CONF MOTOR
	//--------------------------------------------------------------------------------

	@Override
	public Object save(Object entity, String wwid) {
		JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
		System.out.println(actualObj1);
		String idEvaluacion=actualObj1.get("idEvaluacion").toString().replaceAll("\"", "");
		System.out.println(idEvaluacion);
		
		List<ConfMotorOemsSpDTO> response = new ArrayList<ConfMotorOemsSpDTO>();
		JsonNode confMotor=actualObj1.get("confMotor");
		System.out.println(confMotor.get(0));
		System.out.println("el tamaño es: "+confMotor.size());
		Date dat= new Date();
		for (JsonNode node: confMotor) {
			ConfMotorOemsSpDTO resp= new ConfMotorOemsSpDTO();
			List<ConfOem> listOem= new ArrayList<ConfOem>();
			List<ConfSpcodes> listSp= new ArrayList<ConfSpcodes>();
			dat= new Date();
			String idEspmotor=node.get("idEspmotor").toString().replaceAll("\"", "");
			String idNs=node.get("idNs").toString().replaceAll("\"", "");
			String idMatrizPartes=node.get("idMatrizPartes").toString().replaceAll("\"", "");
			String idMatrizHtas=node.get("idMatrizHtas").toString().replaceAll("\"", "");
			String mecReq=node.get("mecReq").toString().replaceAll("\"", "");
			
			ConfMotor conf= new ConfMotor();
			conf.setIdEvaluacion(new BigInteger(idEvaluacion));
			conf.setIdESPM(new BigInteger(idEspmotor));
			conf.setIdNs(new BigInteger(idNs));
			conf.setIdMatrizPartes(new BigInteger(idMatrizPartes));
			conf.setIdMatrizHtas(new BigInteger(idMatrizHtas));
			conf.setMecReq(new BigInteger(mecReq));
			conf.setCreatedBy(wwid);
			conf.setLastUpdatedBy(wwid);
			conf.setLastUpdateDate(dat);
			conf.setCreationDate(dat);
			conf.setDel('N');
			
			resp.setConfMotor(confMotorDao.save(conf));
			
			//JsonNode oems=node.get("oem");
			//System.out.println(oems);
			String[] oems=(node.get("oem").toString().replaceAll("\"", "")).split(",");
			for (String o:oems) {
				o=o.toString().replaceAll("\\[", "");
				o=o.toString().replaceAll("\\]", "");
				ConfOem confOem= new ConfOem();
				confOem.setIdConfMotor(conf.getIdConfMotor());
				confOem.setCreatedBy(wwid);
				confOem.setLastUpdateBy(wwid);
				confOem.setCreationDate(dat);
				confOem.setLastUpdateDate(dat);
				confOem.setIdOem(new BigInteger(o));
				confOem.setDel('N');
				listOem.add(oemService.save(confOem));
				//System.out.println(o);
			}
			resp.setOem(listOem);
            String[] sp=(node.get("sp").toString().replaceAll("\"", "")).split(",");
			//JsonNode sp=node.get("sp");
			
			
			for(String s: sp) {
				s=s.toString().replaceAll("\\[", "");
				s=s.toString().replaceAll("\\]", "");
				ConfSpcodes spcodes= new ConfSpcodes();
				spcodes.setCreatedBy(wwid);
				spcodes.setLastUpdateBy(wwid);
				spcodes.setCreationDate(dat);
				spcodes.setLastUpdateDate(dat);
				spcodes.setIdConfMotor(conf.getIdConfMotor());
				spcodes.setDel('N');
				spcodes.setSpCode(s);
				
				listSp.add(spcodesService.save(spcodes));
				//System.out.println(s);
			}
			
			resp.setSp(listSp);
			response.add(resp);
			
		}
		return response;
	}
	
	//-------------------------------------------------------------------------
	//					  DELETE CONF MOTOR (LOGICAL)
	//-------------------------------------------------------------------------
	
	@Override
	public Object delete(String id, String wwid) {
		//ConfMotor conf= new ConfMotor();
		Date date= new Date();
		ConfMotor conf =confMotorDao.findOne(new BigInteger(id));
		conf.setDel('Y');
		conf.setLastUpdateDate(date);
		conf.setLastUpdatedBy(wwid);
		confMotorDao.save(conf);
		ConfMotorDTO response= new ConfMotorDTO();
		response.setDel(conf.getDel());
		response.setIdConfMotor(conf.getIdConfMotor());
		response.setIdEspmotor(conf.getIdESPM());
		response.setIdEvaluacion(conf.getIdEvaluacion());
		response.setIdMatrizHtas(conf.getIdMatrizHtas());
		response.setIdMatrizPartes(conf.getIdMatrizPartes());
		response.setMecReq(conf.getMecReq());
		response.setIdNs(conf.getIdNs());
		
		List<ConfOem> listOEM= oemService.find(id);
		BigInteger oem1[] = new BigInteger[listOEM.size()];
		int cont=0;
		for(ConfOem oem: listOEM) {
			oem.setDel('Y');
			oem.setLastUpdateBy(wwid);
			oem.setLastUpdateDate(date);
			oemService.save(oem);
			oem1[cont]=oem.getOem().getId();
			cont++;
		}
		
		List<ConfSpcodes> listSpCodes= spcodesService.find(id);
		String sp1[] = new String[listSpCodes.size()];
		 
		cont=0;
		for(ConfSpcodes sp: listSpCodes) {
			sp.setDel('Y');
			sp.setLastUpdateBy(wwid);
			sp.setLastUpdateDate(date);
			spcodesService.save(sp);
			sp1[cont]=sp.getCode().getSpCode();
			cont++;
		}
		response.setSp(sp1);
		response.setOem(oem1);
		
		return response;
	}
	
	//-------------------------------------------------------------------------
	//					  		UPDATE CONF MOTOR
	//-------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public Object update(Object entity, String wwid) {
			
			
			JsonNode actualObj1 =  mapper.convertValue(entity, JsonNode.class);
			System.out.println(actualObj1);
			String idEvaluacion=actualObj1.get("idEvaluacion").toString().replaceAll("\"", "");
			System.out.println(idEvaluacion);
			
			List<ConfMotorOemsSpDTO> response = new ArrayList<ConfMotorOemsSpDTO>();
			JsonNode confMotor=actualObj1.get("confMotor");
			System.out.println(confMotor);
			System.out.println("el tamaño es: "+confMotor.size());
			Date dat= new Date();
			
				ConfMotorOemsSpDTO resp= new ConfMotorOemsSpDTO();
				List<ConfOem> listOem= new ArrayList<ConfOem>();
				List<ConfSpcodes> listSp= new ArrayList<ConfSpcodes>();
				dat= new Date();
				String idConfMotor=confMotor.get("idConfMotor").toString().replaceAll("\"", "");
				
				Object delete=delete(idConfMotor, wwid);
				
				String idEspmotor=confMotor.get("idEspmotor").toString().replaceAll("\"", "");
				String idNs=confMotor.get("idNs").toString().replaceAll("\"", "");
				String idMatrizPartes=confMotor.get("idMatrizPartes").toString().replaceAll("\"", "");
				String idMatrizHtas=confMotor.get("idMatrizHtas").toString().replaceAll("\"", "");
				String mecReq=confMotor.get("mecReq").toString().replaceAll("\"", "");
				
				List<ConfMotor> listConfMotor=(List<ConfMotor> ) em.createQuery(
						"SELECT c from ConfMotor c  where  c.idConfMotor='"+idConfMotor+"'").getResultList();
				
				ConfMotor conf= new ConfMotor();
				if(listConfMotor.size()>1) {
					conf.setIdConfMotor(listConfMotor.get(0).getIdConfMotor());
					conf.setCreatedBy(listConfMotor.get(0).getCreatedBy());
					conf.setCreationDate(listConfMotor.get(0).getCreationDate());
					
				}else {
					conf.setIdConfMotor(new BigInteger(idConfMotor));
					conf.setCreatedBy(wwid);
					conf.setCreationDate(dat);
					
				}
				conf.setIdEvaluacion(new BigInteger(idEvaluacion));
				conf.setIdESPM(new BigInteger(idEspmotor));
				conf.setIdNs(new BigInteger(idNs));
				conf.setIdMatrizPartes(new BigInteger(idMatrizPartes));
				conf.setIdMatrizHtas(new BigInteger(idMatrizHtas));
				conf.setMecReq(new BigInteger(mecReq));
				conf.setLastUpdatedBy(wwid);
				conf.setLastUpdateDate(dat);
				conf.setDel('N');
				
				resp.setConfMotor(confMotorDao.save(conf));
				
				//JsonNode oems=node.get("oem");
				//System.out.println(oems);
				String[] oems=(confMotor.get("oem").toString().replaceAll("\"", "")).split(",");
				for (String o:oems) {
					o=o.toString().replaceAll("\\[", "");
					o=o.toString().replaceAll("\\]", "");
					List<ConfOem> listConfOem=(List<ConfOem> ) em.createQuery(
							"SELECT c from ConfOem c  where c.idConfMotor='"+idConfMotor+"'").getResultList();
					ConfOem confOem= new ConfOem();
					if(listConfOem.size()>1) {
						confOem.setIdConfMotor(listConfOem.get(0).getIdConfMotor());
						confOem.setCreatedBy(listConfOem.get(0).getCreatedBy());
						confOem.setCreationDate(listConfOem.get(0).getCreationDate());
					}else {
						confOem.setIdConfMotor(conf.getIdConfMotor());
						confOem.setCreatedBy(wwid);
						confOem.setCreationDate(dat);
					}
					
					
					confOem.setLastUpdateBy(wwid);
					confOem.setLastUpdateDate(dat);
					confOem.setIdOem(new BigInteger(o));
					confOem.setDel('N');
					listOem.add(oemService.save(confOem));
					//System.out.println(o);
				}
				resp.setOem(listOem);
	            String[] sp=(confMotor.get("sp").toString().replaceAll("\"", "")).split(",");
				//JsonNode sp=node.get("sp");
				
				
				for(String s: sp) {
					s=s.toString().replaceAll("\\[", "");
					s=s.toString().replaceAll("\\]", "");
					ConfSpcodes spcodes= new ConfSpcodes();
					List<ConfSpcodes> listSpCodes=(List<ConfSpcodes> ) em.createQuery(
							"SELECT c from ConfSpcodes c  where  c.idConfMotor='"+idConfMotor+"'").getResultList();
					
					if(listSpCodes!=null) {
						spcodes.setIdConfMotor(listSpCodes.get(0).getIdConfMotor());
						spcodes.setCreatedBy(listSpCodes.get(0).getCreatedBy());
						spcodes.setCreationDate(listSpCodes.get(0).getCreationDate());
					}else {
						spcodes.setIdConfMotor(conf.getIdConfMotor());
						spcodes.setCreatedBy(wwid);
						spcodes.setCreationDate(dat);
				}
					
					
					spcodes.setLastUpdateBy(wwid);
					spcodes.setLastUpdateDate(dat);
					spcodes.setDel('N');
					spcodes.setSpCode(s);
					
					listSp.add(spcodesService.save(spcodes));
					//System.out.println(s);
				}
				
				resp.setSp(listSp);
				response.add(resp);
				
			
			
			return response;
			
		}
	
	
}
