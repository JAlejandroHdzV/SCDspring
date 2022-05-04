package com.cummins.scd.models.services;

/**
 * Servicios que se utilizan para los catálogos de carga de información del menú Administracion
*/


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
//import javax.xml.bind.ParseConversionEvent;

import com.cummins.scd.models.dao.IAux_PuestosDao;
import com.cummins.scd.models.dao.IAux_RelPuestosDao;
import com.cummins.scd.models.dao.IAux_DRDLRDao;
import com.cummins.scd.models.dao.IPqsDao;
import com.cummins.scd.models.dao.IPqsXpuestosDao;
import com.cummins.scd.models.dao.ISpCodeDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_HtasDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_OpcionesDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_RelModulosDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_StatusEvDao;
import com.cummins.scd.models.dto.AtributoMatrizDTO;
import com.cummins.scd.models.dto.EspmNsDTO;
import com.cummins.scd.models.dto.MatrizHtasEncDetAtrDTO;
import com.cummins.scd.models.dto.MatrizHtasEncDetDTO;
import com.cummins.scd.models.dto.MatrizPartesEncDetAtrDTO;
import com.cummins.scd.models.dto.MatrizPartesEncDetDTO;
import com.cummins.scd.models.dto.MotorProductJoinESPM;
import com.cummins.scd.models.dao.ILicenciasQsolDao;
import com.cummins.scd.models.dao.IElectronicToolsDao;
import com.cummins.scd.models.dao.IRescatesDao;
import com.cummins.scd.models.dao.INoDisponibleDao;
import com.cummins.scd.models.dao.IPromotionDao;
import com.cummins.scd.models.dao.IAux_CCDao;
import com.cummins.scd.models.dao.IAux_StatusGarDao;
import com.cummins.scd.models.dao.IESPMDao;
import com.cummins.scd.models.dao.IESPM_EmisionDao;
import com.cummins.scd.models.dao.IESPM_RegionDao;
import com.cummins.scd.models.dao.IESPM_NSDao;
import com.cummins.scd.models.dao.IAux_PartesDao;
import com.cummins.scd.models.dao.IAux_TipoDao;
import com.cummins.scd.models.dao.IAux_StatusMatrizDao;
import com.cummins.scd.models.dao.IGarantiaDao;
import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dao.IMatrizPartesDetDao;
import com.cummins.scd.models.dao.IAtributosMatricesDao;
import com.cummins.scd.models.dao.IMatrizHtasHdDao;
import com.cummins.scd.models.dao.IMatrizHtasDetDao;
import com.cummins.scd.models.dao.IAtributosMatricesHtasDao;
import com.cummins.scd.models.dao.IConfMotorDao;
import com.cummins.scd.models.dao.IConfSpcodesDao;
import com.cummins.scd.models.dao.IConfOemDao;
import com.cummins.scd.models.dao.IAux_TipoQDao;
import com.cummins.scd.models.dao.IConfEvalDao;


import com.cummins.scd.models.entity.Aux_Puestos;
import com.cummins.scd.models.entity.Aux_RelPuestos;
import com.cummins.scd.models.entity.Aux_DRDLR;
import com.cummins.scd.models.entity.Aux_Htas;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.PqsXpuestos;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.Tools;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Opciones;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_RelModulos;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_StatusEv;
import com.cummins.scd.models.entity.LicenciasQsol;
import com.cummins.scd.models.entity.ElectronicTools;
import com.cummins.scd.models.entity.Rescates;
import com.cummins.scd.models.entity.NoDisponible;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.Promotion;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.Aux_CC;
import com.cummins.scd.models.entity.Aux_StatusGar;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_Emision;
import com.cummins.scd.models.entity.ESPM_Region;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.Aux_Partes;
import com.cummins.scd.models.entity.Aux_Tipo;
import com.cummins.scd.models.entity.Aux_StatusMatriz;
import com.cummins.scd.models.entity.Garantia;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.AtributosMatrices;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.CountryPerRegion;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.Aux_TipoQ;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.groovy.runtime.metaclass.NewStaticMetaMethod;
//import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadInfoServiceImpl implements ILoadInfoService{
	//Se declaran todas las interfaces a utilizar pata acceder a las entidades e implementar los metodos del Crud
	
	@Autowired
    private IAux_PuestosDao puestoDao;
    @Autowired
    private IAux_RelPuestosDao relPuestoDao;
    @Autowired
    private IAux_DRDLRDao DRDLRDao;
    @Autowired
    private IPqsDao pqsDao;
    @Autowired
    private IPqsXpuestosDao pqsxpuestosDao;
    @Autowired
    private ISpCodeDao spcodeDao;
    @Autowired
    private ILicenciasQsolDao licenciasqsolDao;
    @Autowired
    private IElectronicToolsDao electronicDao;
    @Autowired
    private IRescatesDao rescatesDao;
    @Autowired
    private INoDisponibleDao nodisponibleDao;
    @Autowired
    private IPromotionDao promotionDao;
    @Autowired
    private IAux_CCDao ccDao;
    @Autowired
    private IAux_StatusGarDao statusgarDao;
    @Autowired
    private IESPMDao espmDao;
    @Autowired
    private IESPM_EmisionDao espmEmisionDao;
    @Autowired
    private IESPM_RegionDao espmRegionDao;
    @Autowired
    private IESPM_NSDao espmNsDao;
    @Autowired
    private IAux_PartesDao partesDao;
    @Autowired
    private IAux_HtasDao htasDao;
    @Autowired
    private IAux_TipoDao tipoDao;
    @Autowired
    private IAux_StatusMatrizDao statusMatrizDao;
    @Autowired
    private IGarantiaDao garantiaDao;
    @Autowired
    private IMatrizPartesHdDao matrizPartesHdDao;
    @Autowired
    private IMatrizPartesDetDao matrizPartesDetDao;
    @Autowired
    private IAtributosMatricesDao atributosMatricesDao;
    @Autowired
    private IMatrizHtasHdDao matrizHtasHdDao;
    @Autowired
    private IMatrizHtasDetDao matrizHtasDetDao;
    @Autowired
    private IAtributosMatricesHtasDao atributosMatricesHtasDao;
    @Autowired
    private IConfMotorDao confmotorDao;
    @Autowired
    private IConfSpcodesDao confspcodesDao;
    @Autowired
    private IConfOemDao confoemDao;
    @Autowired
    private IAux_TipoQDao auxtipoqDao;
    
    @Autowired
    private IConfEvalDao confEvalDao;
    @Autowired
    private IAux_RelModulosDao relModulosDao;
    @Autowired
    private IAux_OpcionesDao opcionesDao;
    @Autowired
    private IAux_StatusEvDao statusEvDao;
    
    @Autowired
    private ICatalogsService catalogService;
    
    @Autowired
    private ILoadInfoService infoService;
    
  
    //Se declara una variable mapper global que se utilizara para mappear el objeto a la entidad
    private ObjectMapper mapper = new ObjectMapper();
    
    //Se declara la variable global de tipo DataSource para las conexiones a la BD
    @Autowired
    private DataSource dataSource;

    
    //Se declara la variable global dat para menjar las fechas temporalmente
    //se quitan cuando se agreguen los triggers en BD
    Date dat = new Date();
    
    
    
    /**
   	 * @desc metodo que permite obtener todos los registros de la Tabla a la que hace referencia una entidad
   	 * @param catalogo - String con el nombre del catalogo (entidad) a consultar
   	 * @return Object - JSON de registros obtenidos de la entidad
   	*/
    @Override
    @Transactional(readOnly = true)
    public Object findAll(String catalogue) {
        switch (catalogue) {
            case "puestos":                
                return puestoDao.findAll();
            case "confEval":                
                return confEvalDao.findAll();
            case "relpuestos":                
            	return relPuestoDao.findAll();
            case "drdlr":                
                return DRDLRDao.findAll();
            case "pqs":
            	
                return pqsDao.findAll(new  Specification<Pqs>() {

                    @Override
                    public Predicate toPredicate(Root<Pqs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        List<Predicate> predicates = new ArrayList<>();
				
                         return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                },new Sort(Sort.Direction.ASC, "programId")
                        .and(new Sort(Sort.Direction.ASC, "nombre")));
            case "pqsxpuesto":                
                return pqsxpuestosDao.findAll();
            case "spcodes":                
                return spcodeDao.findAll();
            case "licenciasqsol":                
                return licenciasqsolDao.findAll();
            case "electronictools":                
                return electronicDao.findAll();
            case "rescates":
                return rescatesDao.findAll();
            case "nodisponible":
                return nodisponibleDao.findAll();
            case "promotion":
                return promotionDao.findAll();
            case "codigocta":
                return ccDao.findAll();
            case "statusgar":
                return statusgarDao.findAll();
            case "espm":
                return espmDao.findAll();
            case "espmEmision":
                return espmEmisionDao.findAll();
            case "espmRegion":
                return espmRegionDao.findAll();
            case "espmNs":
                return espmNsDao.findAll();
            case "partes":
                return partesDao.findAll();
            case "htas":
                return htasDao.findAll();
            case "tipos":
                return tipoDao.findAll();
            case "statusMatriz":
                return statusMatrizDao.findAll();
            case "garantias":
                return garantiaDao.findAll();
            case "matrizPartesHd":
                return matrizPartesHdDao.findAll();
            case "matrizPartesDet":
                return matrizPartesDetDao.findAll();
            case "atributosMatrices":
                return atributosMatricesDao.findAll();
            case "matrizHtasHd":
                return matrizHtasHdDao.findAll();
            case "matrizHtasDet":
                return matrizHtasDetDao.findAll();
            case "atributosMatricesHtas":
                return atributosMatricesHtasDao.findAll();
            case "confMotor":
                return confmotorDao.findAll();
            case "confSpcodes":
                return confspcodesDao.findAll();
            case "confOem":
                return confoemDao.findAll();
            case "tipoQ":
                return auxtipoqDao.findAll();
            case "relModulos":
                return relModulosDao.findAll();
            case "opciones":
                return opcionesDao.findAll();
            case "statusEv":
                return statusEvDao.findAll();
           
            default:
                return null;
        }
    }
    
    /**
   	 * @desc metodo que permite obtener uno de los registros de la Tabla a la que hace referencia una entidad
   	 * @param catalogo, id - String con el nombre del catalogo (entidad) a consultar y el String del identificador del registro a obtener
   	 * @return Object - JSON del registro obtenido de la entidad
   	 * 
   	 * Nota: aplica solo para los que tienen una sola llave primaria
   	*/
    
    @Override
    @Transactional(readOnly = true)
    public Object findById(String catalogue, String id, String lang, Boolean flag) {
        switch (catalogue) {
            case "puestos":                
                return puestoDao.findOne(new BigInteger(id));
            case "relpuestos":                
            	return relPuestoDao.findOne(new BigInteger(id));
            case "drdlr":                
                return DRDLRDao.findOne(new BigInteger(id));
            case "pqs":                
                return pqsDao.findOne(id);
            case "pqsxpuesto":                
                return pqsxpuestosDao.findOne(new BigInteger(id));
            case "spcodes":                
                return spcodeDao.findOne(id);
            case "licenciasqsol":                
                return licenciasqsolDao.findOne(id);
            case "electronictools":                
                return electronicDao.findOne(id);
            case "rescates":                
            	return rescatesDao.findOne(id);
            case "nodisponible":                
                return nodisponibleDao.findOne(id);
            case "promotion":                
                return promotionDao.findOne(id);
            case "codigocta":                
                return ccDao.findOne(id);
            case "statusgar":
                return statusgarDao.findOne(new BigInteger(id));
            case "espm":
                return espmDao.findOne(new BigInteger(id));
            case "espmEmision":
                return espmEmisionDao.findOne(new BigInteger(id));
            case "espmRegion":
                return espmRegionDao.findOne(new BigInteger(id));
            case "espmNs":
                return espmNsDao.findOne(new BigInteger(id));
            case "partes":
                return partesDao.findOne(id);
            case "htas":
                return htasDao.findOne(id);
            case "tipos":
                return tipoDao.findOne(new BigInteger(id));
            case "statusMatriz":
                return statusMatrizDao.findOne(new BigInteger(id));
            case "garantias":
                return garantiaDao.findOne(id);
            case "matrizPartesHd":
                return matrizPartesHdDao.findOne(new BigInteger(id));
            case "matrizPartesDet":
                return matrizPartesDetDao.findOne(new BigInteger(id));
            case "atributosMatrices":
                return atributosMatricesDao.findOne(new BigInteger(id));
            case "matrizHtasHd":
                return atributosMatricesDao.findOne(new BigInteger(id));
            case "matrizHtasDet":
                return matrizHtasDetDao.findOne(new BigInteger(id));
            case "atributosMatricesHtas":
                return atributosMatricesHtasDao.findOne(new BigInteger(id));
            case "confMotor":
                return confmotorDao.findOne(new BigInteger(id));
            case "confSpcodes":
                return confspcodesDao.findOne(new BigInteger(id));
            case "confOem":
                return confoemDao.findOne(new BigInteger(id));
            case "tipoQ":
                return auxtipoqDao.findOne(new BigInteger(id));
           case "relModulos":
                return relModulosDao.findOne(new BigInteger(id));
           case "opciones":
               return opcionesDao.findOne(new BigInteger(id));
           case "statusEv":
               return statusEvDao.findOne(new BigInteger(id));
           case "matPartesObject":
        	   System.out.println("entra a mat partes");
        	   MatrizPartesHd hd= matrizPartesHdDao.findOne(new BigInteger(id));
        	   MatrizPartesDet det=new MatrizPartesDet();
        	   AtributosMatricesHtas atr=new AtributosMatricesHtas();
        	   det.setIdMatriz(new BigInteger(id));
        	   det.setDel('N');
        	   atr.setIdMatriz(new BigInteger(id));
        	   atr.setDel('N');
        	   List<MatrizPartesDet> lstDet= findMatrizPartesDetByCriteria(det, lang, flag);
        	   List<AtributosMatricesHtas> lstAtr= findAtributosMatricesHtasByCriteria(atr);
        	   List<AtributoMatrizDTO> lstAtributos= new ArrayList<>();
        	   List<Region> listRegionP= new ArrayList<Region>();
        	   
        	   for(AtributosMatricesHtas atrr: lstAtr) {
        		   String tipo= atrr.getTipoAtributo().toString();
        		   String idN=atrr.getIdNumber().toString();
        		   String idT=atrr.getIdText();
        		   AtributoMatrizDTO atributos=new AtributoMatrizDTO();
        		   atributos.setCreatedBy(atrr.getCreatedBy());
        		   atributos.setCreationDate(atrr.getCreationDate());
        		   atributos.setDel(atrr.getDel());
        		   atributos.setIdMatriz(atrr.getIdMatriz());
        		   atributos.setIdNumber(atrr.getIdNumber());
        		   atributos.setIdText(atrr.getIdText());
        		   atributos.setLastUpdateBy(atrr.getLastUpdateBy());
        		   atributos.setLastUpdateDate(atrr.getLastUpdateDate());
        		   atributos.setTipoAtributo(atrr.getTipoAtributo());
        		   atributos.setTipoMatriz(atrr.getTipoMatriz());
        		   switch(tipo) {
        		   case "1":
        			   System.out.println("spcodes");
        			   SpCodes spcode=(SpCodes) findById("spcodes", idT, lang, flag);
        			   System.out.println(spcode);
        			   if(spcode!= null) 
        			   {
	        			   atributos.setNombre(spcode!= null?spcode.getProviderName():"");
	        			   Boolean regP=false;
	        			   for(Region r: listRegionP) 
	        			   {
	        				   if(spcode.getCountry().obtenerCountriesPerRegion().get(0).getRegion().getId()==r.getId()) 
	        					   regP=true;
	        			   }
	        			       System.out.println("La lista es: "+listRegionP);
	        			       if(regP==false)
	        				   listRegionP.add(spcode.getCountry().obtenerCountriesPerRegion().get(0).getRegion());
        			   }
        			   break;
        		   case "2":
        			   System.out.println("modelos");
        			   ESPM filterEspm= new ESPM();
        			   filterEspm.setIdESPM(new BigInteger(idN));
        			   //System.out.println(filterEspm);
        			   List<ESPM> espm=  (List<ESPM>) findESPMByCriteria(filterEspm);
        			   System.out.println("espm: "+espm);
        			  
        			   MotorProducts motor= (MotorProducts) catalogService.findById("motorProducts", espm.get(0).getIdMotor().toString());
        			   System.out.println(motor);
        			   atributos.setNombre(motor.getName()!=null?motor.getName():"");
        			   break;
        		   case "3":
        			   System.out.println("oems");
        			   Oems oem=(Oems) catalogService.findById("oems", idN);
        			   System.out.println(oem);
        			   atributos.setNombre(oem.getOem()!= null?oem.getOem():"");
        			   break;
        		   case "4":
        			   System.out.println("canal");
        			   Aux_DRDLR canal=(Aux_DRDLR) findById("drdlr", idN, lang , flag);
        			   System.out.println(canal);
        			   atributos.setNombre(canal.getTipo()!= null?canal.getTipo():"");
        			   break;
        		   
        		   }
        		   
        		   lstAtributos.add(atributos);
        	   }
        	   MatrizPartesEncDetAtrDTO response=new MatrizPartesEncDetAtrDTO();
        	   System.out.println(listRegionP.size());
        	   response.setRegion(listRegionP.size()>0?listRegionP.get(0):null);
        	   response.setMatriz(hd);
        	   response.setDetalles(lstDet);
        	   response.setAtributos(lstAtributos);
               return response;
               
           case "matHtasObject":
        	   
        	   
        	   System.out.println("entra a mat HERRAMIENTAS");
        	   MatrizHtasHd hdH= matrizHtasHdDao.findOne(new BigInteger(id));
        	   MatrizHtasDet detH=new MatrizHtasDet();
        	   AtributosMatricesHtas atrH=new AtributosMatricesHtas();
        	   detH.setIdMatriz(new BigInteger(id));
        	   detH.setDel('N');
        	   atrH.setIdMatriz(new BigInteger(id));
        	   atrH.setDel('N');
        	   List<MatrizHtasDet> lstDetH= findMatrizHtasDetByCriteria(detH, lang, flag);
        	   List<AtributosMatricesHtas> lstAtrH= findAtributosMatricesHtasByCriteria(atrH);
        	   List<AtributoMatrizDTO> lstAtributosH= new ArrayList<>();
        	   List<Region> listRegion= new ArrayList<Region>();
        	   
        	   for(AtributosMatricesHtas atrr: lstAtrH) {
        		   String tipo= atrr.getTipoAtributo().toString();
        		   String idN=atrr.getIdNumber().toString();
        		   String idT=atrr.getIdText();
        		   AtributoMatrizDTO atributos=new AtributoMatrizDTO();
        		   atributos.setCreatedBy(atrr.getCreatedBy());
        		   atributos.setCreationDate(atrr.getCreationDate());
        		   atributos.setDel(atrr.getDel());
        		   atributos.setIdMatriz(atrr.getIdMatriz());
        		   atributos.setIdNumber(atrr.getIdNumber());
        		   atributos.setIdText(atrr.getIdText());
        		   atributos.setLastUpdateBy(atrr.getLastUpdateBy());
        		   atributos.setLastUpdateDate(atrr.getLastUpdateDate());
        		   atributos.setTipoAtributo(atrr.getTipoAtributo());
        		   atributos.setTipoMatriz(atrr.getTipoMatriz());
        		   switch(tipo) {
        		   case "1":
        			   System.out.println("spcodes");
        			   SpCodes spcode=(SpCodes) findById("spcodes", idT, lang, flag);
        			   if(spcode!= null) 
        			   {
        			       Boolean reg=false;
        			       for(Region r: listRegion) {
        				       if(spcode.getCountry().obtenerCountriesPerRegion().get(0).getRegion().getId()==r.getId()) 
        					       reg=true;
        			   }
        			   //System.out.println("la lista es: "+list);
        			       if(reg==false)
        				   listRegion.add(spcode.getCountry().obtenerCountriesPerRegion().get(0).getRegion());
        			   
        			   }
        			       atributos.setNombre(spcode!= null?spcode.getProviderName():"");
        			   break;
        		   case "2":
        			   System.out.println("modelos");
        			   ESPM filterEspm= new ESPM();
        			   filterEspm.setIdESPM(new BigInteger(idN));
        			   List<ESPM> espm=  (List<ESPM>) findESPMByCriteria(filterEspm);
        			  
        			   MotorProducts motor= (MotorProducts) catalogService.findById("motorProducts", espm.get(0).getIdMotor().toString());
        			   atributos.setNombre(motor.getName()!=null?motor.getName():"");
        			   break;
        		   case "3":
        			   System.out.println("oems");
        			   Oems oem=(Oems) catalogService.findById("oems", idN);
        			   atributos.setNombre(oem.getOem()!= null?oem.getOem():"");
        			   break;
        		   case "4":
        			   System.out.println("canal");
        			   Aux_DRDLR canal=(Aux_DRDLR) findById("drdlr", idN, lang, flag);
        			   atributos.setNombre(canal.getTipo()!= null?canal.getTipo():"");
        			   break;
        		   
        		   }
        		   
        		   lstAtributosH.add(atributos);
        	   }
        	   MatrizHtasEncDetAtrDTO responseH=new MatrizHtasEncDetAtrDTO();
        	   
        	   System.out.println(listRegion.toString());
        	   responseH.setRegion(listRegion.size()>0?listRegion.get(0):null);
        	   responseH.setMatriz(hdH);
        	   responseH.setDetalles(lstDetH);
        	   responseH.setAtributos(lstAtributosH);
               return responseH;
               
        	   
        	   /*List<ESPM> lista=espmDao.espmMotor();
        	   System.out.println(lista);*/
        	   //return lista;
        	   
            default:
                return null;
        }
    }
    
    
    

    /**
   	 * @desc metodo que permite guardar la entidad correspondiente
   	 * @param catalogue, entity, wwid -  String del case del catalogo, Objeto a mappear, String con el id de quien esta guardando 
   	 * @return Object - JSON de registro guardado
   	 * 
   	 * Nota: se debe quitar de cada caso lo siguiente, una vez que se tengan los Triggers en BD
   	 * 			dat = new Date();
                espmNs.setCreationDate(dat);
                espmNs.setCreatedBy(wwid);
                espmNs.setLastUpdateDate(dat);
                espmNs.setLastUpdatedBy(wwid);
   	*/
    @Override
    @Transactional
    public Object save(String catalogue, Object entity, String wwid) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("CATALOGO "+catalogue);
        switch (catalogue) {
            case "puestos":  
            	
                Aux_Puestos puesto = mapper.convertValue(entity, Aux_Puestos.class);
                puesto.setCreationDate(dat);
                puesto.setCreatedBy(wwid);
                puesto.setLastUpdateDate(dat);
                puesto.setLastUpdatedBy(wwid);
                return puestoDao.save(puesto);
            case "relpuestos":  
                Aux_RelPuestos relPuestos = mapper.convertValue(entity, Aux_RelPuestos.class);
                relPuestos.setCreationDate(dat);
                relPuestos.setCreatedBy(wwid);
                relPuestos.setLastUpdateDate(dat);
                relPuestos.setLastUpdatedBy(wwid);
                return relPuestoDao.save(relPuestos);
            case "drdlr":  
                Aux_DRDLR drdlr = mapper.convertValue(entity, Aux_DRDLR.class);
                drdlr.setCreationDate(dat);
                drdlr.setCreatedBy(wwid);
                drdlr.setLastUpdateDate(dat);
                drdlr.setLastUpdatedBy(wwid);
                return DRDLRDao.save(drdlr);
            case "pqs":  
                Pqs pqs = mapper.convertValue(entity, Pqs.class);
                System.out.println(pqs);
                Pqs pqsExist= pqsDao.findOne(pqs.getId());
                if(pqsExist != null) {
                	System.out.println("pqsExist"+pqsExist);
                pqs.setCreationDate(pqsExist.getCreationDate());
                pqs.setCreatedBy(pqsExist.getCreatedBy());
                pqs.setLastUpdateDate(dat);
                pqs.setLastUpdatedBy(wwid);
                System.out.println(pqs);
                for(PqsXpuestos p: pqs.getPuestos()) {
                    p.setLastUpdateDate(dat);
                    p.setLastUpdatedBy(wwid);
                    PqsXpuestos pxpFilter=new PqsXpuestos();
                    pxpFilter.setIdProgram(p.getIdProgram());
                    pxpFilter.setIdPuesto(p.getIdPuesto());
                    List<PqsXpuestos> pxpExist=findPQSXpuestosByCriteria(pxpFilter);
                	if(pxpExist.size()>0) {
                		p.setCreatedBy(pxpExist.get(0).getCreatedBy());
                		p.setCreationDate(pxpExist.get(0).getCreationDate());
                	}else {
                		p.setCreatedBy(wwid);
                		p.setCreationDate(dat);
                	}
                }
                }else {
                	pqs.setCreationDate(dat);
                    pqs.setCreatedBy(wwid);
                    pqs.setLastUpdateDate(dat);
                    pqs.setLastUpdatedBy(wwid);
                    for(PqsXpuestos p: pqs.getPuestos()) {
                    	
                    	p.setCreationDate(dat);
                        p.setCreatedBy(wwid);
                        p.setLastUpdateDate(dat);
                        p.setLastUpdatedBy(wwid);
                	
                    }
                }
                
                return pqsDao.save(pqs);
            case "pqsxpuesto":  
                PqsXpuestos pqsxpuestos = mapper.convertValue(entity, PqsXpuestos.class);
                pqsxpuestos.setCreationDate(dat);
                pqsxpuestos.setCreatedBy(wwid);
                pqsxpuestos.setLastUpdateDate(dat);
                pqsxpuestos.setLastUpdatedBy(wwid);
                return pqsxpuestosDao.save(pqsxpuestos);
            case "spcodes":  
                SpCodes spc = mapper.convertValue(entity, SpCodes.class);
                spc.setCreationDate(dat);
                spc.setCreatedBy(wwid);
                spc.setLastUpdateDate(dat);
                spc.setLastUpdateBy(wwid);
                return spcodeDao.save(spc);
            case "licenciasqsol":  
                LicenciasQsol licencia = mapper.convertValue(entity, LicenciasQsol.class);
                dat = new Date();
                licencia.setCreationDate(dat);
                licencia.setCreatedBy(wwid);
                licencia.setLastUpdateDate(dat);
                licencia.setLastUpdatedBy(wwid);
                return licenciasqsolDao.save(licencia);
            case "electronictools":  
                ElectronicTools electronicTools = mapper.convertValue(entity, ElectronicTools.class);
                electronicTools.setCreationDate(dat);
                electronicTools.setCreatedBy(wwid);
                electronicTools.setLastUpdateDate(dat);
                electronicTools.setLastUpdatedBy(wwid);
                return electronicDao.save(electronicTools);
            case "rescates":  
                Rescates rescate = mapper.convertValue(entity, Rescates.class);
                rescate.setCreationDate(dat);
	            rescate.setCreatedBy(wwid);
	            rescate.setLastUpdateDate(dat);
	            rescate.setLastUpdateBy(wwid);
	            return rescatesDao.save(rescate);
            case "nodisponible":  
                NoDisponible nodisp = mapper.convertValue(entity, NoDisponible.class);
                nodisp.setCreationDate(dat);
                nodisp.setCreatedBy(wwid);
                nodisp.setLastUpdateDate(dat);
                nodisp.setLastUpdatedBy(wwid);
                return nodisponibleDao.save(nodisp);
            case "promotion":  
                Promotion prom = mapper.convertValue(entity, Promotion.class);
                dat = new Date();
                prom.setCreationDate(dat);
                prom.setCreatedBy(wwid);
                prom.setLastUpdateDate(dat);
                prom.setLastUpdateBy(wwid);
                return promotionDao.save(prom);
            case "codigocta":  
                Aux_CC cc = mapper.convertValue(entity, Aux_CC.class);
                dat = new Date();
                cc.setCreationDate(dat);
                cc.setCreatedBy(wwid);
                cc.setLastUpdateDate(dat);
                cc.setLastUpdateBy(wwid);
                return ccDao.save(cc);
            case "statusgar":  
                Aux_StatusGar st = mapper.convertValue(entity, Aux_StatusGar.class);
                dat = new Date();
                st.setCreationDate(dat);
                st.setCreatedBy(wwid);
                st.setLastUpdateDate(dat);
                st.setLastUpdateBy(wwid);
                return statusgarDao.save(st);
            case "espm":  
                ESPM espm = mapper.convertValue(entity, ESPM.class);
                dat = new Date();
                espm.setCreationDate(dat);
                espm.setCreatedBy(wwid);
                espm.setLastUpdateDate(dat);
                espm.setLastUpdatedBy(wwid);
                
                return espmDao.save(espm);
            case "espmEmision":  
                ESPM_Emision espmEm = mapper.convertValue(entity, ESPM_Emision.class);
                dat = new Date();
                espmEm.setCreationDate(dat);
                espmEm.setCreatedBy(wwid);
                espmEm.setLastUpdateDate(dat);
                espmEm.setLastUpdatedBy(wwid);
                return espmEmisionDao.save(espmEm);
            case "espmRegion":  
                ESPM_Region espmRe = mapper.convertValue(entity, ESPM_Region.class);
                dat = new Date();
                espmRe.setCreationDate(dat);
                espmRe.setCreatedBy(wwid);
                espmRe.setLastUpdateDate(dat);
                espmRe.setLastUpdatedBy(wwid);
                return espmRegionDao.save(espmRe);
            case "espmNs":  
                ESPM_NS espmNs = mapper.convertValue(entity, ESPM_NS.class);
                dat = new Date();
                espmNs.setCreationDate(dat);
                espmNs.setCreatedBy(wwid);
                espmNs.setLastUpdateDate(dat);
                espmNs.setLastUpdatedBy(wwid);
                return espmNsDao.save(espmNs);
            case "partes":  
                Aux_Partes part = mapper.convertValue(entity, Aux_Partes.class);
                dat = new Date();
                part.setCreationDate(dat);
                part.setCreatedBy(wwid);
                part.setLastUpdateDate(dat);
                part.setLastUpdateBy(wwid);
                return partesDao.save(part);
            case "htas":  
                Aux_Htas htas = mapper.convertValue(entity, Aux_Htas.class);
                dat = new Date();
                htas.setCreationDate(dat);
                htas.setCreatedBy(wwid);
                htas.setLastUpdateDate(dat);
                htas.setLastUpdateBy(wwid);
                return htasDao.save(htas);
            case "tipos":  
                Aux_Tipo tip = mapper.convertValue(entity, Aux_Tipo.class);
                dat = new Date();
                tip.setCreationDate(dat);
                tip.setCreatedBy(wwid);
                tip.setLastUpdateDate(dat);
                tip.setLastUpdatedBy(wwid);
                
                return tipoDao.save(tip);
            case "statusMatriz":  
                Aux_StatusMatriz statMat = mapper.convertValue(entity, Aux_StatusMatriz.class);
                dat = new Date();
                statMat.setCreationDate(dat);
                statMat.setCreatedBy(wwid);
                statMat.setLastUpdateDate(dat);
                statMat.setLastUpdateBy(wwid);
                return statusMatrizDao.save(statMat);
            case "garantias":  
                Garantia gar = mapper.convertValue(entity, Garantia.class);
                dat = new Date();
                gar.setCreationDate(dat);
                gar.setCreatedBy(wwid);
                gar.setLastUpdateDate(dat);
                gar.setLastUpdatedBy(wwid);
                return garantiaDao.save(gar);
            case "matrizPartesHd":  
                MatrizPartesHd mph = mapper.convertValue(entity, MatrizPartesHd.class);
                dat = new Date();
                mph.setCreationDate(dat);
                mph.setCreatedBy(wwid);
                mph.setLastUpdateDate(dat);
                mph.setLastUpdatedBy(wwid);
                return matrizPartesHdDao.save(mph);
            case "matrizPartesDet":  
                MatrizPartesDet mpd = mapper.convertValue(entity, MatrizPartesDet.class);
                dat = new Date();
                mpd.setCreationDate(dat);
                mpd.setCreatedBy(wwid);
                mpd.setLastUpdateDate(dat);
                mpd.setLastUpdateBy(wwid);
                return matrizPartesDetDao.save(mpd);
            	
                
                //---------------------------------------------------------------------------------------------------//No
            /*case "atributosMatrices": 
            	System.out.println(entity);
                AtributosMatrices am = mapper.convertValue(entity, AtributosMatrices.class);
                dat = new Date();
                am.setCreationDate(dat);
                am.setCreatedBy(wwid);
                am.setLastUpdateDate(dat);
                am.setLastUpdatedBy(wwid);
                return atributosMatricesDao.save(am);*/
                
                
                //--------------------------------------------------------------------------
                //				GUARDADO MATRIZ PARTES, DETALLES Y ATRIBUTOS
                //--------------------------------------------------------------------------
            case "matPartesObject": 
            	dat=new Date();
            	JsonNode actualObj1 = null;
    			actualObj1 = mapper.convertValue(entity, JsonNode.class);
    			List<MatrizPartesDet> lstDetalle= new ArrayList<>();
    			List<MatrizPartesDet>lstFinal= new ArrayList<>();
    			// LEYENDO Y GUARDANDO ENCABEZADO
                MatrizPartesHd Enc = mapper.convertValue(actualObj1.get("matriz"), MatrizPartesHd.class);
                Enc.setCreationDate(dat);
                Enc.setCreatedBy(wwid);
                Enc.setLastUpdateDate(dat);
                Enc.setLastUpdatedBy(wwid);
                
                
			
                //MatrizPartesHd oHdMatriz= matrizPartesHdDao.save(Enc);
                MatrizPartesHd oHdMatriz= matrizPartesHdDao.save(Enc);
                System.out.println(Enc);
                MatrizPartesEncDetDTO response= new MatrizPartesEncDetDTO();
                BigInteger idMatriz=Enc.getIdMatriz();
                //System.out.println("El id de la matriz es: "+idMatriz);
                //System.out.println("Encabezado:       "+Enc);
                
                if(oHdMatriz.getIdMatriz() != null) {
                
                //LEYENDO DETALLES MATIZ
                for(JsonNode node:actualObj1.get("detalles")) {
                	MatrizPartesDet oDetalle=new MatrizPartesDet();
                	node.get("np");
                	oDetalle.setNp(node.get("np").toString().replaceAll("\"", ""));
                	oDetalle.setPonderacion(new Float(node.get("ponderacion").toString().replaceAll("\"", "")));
                	oDetalle.setIdMatriz(idMatriz);
                	oDetalle.setDel('N');
                	oDetalle.setQty(new BigInteger( node.get("qty").toString()));
                	oDetalle.setCreationDate(dat);
                    oDetalle.setCreatedBy(wwid);
                    oDetalle.setLastUpdateDate(dat);
                    oDetalle.setLastUpdateBy(wwid);
                    lstDetalle.add(oDetalle);
                }
                
                // GUARDANDO DETALLES MATRIZ
                
                for (MatrizPartesDet matriz: lstDetalle) {
                	MatrizPartesDet oMatDet =matrizPartesDetDao.save(matriz);
                	lstFinal.add(oMatDet);
                }
                
                
                }
                
                
                
                JsonNode atributos=actualObj1.get("atributos");
                String tipoMatriz=atributos.get("tipoMatriz").toString().replaceAll("\"", "");
                System.out.println("tipoMatriz: "+tipoMatriz);
                
                String[] dist=(atributos.get("distribuidor").toString().replaceAll("\"", "")).split(",");
                String[] mod=(atributos.get("modProductos").toString().replaceAll("\"", "")).split(",");
                String[] oem=(atributos.get("oems").toString().replaceAll("\"", "")).split(",");
                String[] can=(atributos.get("canal").toString().replaceAll("\"", "")).split(",");
                
                List<AtributosMatricesHtas>lstAtributos= new ArrayList<>();
                
                //ATRIBUTOS MATRIZ PARTES DISTRIBUIDORES
                for(String d:dist) {
                	System.out.println("distribuidor: "+d);
                	AtributosMatricesHtas att= new AtributosMatricesHtas();
                	att.setTipoAtributo(new BigInteger("1"));
                	att.setIdNumber(new BigInteger("0"));
                	att.setIdMatriz(idMatriz);
                	att.setTipoMatriz(new BigInteger(tipoMatriz));
                	att.setIdText(d);
                	att.setDel('N');
                	att.setCreationDate(dat);
                    att.setCreatedBy(wwid);
                    att.setLastUpdateDate(dat);
                    att.setLastUpdateBy(wwid);
                    //System.out.println(att.toString());
                	lstAtributos.add(atributosMatricesHtasDao.save(att));
                }
              //ATRIBUTOS MATRIZ PARTES MODELOS / PRODUCTOS
                for(String m:mod) {
                	System.out.println("modelos: "+m);
                	AtributosMatricesHtas att= new AtributosMatricesHtas();
                	att.setTipoAtributo(new BigInteger("2"));
                	att.setIdNumber(new BigInteger(m));
                	att.setIdMatriz(idMatriz);
                	att.setTipoMatriz(new BigInteger(tipoMatriz));
                	att.setIdText("NA");
                	att.setDel('N');
                	att.setCreationDate(dat);
                    att.setCreatedBy(wwid);
                    att.setLastUpdateDate(dat);
                    att.setLastUpdateBy(wwid);
                    //System.out.println(att.toString());
                	//lstAtributos.add(att);
                	lstAtributos.add(atributosMatricesHtasDao.save(att));
                }
              //ATRIBUTOS MATRIZ PARTES OEMS
                for(String o:oem) {
                	//System.out.println("oems: "+o);
                	AtributosMatricesHtas att= new AtributosMatricesHtas();
                	att.setTipoAtributo(new BigInteger("3"));
                	att.setIdNumber(new BigInteger(o));
                	att.setIdMatriz(idMatriz);
                	att.setTipoMatriz(new BigInteger(tipoMatriz));
                	att.setIdText("NA");
                	att.setDel('N');
                	att.setCreationDate(dat);
                    att.setCreatedBy(wwid);
                    att.setLastUpdateDate(dat);
                    att.setLastUpdateBy(wwid);
                    //System.out.println(att.toString());
                	//lstAtributos.add(att);
                	lstAtributos.add(atributosMatricesHtasDao.save(att));
                }
              //ATRIBUTOS MATRIZ PARTES CANAL
                for(String c:can) {
                	System.out.println("canal: "+c);
                	AtributosMatricesHtas att= new AtributosMatricesHtas();
                	att.setTipoAtributo(new BigInteger("4"));
                	att.setIdNumber(new BigInteger(c));
                	att.setIdMatriz(idMatriz);
                	att.setTipoMatriz(new BigInteger(tipoMatriz));
                	att.setIdText("NA");
                	att.setDel('N');
                	att.setCreationDate(dat);
                    att.setCreatedBy(wwid);
                    att.setLastUpdateDate(dat);
                    att.setLastUpdateBy(wwid);
                    //System.out.println(att.toString());
                	//lstAtributos.add(att);
                	lstAtributos.add(atributosMatricesHtasDao.save(att));
                }
                response.setDetalles(lstFinal);
                response.setMatriz(oHdMatriz);
                response.setAtributos(lstAtributos);
                
                return response;
                //--------------------------------------------------------------------------
                //				GUARDADO MATRIZ HERRAMIENTAS, DETALLES Y ATRIBUTOS
                //--------------------------------------------------------------------------
            case "matHtasObject" :
            dat=new Date();
            JsonNode JsonMatHtas = null;
            JsonMatHtas = mapper.convertValue(entity, JsonNode.class);
            System.out.println(JsonMatHtas);
			List<MatrizHtasDet> lstDetalleHtas= new ArrayList<>();
			List<MatrizHtasDet>lstFinalHtas= new ArrayList<>();
			// LEYENDO Y GUARDANDO ENCABEZADO
            MatrizHtasHd  EncHtas = mapper.convertValue(JsonMatHtas.get("matriz"), MatrizHtasHd.class);
            EncHtas.setCreationDate(dat);
            EncHtas.setCreatedBy(wwid);
            EncHtas.setLastUpdateDate(dat);
            EncHtas.setLastUpdateBy(wwid);
            
            
		
            //MatrizPartesHd oHdMatriz= matrizPartesHdDao.save(Enc);
            MatrizHtasHd  oHdMatrizHtas= matrizHtasHdDao.save(EncHtas);
            System.out.println(EncHtas);
            MatrizHtasEncDetDTO responseHtas= new MatrizHtasEncDetDTO();
            
            BigInteger idMatrizHtas=EncHtas.getIdMatriz();
            //System.out.println("El id de la matriz es: "+idMatriz);
            //System.out.println("Encabezado:       "+Enc);
            System.out.println("hasta aqui todo bien");
            if(idMatrizHtas != null) {
            System.out.println("entra a if");
            //LEYENDO DETALLES MATIZ
            for(JsonNode node:JsonMatHtas.get("detalles")) {
            	MatrizHtasDet oDetalle=new MatrizHtasDet();
            	node.get("np");
            	oDetalle.setNp(node.get("np").toString().replaceAll("\"", ""));
            	oDetalle.setPonderacion(new Float(node.get("ponderacion").toString().replaceAll("\"", "")));
            	oDetalle.setIdMatriz(idMatrizHtas);
            	oDetalle.setDel('N');
            	oDetalle.setQty(new BigInteger( node.get("qty").toString()));
            	oDetalle.setCreationDate(dat);
                oDetalle.setCreatedBy(wwid);
                oDetalle.setLastUpdateDate(dat);
                oDetalle.setLastUpdateBy(wwid);
                System.out.println(oDetalle);
                lstDetalleHtas.add(oDetalle);
            }
            
            // GUARDANDO DETALLES MATRIZ
            
            for (MatrizHtasDet mat: lstDetalleHtas) {
            	System.out.println(mat);
            	MatrizHtasDet oMatDet =matrizHtasDetDao.save(mat);
            	lstFinalHtas.add(oMatDet);
            }
            
            
            }
            
            
            
            JsonNode atributosHtas=JsonMatHtas .get("atributos");
            System.out.println(atributosHtas);
            String tipoMatrizHtas=atributosHtas.get("tipoMatriz").toString().replaceAll("\"", "");
            System.out.println(tipoMatrizHtas);
           
            System.out.println("tipoMatriz: "+tipoMatrizHtas);
            
            String[] distHtas=(atributosHtas.get("distribuidor").toString().replaceAll("\"", "")).split(",");
            String[] modHtas=(atributosHtas.get("modProductos").toString().replaceAll("\"", "")).split(",");
            String[] oemHtas=(atributosHtas.get("oems").toString().replaceAll("\"", "")).split(",");
            String[] canHtas=(atributosHtas.get("canal").toString().replaceAll("\"", "")).split(",");
            
            List<AtributosMatricesHtas>lstAtributosHtas= new ArrayList<>();
            
          
            for(String d:distHtas) {
            	System.out.println("distribuidor: "+d);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("1"));
            	att.setIdNumber(new BigInteger("0"));
            	att.setIdMatriz(idMatrizHtas);
            	att.setTipoMatriz(new BigInteger(tipoMatrizHtas));
            	att.setIdText(d);
            	att.setDel('N');
            	att.setCreationDate(dat);
                att.setCreatedBy(wwid);
                att.setLastUpdateDate(dat);
                att.setLastUpdateBy(wwid);
                System.out.println(att.toString());
            	lstAtributosHtas.add(atributosMatricesHtasDao.save(att));
            }
          
            for(String mhtas:modHtas) {
            	System.out.println("modelos: "+mhtas);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("2"));
            	att.setIdNumber(new BigInteger(mhtas));
            	att.setIdMatriz(idMatrizHtas);
            	att.setTipoMatriz(new BigInteger(tipoMatrizHtas));
            	att.setIdText("NA");
            	att.setDel('N');
            	att.setCreationDate(dat);
                att.setCreatedBy(wwid);
                att.setLastUpdateDate(dat);
                att.setLastUpdateBy(wwid);
                System.out.println(att.toString());
            	lstAtributosHtas.add(atributosMatricesHtasDao.save(att));
            }
            for(String oh:oemHtas) {
            	System.out.println("oems: "+oh);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("3"));
            	att.setIdNumber(new BigInteger(oh));
            	att.setIdMatriz(idMatrizHtas);
            	att.setTipoMatriz(new BigInteger(tipoMatrizHtas));
            	att.setIdText("NA");
            	att.setDel('N');
            	att.setCreationDate(dat);
                att.setCreatedBy(wwid);
                att.setLastUpdateDate(dat);
                att.setLastUpdateBy(wwid);
                System.out.println(att.toString());
            	lstAtributosHtas.add(atributosMatricesHtasDao.save(att));
            }
            for(String ch:canHtas) {
            	System.out.println("canal: "+ch);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("4"));
            	att.setIdNumber(new BigInteger(ch));
            	att.setIdMatriz(idMatrizHtas);
            	att.setTipoMatriz(new BigInteger(tipoMatrizHtas));
            	att.setIdText("NA");
            	att.setDel('N');
            	att.setCreationDate(dat);
                att.setCreatedBy(wwid);
                att.setLastUpdateDate(dat);
                att.setLastUpdateBy(wwid);
                System.out.println(att.toString());
            	lstAtributosHtas.add(atributosMatricesHtasDao.save(att));
            }
            responseHtas.setDetalles(lstFinalHtas);
            responseHtas.setMatriz(oHdMatrizHtas);
            responseHtas.setAtributos(lstAtributosHtas);
            return responseHtas;
            
            
            //----------------------------------------------------------------
            case "matrizHtasHd":  
                MatrizHtasHd mhh = mapper.convertValue(entity, MatrizHtasHd.class);
                dat = new Date();
                mhh.setCreationDate(dat);
                mhh.setCreatedBy(wwid);
                mhh.setLastUpdateDate(dat);
                mhh.setLastUpdateBy(wwid);
                return matrizHtasHdDao.save(mhh);
            case "matrizHtasDet":  
                MatrizHtasDet mhd = mapper.convertValue(entity, MatrizHtasDet.class);
                dat = new Date();
                mhd.setCreationDate(dat);
                mhd.setCreatedBy(wwid);
                mhd.setLastUpdateDate(dat);
                mhd.setLastUpdateBy(wwid);
                return matrizHtasDetDao.save(mhd);
            case "atributosMatricesHtas":  
                AtributosMatricesHtas amh = mapper.convertValue(entity, AtributosMatricesHtas.class);
                dat = new Date();
                amh.setCreationDate(dat);
                amh.setCreatedBy(wwid);
                amh.setLastUpdateDate(dat);
                amh.setLastUpdateBy(wwid);
                return atributosMatricesHtasDao.save(amh);
            case "confMotor":  
                ConfMotor cm = mapper.convertValue(entity, ConfMotor.class);
                dat = new Date();
                cm.setCreationDate(dat);
                cm.setCreatedBy(wwid);
                cm.setLastUpdateDate(dat);
                cm.setLastUpdatedBy(wwid);
                return confmotorDao.save(cm);
            case "confSpcodes":  
                ConfSpcodes csp = mapper.convertValue(entity, ConfSpcodes.class);
                dat = new Date();
                csp.setCreationDate(dat);
                csp.setCreatedBy(wwid);
                csp.setLastUpdateDate(dat);
                csp.setLastUpdateBy(wwid);
                return confspcodesDao.save(csp);
            case "confOem":  
                ConfOem co = mapper.convertValue(entity, ConfOem.class);
                dat = new Date();
                co.setCreationDate(dat);
                co.setCreatedBy(wwid);
                co.setLastUpdateDate(dat);
                co.setLastUpdateBy(wwid);
                return confoemDao.save(co);
            case "tipoQ":  
                Aux_TipoQ tq = mapper.convertValue(entity, Aux_TipoQ.class);
                dat = new Date();
                tq.setCreationDate(dat);
                tq.setCreatedBy(wwid);
                tq.setLastUpdateDate(dat);
                tq.setLastUpdatedBy(wwid);
                return auxtipoqDao.save(tq);
            case "relModulos":  
                Aux_RelModulos rm = mapper.convertValue(entity, Aux_RelModulos.class);
                dat = new Date();
                rm.setCreationDate( dat);
                rm.setCreatedBy(wwid);
                rm.setLastUpdateDate(dat);
                rm.setLastUpdateBy(wwid);
                return relModulosDao.save(rm);
            case "opciones":  
                Aux_Opciones op = mapper.convertValue(entity, Aux_Opciones.class);
                dat = new Date();
                op.setCreationDate( dat);
                op.setCreatedBy(wwid);
                op.setLastUpdateDate(dat);
                op.setLastUpdateBy(wwid);
                return opcionesDao.save(op);
            case "statusEv":  
                Aux_StatusEv statusEv = mapper.convertValue(entity, Aux_StatusEv.class);
                dat = new Date();
                statusEv.setCreationDate( dat);
                statusEv.setCreatedBy(wwid);
                statusEv.setLastUpdateDate(dat);
                statusEv.setLastUpdateBy(wwid);
                return statusEvDao.save(statusEv);
            

            default:
                return "No se encontro catalogo";
        }
    }
    
    /**
   	 * @desc metodo que permite eliminar registros en la Tabla a la que hace referencia una entidad (No se usa en la vista,
   	 *  solo es para control directo en POSTMAN)
   	 * @param catalogo, id - String con el nombre del catalogo (entidad) a consultar y id del registro a eliminar
   	*/
    
    @Override
    @Transactional
    public void delete(String catalogue, String id) {
        switch (catalogue) {
			case "puestos":  
                puestoDao.delete(new BigInteger(id));              
                break;
            case "relpuestos":                
                relPuestoDao.delete(new BigInteger(id));
                break;
            case "drdlr":                
                DRDLRDao.delete(new BigInteger(id));
                break;
            case "pqs":                
                pqsDao.delete(id);
                break;
            case "pqsxpuesto":                
                pqsxpuestosDao.delete(new BigInteger(id));
                break;
            case "spcodes":                
                spcodeDao.delete(id);
                break;
            case "licenciasqsol":                
                licenciasqsolDao.delete(id);
                break;
            case "electronic":                
                electronicDao.delete(id);
                break;
            case "rescates":                
                rescatesDao.delete(id);
                break;
            case "nodisponible":                
                nodisponibleDao.delete(id);
                break;
            case "promotion":                
                promotionDao.delete(id);
                break;
            case "codigocta":                
                ccDao.delete(id);
                break;
            case "statusgar":                
                statusgarDao.delete(new BigInteger(id));
                break;
            case "espm":                
                espmDao.delete(new BigInteger(id));
                break;
            case "espmEmision":                
                espmEmisionDao.delete(new BigInteger(id));
                break;
            case "espmRegion":                
                espmRegionDao.delete(new BigInteger(id));
                break;
            case "espmNs":                
                espmNsDao.delete(new BigInteger(id));
                break;
            case "partes":                
                partesDao.delete(id);
                break;
            case "htas":                
                htasDao.delete(id);
                break;
            case "tipos":                
                tipoDao.delete(new BigInteger(id));
                break;
            case "statusMatriz":                
                statusMatrizDao.delete(new BigInteger(id));
                break;
            case "garantias":                
                garantiaDao.delete(id);
                break;
            case "matrizPartesHd":                
                matrizPartesHdDao.delete(new BigInteger(id));
                break;
            case "matrizPartesDet":                
                matrizPartesDetDao.delete(new BigInteger(id));
                break;
            case "atributosMatrices":                
                atributosMatricesDao.delete(new BigInteger(id));
                break;
            case "matrizHtasHd":                
                matrizHtasHdDao.delete(new BigInteger(id));
                break;
            case "matrizHtasDet":                
                matrizHtasDetDao.delete(new BigInteger(id));
                break;
            case "atributosMatricesHtas":                
                atributosMatricesHtasDao.delete(new BigInteger(id));
                break;
            case "confMotor":                
                confmotorDao.delete(new BigInteger(id));
                break;
            case "confSpcodes":                
                confspcodesDao.delete(new BigInteger(id));
                break;
            case "confOem":                
                confoemDao.delete(new BigInteger(id));
                break;
            case "tipoQ":                
                auxtipoqDao.delete(new BigInteger(id));
                break;
            case "relModulos":                
                relModulosDao.delete(new BigInteger(id));
            case "opciones":                
                opcionesDao.delete(new BigInteger(id));
                break;
            case "statusEv":                
                statusEvDao.delete(new BigInteger(id));
                break;
            
			default:
                break;
        }
    }
    
    

    /**
   	 * @desc metodo que permite filtrar registros en la Tabla a la que hace referencia una entidad dado el catalogo
   	 * @param catalogo, filter - String con el nombre del catalogo (entidad) a consultar y 
   	 * Objeto JSON que se va a  mappear segun al catalogo que corresponda para comparar con los registros
   	 * @return Object - JSON de registros que cumplan el filtrado
   	*/
    @Override
    public Object findByCriteria(String catalogue, Object filter, String lang, Boolean flag) {
        switch (catalogue) {
            case "puestos":                
                return findPuestoByCriteria(filter,lang, flag);
            case "relpuestos":                
                return findRelPuestosByCriteria(filter);
            case "drdlr":                
                return findDRDLRByCriteria(filter, lang, flag);
            case "pqs":                
                return findPQSByCriteria(filter,lang, flag);
            case "pqsxpuesto":                
                return findPQSXpuestosByCriteria(filter);
            case "spcodes":                
                return findSPCODESByCriteria(filter, lang, flag);
            case "licenciasqsol":                
                return findLicenciasQsolByCriteria(filter);
            case "electronictools":                
                return findElectronicToolsByCriteria(filter);
            case "rescates":                
                return findRescatesByCriteria(filter);
            case "nodisponible":                
                return findNoDisponibleByCriteria(filter);
            case "promotion":                
                return findPromotionByCriteria(filter);
            case "codigocta":                
                return findCCByCriteria(filter);
            case "statusgar":                
                return findStatusGarByCriteria(filter, lang, flag);
            case "espm":                
                return findESPMByCriteria(filter);
            case "espmEmision":                
                return findESPMEmisionByCriteria(filter);
            case "espmRegion":                
                return findESPMRegionByCriteria(filter);
            case "espmNs":                
                return findESPMNSByCriteria(filter);
                
            case "partes":                
                return findPartesByCriteria(filter, lang, flag);
            case "htas":                
                return findHtasByCriteria(filter, lang, flag);
            case "tipos":                
                return findTiposByCriteria(filter);
            case "statusMatriz":                
                return findStatusMatrizByCriteria(filter, lang, flag);
            case "garantias":                
                return findGarantiasByCriteria(filter, lang,flag);
            case "matrizPartesHd":                
                return findMatrizPartesHdByCriteria(filter,lang,flag);
            case "matrizPartesDet":                
                return findMatrizPartesDetByCriteria(filter,lang, flag);
            case "atributosMatrices":                
                return findAtributosMatricesByCriteria(filter);
            case "matrizHtasHd":                
                return findMatrizHtasHdByCriteria(filter,lang , flag);
            case "matrizHtasDet":                
                return findMatrizHtasDetByCriteria(filter, lang, flag);
            case "atributosMatricesHtas":                
                return findAtributosMatricesHtasByCriteria(filter);
            case "confMotor":                
                return findConfMotorByCriteria(filter);
            case "confSpcodes":                
                return findConfSpcodesByCriteria(filter);
            case "confOem":                
                return findConfOemByCriteria(filter);
            case "tipoQ":                
                return findTipoQByCriteria(filter);
            case "relModulos":                
                return findRelModulosByCriteria(filter);
            case "opciones":                
                return findOpcionesByCriteria(filter);
            case "statusEv":                
                return findStatusEvByCriteria(filter, lang, flag);
            default:
                return null;
        }
	}
    
    

    /**
   	 * @desc metodo que permite filtrar registros en la Tabla a la que hace referencia una entidad dado el catalogo
   	 * @param catalogo, filter - String con el nombre del catalogo (entidad) a consultar y 
   	 * Objeto JSON que se va a  mappear segun al catalogo que corresponda para comparar con los registros
   	 * @return Object - JSON de registros que cumplan el filtrado
   	 * 
   	 * 
   	 * NOTA: Es practicamente igual que findByCriteria, por lo que se puede eliminar gran parte de estos meytodos haciendo 
   	 * 		 reajuste a las llamadas en el frontend, dejarlo hasta posible refactorización futura
   	 * 
   	*/
    
    @Override
    public Object findExisting(String catalogue, Object filter) {
        switch (catalogue) {
            case "puestos":                
                return findPuestoExisting(filter);
            case "relpuestos":                
                return findRelPuestosByCriteria(filter);
            case "drdlr":                
                return findDRDLRExisting(filter);
            case "pqs":                
                return findPQSExisting(filter);
            case "pqsxpuesto":                
                return findPQSXpuestosExisting(filter);
            case "spcodes":                
                return findSPCODESExisting(filter);
            case "licenciasqsol":                
                return findLicenciasQsolExisting(filter);
            case "electronictools":                
                return findElectronicToolsExisting(filter);
            case "rescates":                
                return findRescatesExisting(filter);         
            case "nodisponible":                
                return findNoDisponibleExisting(filter);
            case "promotion":                
                return findPromotionExisting(filter);
            case "codigocta":                
                return findCCExisting(filter);
            case "statusgar":                
                return findStatusGarExisting(filter);
            case "espm":                
                return findESPMExisting(filter);
            case "espmEmision":                
                return findESPMEmisionExisting(filter);
            case "espmRegion":                
                return findESPMRegionExisting(filter);
            case "espmNs":                
                return findESPMNSExisting(filter);
            case "partes":                
                return findPartesExisting(filter);
            case "tipos":                
                return findTiposExisting(filter);
            case "statusMatriz":                
                return findStatusMatrizExisting(filter);
            case "garantias":                
                return findGarantiasExisting(filter);
            case "matrizPartesHd":                
                return findMatrizPartesHdExisting(filter);
            case "matrizPartesDet":                
                return findMatrizPartesDetExisting(filter);
            case "atributosMatrices":                
                return findAtributosMatricesExisting(filter);
            case "matrizHtasHd":                
                return findMatrizHtasHdExisting(filter);
            case "matrizHtasDet":                
                return findMatrizHtasDetExisting(filter);
            case "atributosMatricesHtas":                
                return findAtributosMatricesHtasExisting(filter);
            case "confMotor":                
                return findConfMotorExisting(filter);
            case "confSpcodes":                
                return findConfSpcodesExisting(filter);
            case "confOem":                
                return findConfOemExisting(filter);
            case "tipoQ":                
                return findTipoQExisting(filter);
            case "relModulos":                
                return findRelModulosExisting(filter);
            case "opciones":                
                return findOpcionesExisting(filter);
            case "statusEv":                
                return findStatusEvExisting(filter);
            
            default:
                return null;
        }
    }
    
// -------------------------------------------------- COMIENZAN LOS FILTRADOS 
    /**
   	 * @desc metodos que permite filtrar catalogos dados ciertos parametros
   	 * @param entityFilter - Objecto que se mappea a tipo correspondiente para despues
   	 * comparar con JPA en todos los registros de la entidad HeaderPaso y agregar 
   	 * al return el que cumple con los parametros
   	 * @return Object - JSON de todos los registros que coinciden con el filtrado
   	*/
	public List<Aux_Puestos> findPuestoByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_Puestos filter = mapper.convertValue(entityFilter, Aux_Puestos.class);
      
        List<Aux_Puestos> puestos= puestoDao.findAll( new  Specification<Aux_Puestos>() {

			@Override
			public Predicate toPredicate(Root<Aux_Puestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				
				if(filter.getId() != null) {
					predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getId())));
				}				
				if(filter.getPuesto() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("puesto")), "%" + filter.getPuesto().toUpperCase() + "%")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                    predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "puesto"));
        if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<puestos.size();i++) {
		        		puestos.get(i).setPuesto(puestos.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<puestos.size();i++) {
		        		puestos.get(i).setPuesto(puestos.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      puestos.sort(Comparator.comparing(Aux_Puestos::getPuesto));
      return puestos;
    }
    public List<Aux_RelPuestos> findRelPuestosByCriteria(Object entityFilter){
        
        Aux_RelPuestos filter = mapper.convertValue(entityFilter, Aux_RelPuestos.class);
     /*  List<Calendario> list = new ArrayList<Calendario>();
       list.add(filter);
       return list;*/
       return relPuestoDao.findAll(new  Specification<Aux_RelPuestos>() {

           @Override
           public Predicate toPredicate(Root<Aux_RelPuestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               
               if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getId())));
               }
               if(filter.getTitulo() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("titulo")), "%" + filter.getTitulo().toString().toUpperCase() + "%")));
            }
               if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
               }
               
               if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
               }
               
               if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
               }
               
               if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
               }
               if(filter.getDelete() != null) {
                    predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
                }              
              
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }           
       }, new Sort(Sort.Direction.ASC, "titulo")); 
     
    
    }
    public List<Aux_DRDLR> findDRDLRByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_DRDLR filter = mapper.convertValue(entityFilter, Aux_DRDLR.class);
     
       List<Aux_DRDLR> drdlr =DRDLRDao.findAll( new  Specification<Aux_DRDLR>() {

           @Override
           public Predicate toPredicate(Root<Aux_DRDLR> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("id"), filter.getId())));
               }				
               if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("tipo")), "%" + filter.getTipo().toUpperCase() + "%")));
               }
               if(filter.getEnglish() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
               if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
               }                
               if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
               }                
               if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
               }                
               if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
               }
               if(filter.getDelete() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
               }				
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<drdlr.size();i++) {
		        		drdlr.get(i).setTipo(drdlr.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<drdlr.size();i++) {
		        		drdlr.get(i).setTipo(drdlr.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
       }
       drdlr.sort(Comparator.comparing(Aux_DRDLR::getTipo));
       return drdlr;
    }   
  
    public List<Pqs> findPQSByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Pqs filter = mapper.convertValue(entityFilter, Pqs.class);
     
        List<Pqs> pqs= pqsDao.findAll( new  Specification<Pqs>() {

           @Override
           public Predicate toPredicate(Root<Pqs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("programId")), "%" + filter.getId().toUpperCase() + "%")));
                }				
                if(filter.getNombre() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("nombre")), "%" + filter.getNombre().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "programId")
            .and(new Sort(Sort.Direction.ASC, "nombre")));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<pqs.size();i++) {
		        		for(PqsXpuestos pqxp:pqs.get(i).getPuestos()) {
		        			pqxp.getPuesto().setPuesto(pqxp.getPuesto().getEnglish());
		        			
		        		}
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<pqs.size();i++) {
		        		for(PqsXpuestos pqxp:pqs.get(i).getPuestos()) {
		        			pqxp.getPuesto().setPuesto(pqxp.getPuesto().getPortuguese());
		        			
		        		}
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    pqs.sort(Comparator.comparing(Pqs::getId).thenComparing(Pqs::getNombre));
    return pqs;



    
    } 
    public List<PqsXpuestos> findPQSXpuestosByCriteria(Object entityFilter){
        
        PqsXpuestos filter = mapper.convertValue(entityFilter, PqsXpuestos.class);
     
       return pqsxpuestosDao.findAll( new  Specification<PqsXpuestos>() {

           @Override
           public Predicate toPredicate(Root<PqsXpuestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdProgram() != null) {
                   predicates.add(cb.and(cb.like(root.get("programId"), "%" + filter.getIdProgram().toUpperCase() + "%")));
                }				
                if(filter.getIdPuesto() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("idPuesto")), filter.getIdPuesto() )));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "programId"));
    } 
    public List<SpCodes> findSPCODESByCriteria(Object entityFilter, String lang, Boolean flag){
        
        SpCodes filter = mapper.convertValue(entityFilter, SpCodes.class);
     
        List<SpCodes> spcodes= spcodeDao.findAll( new  Specification<SpCodes>() {

           @Override
           public Predicate toPredicate(Root<SpCodes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.like(root.get("spCode"), "%" + filter.getSpCode().toUpperCase() + "%")));
                }				
                if(filter.getProviderName() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("providerName")), "%" + filter.getProviderName().toUpperCase() + "%")));
                }
                if(filter.getTipo() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idTipo")),filter.getTipo())));
                }
                if(filter.getResponsibleBranchCode() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("responsibleBranchCode")), "%" + filter.getResponsibleBranchCode().toUpperCase() + "%")));
                }
                if(filter.getIdPais() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idPais")),filter.getIdPais())));
                }
                if(filter.getIdOem() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idOem")),filter.getIdOem())));
                }
                if(filter.getAdd() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("add")), "%" + filter.getAdd().toUpperCase() + "%")));
                }
                if(filter.getCity() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("city")), "%" + filter.getCity().toUpperCase() + "%")));
                }
                if(filter.getIso() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("iso")), "%" + filter.getIso().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<spcodes.size();i++) {
		        		spcodes.get(i).getDRDLR().setTipo(spcodes.get(i).getDRDLR().getEnglish());
		        		spcodes.get(i).getOEM().setOem(spcodes.get(i).getOEM().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<spcodes.size();i++) {
		        		spcodes.get(i).getDRDLR().setTipo(spcodes.get(i).getDRDLR().getPortuguese());
		        		spcodes.get(i).getOEM().setOem(spcodes.get(i).getOEM().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
     }
     spcodes.sort(Comparator.comparing(SpCodes::getSpCode));
     return spcodes;
       
    } 
    public List<LicenciasQsol> findLicenciasQsolByCriteria(Object entityFilter){
        
        LicenciasQsol filter = mapper.convertValue(entityFilter, LicenciasQsol.class);
     
        return licenciasqsolDao.findAll( new  Specification<LicenciasQsol>() {

           @Override
           public Predicate toPredicate(Root<LicenciasQsol> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.like(root.get("spCode"), "%" + filter.getSpCode().toUpperCase() + "%")));
                }				
                if(filter.getFechaRegistro() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("fechaRegistro")), "%" + filter.getFechaRegistro().toString().toUpperCase() + "%")));
                }
                if(filter.getFechaExpiracion() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("fechaExpiracion")), "%" + filter.getFechaExpiracion().toString().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
    }
    public List<ElectronicTools> findElectronicToolsByCriteria(Object entityFilter){
        
        ElectronicTools filter = mapper.convertValue(entityFilter, ElectronicTools.class);
     
       return electronicDao.findAll( new  Specification<ElectronicTools>() {

           @Override
           public Predicate toPredicate(Root<ElectronicTools> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.like(root.get("idTool"), "%" + filter.getId().toUpperCase() + "%")));
                }				
                if(filter.getSPCode() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSPCode().toUpperCase() + "%")));
                }
                if(filter.getNP() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("numeroParte")), "%" + filter.getNP().toUpperCase() + "%")));
                }
                if(filter.getFechaReg() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("fechaRegistro")), "%" + filter.getFechaReg().toString().toUpperCase() + "%")));
                }
                if(filter.getFechaExp() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("fechaExpiracion")), "%" + filter.getFechaExp().toString().toUpperCase() + "%")));
                 }         
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
    }

    public List<Rescates> findRescatesByCriteria(Object entityFilter){
        
        Rescates filter = mapper.convertValue(entityFilter, Rescates.class);
     
       return rescatesDao.findAll( new  Specification<Rescates>() {

           @Override
           public Predicate toPredicate(Root<Rescates> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               
               if(filter.getFolio() != null) {
              	 predicates.add(cb.and(cb.like(cb.upper(root.get("folio")), "%" + filter.getFolio().toUpperCase() + "%")));
               }		
                
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSpCode().toUpperCase() + "%")));
                }
                if(filter.getFechaFalla() != null) {
            	  	predicates.add(cb.and(cb.equal(root.get("fechaFalla"), filter.getFechaFalla())));
              	}
                if(filter.getCliente() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("cliente")), "%" + filter.getCliente().toUpperCase() + "%")));
                }
                if(filter.getTiempoRespuesta() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("tiempoRespuesta")), filter.getTiempoRespuesta())));
                }
                if(filter.getTiempoReparacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tiempoReparacion"), filter.getTiempoReparacion())));
                }
                if(filter.getTiempoMuerto() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("tiempoMuerto")), filter.getTiempoMuerto())));
                }
                if(filter.getMotivo() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("motivo")), "%" + filter.getMotivo().toUpperCase() + "%")));
                 }           
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null) {
            	  	predicates.add(cb.and(cb.equal(root.get("lastUpdateDate"), filter.getLastUpdateDate())));
              	}             
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }		
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
    }
    public List<NoDisponible> findNoDisponibleByCriteria(Object entityFilter){
    	/*
    	NoDisponible filter = mapper.convertValue(entityFilter, NoDisponible.class);
    	List<NoDisponible> list = new ArrayList<NoDisponible>();
    	
    	int page = 0 + 1;
    	int size = 5;
    	int total = 3;
    	double count = Math.ceil(total / 2);
    	
    	int endLimit = size * page; 
    	int startLimit = endLimit - (size-1);
    	
    	Connection connection= null;
    	
    	//Obtiene BU
        System.out.println("Conexión");
		PreparedStatement cStatement = null;
		ResultSet detalles =  null;
    	
    	try {
			connection = ds.getConnection();
    		//String sql = "INSERT INTO zpet9301_upload_file(DATA,NOMBRE_ARCHIVO,EXTENSION_ARCHIVO) VALUES (?,?,?)";
			String sqlUp = "SELECT * FROM ZMKT7352_DES_NODISP WHERE FOLIO LIKE ? LIMIT 1";
	        cStatement = connection.prepareStatement(sqlUp);
	       
	        cStatement.setString(1, "%"+ filter.getFolio() +"%");
	        detalles = cStatement.executeQuery();
	        System.out.println("Ejecutando");
	        
	        while(detalles.next()) {
	        	NoDisponible d = new NoDisponible();
	        	d.setFolio(detalles.getString("FOLIO"));
	        	d.setCliente(detalles.getString("CLIENTE"));
	        	
	        	list.add(d);
	        }
	        
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
    	
    	return list;
        */
        NoDisponible filter = mapper.convertValue(entityFilter, NoDisponible.class);
        
          
       return nodisponibleDao.findAll( new  Specification<NoDisponible>() {

           @Override
           public Predicate toPredicate(Root<NoDisponible> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
             if(filter.getFolio() != null) {
            	 predicates.add(cb.and(cb.like(cb.upper(root.get("folio")), "%" + filter.getFolio().toUpperCase() + "%")));
             }		
             if(filter.getCliente() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("cliente")), "%" + filter.getCliente().toUpperCase() + "%")));
             }
             if(filter.getFecha() != null) {
                 predicates.add(cb.and(cb.equal(root.get("fecha"), filter.getFecha())));
              }	
             if(filter.getCode() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getCode().toUpperCase() + "%")));
             }
             
             if(filter.getRazon() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("razon")), "%" + filter.getRazon().toUpperCase() + "%")));
              }           
             if(filter.getCreatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
             }                
                      
             if(filter.getLastUpdatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
             }
             if(filter.getDel() != null) {
                predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
             }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
      
    }
    
    @Override
    public List<Promotion> findPromotionByCriteria(Object entityFilter){
        
        Promotion filter = mapper.convertValue(entityFilter, Promotion.class);
     
       return promotionDao.findAll( new  Specification<Promotion>() {

           @Override
           public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
             
             if(filter.getPromotionId() != null) {
                predicates.add(cb.and(cb.like(root.get("promotionId"), "%" + filter.getPromotionId().toUpperCase() + "%")));
             }				
             if(filter.getSpCode() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSpCode().toUpperCase() + "%")));
             }
             if(filter.getProgramId() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("programId")), "%" + filter.getProgramId().toUpperCase() + "%")));
             }
             
             if(filter.getFirstName() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("firstName")), "%" + filter.getFirstName().toUpperCase() + "%")));
             }
             
            if(filter.getMiddleName() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("middleName")), "%" + filter.getMiddleName().toUpperCase() + "%")));
            } 
            if(filter.getLastName() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastName")), "%" + filter.getLastName().toUpperCase() + "%")));
            }
            if(filter.getJobTitle() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("jobTitle")), "%" + filter.getJobTitle().toUpperCase() + "%")));
            }
            if(filter.getDatePassed() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("datePassed")), "%" + filter.getDatePassed().toString().toUpperCase() + "%")));
           }                
            if(filter.getCreatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateDate() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
             }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "promotionId"));
    }
    @Override
    public List<Aux_CC> findCCByCriteria(Object entityFilter){
        
        Aux_CC filter = mapper.convertValue(entityFilter, Aux_CC.class);
     
       return ccDao.findAll( new  Specification<Aux_CC>() {

           @Override
           public Predicate toPredicate(Root<Aux_CC> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getCodigoCta() != null) {
                predicates.add(cb.and(cb.equal(root.get("codigoCta"), filter.getCodigoCta() )));
             }				
                            
            if(filter.getCreatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateDate() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
             }
             if(filter.getDel() != null) {
                predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
             }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "codigoCta"));
    }
    public List<Aux_StatusGar> findStatusGarByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_StatusGar filter = mapper.convertValue(entityFilter, Aux_StatusGar.class);
     
       List<Aux_StatusGar> status= statusgarDao.findAll( new  Specification<Aux_StatusGar>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusGar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdstatus() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idstatus"), filter.getIdstatus())));
                }				
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("status")), "%" + filter.getStatus().toUpperCase() + "%")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<status.size();i++) {
		        		status.get(i).setStatus(status.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<status.size();i++) {
		        		status.get(i).setStatus(status.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      status.sort(Comparator.comparing(Aux_StatusGar::getStatus));
      return status;
    }
    
    
    @Override
   public List<Aux_RelModulos> findRelModulosByCriteria(Object entityFilter){
        
        Aux_RelModulos filter = mapper.convertValue(entityFilter, Aux_RelModulos.class);
     
       return relModulosDao.findAll( new  Specification<Aux_RelModulos>() {

           @Override
           public Predicate toPredicate(Root<Aux_RelModulos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdModulo() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idModulo"), filter.getIdModulo())));
                }				
                if(filter.getIdSubmodulo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("idSubmodulo")),  filter.getIdSubmodulo())));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idModulo"));
    }
    
   @Override
   public List<Aux_Opciones> findOpcionesByCriteria(Object entityFilter){
        
        Aux_Opciones filter = mapper.convertValue(entityFilter, Aux_Opciones.class);
     
       return opcionesDao.findAll( new  Specification<Aux_Opciones>() {

           @Override
           public Predicate toPredicate(Root<Aux_Opciones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdOpcion() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idOpcion"), filter.getIdOpcion())));
                }				
                if(filter.getOpcion() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("opcion")),"%" + filter.getOpcion().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idOpcion"));
    }

   
    @Override
    public Aux_StatusGar findStatusGarByName(Object entityFilter){
        
        Aux_StatusGar filter = mapper.convertValue(entityFilter, Aux_StatusGar.class);
     
       List<Aux_StatusGar> list = statusgarDao.findAll( new  Specification<Aux_StatusGar>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusGar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               	
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("status")), "%" + filter.getStatus().toUpperCase() + "%")));
                }
                
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
       if(list.size()>0)
    	   return list.get(0);
       else
    	   return null;
       
    }
    
    
    public List<ESPM> findESPMByCriteria(Object entityFilter){
        
        ESPM filter = mapper.convertValue(entityFilter, ESPM.class);
     
       return espmDao.findAll( new  Specification<ESPM>() {

           @Override
           public Predicate toPredicate(Root<ESPM> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdMotor() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idMotor"), filter.getIdMotor())));
                 }
                 if(filter.getIdRango() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRango"), filter.getIdRango())));
                 }
                 if(filter.getIdApp() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idApp"), filter.getIdApp())));
                 }	
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    
    @Override
    public List<ESPM_Emision> findESPMEmisionByCriteria(Object entityFilter){
        
        ESPM_Emision filter = mapper.convertValue(entityFilter, ESPM_Emision.class);
     
       return espmEmisionDao.findAll( new  Specification<ESPM_Emision>() {

           @Override
           public Predicate toPredicate(Root<ESPM_Emision> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdEmision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idEmision"), filter.getIdEmision())));
                 }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    
    @Override
    public List<ESPM_Region> findESPMRegionByCriteria(Object entityFilter){
        
        ESPM_Region filter = mapper.convertValue(entityFilter, ESPM_Region.class);
     
       return espmRegionDao.findAll( new  Specification<ESPM_Region>() {

           @Override
           public Predicate toPredicate(Root<ESPM_Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdRegion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));
                 }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    
    @Override
    public List<ESPM_NS> findESPMNSByCriteria(Object entityFilter){
        
        ESPM_NS filter = mapper.convertValue(entityFilter, ESPM_NS.class);
     
       return espmNsDao.findAll( new  Specification<ESPM_NS>() {

           @Override
           public Predicate toPredicate(Root<ESPM_NS> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNs"), filter.getIdNs())));
                 }
                 if(filter.getIdPuesto() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getIdPuesto())));
                 }
                 if(filter.getIdProgram() != null) {
                    predicates.add(cb.and(cb.equal(root.get("programId"), filter.getIdProgram())));
                 }	
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    public List<Aux_Partes> findPartesByCriteria(Object entityFilter,String lang, Boolean flag){
        
        Aux_Partes filter = mapper.convertValue(entityFilter, Aux_Partes.class);
     
       List<Aux_Partes> partes= partesDao.findAll( new  Specification<Aux_Partes>() {

           @Override
           public Predicate toPredicate(Root<Aux_Partes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("np")), "%" + filter.getNp().toUpperCase() + "%")));
                }				
                if(filter.getnPAnterior() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("nPAnterior")), "%" + filter.getnPAnterior().toUpperCase() + "%")));
                }
                if(filter.getnPEquivalente() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nPEquivalente")), "%" + filter.getnPEquivalente().toUpperCase() + "%")));
                 }
                if(filter.getdSpanish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dSpanish")), "%" + filter.getdSpanish().toUpperCase() + "%")));
                }
                if(filter.getdEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dEnglish")), "%" + filter.getdEnglish().toUpperCase() + "%")));
                }
                if(filter.getdPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dPortuguese")), "%" + filter.getdPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCodigoVenta() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("codigoVenta")), "%" + filter.getCodigoVenta().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "np"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<partes.size();i++) {
		        		partes.get(i).setdSpanish(partes.get(i).getdEnglish());
		        		//System.out.println("en");
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<partes.size();i++) {
		        		partes.get(i).setdSpanish(partes.get(i).getdPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    partes.sort(Comparator.comparing(Aux_Partes::getNp));
    return partes;
    }
    
    //-------------------------------------------------------------------
    
public List<Aux_Htas> findHtasByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_Htas filter = mapper.convertValue(entityFilter, Aux_Htas.class);
     
       List<Aux_Htas> htas= htasDao.findAll( new  Specification<Aux_Htas>() {

           @Override
           public Predicate toPredicate(Root<Aux_Htas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("np")), "%" + filter.getNp().toUpperCase() + "%")));
                }				
                if(filter.getnPAnterior() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("nPAnterior")), "%" + filter.getnPAnterior().toUpperCase() + "%")));
                }
                if(filter.getFileName() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("fileName")), "%" + filter.getFileName().toUpperCase() + "%")));
                 }
                if(filter.getFileType() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("fileType")), "%" + filter.getFileType().toUpperCase() + "%")));
                 }
                if(filter.getdSpanish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dSpanish")), "%" + filter.getdSpanish().toUpperCase() + "%")));
                }
                if(filter.getdEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dEnglish")), "%" + filter.getdEnglish().toUpperCase() + "%")));
                }
                if(filter.getdPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("dPortuguese")), "%" + filter.getdPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCodigoVenta() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("codigoVenta")), "%" + filter.getCodigoVenta().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "np"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<htas.size();i++) {
		        		htas.get(i).setdSpanish(htas.get(i).getdEnglish());
		        		//System.out.println("en");
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<htas.size();i++) {
		        		htas.get(i).setdSpanish(htas.get(i).getdPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
     }
     htas.sort(Comparator.comparing(Aux_Htas::getNp));
     return htas;
    }
    
    //-------------------------------------------------------------------
    public List<Aux_Tipo> findTiposByCriteria(Object entityFilter){
        
        Aux_Tipo filter = mapper.convertValue(entityFilter, Aux_Tipo.class);
     
       return tipoDao.findAll( new  Specification<Aux_Tipo>() {

           @Override
           public Predicate toPredicate(Root<Aux_Tipo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getId())));
                }				
                if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("tipo")), "%" + filter.getTipo().toUpperCase() + "%")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    }
    
    @Override
public Aux_DRDLR findTiposByName(Object entityFilter, String language){
        
    	Aux_DRDLR filter = mapper.convertValue(entityFilter, Aux_DRDLR.class);
     
       List<Aux_DRDLR> list = DRDLRDao.findAll( new  Specification<Aux_DRDLR>() {

    	   @Override
           public Predicate toPredicate(Root<Aux_DRDLR> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
              
               if(filter.getTipo() != null) {
            	   if(language.equals("es_ES"))
            		   predicates.add(cb.and(cb.like(cb.upper(root.get("tipo")), "%" + filter.getTipo().toUpperCase() + "%")));
            	   if(language.equals("en_US"))
            		   predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getTipo().toUpperCase() + "%")));
            	   if(language.equals("pt_BR"))
            		   predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getTipo().toUpperCase() + "%")));
               }
               				
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    
       if(list.size() >0)
    	   return list.get(0);
       else
    	   return null;
}
    
    public List<Aux_StatusMatriz> findStatusMatrizByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_StatusMatriz filter = mapper.convertValue(entityFilter, Aux_StatusMatriz.class);
     
       List<Aux_StatusMatriz> statusMatriz= statusMatrizDao.findAll( new  Specification<Aux_StatusMatriz>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusMatriz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdStatus() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }				
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("status")), "%" + filter.getStatus().toUpperCase() + "%")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<statusMatriz.size();i++) {
		        		statusMatriz.get(i).setStatus(statusMatriz.get(i).getEnglish());
		        		//System.out.println("en");
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<statusMatriz.size();i++) {
		        		statusMatriz.get(i).setStatus(statusMatriz.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    statusMatriz.sort(Comparator.comparing(Aux_StatusMatriz::getStatus));
    return statusMatriz;
    }
    
    @Override
    public List<Garantia> findGarantiasByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Garantia filter = mapper.convertValue(entityFilter, Garantia.class);
     
       List<Garantia> garantia= garantiaDao.findAll( new  Specification<Garantia>() {

           @Override
           public Predicate toPredicate(Root<Garantia> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getFolio() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("folio")), filter.getFolio().toUpperCase())));
                }
                                			
                if(filter.getStatus() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("idStatus")), "%" + filter.getStatus().toUpperCase() + "%")));
                }			
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSpCode().toUpperCase() + "%")));
                }
                if(filter.getCodigoCta() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("codigoCta")), "%" + filter.getCodigoCta().toUpperCase() + "%")));
                }
                if(filter.getEsn() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("esn")), "%" + filter.getEsn().toUpperCase() + "%")));
                }
                if(filter.getMontoReclamado() != null) {
                    predicates.add(cb.and(cb.equal(root.get("montoReclamado"), filter.getMontoReclamado())));
                }
                if(filter.getMontoPagado() != null) {
                    predicates.add(cb.and(cb.equal(root.get("montoPagado"), filter.getMontoPagado())));
                }
                if(filter.getFechaReclamo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaReclamo")), filter.getFechaReclamo())));
                 }
                if(filter.getFechaFalla() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaFalla")), filter.getFechaFalla())));
                 }
                if(filter.getFechaFinReparacion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaFinReparacion")), filter.getFechaFinReparacion())));
                 }
                
                if(filter.getFechaRechazo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaRechazo")), filter.getFechaRechazo())));
                 }
                
                if(filter.getFechaSolucion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaSolucion")), filter.getFechaSolucion())));
                 }
                
                if(filter.getDiasReparacion() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("diasReparacion")), filter.getDiasReparacion())));
                }
                if(filter.getTotal() != null) {
                    predicates.add(cb.and(cb.equal(root.get("totalSrts"), filter.getTotal())));
                }        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
                 }
                          
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "folio"));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<garantia.size();i++) {
		        		garantia.get(i).getStatusGar().setStatus(garantia.get(i).getStatusGar().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<garantia.size();i++) {
		        		garantia.get(i).getStatusGar().setStatus(garantia.get(i).getStatusGar().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
     }
     garantia.sort(Comparator.comparing(Garantia::getFolio));
     return garantia;
    }

    @Override
    public List<MatrizPartesHd> findMatrizPartesHdByCriteria(Object entityFilter, String lang, Boolean flag){
        
        MatrizPartesHd filter = mapper.convertValue(entityFilter, MatrizPartesHd.class);
       SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
       List<MatrizPartesHd> matrizHd= matrizPartesHdDao.findAll( new  Specification<MatrizPartesHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }
                                			
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nombre")), "%" + filter.getNombre().toUpperCase() + "%")));
                }			
                if(filter.getNoRevision() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("noRevision")), "%" + filter.getNoRevision().toUpperCase() + "%")));
                }
                if(filter.getIdTipo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idTipo")), filter.getIdTipo())));
                }
                if(filter.getIdNs() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdStatus() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idStatus")), filter.getIdStatus())));
                }
                if(filter.getSo() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("so")), "%" + filter.getSo().toUpperCase() + "%")));
                }
                if(filter.getESN() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("esn")), "%" + filter.getESN().toUpperCase() + "%")));
                }
                if(filter.getCPL() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("cpl")), "%" + filter.getCPL().toUpperCase() + "%")));
                }
                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<matrizHd.size();i++) {
		        		matrizHd.get(i).getStatus().setStatus(matrizHd.get(i).getStatus().getEnglish());
		        		matrizHd.get(i).getNs().setServiceLevel(matrizHd.get(i).getNs().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<matrizHd.size();i++) {
		        		matrizHd.get(i).getStatus().setStatus(matrizHd.get(i).getStatus().getPortuguese());
		        		matrizHd.get(i).getNs().setServiceLevel(matrizHd.get(i).getNs().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      matrizHd.sort(Comparator.comparing(MatrizPartesHd::getNombre));
      return matrizHd;
    }
    
    
    /*
     *  @Override
    public List<MatrizPartesHdDTO> findMatrizPartesHdByCriteria(Object entityFilter){
        
        MatrizPartesHd filter = mapper.convertValue(entityFilter, MatrizPartesHd.class);
       SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
       List<MatrizPartesHd> filterMP= matrizPartesHdDao.findAll( new  Specification<MatrizPartesHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }
                                			
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nombre")), "%" + filter.getNombre().toUpperCase() + "%")));
                }			
                if(filter.getNoRevision() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("noRevision")), "%" + filter.getNoRevision().toUpperCase() + "%")));
                }
                if(filter.getIdTipo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idTipo")), filter.getIdTipo())));
                }
                if(filter.getIdNs() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdStatus() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idStatus")), filter.getIdStatus())));
                }
                if(filter.getSo() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("so")), "%" + filter.getSo().toUpperCase() + "%")));
                }
                if(filter.getESN() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("esn")), "%" + filter.getESN().toUpperCase() + "%")));
                }
                if(filter.getCPL() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("cpl")), "%" + filter.getCPL().toUpperCase() + "%")));
                }
                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
       
       List<MatrizPartesHdDTO> lstPartes= new ArrayList<>();
       
       for(MatrizPartesHd mat: filterMP) {
    	   MatrizPartesHdDTO matriz= new MatrizPartesHdDTO();
    	   matriz.setCpl(mat.getCPL());
    	   matriz.setCreatedBy(mat.getCPL());
    	   matriz.setCreationDate(formatter.format(mat.getCreationDate()));
    	   matriz.setDel(mat.getDel());
    	   matriz.setEsn(mat.getESN());
    	   matriz.setIdMatriz(mat.getIdMatriz());
    	   matriz.setIdStatus(mat.getIdStatus());
    	   matriz.setNombre(mat.getNombre());
    	   matriz.setNoRevision(mat.getNoRevision());
    	   matriz.setIdTipo(mat.getIdTipo());
    	   matriz.setIdNs(mat.getIdNs());
    	   matriz.setSo(matriz.getSo());
    	   matriz.setLastUpdateBy(mat.getLastUpdatedBy());   
    	   matriz.setLastUpdateDate(formatter.format(mat.getLastUpdateDate()));
    	  lstPartes.add(matriz);
       }
       
       return lstPartes;
    }
     * 
     * */
    
    @Override
    public List<MatrizPartesDet> findMatrizPartesDetByCriteria(Object entityFilter, String lang, Boolean flag){
        
        MatrizPartesDet filter = mapper.convertValue(entityFilter, MatrizPartesDet.class);
     
       List<MatrizPartesDet> mat= matrizPartesDetDao.findAll( new  Specification<MatrizPartesDet>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesDet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz() )));
               }
                                			
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("np")), filter.getNp().toUpperCase() )));
                }			
                if(filter.getPonderacion() != 0 ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("ponderacion")), filter.getPonderacion())));
                }
               
                if(filter.getQty() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("qty")), filter.getQty())));
                }                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
                }               
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<mat.size();i++) {
		        		mat.get(i).getPartes().setdSpanish(mat.get(i).getPartes().getdEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<mat.size();i++) {
		        		mat.get(i).getPartes().setdSpanish(mat.get(i).getPartes().getdPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
     }
       Comparator<Aux_Partes> byAuxParts = Comparator.comparing(Aux_Partes::getdSpanish);
     mat.sort(Comparator.comparing(MatrizPartesDet::getPartes, byAuxParts));
     return mat;
    }
    public List<AtributosMatrices> findAtributosMatricesByCriteria(Object entityFilter){
        
        AtributosMatrices filter = mapper.convertValue(entityFilter, AtributosMatrices.class);
     
       return atributosMatricesDao.findAll( new  Specification<AtributosMatrices>() {

           @Override
           public Predicate toPredicate(Root<AtributosMatrices> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }

                if(filter.getTipoMatriz() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("tipoMatriz")), filter.getTipoMatriz())));
                }
                 if(filter.getTipoAtributo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("tipoAtributo")), filter.getTipoAtributo())));
                }
  			
                if(filter.getIdText() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("idText")), "%" + filter.getIdText().toUpperCase() + "%")));
                }
                if(filter.getIdNumber() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNumber")), filter.getIdNumber())));
                }			                       
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }

    @Override
    public List<MatrizHtasHd> findMatrizHtasHdByCriteria(Object entityFilter, String lang, Boolean flag){
        
        MatrizHtasHd filter = mapper.convertValue(entityFilter, MatrizHtasHd.class);
     
       List<MatrizHtasHd> matrizHd= matrizHtasHdDao.findAll( new  Specification<MatrizHtasHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizHtasHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }
                                			
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nombre")), "%" + filter.getNombre().toUpperCase() + "%")));
                }			
                if(filter.getNoRevision() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("noRevision")), "%" + filter.getNoRevision().toUpperCase() + "%")));
                }
                if(filter.getIdNs() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdStatus() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idStatus")), filter.getIdStatus())));
                }
                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<matrizHd.size();i++) {
		        		matrizHd.get(i).getStatus().setStatus(matrizHd.get(i).getStatus().getEnglish());
		        		matrizHd.get(i).getNs().setServiceLevel(matrizHd.get(i).getNs().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<matrizHd.size();i++) {
		        		matrizHd.get(i).getStatus().setStatus(matrizHd.get(i).getStatus().getPortuguese());
		        		matrizHd.get(i).getNs().setServiceLevel(matrizHd.get(i).getNs().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
     }
     matrizHd.sort(Comparator.comparing(MatrizHtasHd::getNombre));
     return matrizHd;
    }
    @Override
    public List<MatrizHtasDet> findMatrizHtasDetByCriteria(Object entityFilter, String lang, Boolean flag){
        
        MatrizHtasDet filter = mapper.convertValue(entityFilter, MatrizHtasDet.class);
     
       List<MatrizHtasDet> mat= matrizHtasDetDao.findAll( new  Specification<MatrizHtasDet>() {

           @Override
           public Predicate toPredicate(Root<MatrizHtasDet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }
                                			
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("np")), "%" + filter.getNp().toUpperCase() + "%")));
                }			
                if(filter.getPonderacion() != 0 ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("ponderacion")), filter.getPonderacion())));
                }
               
                if(filter.getQty() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("qty")), filter.getQty())));
                }                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<mat.size();i++) {
		        		mat.get(i).getPartes().setdSpanish(mat.get(i).getPartes().getdEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<mat.size();i++) {
		        		mat.get(i).getPartes().setdSpanish(mat.get(i).getPartes().getdPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
      Comparator<Aux_Htas> byAuxHtas = Comparator.comparing(Aux_Htas::getdSpanish);
    mat.sort(Comparator.comparing(MatrizHtasDet::getPartes, byAuxHtas));
    return mat;
    }
    @Override
    public List<AtributosMatricesHtas> findAtributosMatricesHtasByCriteria(Object entityFilter){
        
        AtributosMatricesHtas filter = mapper.convertValue(entityFilter, AtributosMatricesHtas.class);
     
       return atributosMatricesHtasDao.findAll( new  Specification<AtributosMatricesHtas>() {

           @Override
           public Predicate toPredicate(Root<AtributosMatricesHtas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }

                if(filter.getTipoMatriz() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("tipoMatriz")), filter.getTipoMatriz())));
                }
                 if(filter.getTipoAtributo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("tipoAtributo")), filter.getTipoAtributo())));
                }
  			
                if(filter.getIdText() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("idText")), "%" + filter.getIdText().toUpperCase() + "%")));
                }
                if(filter.getIdNumber() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNumber")), filter.getIdNumber())));
                }			                       
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }
    @Override
    public List<ConfMotor> findConfMotorByCriteria(Object entityFilter){
        
        ConfMotor filter = mapper.convertValue(entityFilter, ConfMotor.class);
     
       return confmotorDao.findAll( new  Specification<ConfMotor>() {

           @Override
           public Predicate toPredicate(Root<ConfMotor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdConfMotor() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idConfMotor")), filter.getIdConfMotor())));
                }

                if(filter.getIdEvaluacion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idEvaluacion")), filter.getIdEvaluacion())));
                }
                if(filter.getIdESPM() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idEspmotor")), filter.getIdESPM())));
                }
                if(filter.getIdNs() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdMatrizPartes() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatrizPartes")), filter.getIdMatrizPartes())));
                }
                if(filter.getIdMatrizHtas() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatrizHtas")), filter.getIdMatrizHtas())));
                }

                if(filter.getMecReq() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("mecReq")), filter.getMecReq())));
                }			                       
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfMotor"));
    }
    @Override
    public List<ConfSpcodes> findConfSpcodesByCriteria(Object entityFilter){
        
        ConfSpcodes filter = mapper.convertValue(entityFilter, ConfSpcodes.class);
     
       return confspcodesDao.findAll( new  Specification<ConfSpcodes>() {

           @Override
           public Predicate toPredicate(Root<ConfSpcodes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getSpCode() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getSpCode().toUpperCase() + "%")));
             }
                            
            if(filter.getCreatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateDate() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
             }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfMotor"));
    }
    @Override
    public List<ConfOem> findConfOemByCriteria(Object entityFilter){
        
        ConfOem filter = mapper.convertValue(entityFilter, ConfOem.class);
     
       return confoemDao.findAll( new  Specification<ConfOem>() {

           @Override
           public Predicate toPredicate(Root<ConfOem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
            if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getIdOem() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idOem")),filter.getIdOem())));
             }
                            
            if(filter.getCreatedBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateDate() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
             }                
             if(filter.getLastUpdateBy() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
             }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfMotor"));
    }
    @Override
    public List<Aux_TipoQ> findTipoQByCriteria(Object entityFilter){
        
        Aux_TipoQ filter = mapper.convertValue(entityFilter, Aux_TipoQ.class);
     
       return auxtipoqDao.findAll( new  Specification<Aux_TipoQ>() {

           @Override
           public Predicate toPredicate(Root<Aux_TipoQ> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdTipo() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getIdTipo())));
                }				
                if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("tipo")), "%" + filter.getTipo().toUpperCase() + "%")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("english")), "%" + filter.getEnglish().toUpperCase() + "%")));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portuguese")), "%" + filter.getPortuguese().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDelete() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    }
    
    @Override
    public List<Aux_StatusEv> findStatusEvByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_StatusEv filter = mapper.convertValue(entityFilter, Aux_StatusEv.class);
     
       List<Aux_StatusEv> status= statusEvDao.findAll( new  Specification<Aux_StatusEv>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusEv> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               System.out.println("critera de aux status ev");
                if(filter.getIdStatus() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }				
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("status")), "%" + filter.getStatus().toUpperCase() + "%")));
                }
                if(filter.getIngles() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("ingles")), "%" + filter.getIngles().toUpperCase() + "%")));
                }
                if(filter.getPortugues() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("portugues")), "%" + filter.getPortugues().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")),filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")),filter.getLastUpdateDate().toString())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdateBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idStatus"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<status.size();i++) {
		        		status.get(i).setStatus(status.get(i).getIngles());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<status.size();i++) {
		        		status.get(i).setStatus(status.get(i).getPortugues());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    status.sort(Comparator.comparing(Aux_StatusEv::getIdStatus));
    return status;
    }
   
//----------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------Find Existing ---------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------

    public List<Aux_Puestos> findPuestoExisting(Object entityFilter){
        
        Aux_Puestos filter = mapper.convertValue(entityFilter, Aux_Puestos.class);
     /*  List<Calendario> list = new ArrayList<Calendario>();
       list.add(filter);
       return list;*/
       return puestoDao.findAll(new  Specification<Aux_Puestos>() {

           @Override
           public Predicate toPredicate(Root<Aux_Puestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
                if(filter.getId() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getId())));
                }
                if(filter.getPuesto() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("puesto")), filter.getPuesto().toUpperCase())));
                // predicates.add(cb.and(cb.equal(cb.upper(root.get("region")), "SA")));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }             
                if(filter.getDel() != null) {
                    predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }
            
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       });
       
       
    }
    
    public List<Aux_RelPuestos> findRelPuestosExisting(Object entityFilter){
        
        Aux_RelPuestos filter = mapper.convertValue(entityFilter, Aux_RelPuestos.class);
     
        return relPuestoDao.findAll( new  Specification<Aux_RelPuestos>() {

           @Override
           public Predicate toPredicate(Root<Aux_RelPuestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getId())));
                }				
                if(filter.getTitulo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("titulo")), filter.getTitulo().toUpperCase())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }
                if(filter.getDelete() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "titulo"));
        
    }   
    public List<Aux_DRDLR> findDRDLRExisting(Object entityFilter){
        
        Aux_DRDLR filter = mapper.convertValue(entityFilter, Aux_DRDLR.class);
     
       return DRDLRDao.findAll( new  Specification<Aux_DRDLR>() {

           @Override
           public Predicate toPredicate(Root<Aux_DRDLR> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("id"), filter.getId())));
                }				
                if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("tipo")), filter.getTipo().toUpperCase())));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }                
                if(filter.getDelete() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    } 
    public List<Pqs> findPQSExisting(Object entityFilter){
        
        Pqs filter = mapper.convertValue(entityFilter, Pqs.class);
     
       return pqsDao.findAll( new  Specification<Pqs>() {

           @Override
           public Predicate toPredicate(Root<Pqs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("programId")), filter.getId().toUpperCase())));
                }				
                if(filter.getNombre() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("nombre")), filter.getNombre().toUpperCase() )));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
    } 
	public List<PqsXpuestos> findPQSXpuestosExisting(Object entityFilter){
        
        PqsXpuestos filter = mapper.convertValue(entityFilter, PqsXpuestos.class);
     
       return pqsxpuestosDao.findAll( new  Specification<PqsXpuestos>() {

           @Override
           public Predicate toPredicate(Root<PqsXpuestos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdProgram() != null) {
                   predicates.add(cb.and(cb.equal(root.get("programId"), filter.getIdProgram().toUpperCase())));
                }				
                if(filter.getIdPuesto() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("idPuesto")), filter.getIdPuesto() )));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "programId"));
    } 
    public List<SpCodes> findSPCODESExisting(Object entityFilter){
        
        SpCodes filter = mapper.convertValue(entityFilter, SpCodes.class);
     
       return spcodeDao.findAll( new  Specification<SpCodes>() {

           @Override
           public Predicate toPredicate(Root<SpCodes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("spCode")), filter.getSpCode().toUpperCase())));
                }				
                if(filter.getProviderName() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("providerName")), filter.getProviderName().toUpperCase())));
                }
                if(filter.getTipo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getTipo())));
                }
                if(filter.getResponsibleBranchCode() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("responsibleBranchCode")), filter.getResponsibleBranchCode().toUpperCase())));
                }
                if(filter.getIdPais() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPais"), filter.getIdPais())));
                }
                if(filter.getIdOem() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idOem"), filter.getIdOem())));
                }
                if(filter.getCity() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("city")), filter.getCity().toUpperCase())));
                }
                if(filter.getIso() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("iso")), filter.getIso().toUpperCase())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }                          
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
    }
    public List<LicenciasQsol> findLicenciasQsolExisting(Object entityFilter){
        
        LicenciasQsol filter = mapper.convertValue(entityFilter, LicenciasQsol.class);
     
       return licenciasqsolDao.findAll( new  Specification<LicenciasQsol>() {

           @Override
           public Predicate toPredicate(Root<LicenciasQsol> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.equal(root.get("spCode"), filter.getSpCode())));
                }				
                if(filter.getFechaRegistro() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaRegistro")), filter.getFechaRegistro().toString().toUpperCase())));
                }
                if(filter.getFechaExpiracion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaExpiracion")), filter.getFechaExpiracion().toString().toUpperCase())));
                }           
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "spCode"));
    }
    public List<ElectronicTools> findElectronicToolsExisting(Object entityFilter){
        
        ElectronicTools filter = mapper.convertValue(entityFilter, ElectronicTools.class);
     
       return electronicDao.findAll( new  Specification<ElectronicTools>() {

           @Override
           public Predicate toPredicate(Root<ElectronicTools> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTool"), filter.getId().toUpperCase())));
                }				
                if(filter.getSPCode() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("spCode")), filter.getSPCode().toUpperCase())));
                }
                if(filter.getNP() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("numeroParte")), filter.getNP().toUpperCase())));
                }
                if(filter.getFechaReg() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaRegistro")), filter.getFechaReg().toString().toUpperCase())));
                }
                if(filter.getFechaExp() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaExpiracion")), filter.getFechaExp().toString().toUpperCase())));
                }           
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }     
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idTool"));
    }
    public List<Rescates> findRescatesExisting(Object entityFilter){
        
        Rescates filter = mapper.convertValue(entityFilter, Rescates.class);
     
       return rescatesDao.findAll( new  Specification<Rescates>() {

           @Override
           public Predicate toPredicate(Root<Rescates> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getFolio() != null) {
                   predicates.add(cb.and(cb.equal(root.get("folio"), filter.getFolio().toUpperCase())));
                }				
               /* if(filter.getCode() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("spcode")), filter.getCode().toUpperCase())));
                }*/
                if(filter.getFechaFalla() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaFalla")), filter.getFechaFalla().toString().toUpperCase())));
                }
                if(filter.getCliente() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("cliente")), filter.getCliente().toUpperCase())));
                }
                if(filter.getTiempoRespuesta() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tiempoRespuesta"), filter.getTiempoRespuesta())));
                }
                if(filter.getTiempoReparacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tiempoReparacion"), filter.getTiempoReparacion())));
                }
                if(filter.getTiempoMuerto() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tiempoMuerto"), filter.getTiempoMuerto())));
                }
                if(filter.getMotivo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("motivo"), filter.getMotivo().toUpperCase())));
                }	
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "folio"));
    }    
    public List<NoDisponible> findNoDisponibleExisting(Object entityFilter){
        
        NoDisponible filter = mapper.convertValue(entityFilter, NoDisponible.class);
     
       return nodisponibleDao.findAll( new  Specification<NoDisponible>() {

           @Override
           public Predicate toPredicate(Root<NoDisponible> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getFolio() != null) {
              	 predicates.add(cb.and(cb.like(cb.upper(root.get("folio")), "%" + filter.getFolio().toUpperCase() + "%")));
               }		
               if(filter.getCliente() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("cliente")), "%" + filter.getCliente().toUpperCase() + "%")));
               }
               if(filter.getFecha() != null) {
                   predicates.add(cb.and(cb.equal(root.get("fecha"), filter.getFecha())));
                }	
               if(filter.getCode() != null) {
                  predicates.add(cb.and(cb.like(cb.upper(root.get("spCode")), "%" + filter.getCode().toUpperCase() + "%")));
               }
               
               if(filter.getRazon() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("razon")), "%" + filter.getRazon().toUpperCase() + "%")));
                }           
               if(filter.getCreatedBy() != null ) {
                  predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
               }                
                        
               if(filter.getLastUpdatedBy() != null ) {
                  predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
               }
               if(filter.getDel() != null) {
                  predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
               }			
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "folio"));
    }
	public List<Promotion> findPromotionExisting(Object entityFilter){
        
        Promotion filter = mapper.convertValue(entityFilter, Promotion.class);
     
       return promotionDao.findAll( new  Specification<Promotion>() {

           @Override
           public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
              /* 
               if(filter.getIdPromotion() != null) {
                predicates.add(cb.and(cb.equal(root.get("promotionId"), filter.getIdPromotion().toUpperCase())));
             }				
             if(filter.getIdSpCode() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("spCode")), filter.getIdSpCode().toUpperCase())));
             }
             if(filter.getIdProgram() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("programId")), filter.getIdProgram().toUpperCase())));
             }*/
             if(filter.getFirstName() != null) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("firstName")), filter.getFirstName().toUpperCase())));
             }
             if(filter.getMiddleName() != null) {
                 predicates.add(cb.and(cb.equal(root.get("middleName"), filter.getMiddleName().toUpperCase())));
             }
             if(filter.getLastName() != null) {
                predicates.add(cb.and(cb.equal(root.get("lastName"), filter.getLastName().toUpperCase())));
            }	
            if(filter.getJobTitle() != null) {
                predicates.add(cb.and(cb.equal(root.get("jobTitle"), filter.getJobTitle().toUpperCase())));
            }	
            if(filter.getDatePassed() != null) {
                predicates.add(cb.and(cb.equal(root.get("datePassed"), filter.getDatePassed().toString().toUpperCase())));
            }		
             if(filter.getCreationDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
             }                
             if(filter.getCreatedBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
             }                
             if(filter.getLastUpdateDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
             }                
             if(filter.getLastUpdateBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
             }
                          
            		
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "promotionId"));
    }
    public List<Aux_CC> findCCExisting(Object entityFilter){
        
        Aux_CC filter = mapper.convertValue(entityFilter, Aux_CC.class);
     
       return ccDao.findAll( new  Specification<Aux_CC>() {

           @Override
           public Predicate toPredicate(Root<Aux_CC> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getCodigoCta() != null) {
                predicates.add(cb.and(cb.equal(root.get("codigocta"), filter.getCodigoCta().toUpperCase())));
             }				
             		
             if(filter.getCreationDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
             }                
             if(filter.getCreatedBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
             }                
             if(filter.getLastUpdateDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
             }                
             if(filter.getLastUpdateBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
             }  
             if(filter.getDel() != null) {
                predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDel())));
             }	              
            		
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "codigocta"));
    }
    public List<Aux_StatusGar> findStatusGarExisting(Object entityFilter){
        
        Aux_StatusGar filter = mapper.convertValue(entityFilter, Aux_StatusGar.class);
     
       return statusgarDao.findAll( new  Specification<Aux_StatusGar>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusGar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdstatus() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idstatus"), filter.getIdstatus())));
                }				
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("status")), filter.getStatus().toUpperCase())));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }        
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
    }
    public List<ESPM> findESPMExisting(Object entityFilter){
        
        ESPM filter = mapper.convertValue(entityFilter, ESPM.class);
     
       return espmDao.findAll( new  Specification<ESPM>() {

           @Override
           public Predicate toPredicate(Root<ESPM> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdMotor() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idMotor"), filter.getIdMotor())));
                }
                if(filter.getIdRango() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRango"), filter.getIdRango())));
                }
                if(filter.getIdApp() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idApp"), filter.getIdApp())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
	public List<ESPM_Emision> findESPMEmisionExisting(Object entityFilter){
        
        ESPM_Emision filter = mapper.convertValue(entityFilter, ESPM_Emision.class);
     
       return espmEmisionDao.findAll( new  Specification<ESPM_Emision>() {

           @Override
           public Predicate toPredicate(Root<ESPM_Emision> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM()!= null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdEmision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idEmision"), filter.getIdEmision())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }          
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    public List<ESPM_Region> findESPMRegionExisting(Object entityFilter){
        
        ESPM_Region filter = mapper.convertValue(entityFilter, ESPM_Region.class);
     
       return espmRegionDao.findAll( new  Specification<ESPM_Region>() {

           @Override
           public Predicate toPredicate(Root<ESPM_Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdRegion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }          
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    public List<ESPM_NS> findESPMNSExisting(Object entityFilter){
        
        ESPM_NS filter = mapper.convertValue(entityFilter, ESPM_NS.class);
     
       return espmNsDao.findAll( new  Specification<ESPM_NS>() {

           @Override
           public Predicate toPredicate(Root<ESPM_NS> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdESPM() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }				
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNs"), filter.getIdNs())));
                }
                if(filter.getIdPuesto() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPuesto"), filter.getIdPuesto())));
                }
                if(filter.getIdProgram() != null) {
                    predicates.add(cb.and(cb.equal(root.get("programId"), filter.getIdProgram())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEspmotor"));
    }
    public List<Aux_Partes> findPartesExisting(Object entityFilter){
        
        Aux_Partes filter = mapper.convertValue(entityFilter, Aux_Partes.class);
     
       return partesDao.findAll( new  Specification<Aux_Partes>() {

           @Override
           public Predicate toPredicate(Root<Aux_Partes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("np")), filter.getNp().toUpperCase())));
                }				
                if(filter.getnPAnterior() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("nPAnterior")),filter.getnPAnterior().toUpperCase())));
                }
                if(filter.getnPEquivalente() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("nPEquivalente")),filter.getnPEquivalente().toUpperCase())));
                    }
                if(filter.getdSpanish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("dSpanish")),filter.getdSpanish().toUpperCase())));
                }
                if(filter.getdEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("dEnglish")),filter.getdEnglish().toUpperCase())));
                }
                if(filter.getdPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("dPortuguese")),filter.getdPortuguese().toUpperCase())));
                }
                if(filter.getCodigoVenta() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("codigoVenta")),filter.getCodigoVenta().toUpperCase())));
                } 
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }              
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "np"));
    }
    public List<Aux_Tipo> findTiposExisting(Object entityFilter){
        
        Aux_Tipo filter = mapper.convertValue(entityFilter, Aux_Tipo.class);
     
       return tipoDao.findAll( new  Specification<Aux_Tipo>() {

           @Override
           public Predicate toPredicate(Root<Aux_Tipo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getId())));
                }				
                if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("tipo")), filter.getTipo().toUpperCase())));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    }
    public List<Aux_StatusMatriz> findStatusMatrizExisting(Object entityFilter){
        
        Aux_StatusMatriz filter = mapper.convertValue(entityFilter, Aux_StatusMatriz.class);
     
       return statusMatrizDao.findAll( new  Specification<Aux_StatusMatriz>() {

           @Override
           public Predicate toPredicate(Root<Aux_StatusMatriz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdStatus() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }				
                if(filter.getStatus() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("status")), filter.getStatus().toUpperCase())));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
    }
    public List<Garantia> findGarantiasExisting(Object entityFilter){
        
        Garantia filter = mapper.convertValue(entityFilter, Garantia.class);
     
       return garantiaDao.findAll( new  Specification<Garantia>() {

           @Override
           public Predicate toPredicate(Root<Garantia> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getFolio() != null) {
                   predicates.add(cb.and(cb.equal(root.get("folio"), filter.getFolio())));
                }
                if(filter.getCodigoCta() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("codigoCta")), filter.getCodigoCta().toUpperCase())));
                 }			
                if(filter.getSpCode() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("spcode")), filter.getSpCode().toUpperCase())));
                }
                if(filter.getEsn() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("esn")), filter.getEsn().toUpperCase())));
                }
                if(filter.getMontoReclamado() != null) {
                    predicates.add(cb.and(cb.equal(root.get("montoReclamado"), filter.getMontoReclamado())));
                }
                if(filter.getMontoPagado() != null) {
                    predicates.add(cb.and(cb.equal(root.get("montoPagado"), filter.getMontoPagado())));
                }
                if(filter.getFechaReclamo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaReclamo")), filter.getFechaReclamo().toString().toUpperCase())));
                }
                if(filter.getFechaFalla() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaFalla")), filter.getFechaFalla().toString().toUpperCase())));
                }
                if(filter.getFechaFinReparacion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaFinReparacion")), filter.getFechaFinReparacion().toString().toUpperCase())));
                }
                if(filter.getFechaRechazo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaRechazo")), filter.getFechaRechazo().toString().toUpperCase())));
                }
                if(filter.getFechaSolucion() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("fechaSolucion")), filter.getFechaSolucion().toString().toUpperCase())));
                }
                if(filter.getDiasReparacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("diasReparacion"), filter.getDiasReparacion())));
                }
                if(filter.getTotal() != null) {
                    predicates.add(cb.and(cb.equal(root.get("totalSrts"), filter.getTotal())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "folio"));
    }
    public List<MatrizPartesHd> findMatrizPartesHdExisting(Object entityFilter){
        
        MatrizPartesHd filter = mapper.convertValue(entityFilter, MatrizPartesHd.class);
     
       return matrizPartesHdDao.findAll( new  Specification<MatrizPartesHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idMatriz"), filter.getIdMatriz())));
                }
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("nombre")), filter.getNombre().toUpperCase())));
                }
                if(filter.getNoRevision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("noRevision"), filter.getNoRevision())));
                }
                if(filter.getIdTipo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getIdTipo())));
                }
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNs"), filter.getIdNs())));
                }	
                if(filter.getIdStatus() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }				
                if(filter.getSo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("so")), filter.getSo().toUpperCase())));
                }
                if(filter.getESN() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("esn")), filter.getESN().toUpperCase())));
                }
                if(filter.getCPL() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("cpl")), filter.getCPL().toUpperCase())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
    }
    public List<MatrizPartesDet> findMatrizPartesDetExisting(Object entityFilter){
        
        MatrizPartesDet filter = mapper.convertValue(entityFilter, MatrizPartesDet.class);
     
       return matrizPartesDetDao.findAll( new  Specification<MatrizPartesDet>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesDet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("np")), filter.getNp().toUpperCase())));
                }
                if(filter.getPonderacion() != 0) {
                    predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
                }
              
                if(filter.getQty() != null) {
                    predicates.add(cb.and(cb.equal(root.get("qty"), filter.getQty())));
                }	
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }
    public List<AtributosMatrices> findAtributosMatricesExisting(Object entityFilter){
        
        AtributosMatrices filter = mapper.convertValue(entityFilter, AtributosMatrices.class);
     
       return atributosMatricesDao.findAll( new  Specification<AtributosMatrices>() {

           @Override
           public Predicate toPredicate(Root<AtributosMatrices> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idMatriz"), filter.getIdMatriz())));
                }
                if(filter.getTipoMatriz() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tipoMatriz"), filter.getTipoMatriz())));
                }
                if(filter.getTipoAtributo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tipoAtributo"), filter.getTipoAtributo())));
                }
                if(filter.getIdText() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idText")), filter.getIdText().toUpperCase())));
                }
                if(filter.getIdNumber() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNumber"), filter.getIdNumber())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }
    public List<MatrizHtasHd> findMatrizHtasHdExisting(Object entityFilter){
        
        MatrizHtasHd filter = mapper.convertValue(entityFilter, MatrizHtasHd.class);
     
       return matrizHtasHdDao.findAll( new  Specification<MatrizHtasHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizHtasHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idMatriz"), filter.getIdMatriz())));
                }
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("nombre")), filter.getNombre().toUpperCase())));
                }
                if(filter.getNoRevision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("noRevision"), filter.getNoRevision())));
                }
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNs"), filter.getIdNs())));
                }	
                if(filter.getIdStatus() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }				
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
    }
    public List<MatrizHtasDet> findMatrizHtasDetExisting(Object entityFilter){
        
        MatrizHtasDet filter = mapper.convertValue(entityFilter, MatrizHtasDet.class);
     
       return matrizHtasDetDao.findAll( new  Specification<MatrizHtasDet>() {

           @Override
           public Predicate toPredicate(Root<MatrizHtasDet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idMatriz"), filter.getIdMatriz())));
                }
                if(filter.getNp() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("np")), filter.getNp().toUpperCase())));
                }
                if(filter.getPonderacion() != 0) {
                    predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
                }
              
                if(filter.getQty() != null) {
                    predicates.add(cb.and(cb.equal(root.get("qty"), filter.getQty())));
                }	
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }
    public List<AtributosMatricesHtas> findAtributosMatricesHtasExisting(Object entityFilter){
        
        AtributosMatricesHtas filter = mapper.convertValue(entityFilter, AtributosMatricesHtas.class);
     
       return atributosMatricesHtasDao.findAll( new  Specification<AtributosMatricesHtas>() {

           @Override
           public Predicate toPredicate(Root<AtributosMatricesHtas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idMatriz"), filter.getIdMatriz())));
                }
                if(filter.getTipoMatriz() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tipoMatriz"), filter.getTipoMatriz())));
                }
                if(filter.getTipoAtributo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tipoAtributo"), filter.getTipoAtributo())));
                }
                if(filter.getIdText() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idText")), filter.getIdText().toUpperCase())));
                }
                if(filter.getIdNumber() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idNumber"), filter.getIdNumber())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdateBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idMatriz"));
    }

    
    public List<ConfMotor> findConfMotorExisting(Object entityFilter){
        
        ConfMotor filter = mapper.convertValue(entityFilter, ConfMotor.class);
     
       return confmotorDao.findAll( new  Specification<ConfMotor>() {

           @Override
           public Predicate toPredicate(Root<ConfMotor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdConfMotor() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
                }
                if(filter.getIdEvaluacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
                }
                if(filter.getIdESPM() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idEspmotor"), filter.getIdESPM())));
                }
                if(filter.getIdNs() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdMatrizPartes() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idMatrizPartes"), filter.getIdMatrizPartes())));
                }
                if(filter.getIdMatrizHtas() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idMatrizHtas"), filter.getIdMatrizHtas())));
                }
                if(filter.getCreationDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
                }                
                if(filter.getCreatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
                }                
                if(filter.getLastUpdateDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfMotor"));
    }
    public List<ConfSpcodes> findConfSpcodesExisting(Object entityFilter){
        
        ConfSpcodes filter = mapper.convertValue(entityFilter, ConfSpcodes.class);
     
       return confspcodesDao.findAll( new  Specification<ConfSpcodes>() {

           @Override
           public Predicate toPredicate(Root<ConfSpcodes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getSpCode() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("spCode")), filter.getSpCode().toUpperCase())));
             }
             		
             if(filter.getCreationDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
             }                
             if(filter.getCreatedBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
             }                
             if(filter.getLastUpdateDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
             }                
             if(filter.getLastUpdateBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
             }
                          
            		
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfmotor"));
    }
    public List<ConfOem> findConfOemExisting(Object entityFilter){
        
        ConfOem filter = mapper.convertValue(entityFilter, ConfOem.class);
     
       return confoemDao.findAll( new  Specification<ConfOem>() {

           @Override
           public Predicate toPredicate(Root<ConfOem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getIdConfMotor() != null) {
                predicates.add(cb.and(cb.equal(root.get("idConfMotor"), filter.getIdConfMotor())));
             }				
             if(filter.getIdOem() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idOem")), filter.getIdOem())));
             }
             		
             if(filter.getCreationDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate().toString().toUpperCase())));
             }                
             if(filter.getCreatedBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")),filter.getCreatedBy().toUpperCase())));
             }                
             if(filter.getLastUpdateDate() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate().toString().toUpperCase())));
             }                
             if(filter.getLastUpdateBy() != null ) {
                 predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdateBy().toUpperCase() )));
             }
                          
            		
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idConfmotor"));
    }

    public List<Aux_TipoQ> findTipoQExisting(Object entityFilter){
        
        Aux_TipoQ filter = mapper.convertValue(entityFilter, Aux_TipoQ.class);
     
       return auxtipoqDao.findAll( new  Specification<Aux_TipoQ>() {

           @Override
           public Predicate toPredicate(Root<Aux_TipoQ> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdTipo() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getIdTipo())));
                }				
                if(filter.getTipo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("tipo")), filter.getTipo().toUpperCase())));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase())));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase())));
                }               
                if(filter.getDelete() != null) {
                   predicates.add(cb.and(cb.equal(root.get("deleted"), filter.getDelete())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "tipo"));
    }
    
    
public List<Aux_RelModulos> findRelModulosExisting(Object entityFilter){
        
        Aux_RelModulos filter = mapper.convertValue(entityFilter, Aux_RelModulos.class);
     
       return relModulosDao.findAll( new  Specification<Aux_RelModulos>() {

           @Override
           public Predicate toPredicate(Root<Aux_RelModulos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdModulo() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idModulo"), filter.getIdModulo())));
                }				
                if(filter.getIdSubmodulo() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("idSubmodulo")), filter.getIdSubmodulo())));
                }
                if(filter.getCreatedBy() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")), filter.getCreatedBy())));
                }
                if(filter.getLastUpdateBy() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")), filter.getLastUpdateBy())));
                }
                if(filter.getLastUpdateDate() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
                }
                if(filter.getCreationDate() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idModulo"));
    }

public List<Aux_Opciones> findOpcionesExisting(Object entityFilter){
    
    Aux_Opciones filter = mapper.convertValue(entityFilter, Aux_Opciones.class);
 
   return opcionesDao.findAll( new  Specification<Aux_Opciones>() {

       @Override
       public Predicate toPredicate(Root<Aux_Opciones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
           List<Predicate> predicates = new ArrayList<>();
           
            if(filter.getIdOpcion() != null) {
               predicates.add(cb.and(cb.equal(root.get("idOpcion"), filter.getIdOpcion())));
            }				
            if(filter.getOpcion() != null) {
               predicates.add(cb.and(cb.equal(cb.upper(root.get("opcion")), filter.getOpcion())));
            }
            if(filter.getCreatedBy() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")), filter.getCreatedBy())));
            }
            if(filter.getLastUpdateBy() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")), filter.getLastUpdateBy())));
            }
            if(filter.getLastUpdateDate() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
            }
            if(filter.getCreationDate() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
            }               
            if(filter.getDel() != null) {
               predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
            }				
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
       }
       
   }, new Sort(Sort.Direction.ASC, "idOpcion"));
}

public List<Aux_StatusEv> findStatusEvExisting(Object entityFilter){
    
    Aux_StatusEv filter = mapper.convertValue(entityFilter, Aux_StatusEv.class);
 
   return statusEvDao.findAll( new  Specification<Aux_StatusEv>() {

       @Override
       public Predicate toPredicate(Root<Aux_StatusEv> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
           List<Predicate> predicates = new ArrayList<>();
           
            if(filter.getIdStatus() != null) {
               predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
            }				
            if(filter.getStatus() != null) {
               predicates.add(cb.and(cb.equal(cb.upper(root.get("status")), filter.getStatus())));
            }
            if(filter.getCreatedBy() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("createdBy")), filter.getCreatedBy())));
            }
            if(filter.getLastUpdateBy() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")), filter.getLastUpdateBy())));
            }
            if(filter.getLastUpdateDate() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), filter.getLastUpdateDate())));
            }
            if(filter.getCreationDate() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), filter.getCreationDate())));
            }               
            if(filter.getDel() != null) {
               predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
            }				
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
       }
       
   }, new Sort(Sort.Direction.ASC, "idStatus"));
}
    
   

    
    // ----------- COMIENZAN METODOS DE ELIMINACIÓN PARA LOS CATALOGOS QUE ACEPTAN CARGA MASIVA Y LIMPIAN LA TABLA
    // ----------- Se utilizan querys en lugar de JPA
    /**
	 * @desc metodos que permiten eliminar logicamente todos los registros de una tabla al hacer carga masiva
	 * @param NA
	 * @return Int - número de registros que se ven afectados
	*/
    
    
    @Override
    public Integer logicDeleteSpCode() throws SQLException {
    	Connection connection = null;
		
		
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_CAT_SPCODES WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		
			
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"UPDATE ZMKT7352_DES_CAT_SPCODES SET DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "Y");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    	
    }
    
    @Override
    public Integer logicDeleteLicencias() throws SQLException {
    	Connection connection = null;
		
		
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_LICENCIAS_QSOL WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"UPDATE ZMKT7352_DES_LICENCIAS_QSOL SET DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "Y");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    }
    
    @Override
    public Integer logicDeleteRescates() throws SQLException {
    	Connection connection = null;
		
		
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_RESCATES WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		
			
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"UPDATE ZMKT7352_DES_RESCATES SET DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "Y");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    	
    }
    
    @Override
    public Integer logicDeleteElectronicTools() throws SQLException {
    	Connection connection = null;
		
		
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_LICENCIAS_INSITE WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		
			
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"UPDATE ZMKT7352_DES_LICENCIAS_INSITE SET DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "Y");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    	
    }
    

    @Override
    public Integer logicDeleteNoDisponible() throws SQLException {
    	Connection connection = null;
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_NODISP WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		
			
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"UPDATE ZMKT7352_DES_NODISP SET DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "Y");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    	
    }
    @Override
    public Integer logicDeleteGarantia() throws SQLException {
    	Connection connection = null;
		
		System.out.println("Entramos al metodo");
		ResultSet re = null;
		PreparedStatement sStatement = null;
		int n = 0;
		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"SELECT COUNT(*) FROM ZMKT7352_DES_GARANTIAS WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
			re = sStatement.executeQuery();
	        
	        System.out.println("Ejecutamos");
	        if(re.next()) {
	            //Si hay resultados obtengo el valor. 
	             n= re.getInt(1);
	         }
			
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
		
			
		try {
			connection = dataSource.getConnection();
			String sqlSentenceBitacoraUp = 
					"DELETE FROM ZMKT7352_DES_GARANTIAS WHERE DELETE_IND = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, "N");
	        sStatement.execute();
			
	    
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
		}finally {
			connection.close();
		}
	    return n;
    	
    }
    
    @Override
    public Object deletePromotion(Object entity) {
    	
    	Promotion prom = mapper.convertValue(entity, Promotion.class);
         
    	Connection connection = null;
		
		PreparedStatement sStatement = null;

		try {
			connection = dataSource.getConnection();
			System.out.println("Conectamos");
			String sqlSentenceBitacoraUp = 
					"DELETE FROM ZMKT7352_DES_PROMOTION WHERE PROMOTION_ID = ? AND PROGRAM_ID=? AND SP_CODE = ?";
			sStatement = connection.prepareStatement(sqlSentenceBitacoraUp);
			sStatement.setString(1, prom.getPromotionId() );
			sStatement.setString(2, prom.getProgramId());
			sStatement.setString(3, prom.getSpCode());
			sStatement.execute();
	        
	        System.out.println("Ejecutamos");
	       
		}catch(Exception ex) {
			System.out.println("Excepción"+ex.getMessage() );
			
			
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
    	return prom;
    }
    
    
    
	@Override
	public List<SpCodes> findSpcodesXregion(String region, String lang, Boolean flag){
    	
    	System.out.println("find spcode x region");
    	List<SpCodes> listSpcodes = new ArrayList<>();
    	CountryPerRegion filterRegion= new CountryPerRegion();
    	filterRegion.setIdRegion(new BigInteger(region));
    	filterRegion.setDel('N');
    	
		
		try {
			@SuppressWarnings("unchecked")
		List<CountryPerRegion> listaPaises= (List<CountryPerRegion>) catalogService.findByCriteria("countryPerRegion", filterRegion,lang,false);
    	
    	if(listaPaises!=null) {
    		for(CountryPerRegion paises: listaPaises) {
    			System.out.println("pais"+ paises.getCountry().getName()+" id pais"+ paises.getCountry().getId());
    			SpCodes filtroSpcode= new SpCodes();
    			filtroSpcode.setIdPais(paises.getCountry().getId());
    			filtroSpcode.setTipo(new BigInteger("1"));
    			filtroSpcode.setDel('N');
    			List<SpCodes> lstSpcodesXpais= (List<SpCodes>) findSPCODESByCriteria(filtroSpcode,lang,flag);
    			for (SpCodes sp: lstSpcodesXpais) {
    				System.out.println(sp);
    				listSpcodes.add(sp);
    			}
    		}
    	}
    	System.out.println("la lista es "+ listSpcodes);
    	
		}catch(Exception e) {
			System.out.println("error "+ e);
		}
		listSpcodes.sort(Comparator.comparing(SpCodes::getProviderName));
		return listSpcodes;
    }
	
	
	
	
	/*@Override
	public List<MotorProducts> findMotorProductsXregion(String region){
    	
		System.out.println("find motor x region");
    	List<MotorProducts> listMotorProductos = new ArrayList<>();
    	
    	ESPM_Region filterRegion= new ESPM_Region();
    	filterRegion.setIdRegion(new BigInteger(region));
    	filterRegion.setDel('N');
    	System.out.println(filterRegion);
		try {
			@SuppressWarnings("unchecked")
			List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) findESPMRegionByCriteria(filterRegion);
    	
    	if(listaESPMxRegion!=null) {
    		for(ESPM_Region espmRegion: listaESPMxRegion) {
    			ESPM filtroEspm= new ESPM();
    			filtroEspm.setIdESPM(espmRegion.getIdESPM());
    			filtroEspm.setDel('N');
    			List<ESPM> lstESPM= (List<ESPM>) findESPMByCriteria(filtroEspm);
    			
    			for (ESPM espm: lstESPM) {
    				BigInteger id=espm.getMotor().getId();
    				String nombre=espm.getMotor().getName();
    				System.out.println(id);
    				MotorProducts filtroMotor= new MotorProducts();
    				filtroMotor.setId(espm.getIdMotor());
    				filtroMotor.setDel('N');
    				@SuppressWarnings("unchecked")
					List<MotorProducts> lstMotor= (List<MotorProducts>) catalogService.findByCriteria("motorProducts", filtroMotor);
    				
    				
    				for (MotorProducts motor: lstMotor) {
    					Boolean bandera=true;
    					for (MotorProducts m: listMotorProductos) {
    						
    						if(m.getId()==motor.getId()) {
    							bandera=false;
    							break;
    						}
    					}
    					if(bandera)
    				    listMotorProductos.add(motor);
    				}
    			}
    		}
    	}
    	System.out.println("la lista es "+ listMotorProductos);
    	
		}catch(Exception e) {
			System.out.println("error "+ e);
		}
		return listMotorProductos;
    }*/
	
	
	@Override
	public List<MotorProducts> findMotorProductsXregion(String region){
    	
		System.out.println("find motor x region");
    	List<MotorProducts> listMotorProductos = new ArrayList<>();
    	
    	ESPM_Region filterRegion= new ESPM_Region();
    	filterRegion.setIdRegion(new BigInteger(region));
    	filterRegion.setDel('N');
    	System.out.println(filterRegion);
		try {
			@SuppressWarnings("unchecked")
		//List<CountryPerRegion> listaPaises= (List<CountryPerRegion>) catalogService.findByCriteria("countryPerRegion", filterRegion);
			List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) findESPMRegionByCriteria(filterRegion);
    	
    	if(listaESPMxRegion!=null) {
    		for(ESPM_Region espmRegion: listaESPMxRegion) {
    			ESPM filtroEspm= new ESPM();
    			filtroEspm.setIdESPM(espmRegion.getIdESPM());
    			filtroEspm.setDel('N');
    			List<ESPM> lstESPM= (List<ESPM>) findESPMByCriteria(filtroEspm);
    			
    			//System.out.println(lstESPM);
    			for (ESPM espm: lstESPM) {
    			 Boolean existe=false;
    				MotorProducts motor= new MotorProducts();
    				motor.setId(espm.getMotor().getId());
    				motor.setName(espm.getMotor().getName());
    				motor.setComercialName(espm.getMotor().getComercialName());
    				for(MotorProducts m: listMotorProductos) {
    					if(m.getId()==motor.getId()) {
    						existe=true;
    						break;
    					}
    					
    				}
    				if(existe==false)
				    listMotorProductos.add(motor);
					
    					
    				
    				//System.out.println(espm);
    			}
    		}
    	}
    	//System.out.println("la lista es "+ listMotorProductos);
    	
		}catch(Exception e) {
			System.out.println("error "+ e);
		}
		listMotorProductos.sort(Comparator.comparing(MotorProducts::getName));
		return listMotorProductos;
    }
	
	@Override
	public List<MotorProductJoinESPM> findMotorRegionEspm(String region){
    	
		System.out.println("find motor x region join espm");
    	List<MotorProductJoinESPM> listMotorProductos = new ArrayList<>();
    	
    	ESPM_Region filterRegion= new ESPM_Region();
    	filterRegion.setIdRegion(new BigInteger(region));
    	filterRegion.setDel('N');
    	System.out.println(filterRegion);
		try {
			@SuppressWarnings("unchecked")
		//List<CountryPerRegion> listaPaises= (List<CountryPerRegion>) catalogService.findByCriteria("countryPerRegion", filterRegion);
			List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) findESPMRegionByCriteria(filterRegion);
    	
    	if(listaESPMxRegion!=null) {
    		for(ESPM_Region espmRegion: listaESPMxRegion) {
    			ESPM filtroEspm= new ESPM();
    			filtroEspm.setIdESPM(espmRegion.getIdESPM());
    			filtroEspm.setDel('N');
    			
    			
    			List<ESPM> lstESPM= (List<ESPM>) findESPMByCriteria(filtroEspm);
    			
    			//System.out.println(lstESPM);
    			for (ESPM espm: lstESPM) {
    				
    				//------------------------------
        			//           APLICACION
        			//-------------------------------
        			Application app= (Application) catalogService.findById("application", espm.getIdApp().toString());
        			Ranks rank=(Ranks) catalogService.findById("ranks", espm.getIdRango().toString());
        			MotorProducts motor=(MotorProducts)catalogService.findById("motorProducts", espm.getIdMotor().toString());
    				
    				MotorProductJoinESPM mpespm=new MotorProductJoinESPM();
    				mpespm.setIdEspm(espm.getIdESPM());
    				mpespm.setName(motor.getName()+" ("+espm.getIdESPM()+"/"+rank.getRank()+"/"+app.getApplication()+")");
    				listMotorProductos.add(mpespm);
    			}
    		}
    	}
    	//System.out.println("la lista es "+ listMotorProductos);
    	
		}catch(Exception e) {
			System.out.println("error "+ e);
		}
		listMotorProductos.sort(Comparator.comparing(MotorProductJoinESPM::getName));
		return listMotorProductos;
    }
	
	
	
    
    
}