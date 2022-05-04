package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatformProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IPerfilesDao;
import com.cummins.scd.models.dao.IRelPerfilExcsDao;
import com.cummins.scd.models.dao.IRelPerfilMecanicos;
import com.cummins.scd.models.dao.IRelPerfilMotor;
import com.cummins.scd.models.dto.PerfilDTO;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMecanicos;
import com.cummins.scd.models.entity.RelPerfilMotor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PerfilesService implements IPerfilesService {
	
	@Autowired
	IPerfilesDao perfilesDao;

	@Autowired
	IRelPerfilExcsDao exceptionsDao;
	
	@Autowired
	IConfMotorService confMotorService;
	
	@Autowired
	IRelPerfilMotor relPerfilMotorService;
	
	@Autowired
	IEspmService espmService;
	
	@Autowired
	IRelPerfilMecanicos relPerfilMecanicosService;
	
	private ObjectMapper mapper = new ObjectMapper();

	@PersistenceContext
	private EntityManager em;
	
	
	//----------------------------------------------------------
	//					GET ALL  PERFIL BY DEL='N'
	//----------------------------------------------------------
	
	/*SELECT  A.SP_CODE, 
        B.SERVICE_PROVIDER_NAME, 
        O.OEM,       
        (
            SELECT  listagg(CMO.NOMBRE, ', ') within group (order by CMO.NOMBRE)
            FROM    ZMKT.ZMKT7352_DES_REL_PERFIL_MOTOR PM
                    LEFT JOIN ZMKT.ZMKT7352_DES_CONF_MOTOR CM ON CM.ID_CONF_MOTOR = PM.ID_CONF_MOTOR
                    LEFT JOIN ZMKT.ZMKT7352_DES_ESPM EM ON EM.ID_ESPMOTOR = CM.ID_ESPMOTOR
                    LEFT JOIN ZMKT.ZMKT7352_DES_CAT_MOTORES CMO ON CMO.ID = EM.ID_MOTOR
            WHERE   PM.DELETE_IND = 'N'
                    AND PM.ID_PERFIL = A.ID_PERFIL   
        ) MOTORES,        

        A.NO_MEC_PROMOTION
FROM    ZMKT.ZMKT7352_DES_PERFILES A
        LEFT JOIN ZMKT.ZMKT7352_DES_CAT_SPCODES B ON B.SP_CODE = A.SP_CODE
        LEFT JOIN ZMKT.ZMKT7352_DES_CAT_OEM O ON O.ID_OEM = A.ID_OEM
WHERE   A.DELETE_IND = 'N'
        AND A.ID_EVALUACION = :p_id_evaluacion
        AND B.RESPONSIBLE_BRANCH_CODE = TRIM(UPPER(:p_sp_code_dr))
ORDER BY TO_NUMBER(A.SP_CODE)
*/

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public Object getPerfilesByEvAndResp(String idEvaluacion, String respBranchCode){
		List<Perfiles> listPerfiles=(List<Perfiles> ) em.createQuery(
				"SELECT c from Perfiles c "
							+ "where c.del='N' "
							+ "and c.idEvaluacion='"+idEvaluacion+"' "
							+ "and c.sp.responsibleBranchCode='"+respBranchCode+"'").getResultList();
		List<PerfilDTO> response =new ArrayList<PerfilDTO>();
		
		/*
		 * SP Code
	Nombre
	OEM
	Motor
	No. Mecánicos
*/
		for(Perfiles p:listPerfiles) {
			PerfilDTO per =new PerfilDTO();
			per.setSpcode(p.getSpCode());
			per.setNombre(p.getSp().getProviderName());
			per.setOem(p.getIdo().getOem());
			per.setMec(p.getNoMecPromotion().toString());
			List<RelPerfilMotor> rpm= (List<RelPerfilMotor> ) em.createQuery(
				"SELECT c from RelPerfilMotor c "
							+ "where c.del='N' "
							+ "and c.idPerfil='"+p.getIdPerfil()+"' ").getResultList();
			List<String> nameMotors=new ArrayList<String>();
			for(RelPerfilMotor rpmotor: rpm) {
				List<MotorProducts> motor= (List<MotorProducts>) espmService.EspmJoinMotorProductsName(rpmotor.getCm().getIdESPM().toString());
				for(MotorProducts mp: motor) {
					nameMotors.add(mp.getComercialName());
					
				}
					
			}
			per.setMotors(String.join(", ", nameMotors));
			
			response.add(per);
		}
		
		
		return  response;
	}
	
	
	//----------------------------------------------------------
	//					GET ALL  PERFIL BY DEL='N'
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Perfiles> getPerfilesByDel(){
		List<Perfiles> listPerfiles=(List<Perfiles> ) em.createQuery(
				"SELECT c from Perfiles c where c.del='N'").getResultList();
		return  listPerfiles;
	}
	
	//----------------------------------------------------------
	//	GET  PERFILES BY DEL = 'N'  AND EVALUACION ID OR BY DEL = 'N'  AND EVALUACION ID AND SP CODE
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Perfiles> getPerfiles(String id, String code){
		if(code==null)
		{
			List<Perfiles> listPerfiles=(List<Perfiles> ) em.createQuery(
					"SELECT c from Perfiles c where c.del='N' AND c.idEvaluacion=" + id).getResultList();
			return  listPerfiles;
		}
		else
		{
			List<Perfiles> listPerfiles=(List<Perfiles> ) em.createQuery(
					"SELECT c from Perfiles c where c.del='N' AND c.idEvaluacion=" + id + " AND c.spCode= '" + code + "'").getResultList();
			return  listPerfiles;	
		}
	}
	//--------------------------------------------------------------------------
	//	GET  PERFILES EXCEPTIONS BY DEL = 'N'  AND ID PERFIL ID AND ID CONFMOTOR
	//--------------------------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<RelPerfilExcs> getPerfilExceptions(String idPerfil,String idConfMotor){
		List<RelPerfilExcs> listPerfilesExceptions=(List<RelPerfilExcs> ) em.createQuery(
				"SELECT c from RelPerfilExcs c where c.del='N' AND c.idPerfil=" + idPerfil + " AND c.idConfMotor= " + idConfMotor).getResultList();
		
		return  listPerfilesExceptions;
		
	}
	//--------------------------------------------------------------------------
	//	GET  PERFILES MECANICOS BY DEL = 'N'  AND ID PERFIL ID
	//--------------------------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<RelPerfilMecanicos> getPerfilMecanicos(String idPerfil){
		List<RelPerfilMecanicos> listPerfilesMec=(List<RelPerfilMecanicos> ) em.createQuery(
				"SELECT c from RelPerfilMecanicos c where c.del='N' AND c.idPerfil=" + idPerfil).getResultList();
		
		return  listPerfilesMec;
		
	}
	//----------------------------------------------------------
	//	GET PERFILES MOTOR BY DEL= 'N' AND ID PERFIL
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<RelPerfilMotor> getPerfilesMotor(String IdPerfil){
		
			List<RelPerfilMotor> listPerfiles=(List<RelPerfilMotor> ) em.createQuery(
					"SELECT c from RelPerfilMotor c where c.del='N' AND c.idPerfil=" + IdPerfil).getResultList();
			return  listPerfiles;
	
	}
	@Transactional(readOnly = false)
	public RelPerfilMotor saveRPM(RelPerfilMotor rpm)
	{
		return relPerfilMotorService.save(rpm);
	}
	//----------------------------------------------------------
	//	GET PERFILES MOTOR BY CONF MOTOR BY ID PERFIL BY ID EVALUACION
	//----------------------------------------------------------
	
	//@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Object setPerfiles(String wwid,String IdPerfilAnterior,String IdPerfilNew,String IdEv,String IdEvCopy){
		Date date = new Date();
		List<ConfMotor> ListaAux= new ArrayList<ConfMotor>();
		/*List<ConfMotor> ListaAuxNew= new ArrayList<ConfMotor>();
		Map<String, Object> response = new HashMap<>();*/

		
		
		//ListaPerfilesMotor.get(0).getCm().getIdESPM()
	
		/*List<ConfMotor> ListaConfMotorNew= confMotorService.getConfMotor(IdEv);
		System.out.println(ListaConfMotorNew);*/
		//List<ConfMotor> ListaConfMotorCopy= confMotorService.getConfMotor(IdEvCopy);
		
		List<RelPerfilMotor> lstRpm= getPerfilesMotor(IdPerfilAnterior);
		//System.out.println(lstRpm);
		
		
		for(RelPerfilMotor relpm:lstRpm)
		{
			List<ConfMotor>Lista=confMotorService.getConfMotorByNsEspmEV(relpm.getCm().getIdNs().toString(), relpm.getCm().getIdESPM().toString(), IdEv);
			if(Lista.size()>0)
			{
				ListaAux.add(Lista.get(0));
				
				//Inserta REL PERFIL MOTOR
				RelPerfilMotor rpm = new RelPerfilMotor();
				rpm.setIdPerfil(new BigInteger (IdPerfilNew));
				rpm.setIdConfMotor(Lista.get(0).getIdConfMotor());
				rpm.setCalificacion(relpm.getCalificacion());
				rpm.setIdEstatus(relpm.getIdEstatus());
				rpm.setComentarios(relpm.getComentarios());
				rpm.setCreationDate(date);
				rpm.setCreatedBy(wwid);
				rpm.setLastUpdateBy(wwid);
				rpm.setLastUpdateDate(date);
				rpm.setDel('N');
				//System.out.println(rpm);
				RelPerfilMotor newRPM = new RelPerfilMotor();
				newRPM=saveRPM(rpm);
				//System.out.println(newRPM);
				
				//---------------------------------
				// Carga Perfil Excepciones
				//---------------------------------
				
				// Rel Perfil Excepciones con perfil nuevo
				List<RelPerfilExcs>ListaNewExcepciones= new ArrayList<RelPerfilExcs>();
				ListaNewExcepciones= getPerfilExceptions(IdPerfilNew,Lista.get(0).getIdConfMotor().toString());
				
				//Verifica Rel Perfil Excepciones con perfil anterior
				List<RelPerfilExcs>ListaExcepcionesAnterior= new ArrayList<RelPerfilExcs>();
				ListaExcepcionesAnterior= getPerfilExceptions(IdPerfilAnterior, relpm.getCm().getIdConfMotor().toString());
				System.out.println(ListaExcepcionesAnterior);
				if(ListaExcepcionesAnterior.size()>0)
				{
					for(RelPerfilExcs rpexAnterior: ListaExcepcionesAnterior)
					{
						
						if(ListaNewExcepciones.size()==0)
						{
							RelPerfilExcs pex= new RelPerfilExcs();
							pex.setIdPerfil(new BigInteger(IdPerfilNew));
							pex.setIdConfMotor(Lista.get(0).getIdConfMotor());
							pex.setIdModulo(rpexAnterior.getIdModulo());
							pex.setIdSubmodulo(rpexAnterior.getIdSubmodulo());
							pex.setCreationDate(date);
							pex.setCreatedBy(wwid);
							pex.setLastUpdateDate(date);
							pex.setLastUpdateBy(wwid);
							pex.setDel('N');
							RelPerfilExcs Newpex= new RelPerfilExcs();
							Newpex=exceptionsDao.save(pex);
						}
						
					}
				}
				
				//---------------------------------
				// Carga Perfil Mecanicos
				//---------------------------------
				
				// Rel Perfil Excepciones con perfil nuevo
				List<RelPerfilMecanicos>ListaNewMecanicos= new ArrayList<RelPerfilMecanicos>();
				ListaNewMecanicos= getPerfilMecanicos(IdPerfilNew);
				
				//Verifica Rel Perfil Excepciones con perfil anterior
				List<RelPerfilMecanicos>ListaMecanicosAnterior= new ArrayList<RelPerfilMecanicos>();
				ListaMecanicosAnterior= getPerfilMecanicos(IdPerfilAnterior);

				if(ListaMecanicosAnterior.size()>0)
				{
					for(RelPerfilMecanicos rpmecAnterior: ListaMecanicosAnterior)
					{
						
						if(ListaNewMecanicos.size()==0)
						{
							RelPerfilMecanicos rpmec= new RelPerfilMecanicos();
							rpmec.setIdPerfil(new BigInteger(IdPerfilNew));
							rpmec.setPromotionId(rpmecAnterior.getPromotionId());
							rpmec.setNombre(rpmecAnterior.getNombre());
							rpmec.setCursos(rpmecAnterior.getCursos());

							rpmec.setCreationDate(date);
							rpmec.setCreatedBy(wwid);
							rpmec.setLastUpdateDate(date);
							rpmec.setLastUpdateBy(wwid);
							rpmec.setDel('N');
							RelPerfilMecanicos Newrpmec= new RelPerfilMecanicos();
							Newrpmec=relPerfilMecanicosService.save(rpmec);
						}
						
					}
				}
			}
					
			
			
			// Anterior rpm.getIdConfMotor();
			//CONSULTAR SI EXISTE con los parametros rpm.getIdNs(), rpm.getIdESPM(), idEv
			
			
			
			
			
			
			
			//System.out.println(rpm.getIdConfMotor() + "-" + cmcopy.getIdConfMotor() );
			
			
			/*if(rpm.getIdConfMotor().equals(cmcopy.getIdConfMotor()))
			{
				ListaAux.add(cmcopy);
			}*/
		}
	
		/*System.out.println(ListaAux);
		for(ConfMotor cmcopy: ListaAux)
		{
			for(ConfMotor cmnew: ListaConfMotorNew)
			{
				if(cmcopy.getIdESPM().equals(cmnew.getIdESPM())&&
					cmcopy.getIdNs().equals(cmnew.getIdNs()))
				{
				
					ListaAuxNew.add(cmnew);

				}

			}
			
		}
		System.out.println(ListaAuxNew);
		response.put("ConfMotor", ListaAux);
		response.put("CONF_MOTOR_NUEVA", ListaAuxNew);
		return response;*/
		return null;
	
	}
	//----------------------------------------------------------
	//					SAVE PERFIL
	//----------------------------------------------------------
	@Override
	public Object save(String wwid, String idEv, String idEvCopy)
	{ 
		Date date= new Date();
		Map<String, Object> response = new HashMap<>();
		List<Perfiles> lPerfilesNew= new ArrayList<Perfiles>();
		List<Perfiles> lSavedPerfiles= new ArrayList<Perfiles>();
		List <RelPerfilExcs> lSavedExceptions = new ArrayList<RelPerfilExcs>();
		List<Perfiles> lPerfilesOld= getPerfiles(idEvCopy,null); //Obtener los perfiles con la evaluacion a copiar
		//System.out.println(lPerfilesOld);
		for(Perfiles per: lPerfilesOld) {
			System.out.println("perfil: "+per);
			
		}
		if(lPerfilesOld.size()>0)
		{
			
			for(Perfiles perfil: lPerfilesOld)
			{
				lPerfilesNew = getPerfiles(idEv,perfil.getSpCode());
				if(lPerfilesNew.size()>0)
				{
					Perfiles perfilNewExiste = new Perfiles();
					perfilNewExiste=mapper.convertValue(lPerfilesNew.get(0), Perfiles.class); 
					//extraer
					setPerfiles(wwid,perfil.getIdPerfil().toString(),perfilNewExiste.getIdPerfil().toString(),idEv,idEvCopy);
					
				}
				else
				{
					//copia el perfil
					Perfiles perfilNew = new Perfiles();
					Perfiles perfilSaved = new Perfiles();
					
					perfilNew.setIdEvaluacion(new BigInteger(idEv));
					perfilNew.setSpCode(perfil.getSpCode());
					perfilNew.setIdOem(perfil.getIdOem());
					perfilNew.setFronterizo(perfil.getFronterizo());
					perfilNew.setNoBahias(perfil.getNoBahias());
					perfilNew.setNoMecPromotion(perfil.getNoMecPromotion());
					perfilNew.setNoMecanicos(perfil.getNoMecanicos());
					perfilNew.setNoAyudantes(perfil.getNoAyudantes());
					perfilNew.setComentarios(perfil.getComentarios());
					perfilNew.setResultadoBloqueado(perfil.getResultadoBloqueado());
					
					perfilNew.setCreatedBy(wwid);
					perfilNew.setCreationDate(date);
					perfilNew.setLastUpdateBy(wwid);
					perfilNew.setLastUpdateDate(date);
					perfilNew.setDel('N');
					System.out.println("nuevo perfil: "+perfilNew);
					
					perfilSaved= perfilesDao.save(perfilNew);
					lSavedPerfiles.add(perfilSaved);
					response.put("perfiles", lSavedPerfiles);
					
					System.out.println(perfil.getIdPerfil());
					setPerfiles(wwid,perfil.getIdPerfil().toString(),perfilSaved.getIdPerfil().toString(),idEv,idEvCopy);
					
					/*List<RelPerfilExcs>ListaExcepciones= new ArrayList<RelPerfilExcs>();
					ListaExcepciones= getPerfilExceptions(perfil.getIdPerfil().toString());
					System.out.println(ListaExcepciones);
					if(ListaExcepciones.size()>0)
					{
						for(RelPerfilExcs ex: ListaExcepciones)
						{
							RelPerfilExcs newEx= new RelPerfilExcs();
							newEx.setIdPerfil(perfilSaved.getIdPerfil());
							newEx.setIdModulo(ex.getIdModulo());
							newEx.setIdSubmodulo(ex.getIdSubmodulo());	
							newEx.setIdConfMotor(ex.getIdConfMotor());
							newEx.setCreatedBy(wwid);
							newEx.setCreationDate(date);
							newEx.setLastUpdateBy(wwid);
							newEx.setLastUpdateDate(date);
							newEx.setDel('N');
							lSavedExceptions.add(exceptionsDao.save(newEx));
							response.put("Exceptions", lSavedExceptions);
						}
					}
					*/
					
					
					
				}
				
				//Object ListaConfMotor= getPerfilesMotorByConfMotor(perfil.getIdPerfil().toString(),idEv, idEvCopy);
				//for()
			}
			
			return response;
			
		}
		else
		{
			return null;
		}
		
	}
		

}
