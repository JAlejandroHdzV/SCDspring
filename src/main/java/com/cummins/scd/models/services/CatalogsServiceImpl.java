package com.cummins.scd.models.services;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cummins.scd.models.dao.IRegionDao;
import com.cummins.scd.models.dao.ICountryDao;
import com.cummins.scd.models.dao.ICountryPerRegionDao;
import com.cummins.scd.models.dao.IMotorProductsDao;
import com.cummins.scd.models.dao.IRanksDao;
import com.cummins.scd.global.FileStorageException;
import com.cummins.scd.models.dao.IApplicationDao;
import com.cummins.scd.models.dao.IEmissionsDao;
import com.cummins.scd.models.dao.IOemsDao;
import com.cummins.scd.models.dao.IServiceLevelDao;
import com.cummins.scd.models.dao.IAux_EvaluacionDao;
import com.cummins.scd.models.dao.IAux_RevisionesDao;
import com.cummins.scd.models.dao.IAux_AnioDao;
import com.cummins.scd.models.dao.IAux_StatusDao;
import com.cummins.scd.models.dao.IEvaluacionesDao;
import com.cummins.scd.models.dao.ICategoryDao;
import com.cummins.scd.models.dao.IToolsDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_ModulosDao;
import com.cummins.scd.models.dao.auxiliar.tables.IAux_RolesDao;
import com.cummins.scd.models.dto.EvaluacionesDTO;
import com.cummins.scd.models.dao.IQSolDao;
import com.cummins.scd.models.dao.IQuejasDao;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.CountryPerRegion;
import com.cummins.scd.models.entity.ESPM_Region;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.Emissions;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.Aux_Evaluacion;
import com.cummins.scd.models.entity.Aux_Revisiones;
import com.cummins.scd.models.entity.Aux_Anio;
import com.cummins.scd.models.entity.Aux_Status;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.Category;
import com.cummins.scd.models.entity.Tools;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Modulos;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;
import com.cummins.scd.models.entity.QSol;
import com.cummins.scd.models.entity.Quejas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


//import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CatalogsServiceImpl implements ICatalogsService{

	@Autowired
    private IRegionDao regionDao;
    @Autowired
    private ICountryDao countryDao;
    @Autowired
    private ICountryPerRegionDao countryPerRegionDao;
    @Autowired
    private IMotorProductsDao motorProductsDao;
    @Autowired
    private IRanksDao ranksDao;
    @Autowired
    private IApplicationDao applicationDao;
    @Autowired
    private IEmissionsDao emissionsDao;
    @Autowired
    private IOemsDao oemsDao;
    @Autowired
    private IServiceLevelDao serviceLevelDao;
    @Autowired
    private IAux_EvaluacionDao auxEvaluacionDao;
    @Autowired
    private IAux_RevisionesDao auxRevisionesDao;
    @Autowired
    private IAux_AnioDao auxAnioDao;
    @Autowired
    private IAux_StatusDao auxStatusDao;
    @Autowired
    private IEvaluacionesDao evaluacionesDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IToolsDao toolsDao;
    @Autowired
    private IQSolDao qsolDao;
    @Autowired
    private IQuejasDao quejasDao;
    @Autowired
    private IAux_ModulosDao modulosDao;
    @Autowired
    private IAux_RolesDao rolesDao;
    
    /*
    public Object findByCriteria(String catalogue, Object filter);
    */
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @PersistenceContext
	private EntityManager em;
  
    Date date;
    //------------------------------------------------------------------------------
    //								Switch find all from catalog
    //------------------------------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public Object findAll(String catalogue) {
        switch (catalogue) {
            case "region":                
                return regionDao.findAll();
            case "country":                
            	return countryDao.findAll();
            case "countryPerRegion":                
                return countryPerRegionDao.findAll();
            case "motorProducts":                
                return motorProductsDao.findAll();
            case "ranks":                
                return ranksDao.findAll();
            case "application":                
                return applicationDao.findAll();
            case "emissions":                
                return emissionsDao.findAll();
            case "oems":                
                return oemsDao.findAll();
            case "serviceLevel":                
                return serviceLevelDao.findAll();
            case "tipoEvaluacion":
                return auxEvaluacionDao.findAll();
            case "revisiones":
                return auxRevisionesDao.findAll();
            case "anio":
                return auxAnioDao.findAll();
            case "status":
                return auxStatusDao.findAll();
            case "evaluaciones":
                return evaluacionesDao.findAll();
            case "categoria":
                return categoryDao.findAll();
            case "tools":
                return toolsDao.findAll(new  Specification<Tools>() {

                    @Override
                    public Predicate toPredicate(Root<Tools> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        List<Predicate> predicates = new ArrayList<>();
				
                         return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                },new Sort(Sort.Direction.ASC, "np")
                        .and(new Sort(Sort.Direction.ASC, "nPAnterior")));
            case "qsol":
                return qsolDao.findAll();
            case "quejas":
                return quejasDao.findAll();
            case "modulos":
                return modulosDao.findAll();
            case "roles":
                return rolesDao.findAll();
            default:
                return null;
        }
    }
    //------------------------------------------------------------------------------
    //								Switch findbyId from catalog
    //------------------------------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public Object findById(String catalogue, String id) {
        switch (catalogue) {
            case "region":                
                return regionDao.findOne(new BigInteger(id));
            case "country":                
            	return countryDao.findOne(new BigInteger(id));
            case "countryPerRegion":                
                return countryPerRegionDao.findOne(new BigInteger(id));
            case "motorProducts":                
                return motorProductsDao.findOne(new BigInteger(id));
            case "ranks":                
                return ranksDao.findOne(new BigInteger(id));
            case "application":                
                return applicationDao.findOne(new BigInteger(id));
            case "emissions":                
                return emissionsDao.findOne(new BigInteger(id));
            case "oems":                
                return oemsDao.findOne(new BigInteger(id));
            case "serviceLevel":                
            	return serviceLevelDao.findOne(new BigInteger(id));
            case "tipoEvaluacion":                
                return auxEvaluacionDao.findOne(new BigInteger(id));
            case "revisiones":                
                return auxRevisionesDao.findOne(new BigInteger(id));
            case "anio":                
                return auxAnioDao.findOne(new BigInteger(id));
            case "status":                
                return auxStatusDao.findOne(new BigInteger(id));
            case "evaluaciones":                
                return evaluacionesDao.findOne(new BigInteger(id));
            case "categoria":                
                return  categoryDao.findOne(new BigInteger(id));
            case "tools":                
                return  toolsDao.findOne(id);
            case "qsol":                
            	return  qsolDao.findOne(new BigInteger(id));
            case "quejas":
                return quejasDao.findOne(new BigInteger(id));
            case "modulos":
                return modulosDao.findOne(new BigInteger(id));
            case "roles":
                return rolesDao.findOne(new BigInteger(id));
            default:
                return null;
        }
    }
    
    
    //------------------------------------------------------------------------------
    //								Switch save catalog
    //------------------------------------------------------------------------------
    @Override
    @Transactional
    public Object save(String catalogue, Object entity, String wwid) {
        ObjectMapper mapper = new ObjectMapper();
        switch (catalogue) {
            case "region":  
                Region region = mapper.convertValue(entity, Region.class);
                region.setCreatedBy(wwid);
                region.setLastUpdatedBy(wwid);
                return regionDao.save(region);
            case "country":  
                Country country = mapper.convertValue(entity, Country.class);
                date = new Date();
                country.setCreatedBy(wwid);
                country.setLastUpdatedBy(wwid);
                country.setCreationDate(date);
                country.setLastUpdateDate(date);
                return countryDao.save(country);
            case "countryPerRegion":  
                CountryPerRegion countryPerRegion = mapper.convertValue(entity, CountryPerRegion.class);
                date = new Date();
                countryPerRegion.setCreatedBy(wwid);
                countryPerRegion.setLastUpdatedBy(wwid);
                countryPerRegion.setCreationDate(date);
                countryPerRegion.setLastUpdateDate(date);
                return countryPerRegionDao.save(countryPerRegion);
            case "motorProducts":  
                MotorProducts motorProducts = mapper.convertValue(entity, MotorProducts.class);
                date = new Date();
                motorProducts.setCreatedBy(wwid);
                motorProducts.setLastUpdatedBy(wwid);
                motorProducts.setCreationDate(date);
                motorProducts.setLastUpdateDate(date);
                return motorProductsDao.save(motorProducts);
            case "ranks":  
                Ranks ranks = mapper.convertValue(entity, Ranks.class);
                date = new Date();
                ranks.setCreatedBy(wwid);
                ranks.setLastUpdatedBy(wwid);
                ranks.setCreationDate(date);
                ranks.setLastUpdateDate(date);
                return ranksDao.save(ranks);
            case "application":  
                Application application = mapper.convertValue(entity, Application.class);
                date = new Date();
                application.setCreatedBy(wwid);
                application.setLastUpdatedBy(wwid);
                application.setCreationDate(date);
                application.setLastUpdateDate(date);
                return applicationDao.save(application);
            case "emissions":  
                Emissions emissions = mapper.convertValue(entity, Emissions.class);
                date = new Date();
                emissions.setCreatedBy(wwid);
                emissions.setLastUpdatedBy(wwid);
                emissions.setCreationDate(date);
                emissions.setLastUpdateDate(date);
                return emissionsDao.save(emissions);
            case "oems":  
                Oems oems = mapper.convertValue(entity, Oems.class);
                date = new Date();
                oems.setCreatedBy(wwid);
                oems.setLastUpdatedBy(wwid);
                oems.setCreationDate(date);
                oems.setLastUpdateDate(date);
                return oemsDao.save(oems);
            case "serviceLevel":  
                ServiceLevel serviceLevel = mapper.convertValue(entity, ServiceLevel.class);
                date = new Date();
                serviceLevel.setCreatedBy(wwid);
                serviceLevel.setLastUpdatedBy(wwid);
                serviceLevel.setCreationDate(date);
                serviceLevel.setLastUpdateDate(date);
                
                return serviceLevelDao.save(serviceLevel);
            case "tipoEvaluacion":  
                Aux_Evaluacion auxEvaluacion = mapper.convertValue(entity, Aux_Evaluacion.class);
                date = new Date();
                auxEvaluacion.setCreatedBy(wwid);
                auxEvaluacion.setLastUpdatedBy(wwid);
                auxEvaluacion.setCreationDate(date);
                auxEvaluacion.setLastUpdateDate(date);
                
                return auxEvaluacionDao.save(auxEvaluacion);
            case "revisiones":  
                Aux_Revisiones auxRevisiones = mapper.convertValue(entity, Aux_Revisiones.class);
                auxRevisiones.setCreatedBy(wwid);
                auxRevisiones.setLastUpdatedBy(wwid);
                auxRevisiones.setCreationDate(date);
                auxRevisiones.setLastUpdateDate(date);
                return auxRevisionesDao.save(auxRevisiones);
            case "anio":  
                Aux_Anio auxAnio = mapper.convertValue(entity, Aux_Anio.class);
                return auxAnioDao.save(auxAnio);
            case "status":  
                Aux_Status auxStatus = mapper.convertValue(entity, Aux_Status.class);
                return auxStatusDao.save(auxStatus);
            case "evaluaciones":  
                Evaluaciones evaluaciones = mapper.convertValue(entity, Evaluaciones.class);
                System.out.println(evaluaciones);
                date = new Date();
                evaluaciones.setCreatedBy(wwid);
                evaluaciones.setLastUpdatedBy(wwid);
                evaluaciones.setCreationDate(date);
                evaluaciones.setLastUpdateDate(date);
                List<QSol> listQ = evaluaciones.getDetalles();
                evaluaciones.setDetalles(null);
               
                
                Evaluaciones eval = evaluacionesDao.save(evaluaciones);
                System.out.println(eval.getId());
                /*eval.setDetalles(listQ);
                List<QSol> listQn = savePreguntas(eval);
                eval.setDetalles(listQn);*/
                return eval;
            case "categoria":  
                Category categorias = mapper.convertValue(entity, Category.class);
                date = new Date();
                categorias.setCreatedBy(wwid);
                categorias.setLastUpdatedBy(wwid);
                categorias.setCreationDate(date);
                categorias.setLastUpdateDate(date);
                return categoryDao.save(categorias);
            case "tools":  
                Tools tool = mapper.convertValue(entity, Tools.class);
                date = new Date();
                tool.setCreatedBy(wwid);
                tool.setLastUpdatedBy(wwid);
                tool.setCreationDate(date);
                tool.setLastUpdateDate(date);
                return toolsDao.save(tool);
            case "qsol":  
                QSol qsol = mapper.convertValue(entity, QSol.class);
                date = new Date();
                qsol.setCreatedBy(wwid);
                qsol.setLastUpdatedBy(wwid);
                qsol.setCreationDate(date);
                qsol.setLastUpdateDate(date);
                return qsolDao.save(qsol);
            case "qsollist":  
            	List<QSol> list = new ArrayList<QSol>();
            	JsonNode actualObj = null;
			System.out.println("Trata de mappear");
			actualObj = mapper.convertValue(entity, JsonNode.class);
			int count = 0;
			float totalPonderacion = 0;
			if(actualObj!=null) {
				System.out.println("No vacio");
				 /*@SuppressWarnings("unchecked")
				List<QSol> listQsol=(List<QSol> ) 
						 em.createQuery("SELECT c from QSol c where c.idEvaluacion='"+
				         actualObj.get(0).get("idEvaluacion").toString().replaceAll("\"", "")+"' ")
						 .getResultList();
				 System.out.println(listQsol);
				 for(QSol qs:listQsol) 
				 {
				      qs.setLastUpdateDate(date); 
				      qs.setLastUpdatedBy(wwid);
				      qs.setDel('Y');
				      qsolDao.save(qs);
				 }*/
				 while(count<actualObj.size()) {
					 System.out.println("Recorrer");
					 QSol qsolL = new QSol();
					
					 if(actualObj.get(count).get("del")!=null)
						 qsolL.setDel(actualObj.get(count).get("del").toString().replaceAll("\"", "").charAt(0));
					 if(actualObj.get(count).get("idAskqsol")!=null)
						 qsolL.setIdAskqsol(new BigInteger (actualObj.get(count).get("idAskqsol").toString().replaceAll("\"", "")));
						
					 if(actualObj.get(count).get("idEvaluacion")!=null)
						 qsolL.setIdEvaluacion(new BigInteger(actualObj.get(count).get("idEvaluacion").toString().replaceAll("\"", "")));
					 if(actualObj.get(count).get("ponderacion")!=null) {
						 qsolL.setPonderacion(new Float(actualObj.get(count).get("ponderacion").toString().replaceAll("\"", "")));
						 totalPonderacion = totalPonderacion + qsolL.getPonderacion();
						 System.out.println(totalPonderacion);
					 }
					 if(actualObj.get(count).get("pregunta")!=null)
						 qsolL.setPregunta(actualObj.get(count).get("pregunta").toString().replaceAll("\"", ""));
					 if(actualObj.get(count).get("english")!=null)
						 qsolL.setEnglish(actualObj.get(count).get("english").toString().replaceAll("\"", ""));
					 if(actualObj.get(count).get("portuguese")!=null)
						 qsolL.setPortuguese(actualObj.get(count).get("portuguese").toString().replaceAll("\"", ""));
					 qsolL.setCreatedBy(wwid);
			         qsolL.setLastUpdatedBy(wwid);
			         qsolL.setCreationDate(date);
			         qsolL.setLastUpdateDate(date);
			         System.out.println("Trata de guardar");
					 list.add(qsolDao.save(qsolL));
					 count++;
				 }
			}
            
            	return list;
            case "quejas":  
                Quejas q = mapper.convertValue(entity, Quejas.class);
                date = new Date();
                q.setCreationDate(date);
                q.setCreatedBy(wwid);
                q.setLastUpdateDate(date);
                q.setLastUpdateBy(wwid);
                return quejasDao.save(q);
            case "modulos":  
                Aux_Modulos am = mapper.convertValue(entity, Aux_Modulos.class);
                date = new Date();
                am.setCreationDate(date);
                am.setCreatedBy(wwid);
                am.setLastUpdateDate(date);
                am.setLastUpdateBy(wwid);
                return modulosDao.save(am);
            case "roles":  
                Aux_Roles rol = mapper.convertValue(entity, Aux_Roles.class);
                date = new Date();
                rol.setCreationDate(date);
                rol.setCreatedBy(wwid);
                rol.setLastUpdateDate(date);
                rol.setLastUpdateBy(wwid);
                return rolesDao.save(rol);
            default:
                return null;
        }
    }
    
    //------------------------------------------------------------------------------
    //							Switch delete catalog
    //------------------------------------------------------------------------------
    @Override
    @Transactional
    public void delete(String catalogue, String id) {
        switch (catalogue) {
			case "region":  
                regionDao.delete(new BigInteger(id));              
                break;
            case "country":                
                countryDao.delete(new BigInteger(id));
                break;
            case "countryPerRegion":                
                countryPerRegionDao.delete(new BigInteger(id));
                break;
            case "motorProducts":                
                motorProductsDao.delete(new BigInteger(id));
                break;
            case "ranks":                
                ranksDao.delete(new BigInteger(id));
                break;
            case "application":                
                applicationDao.delete(new BigInteger(id));
                break;
            case "emissions":                
                emissionsDao.delete(new BigInteger(id));
                break;
            case "oems":                
                oemsDao.delete(new BigInteger(id));
                break;
            case "serviceLevel":                
                serviceLevelDao.delete(new BigInteger(id));
                break;
            case "tipoEvaluacion":                
                auxEvaluacionDao.delete(new BigInteger(id));
                break;
            case "anio":                
                auxAnioDao.delete(new BigInteger(id));
                break;
            case "status":                
                auxStatusDao.delete(new BigInteger(id));
                break;
            case "evaluaciones":                
                evaluacionesDao.delete(new BigInteger(id));
                break;
            case "categoria":                
                categoryDao.delete(new BigInteger(id));
                break;
            case "tools":                
                toolsDao.delete(id);
                break;
            case "qsol":                
                qsolDao.delete(new BigInteger(id));
                break;
            case "quejas":                
                quejasDao.delete(new BigInteger(id));
            case "modulos":                
                modulosDao.delete(new BigInteger(id));
                break;
            case "roles":                
                rolesDao.delete(new BigInteger(id));
                break;
			default:
                break;
        }
    }
    //------------------------------------------------------------------------------
    //					Switch find by criteria from catalog
    //------------------------------------------------------------------------------
    @Override
    public Object findByCriteria(String catalogue, Object filter, String lang, Boolean flag) {
        switch (catalogue) {
            case "region":                
                return findRegionByCriteria(filter);
            case "country":                
                return findCountryByCriteria(filter);
            case "countryPerRegion":                
                return findCountryPerRegionByCriteria(filter);
            case "motorProducts":                
                return findMotorProductsByCriteria(filter, lang, flag);
            case "ranks":                
                return findRanksByCriteria(filter, lang,flag);
            case "application":                
                return findApplicationByCriteria(filter, lang,flag);
            case "emissions":                
                return findEmissionsByCriteria(filter, lang, flag);
            case "oems":                
                return findOemsByCriteria(filter, lang, flag);
            case "serviceLevel":                
                return findServiceLevelByCriteria(filter, lang, flag);
            case "tipoEvaluacion":                
                return findEvaluacionByCriteria(filter, lang, flag);
            case "revisiones":                
                return findRevisionesByCriteria(filter, lang, flag);
            case "anio":                
                return findAnioByCriteria(filter);
            case "status":                
                return findStatusByCriteria(filter,lang, flag);
            case "evaluaciones":                
                return findEvaluacionesByCriteria(filter, lang, flag);
            case "categoria":                
                return findCategoryByCriteria(filter, lang, flag);
            case "tools":                
                return findToolByCriteria(filter, lang, flag);
            case "qsol":                
                return findQSolByCriteria(filter,lang, flag);
            case "quejas":                
                return findQuejasByCriteria(filter);
            case "modulos":                
                return findModulosByCriteria(filter);
            case "roles":                
                return findRolesByCriteria(filter);
            default:
                return null;
        }
	}
    //------------------------------------------------------------------------------
    //						Switch find by Existing from catalog
    //------------------------------------------------------------------------------
    @Override
    public Object findExisting(String catalogue, Object filter) {
        switch (catalogue) {
            case "region":                
                return findRegionExisting(filter);
            case "country":                
                return findCountryByCriteria(filter);
            case "countryPerRegion":                
                return findCountryPerRegionByCriteria(filter);
            case "motorProducts":                
                return findMotorProductsExisting(filter);
            case "ranks":                
                return findRanksExisting(filter);
            case "application":                
                return findApplicationExisting(filter);
            case "emissions":                
                return findEmissionsExisting(filter);
            case "oems":                
                return findOemsExisting(filter);
            case "serviceLevel":                
                return findServiceLevelExisting(filter);
            case "tipoEvaluacion":                
                return findEvaluacionExisting(filter);
            case "revisiones":                
                return findRevisionesExisting(filter);         
            case "anio":                
                return findAnioExisting(filter);
            case "status":                
                return findStatusExisting(filter);
            case "evaluaciones":                
                return findEvaluacionesExisting(filter);
            case "categoria":                
                return findCategoryExisting(filter);
            case "tools":                
                return findToolExisting(filter);
            case "qsol":                
                return findQSolExisting(filter);
            case "quejas":                
                return findQuejasExisting(filter);
            case "modulos":                
                return findModulosExisting(filter);
            
            default:
                return null;
        }
    }
    
	//------------------------------------------------------------------------------------
	//                           Find By Criteria from catalog
	//------------------------------------------------------------------------------------
	public List<Region> findRegionByCriteria(Object entityFilter){
        
	     Region filter = mapper.convertValue(entityFilter, Region.class);
      
        return regionDao.findAll( new  Specification<Region>() {

			@Override
			public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				
				if(filter.getId() != null) {
					predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getId())));
				}				
				if(filter.getName() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("region")), "%" + filter.getName().toUpperCase() + "%")));
                }
                if(filter.getCreationDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getCreatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
					predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                    predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}, new Sort(Sort.Direction.ASC, "region"));
    }
    public List<CountryPerRegion> findCountryPerRegionByCriteria(Object entityFilter){
        
        CountryPerRegion filter = mapper.convertValue(entityFilter, CountryPerRegion.class);
     /*  List<Calendario> list = new ArrayList<Calendario>();
       list.add(filter);
       return list;*/
       return countryPerRegionDao.findAll(new  Specification<CountryPerRegion>() {

           @Override
           public Predicate toPredicate(Root<CountryPerRegion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               
               if(filter.getIdRegion() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));
               }
               if(filter.getIdCountry() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idPais"), filter.getIdCountry())));
                }
               if(filter.getCreationDate() != null ) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
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
       }, new Sort(Sort.Direction.ASC, "country.name")); 
            //.and(new Sort(Sort.Direction.ASC, "country.name"))); 
     //"region.region"
    
    }
    
    
    public List<MotorProducts> findMotorProductsByCriteria(Object entityFilter, String lang, Boolean flag){
        
        MotorProducts filter = mapper.convertValue(entityFilter, MotorProducts.class);
     
        List<MotorProducts> motor= motorProductsDao.findAll( new  Specification<MotorProducts>() {

           @Override
           public Predicate toPredicate(Root<MotorProducts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("id"), filter.getId())));
               }				
               if(filter.getName() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%")));
               }
               if(filter.getComercialName() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("comercialName")), "%" + filter.getComercialName().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "name"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<motor.size();i++) {
		        		motor.get(i).getCategory().setCategory(motor.get(i).getCategory().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<motor.size();i++) {
		        		motor.get(i).getCategory().setCategory(motor.get(i).getCategory().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      motor.sort(Comparator.comparing(MotorProducts::getName));
      return motor;
    }   
    public List<Country> findCountryByCriteria(Object entityFilter){
        
        Country filter = mapper.convertValue(entityFilter, Country.class);
     
        return countryDao.findAll( new  Specification<Country>() {

           @Override
           public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idCountry"), filter.getId())));
               }
               
               if(filter.getName() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "name"));
    }
    
    @Override
    public Country findCountryByName(Object entityFilter){
        
        Country filter = mapper.convertValue(entityFilter, Country.class);
     
        List<Country> list = countryDao.findAll( new  Specification<Country>() {

           @Override
           public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
               
               
               if(filter.getName() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%")));
               }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "name"));
        
        if(list.size()>0)
        	return list.get(0);
        else
        	return null;
    }
    
    @Override
    public Application findApplicationByName(Object entityFilter){
        
        Application filter = mapper.convertValue(entityFilter, Application.class);
     
        List<Application> list = applicationDao.findAll( new  Specification<Application>() {

           @Override
           public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if(filter.getApplication() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("application")), filter.getApplication().toUpperCase())));
               }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "application"));
        
        if(list.size()>0)
        	return list.get(0);
        else
        	return null;
    }
    @Override
    public ServiceLevel findServiceLevelByName(Object entityFilter){
        
        ServiceLevel filter = mapper.convertValue(entityFilter, ServiceLevel.class);
     
        List<ServiceLevel> list =serviceLevelDao.findAll( new  Specification<ServiceLevel>() {

           @Override
           public Predicate toPredicate(Root<ServiceLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if(filter.getServiceLevel() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("serviceLevel")), filter.getServiceLevel().toUpperCase())));
               }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "serviceLevel"));
        
        if(list.size()>0)
        	return list.get(0);
        else
        	return null;
    }
    
    @Override
    public MotorProducts findMotorProductsByName(Object entityFilter){
        
        MotorProducts filter = mapper.convertValue(entityFilter, MotorProducts.class);
     
        List<MotorProducts> list =motorProductsDao.findAll( new  Specification<MotorProducts>() {

           @Override
           public Predicate toPredicate(Root<MotorProducts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if(filter.getName() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("name")), filter.getName().toUpperCase())));
               }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "name"));
        
        if(list.size()>0)
        	return list.get(0);
        else
        	return null;
    }
    
    @Override
    public Ranks findRankByName(Object entityFilter){
        
        Ranks filter = mapper.convertValue(entityFilter, Ranks.class);
     
        List<Ranks> list =ranksDao.findAll( new  Specification<Ranks>() {

           @Override
           public Predicate toPredicate(Root<Ranks> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if(filter.getRank() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("rank")), filter.getRank().toUpperCase())));
               }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "rank"));
        
        if(list.size()>0)
        	return list.get(0);
        else
        	return null;
    }
    
    public List<Ranks> findRanksByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Ranks filter = mapper.convertValue(entityFilter, Ranks.class);
     
       List<Ranks> ranks= ranksDao.findAll( new  Specification<Ranks>() {

           @Override
           public Predicate toPredicate(Root<Ranks> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idRank"), filter.getId())));
                }				
                if(filter.getRank() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("rank")), "%" + filter.getRank().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "rank"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<ranks.size();i++) {
		        		ranks.get(i).setRank(ranks.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<ranks.size();i++) {
		        		ranks.get(i).setRank(ranks.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
       }
       ranks.sort(Comparator.comparing(Ranks::getRank));
       return ranks;
    } 
    
    
    //--------------------------------------------
    //		application
    //-------------------------------------------
    public List<Application> findApplicationByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Application filter = mapper.convertValue(entityFilter, Application.class);
     
        List<Application> app= applicationDao.findAll( new  Specification<Application>() {

           @Override
           public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idApp"), filter.getId())));
                }				
                if(filter.getApplication() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("application")), "%" + filter.getApplication().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "application"));
        if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<app.size();i++) {
		        		app.get(i).setApplication(app.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<app.size();i++) {
		        		app.get(i).setApplication(app.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
        }
        app.sort(Comparator.comparing(Application::getApplication));
        return app;
    } 
    
    
    
    
    
    public List<Emissions> findEmissionsByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Emissions filter = mapper.convertValue(entityFilter, Emissions.class);
     
       List<Emissions> emissions= emissionsDao.findAll( new  Specification<Emissions>() {

           @Override
           public Predicate toPredicate(Root<Emissions> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEmission"), filter.getId())));
                }				
                if(filter.getEmission() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("emission")), "%" + filter.getEmission().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "emission"));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<emissions.size();i++) {
		        		emissions.get(i).setEmission(emissions.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<emissions.size();i++) {
		        		emissions.get(i).setEmission(emissions.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
       }
       emissions.sort(Comparator.comparing(Emissions::getEmission));
       return emissions;
    } 
    public List<Oems> findOemsByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Oems filter = mapper.convertValue(entityFilter, Oems.class);
     
       List<Oems> oems= oemsDao.findAll( new  Specification<Oems>() {

           @Override
           public Predicate toPredicate(Root<Oems> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idOem"), filter.getId())));
                }				
                if(filter.getOem() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("oem")), "%" + filter.getOem().toUpperCase() + "%")));
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
                   predicates.add(cb.and(cb.equal(root.get("del") , filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "oem"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<oems.size();i++) {
		        		oems.get(i).setOem(oems.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<oems.size();i++) {
		        		oems.get(i).setOem(oems.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      oems.sort(Comparator.comparing(Oems::getOem));
      return oems;
    }
    @Override
    public Oems findOemsByName(Object entityFilter){
        
        Oems filter = mapper.convertValue(entityFilter, Oems.class);
     
       List<Oems> list = oemsDao.findAll( new  Specification<Oems>() {

           @Override
           public Predicate toPredicate(Root<Oems> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idOem"), filter.getId())));
                }				
                if(filter.getOem() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("oem")), "%" + filter.getOem().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "oem"));
       
       if(list.size()>0)
    	   return list.get(0);
       else
    	   return null;
    }
    
    @Override
    public List<ServiceLevel> findServiceLevelByCriteria(Object entityFilter, String lang, Boolean flag){
        
        ServiceLevel filter = mapper.convertValue(entityFilter, ServiceLevel.class);
     
       List<ServiceLevel> serviceLevel= serviceLevelDao.findAll( new  Specification<ServiceLevel>() {

           @Override
           public Predicate toPredicate(Root<ServiceLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idServiceLevel"), filter.getId())));
                }				
                if(filter.getServiceLevel() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("serviceLevel")), "%" + filter.getServiceLevel().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "serviceLevel"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<serviceLevel.size();i++) {
		        	 serviceLevel.get(i).setServiceLevel(serviceLevel.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<serviceLevel.size();i++) {
		        		serviceLevel.get(i).setServiceLevel(serviceLevel.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      serviceLevel.sort(Comparator.comparing(ServiceLevel::getServiceLevel));
      return serviceLevel;
    }

    @Override
    public List<Aux_Evaluacion> findEvaluacionByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_Evaluacion filter = mapper.convertValue(entityFilter, Aux_Evaluacion.class);
     
       List<Aux_Evaluacion> ev= auxEvaluacionDao.findAll( new  Specification<Aux_Evaluacion>() {

           @Override
           public Predicate toPredicate(Root<Aux_Evaluacion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTEvaluacion"), filter.getId())));
                }				
                if(filter.getEvaluacion() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("tevaluacion")), "%" + filter.getEvaluacion().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "tevaluacion"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<ev.size();i++) {
		        		ev.get(i).setEvaluacion(ev.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<ev.size();i++) {
		        		ev.get(i).setEvaluacion(ev.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
   }
   ev.sort(Comparator.comparing(Aux_Evaluacion::getEvaluacion));
   return ev;
    }
    @Override
    public List<Aux_Revisiones> findRevisionesByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Aux_Revisiones filter = mapper.convertValue(entityFilter, Aux_Revisiones.class);
     
       List<Aux_Revisiones> revisiones= auxRevisionesDao.findAll( new  Specification<Aux_Revisiones>() {

           @Override
           public Predicate toPredicate(Root<Aux_Revisiones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idrevision"), filter.getId())));
                }				
                if(filter.getRevision() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("revision")), "%" + filter.getRevision().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "revision"));
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<revisiones.size();i++) {
		        		revisiones.get(i).setRevision(revisiones.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<revisiones.size();i++) {
		        		revisiones.get(i).setRevision(revisiones.get(i).getEnglish());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    revisiones.sort(Comparator.comparing(Aux_Revisiones::getRevision));
    return revisiones;
    }
    
    @Override
    public List<Aux_Anio> findAnioByCriteria(Object entityFilter){
        
        Aux_Anio filter = mapper.convertValue(entityFilter, Aux_Anio.class);
     
       return auxAnioDao.findAll( new  Specification<Aux_Anio>() {

           @Override
           public Predicate toPredicate(Root<Aux_Anio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idanio"), filter.getId())));
                }				
                if(filter.getAnio() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("anio")), "%" + filter.getAnio().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "anio"));
    }
    
    @Override
    public List<Aux_Status> findStatusByCriteria(Object entityFilter,String lang, Boolean flag){
        
        Aux_Status filter = mapper.convertValue(entityFilter, Aux_Status.class);
     
       List<Aux_Status> status= auxStatusDao.findAll( new  Specification<Aux_Status>() {

           @Override
           public Predicate toPredicate(Root<Aux_Status> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idstatus"), filter.getId())));
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
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
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
      status.sort(Comparator.comparing(Aux_Status::getStatus));
      return status;
    }
    @Override
    public List<Evaluaciones> findEvaluacionesByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Evaluaciones filter = mapper.convertValue(entityFilter, Evaluaciones.class);
       
        List<Evaluaciones> list = evaluacionesDao.findAll( new  Specification<Evaluaciones>() {

           @Override
           public Predicate toPredicate(Root<Evaluaciones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();

                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getId())));
                }
                if(filter.getIdRegion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));
                                  
                }
                if(filter.getIdTEvaluacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idTevaluacion"), filter.getIdTEvaluacion())));
                }
                if(filter.getIdRevision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRevision"), filter.getIdRevision())));
                }
                if(filter.getIdAnio() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idAnio"), filter.getIdAnio())));
                }
                if(filter.getIdStatus() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }
                
                if(filter.getInitDate() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("initDate")), "%" + filter.getInitDate().toString().toUpperCase() + "%")));
                }
                if(filter.getEndDate() != null ) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("endDate")), "%" + filter.getEndDate().toString().toUpperCase() + "%")));
                }		
                if(filter.getText1() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("text1")), "%" + filter.getText1().toUpperCase() + "%")));
                }
                if(filter.getText2() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("text2")), "%" + filter.getText2().toUpperCase() + "%")));
                }
                if(filter.getText3() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("text3")), "%" + filter.getText3().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "idRegion"));
        
        for(Evaluaciones ev:list) {
        	ev.setNombreRegion(ev.getRegion().getName());
        	
        }
        
        if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<list.size();i++) {
		        		list.get(i).getStatus().setStatus(list.get(i).getStatus().getEnglish());
		        		list.get(i).getRevisiones().setRevision(list.get(i).getRevisiones().getEnglish());
		        		list.get(i).getTEvaluacion().setEvaluacion(list.get(i).getTEvaluacion().getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<list.size();i++) {
		        		list.get(i).getStatus().setStatus(list.get(i).getStatus().getPortuguese());
		        		list.get(i).getRevisiones().setRevision(list.get(i).getRevisiones().getPortuguese());
		        		list.get(i).getTEvaluacion().setEvaluacion(list.get(i).getTEvaluacion().getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
       }
        
       
        
        list.sort(Comparator.comparing(Evaluaciones::obtenerNombreRegion));
        
        return list;
        
    }

    @Override
    public List<Category> findCategoryByCriteria(Object entityFilter, String lang, Boolean flag){
        
        Category filter = mapper.convertValue(entityFilter, Category.class);
     
       List<Category> categoria=categoryDao.findAll( new  Specification<Category>() {

           @Override
           public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idCategoria"), filter.getId())));
                }				
                if(filter.getCategory() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("categoria")), "%" + filter.getCategory().toUpperCase() + "%")));
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
           
       }, new Sort(Sort.Direction.ASC, "categoria"));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<categoria.size();i++) {
		        		categoria.get(i).setCategory(categoria.get(i).getEnglish());
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<categoria.size();i++) {
		        		categoria.get(i).setCategory(categoria.get(i).getPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
       }
       categoria.sort(Comparator.comparing(Category::getCategory));
       return categoria;
    }
    public List<Tools> findToolByCriteria(Object entityFilter, String lang, Boolean flag){
        System.out.println("Lang: "+lang+ " Bandera: "+flag+" tools catalog");
        Tools filter = mapper.convertValue(entityFilter, Tools.class);
     
       List<Tools> tools= toolsDao.findAll( new  Specification<Tools>() {

           @Override
           public Predicate toPredicate(Root<Tools> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getnP() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nP")), "%" + filter.getnP().toUpperCase() + "%")));
                }				
                if(filter.getnPAnterior() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("nPAnterior")), "%" + filter.getnPAnterior().toUpperCase() + "%")));
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
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nP"));
       
       if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<tools.size();i++) {
		        		tools.get(i).setdSpanish(tools.get(i).getdEnglish());
		        		//System.out.println("en");
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<tools.size();i++) {
		        		tools.get(i).setdSpanish(tools.get(i).getdPortuguese());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
      }
      tools.sort(Comparator.comparing(Tools::getnP));
      return tools;
    }
    
    
    
    public List<QSol> findQSolByCriteria(Object entityFilter, String lang, Boolean flag){
        
    	
    	
        QSol filter = mapper.convertValue(entityFilter, QSol.class);
     /*  List<Calendario> list = new ArrayList<Calendario>();
       list.add(filter);
       return list;*/
        List<QSol> list = qsolDao.findAll(new  Specification<QSol>() {

           @Override
           public Predicate toPredicate(Root<QSol> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if(filter.getIdAskqsol() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idAskqsol"), filter.getIdAskqsol())));
               }  
               
            if(filter.getIdEvaluacion() != null) {
                predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
            }
            if(filter.getPregunta() != null) {
                predicates.add(cb.and(cb.like(root.get("pregunta"), "%" +  filter.getPregunta().toUpperCase() + "%")));
            }
            if(filter.getPonderacion() >0) {
                predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
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
       }, new Sort(Sort.Direction.ASC, "idEvaluacion"));
     
       for(QSol ev: list) {
    	   
       	ev.setNombreRegion(ev.obtenerEvaluacion().getRegion().getName());
       	
       }
       if(flag==false) {
	       switch(lang) {
		       case"en_US":
			       	for(int i=0; i<list.size();i++) {
			       		list.get(i).setPregunta(list.get(i).getEnglish());
		       	}
		       	break;
		       	
		       case"pt_BR":
			       	for(int i=0; i<list.size();i++) {
			       		list.get(i).setPregunta(list.get(i).getPortuguese());
		       	}
		       	
		       	break;
		       	
		       default:
		       	break;
	       }
       }
         
       list.sort(Comparator.comparing(QSol::getNombreRegion));
       
       return list;
    
    }
 public List<Quejas> findQuejasByCriteria(Object entityFilter){
        
        Quejas filter = mapper.convertValue(entityFilter, Quejas.class);
     
       return quejasDao.findAll( new  Specification<Quejas>() {

           @Override
           public Predicate toPredicate(Root<Quejas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdEvaluacion() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
                }				
                if(filter.getQueja() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("queja")), "%" + filter.getQueja().toUpperCase() + "%")));
                }
                if(filter.getPonderacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
                 }
                 if(filter.getIdTipo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idTipo"), filter.getIdTipo())));
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
           
       }, new Sort(Sort.Direction.ASC, "queja"));
    }
 
 public List<Aux_Modulos> findModulosByCriteria(Object entityFilter){
     
     Aux_Modulos filter = mapper.convertValue(entityFilter, Aux_Modulos.class);
  
    return modulosDao.findAll( new  Specification<Aux_Modulos>() {

        @Override
        public Predicate toPredicate(Root<Aux_Modulos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();
            
             if(filter.getIdModulo() != null) {
                predicates.add(cb.and(cb.equal(root.get("idModulo"), filter.getIdModulo())));
             }				
             if(filter.getModulo() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("modulo")), "%" + filter.getModulo().toUpperCase() + "%")));
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
 public List<Aux_Roles> findRolesByCriteria(Object entityFilter){
     
     Aux_Roles filter = mapper.convertValue(entityFilter, Aux_Roles.class);
  
    return rolesDao.findAll( new  Specification<Aux_Roles>() {

        @Override
        public Predicate toPredicate(Root<Aux_Roles> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();
            
             if(filter.getIdRol() != null) {
                predicates.add(cb.and(cb.equal(root.get("idRol"), filter.getIdRol())));
             }				
             if(filter.getRol() != null) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("rol")), "%" + filter.getRol().toUpperCase() + "%")));
             }
             if(filter.getIngles() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("ingles")), "%" + filter.getIngles().toUpperCase() + "%")));
             }
             if(filter.getPortugues() != null) {
                 predicates.add(cb.and(cb.like(cb.upper(root.get("portugues")), "%" + filter.getPortugues().toUpperCase() + "%")));
             }
             if(filter.getCreationDate() != null ) {
                predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")), "%" + filter.getCreationDate().toString().toUpperCase() + "%")));
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
        
    }, new Sort(Sort.Direction.ASC, "idRol"));
 }

	//--------------------------------------------------------------------
	//                         Methods Find Existing
	//--------------------------------------------------------------------

    public List<Region> findRegionExisting(Object entityFilter){
        
        Region filter = mapper.convertValue(entityFilter, Region.class);
     /*  List<Calendario> list = new ArrayList<Calendario>();
       list.add(filter);
       return list;*/
       return regionDao.findAll(new  Specification<Region>() {

           @Override
           public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
                if(filter.getId() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getId())));
                }
                if(filter.getName() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("region")), filter.getName().toUpperCase())));
                // predicates.add(cb.and(cb.equal(cb.upper(root.get("region")), "SA")));
                }            
                if(filter.getDel() != null) {
                    predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }
            
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       });
    }
    public List<MotorProducts> findMotorProductsExisting(Object entityFilter){
        
        MotorProducts filter = mapper.convertValue(entityFilter, MotorProducts.class);
     
        return motorProductsDao.findAll( new  Specification<MotorProducts>() {

           @Override
           public Predicate toPredicate(Root<MotorProducts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("id"), filter.getId())));
                }				
                if(filter.getName() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("name")), filter.getName().toUpperCase())));
                }
                if(filter.getComercialName() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("comercialName")), filter.getComercialName().toUpperCase())));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "name"));
    }   
    public List<Ranks> findRanksExisting(Object entityFilter){
        
        Ranks filter = mapper.convertValue(entityFilter, Ranks.class);
     
       return ranksDao.findAll( new  Specification<Ranks>() {

           @Override
           public Predicate toPredicate(Root<Ranks> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idRank"), filter.getId())));
                }				
                if(filter.getRank() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("rank")), filter.getRank().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "rank"));
    } 
    public List<Application> findApplicationExisting(Object entityFilter){
        
        Application filter = mapper.convertValue(entityFilter, Application.class);
     
       return applicationDao.findAll( new  Specification<Application>() {

           @Override
           public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idApp"), filter.getId())));
                }				
                if(filter.getApplication() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("application")), filter.getApplication().toUpperCase() )));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")), filter.getEnglish().toUpperCase() )));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase() )));
                }                
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "application"));
    } 
	public List<Emissions> findEmissionsExisting(Object entityFilter){
        
        Emissions filter = mapper.convertValue(entityFilter, Emissions.class);
     
       return emissionsDao.findAll( new  Specification<Emissions>() {

           @Override
           public Predicate toPredicate(Root<Emissions> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEmission"), filter.getId())));
                }				
                if(filter.getEmission() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("emission")), filter.getEmission().toUpperCase() )));
                }
                if(filter.getEnglish() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("english")),  filter.getEnglish().toUpperCase() )));
                }
                if(filter.getPortuguese() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("portuguese")), filter.getPortuguese().toUpperCase() )));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "emission"));
    } 
    public List<Oems> findOemsExisting(Object entityFilter){
        
        Oems filter = mapper.convertValue(entityFilter, Oems.class);
        
        
       return oemsDao.findAll( new  Specification<Oems>() {

           @Override
           public Predicate toPredicate(Root<Oems> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idOem"), filter.getId())));
                }				
                if(filter.getOem() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("oem")), filter.getOem().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "oem" ) );
    }
    public List<ServiceLevel> findServiceLevelExisting(Object entityFilter){
        
    	
        ServiceLevel filter = mapper.convertValue(entityFilter, ServiceLevel.class);
     
       return serviceLevelDao.findAll( new  Specification<ServiceLevel>() {

           @Override
           public Predicate toPredicate(Root<ServiceLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idServiceLevel"), filter.getId())));
                }				
                if(filter.getServiceLevel() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("serviceLevel")), filter.getServiceLevel().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "serviceLevel"));
    }
    public List<Aux_Evaluacion> findEvaluacionExisting(Object entityFilter){
        
        Aux_Evaluacion filter = mapper.convertValue(entityFilter, Aux_Evaluacion.class);
     
       return auxEvaluacionDao.findAll( new  Specification<Aux_Evaluacion>() {

           @Override
           public Predicate toPredicate(Root<Aux_Evaluacion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idTEvaluacion"), filter.getId())));
                }				
                if(filter.getEvaluacion() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("tevaluacion")), filter.getEvaluacion().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "tevaluacion"));
    }
    public List<Aux_Revisiones> findRevisionesExisting(Object entityFilter){
        
        Aux_Revisiones filter = mapper.convertValue(entityFilter, Aux_Revisiones.class);
     
       return auxRevisionesDao.findAll( new  Specification<Aux_Revisiones>() {

           @Override
           public Predicate toPredicate(Root<Aux_Revisiones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idrevision"), filter.getId())));
                }				
                if(filter.getRevision() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("revision")), filter.getRevision().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "revision"));
    }    
    public List<Aux_Anio> findAnioExisting(Object entityFilter){
        
        Aux_Anio filter = mapper.convertValue(entityFilter, Aux_Anio.class);
     
       return auxAnioDao.findAll( new  Specification<Aux_Anio>() {

           @Override
           public Predicate toPredicate(Root<Aux_Anio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idanio"), filter.getId())));
                }				
                if(filter.getAnio() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("anio")), filter.getAnio().toUpperCase())));
                }               
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "anio"));
    }
    public List<Aux_Status> findStatusExisting(Object entityFilter){
        
        Aux_Status filter = mapper.convertValue(entityFilter, Aux_Status.class);
     
       return auxStatusDao.findAll( new  Specification<Aux_Status>() {

           @Override
           public Predicate toPredicate(Root<Aux_Status> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idstatus"), filter.getId())));
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
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }            
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "status"));
    }
    public List<Evaluaciones> findEvaluacionesExisting(Object entityFilter){
        
        Evaluaciones filter = mapper.convertValue(entityFilter, Evaluaciones.class);
     
       return evaluacionesDao.findAll( new  Specification<Evaluaciones>() {

           @Override
           public Predicate toPredicate(Root<Evaluaciones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
                if(filter.getId() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getId())));
                }
                if(filter.getIdRegion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));
                }
                if(filter.getIdTEvaluacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idTevaluacion"), filter.getIdTEvaluacion())));
                }
                if(filter.getIdRevision() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idRevision"), filter.getIdRevision())));
                }
                if(filter.getIdAnio() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idAnio"), filter.getIdAnio())));
                }
                if(filter.getIdStatus() != null) {
                    predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
                }
                
                if(filter.getInitDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("initDate")), filter.getInitDate().toString().toUpperCase())));
                }
                if(filter.getEndDate() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("endDate")), filter.getEndDate().toString().toUpperCase())));
                }		
                if(filter.getText1() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("text1")), filter.getText1().toUpperCase())));
                }
                if(filter.getText2() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("text2")), filter.getText2().toUpperCase())));
                }
                if(filter.getText3() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("text3")), filter.getText3().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "idEvaluacion"));
    }     
    public List<Category> findCategoryExisting(Object entityFilter){
        
        Category filter = mapper.convertValue(entityFilter, Category.class);
     
       return categoryDao.findAll( new  Specification<Category>() {

           @Override
           public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getId() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idCategoria"), filter.getId())));
                }				
                if(filter.getCategory() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("categoria")), filter.getCategory().toUpperCase())));
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
           
       }, new Sort(Sort.Direction.ASC, "categoria"));
    }
    
    @Override
    public List<Tools> findToolExisting(Object entityFilter){
        
        Tools filter = mapper.convertValue(entityFilter, Tools.class);
     
       return toolsDao.findAll( new  Specification<Tools>() {

           @Override
           public Predicate toPredicate(Root<Tools> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getnP() != null) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("nP")), filter.getnP().toUpperCase())));
                }				
                if(filter.getnPAnterior() != null) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("nPAnterior")),filter.getnPAnterior().toUpperCase())));
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
                if(filter.getLastUpdatedBy() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
                }              
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nP"));
    }

	public List<QSol> findQSolExisting(Object entityFilter){
        
        QSol filter = mapper.convertValue(entityFilter, QSol.class);
     
       return qsolDao.findAll( new  Specification<QSol>() {

           @Override
           public Predicate toPredicate(Root<QSol> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
            if(filter.getIdAskqsol() != null) {
                predicates.add(cb.and(cb.equal(root.get("idAskqsol"), filter.getIdAskqsol())));
            }  
               
            if(filter.getIdEvaluacion() != null) {
                predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
            }
            if(filter.getPregunta() != null) {
                predicates.add(cb.and(cb.equal(root.get("pregunta"), filter.getPregunta().toUpperCase())));
            }
            if(filter.getPonderacion() >0) {
                predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
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
            if(filter.getLastUpdatedBy() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("lastUpdateBy")),filter.getLastUpdatedBy().toUpperCase() )));
            }              
            if(filter.getDel() != null) {
                predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
            }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "idEvaluacion"));
    }
public List<Quejas> findQuejasExisting(Object entityFilter){
        
        Quejas filter = mapper.convertValue(entityFilter, Quejas.class);
     
       return quejasDao.findAll( new  Specification<Quejas>() {

           @Override
           public Predicate toPredicate(Root<Quejas> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
        
                if(filter.getIdEvaluacion() != null) {
                   predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getIdEvaluacion())));
                }				
                if(filter.getQueja() != null) {
                   predicates.add(cb.and(cb.equal(cb.upper(root.get("queja")), filter.getQueja().toUpperCase())));
                }
                if(filter.getPonderacion() != null) {
                    predicates.add(cb.and(cb.equal(root.get("ponderacion"), filter.getPonderacion())));
                 }
                 if(filter.getIdTipo() != null) {
                    predicates.add(cb.and(cb.equal(root.get("tipo"), filter.getIdTipo())));
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
           
       }, new Sort(Sort.Direction.ASC, "queja"));
    }

public List<Aux_Modulos> findModulosExisting(Object entityFilter){
    
    Aux_Modulos filter = mapper.convertValue(entityFilter, Aux_Modulos.class);
 
   return modulosDao.findAll( new  Specification<Aux_Modulos>() {

       @Override
       public Predicate toPredicate(Root<Aux_Modulos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
           List<Predicate> predicates = new ArrayList<>();
    
            if(filter.getIdModulo() != null) {
               predicates.add(cb.and(cb.equal(root.get("idModulo"), filter.getIdModulo())));
            }				
            if(filter.getModulo() != null) {
               predicates.add(cb.and(cb.equal(cb.upper(root.get("modulo")), filter.getModulo().toUpperCase())));
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
       
   }, new Sort(Sort.Direction.ASC, "idModulo"));
}
	
    public Oems storeFileOem(BigInteger id,String oem, String english,String portuguese, MultipartFile file, Date creationDate,String createdBy,Date lastUpdateDate, String lastUpdateBy, Character delete)
     throws FileStorageException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException(null, "Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Date date = new Date();
            Oems dbFile = new Oems();
            if(id != null)
                dbFile.setId(id);
            dbFile.setOem(oem);
            dbFile.setEnglish(english);
            dbFile.setPortuguese(portuguese);
            dbFile.setLogo(file.getBytes()); 
            dbFile.setFileName(fileName);
            dbFile.setFileType(file.getContentType());
            dbFile.setCreatedBy(createdBy);
            dbFile.setLastUpdatedBy(lastUpdateBy);
            dbFile.setCreationDate(creationDate);
            dbFile.setLastUpdateDate(lastUpdateDate);
            dbFile.setDel(delete);
            return oemsDao.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException(null,"Could not store file ");
        }
    }

    public MotorProducts storeFileMotorProducts(BigInteger id,String name, String comercialName, BigInteger idCategoria, MultipartFile file, Date creationDate,String createdBy,Date lastUpdateDate, String lastUpdateBy, Character delete)
     throws FileStorageException {
        // Normalize file name
        boolean isFileValid = file != null && !file.isEmpty();
        String fileName = isFileValid ? StringUtils.cleanPath(file.getOriginalFilename()) : "";

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException(null, "Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Date date = new Date();
            MotorProducts dbFile = new MotorProducts();
            if(id != null)
                dbFile.setId(id);
            if (isFileValid)
                dbFile.setFileName(fileName);
            dbFile.setName(name);
            dbFile.setComercialName(comercialName);
            dbFile.setIdCategoria(idCategoria);
            dbFile.setCoverPage(file.getBytes());
            dbFile.setFileType(file.getContentType());
            dbFile.setCreatedBy(createdBy);
            dbFile.setLastUpdatedBy(lastUpdateBy);
            dbFile.setCreationDate(creationDate);
            dbFile.setLastUpdateDate(lastUpdateDate);
            dbFile.setDel(delete);
            return motorProductsDao.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException(null,"Could not store file ");
        }
    }

    public Tools storeFileTools(String np,String npAnterior,String spanish,String english,String portuguese,String codigoVenta,MultipartFile file, String wwid, Character delete)
     throws FileStorageException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException(null, "Sorry! Filename contains invalid path sequence " + fileName);
            }
             Date date = new Date();
            Tools dbFile = new Tools();
            dbFile.setnP(np);
            dbFile.setnPAnterior(npAnterior);
            dbFile.setdSpanish(spanish);
            dbFile.setdEnglish(english);
            dbFile.setdPortuguese(portuguese);
            dbFile.setCodigoVenta(codigoVenta);
            dbFile.setEspecificacion(file.getBytes()); 
            dbFile.setFileName(fileName);
            dbFile.setFileType(file.getContentType());
            dbFile.setCreatedBy(wwid);
            dbFile.setLastUpdatedBy(wwid);
            dbFile.setCreationDate(date);
            dbFile.setLastUpdateDate(date);
            dbFile.setDel(delete);
            return toolsDao.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException(null,"Could not store file ");
        }
    }

    public Oems getFileOem(String fileId) {
        return oemsDao.findOne(new BigInteger(fileId));
             //   .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    public MotorProducts getFileMotorProducts(String fileId) {
        return motorProductsDao.findOne(new BigInteger(fileId));
             //   .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    public Tools getFileTools(String np) {
        return toolsDao.findOne(np);
             //   .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    
    public List<QSol> savePreguntas(Evaluaciones eval) {
    	
    	List<QSol> list = eval.getDetalles();
    	List<QSol> newList = new ArrayList<QSol>();
    	
    	for(QSol el:list) {
    		el.setIdEvaluacion(eval.getId());
    		QSol sav = qsolDao.save(el);
    		if(sav!=null)
    			newList.add(sav);
    	}
    	return newList;
    	
    }
    
    /*@Override
	public List<MotorProducts> findMotorProductsXregion(String region){
    	
    	System.out.println("find motor x region");
    	List<MotorProducts> listMotorProductos = new ArrayList<>();
    	
    	ESPM_Region filterRegion= new ESPM_Region();
    	filterRegion.setIdRegion(new BigInteger(region));
    	filterRegion.setDel('N');
    	
		
		try {
			@SuppressWarnings("unchecked")
		List<ESPM_Region> listaESPMxRegion= (List<ESPM_Region>) findByCriteria("countryPerRegion", filterRegion);
    	
    	if(listaESPMxRegion!=null) {
    		for( paises: listaPaises) {
    			System.out.println("pais"+ paises.getCountry().getName()+" id pais"+ paises.getCountry().getId());
    			Esp filtroSpcode= new SpCodes();
    			filtroSpcode.setIdPais(paises.getCountry().getId());
    			filtroSpcode.setDel('N');
    			List<SpCodes> lstSpcodesXpais= (List<SpCodes>) findSPCODESByCriteria(filtroSpcode);
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
		return listSpcodes;
    }*/
    
    
	
}