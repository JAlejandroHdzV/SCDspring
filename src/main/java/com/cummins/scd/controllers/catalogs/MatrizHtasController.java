package com.cummins.scd.controllers.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.dao.IAtributosMatricesHtasDao;
import com.cummins.scd.models.dao.IMatrizHtasDetDao;
import com.cummins.scd.models.dao.IMatrizHtasHdDao;
import com.cummins.scd.models.dao.IMatrizPartesDetDao;
import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dto.MatrizHtasEncDetDTO;
import com.cummins.scd.models.dto.MatrizPartesEncDetDTO;
import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.services.ILoadInfoService;
import com.cummins.scd.models.services.catalogs.IMatrizHtasService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class MatrizHtasController {

	@Autowired
	private ILoadInfoService loadService;
	@Autowired
	private IMatrizHtasDetDao matrizHtasDetDao;
	
	@Autowired
	private IMatrizHtasHdDao matrizHtasHdDao;
	
	@Autowired
	private IMatrizHtasService matrixHtasServiceDao;
	
	@Autowired
	private IAtributosMatricesHtasDao atributosDao;
		
	private ObjectMapper mapper = new ObjectMapper();
	
	
	//-----------------------------------------------------------------------------------
			//					
			//-----------------------------------------------------------------------------------
			@PostMapping("v1/admin/loadInfo/filter/matHtas")
		    public ResponseEntity<?> filter(@RequestBody Object filter){

		        Object catalogsEntity = null;
				Map<String, Object> response = new HashMap<>();

				try {
					//catalogsEntity = matrixHdService.partsMatrixExistConf(filter, "Español");
					catalogsEntity= matrixHtasServiceDao.matricesValidas(filter);
				} catch (DataAccessException e) {
					response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} catch(IllegalArgumentException mapExcepcion) {
					response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
					response.put("error", mapExcepcion.getMessage());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}catch(Exception e) {
					response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
					//response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				if(catalogsEntity == null) {
					response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}

				return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
			}
	
	
	
	//-----------------------------------------------------------------------------------
	//					Delete Part Matrix HD, DET, ATTR
	//-----------------------------------------------------------------------------------
	@PutMapping("/v1/admin/loadInfo/matHtas/{id}")
	public ResponseEntity<?> update( @PathVariable String id,HttpServletRequest request) {
		System.out.println("entra a herramientas");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		
		try {
			catalogsEntity = delEntity( id, wwid);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("mensaje", "Error al mapear datos.");
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
		} catch (Exception e) {
			response.put("mensaje", "Error al actualizar en la base de datos.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			System.out.println("CALL SERVICE");
			
			//catalogsService.sa .save( catalogsEntity,wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		
		
	}
	
	//------------------------------------------------------------------------------------
	//								       UPDATE
	//------------------------------------------------------------------------------------
	@PutMapping("v1/admin/loadInfo/matHtas")
	public ResponseEntity<?> update(@RequestBody Object entity,HttpServletRequest request) {
		//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		
		try {
			catalogsEntity = updateEntity(entity, wwid);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("mensaje", "Error al mapear datos.");
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
		} catch (Exception e) {
			response.put("mensaje", "Error al actualizar en la base de datos.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		
		
		try {
			System.out.println("CALL SERVICE");
			
			//catalogsService.save(catalogue, catalogsEntity,wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		
		
	}
	
	public Object delEntity(String id, String loggedUser) throws IllegalArgumentException, Exception {
		String len = LocaleContextHolder.getLocale().toString();
		//BAJA ENCABEZADO
		Date dat= new Date();
		MatrizHtasHd matriz= matrizHtasHdDao.findOne(new BigInteger(id));
		if(matriz!=null) {
		matriz.setDel('Y');
		matriz.setLastUpdateDate(dat);
		matriz.setLastUpdateBy(loggedUser);
		MatrizHtasHd matrizBaja=matrizHtasHdDao.save(matriz);
		System.out.println(matrizBaja);
		//FILTER
		MatrizHtasHd filter= new MatrizHtasHd();
		filter.setIdMatriz(new  BigInteger(id));
		filter.setDel('N');
		//BAJA DETALLES
		MatrizHtasDet filterDetalle= new MatrizHtasDet();
		filterDetalle.setDel('N');
		filterDetalle.setIdMatriz(new BigInteger(id));
		List<MatrizHtasDet> detalles= loadService.findMatrizHtasDetByCriteria(filterDetalle, len, false);
		List<MatrizHtasDet> detallesBaja= new ArrayList<>();
		for (MatrizHtasDet parte: detalles) {
			parte.setDel('Y');
			parte.setLastUpdateBy(loggedUser);
			parte.setLastUpdateDate(dat);
			detallesBaja.add(matrizHtasDetDao.save(parte));
		}
		
		// BAJA ATRIBUTOS
		AtributosMatricesHtas filterAtributos= new AtributosMatricesHtas();
		filterAtributos.setDel('N');
		filterAtributos.setIdMatriz(new BigInteger(id));
		List<AtributosMatricesHtas> atributos=loadService.findAtributosMatricesHtasByCriteria(filterAtributos);
		List<AtributosMatricesHtas> atributosBaja=new ArrayList<>();
		for (AtributosMatricesHtas atr: atributos) {
			atr.setDel('Y');
			atr.setLastUpdateBy(loggedUser);
			atr.setLastUpdateDate(dat);
		    atributosBaja.add(atributosDao.save(atr));
		}
		//RESPUESTA
		MatrizHtasEncDetDTO response=new MatrizHtasEncDetDTO();
		response.setMatriz(matrizBaja);
		response.setDetalles(detallesBaja);
		response.setAtributos(atributosBaja);
	return response;
		}else {
			return null;
		}
	}
	
	
	
	//-------------------------------------------------------------------------
	//							UPDATE MATRIZ PARTES
	//-------------------------------------------------------------------------
public Object updateEntity(Object entity, String loggedUser) throws IllegalArgumentException, Exception {
		String len = LocaleContextHolder.getLocale().toString();
		System.out.println("Entra a modificacion");
		JsonNode actualObj1 = null;
		Date dat= new Date();
		actualObj1 = mapper.convertValue(entity, JsonNode.class);
        
		List<MatrizHtasDet> lstDetalle= new ArrayList<>();
		List<MatrizHtasDet>lstFinal= new ArrayList<>();
		// LEYENDO Y GUARDANDO ENCABEZADO
        MatrizHtasHd Enc = mapper.convertValue(actualObj1.get("matriz"), MatrizHtasHd.class);
        
        
        MatrizHtasHd matrizExisting= matrizHtasHdDao.findOne(Enc.getIdMatriz());
        System.out.println("hasta aqui todo bien");
        if (matrizExisting!=null) {
        	Enc.setCreationDate(matrizExisting.getCreationDate());
            Enc.setCreatedBy(matrizExisting.getCreatedBy());
            Enc.setLastUpdateDate(dat);
            Enc.setLastUpdateBy(loggedUser);
        }else {
        	Enc.setCreationDate(dat);
            Enc.setCreatedBy(loggedUser);
            Enc.setLastUpdateDate(dat);
            Enc.setLastUpdateBy(loggedUser);
        }

        BigInteger idMatriz=Enc.getIdMatriz();
        Object actualizacion= delEntity(idMatriz.toString(), loggedUser);
	
        //MatrizPartesHd oHdMatriz= matrizPartesHdDao.save(Enc);
        MatrizHtasHd oHdMatriz= matrizHtasHdDao.save(Enc);
        System.out.println(Enc);
        MatrizHtasEncDetDTO response= new MatrizHtasEncDetDTO();
        //System.out.println("El id de la matriz es: "+idMatriz);
        //System.out.println("Encabezado:       "+Enc);
        
        if(oHdMatriz.getIdMatriz() != null) {
        
        //LEYENDO DETALLES MATIZ
        for(JsonNode node:actualObj1.get("detalles")) {
        	MatrizHtasDet oDetalle=new MatrizHtasDet();
        	node.get("np");
        	oDetalle.setNp(node.get("np").toString().replaceAll("\"", ""));
        	oDetalle.setIdMatriz(idMatriz);
        	
        	
        	//List<MatrizPartesDet> detalleExisting= matrizPartesDetDao.findOne(oDetalle.getIdMatriz());
        	List<MatrizHtasDet> detalles= loadService.findMatrizHtasDetByCriteria(oDetalle, len, false);
        	
        	if (detalles.size()>0) {
        		oDetalle.setCreationDate(detalles.get(0).getCreationDate());
                oDetalle.setCreatedBy(detalles.get(0).getCreatedBy());
        		
        	}else {
        		oDetalle.setCreationDate(dat);
                oDetalle.setCreatedBy(loggedUser);
        		
        	}

            oDetalle.setLastUpdateDate(dat);
            oDetalle.setLastUpdateBy(loggedUser);
        	oDetalle.setPonderacion(new Float(node.get("ponderacion").toString().replaceAll("\"", "")));
        	oDetalle.setDel('N');
        	oDetalle.setQty(new BigInteger( node.get("qty").toString()));
        	
            lstDetalle.add(oDetalle);
        }
        
        // GUARDANDO DETALLES MATRIZ
        
        for (MatrizHtasDet matriz: lstDetalle) {
        	
        	MatrizHtasDet oMatDet =matrizHtasDetDao.save(matriz);
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
        	
    		List<AtributosMatricesHtas> atributoExist=loadService.findAtributosMatricesHtasByCriteria(att);
    		att.setDel('N');
    		att.setLastUpdateDate(dat);
            att.setLastUpdateBy(loggedUser);
        	if(atributoExist.size()>0) {
        	att.setCreationDate(atributoExist.get(0).getCreationDate());
            att.setCreatedBy(atributoExist.get(0).getCreatedBy());
        	}else {
        		att.setCreationDate(dat);
                att.setCreatedBy(loggedUser);
        	}
            //System.out.println(att.toString());
        	lstAtributos.add(atributosDao.save(att));
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
        	List<AtributosMatricesHtas> atributoExist=loadService.findAtributosMatricesHtasByCriteria(att);
    		att.setDel('N');
    		att.setLastUpdateDate(dat);
            att.setLastUpdateBy(loggedUser);
        	if(atributoExist.size()>0) {
        	att.setCreationDate(atributoExist.get(0).getCreationDate());
            att.setCreatedBy(atributoExist.get(0).getCreatedBy());
        	}else {
        		att.setCreationDate(dat);
                att.setCreatedBy(loggedUser);
        	}
        	att.setDel('N');
            //System.out.println(att.toString());
        	//lstAtributos.add(att);
        	lstAtributos.add(atributosDao.save(att));
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
        	List<AtributosMatricesHtas> atributoExist=loadService.findAtributosMatricesHtasByCriteria(att);
    		att.setDel('N');
    		att.setLastUpdateDate(dat);
            att.setLastUpdateBy(loggedUser);
        	if(atributoExist.size()>0) {
        	att.setCreationDate(atributoExist.get(0).getCreationDate());
            att.setCreatedBy(atributoExist.get(0).getCreatedBy());
        	}else {
        		att.setCreationDate(dat);
                att.setCreatedBy(loggedUser);
        	}
            //System.out.println(att.toString());
        	//lstAtributos.add(att);
        	lstAtributos.add(atributosDao.save(att));
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
        	List<AtributosMatricesHtas> atributoExist=loadService.findAtributosMatricesHtasByCriteria(att);
    		att.setDel('N');
    		att.setLastUpdateDate(dat);
            att.setLastUpdateBy(loggedUser);
        	if(atributoExist.size()>0) {
        	att.setCreationDate(atributoExist.get(0).getCreationDate());
            att.setCreatedBy(atributoExist.get(0).getCreatedBy());
        	}else {
        		att.setCreationDate(dat);
                att.setCreatedBy(loggedUser);
        	}
            //System.out.println(att.toString());
        	//lstAtributos.add(att);
        	lstAtributos.add(atributosDao.save(att));
        }
        response.setDetalles(lstFinal);
        response.setMatriz(oHdMatriz);
        response.setAtributos(lstAtributos);
        
        
		
	return response;
	}
}
