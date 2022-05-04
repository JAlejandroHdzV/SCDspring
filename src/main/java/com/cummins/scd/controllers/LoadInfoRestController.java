package com.cummins.scd.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_Emision;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.ESPM_Region;
import com.cummins.scd.models.entity.ElectronicTools;
import com.cummins.scd.models.entity.Garantia;
import com.cummins.scd.models.entity.LicenciasQsol;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.NoDisponible;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.Promotion;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Rescates;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.services.ILoadInfoService;
import com.cummins.scd.models.services.catalogs.IPqsService;
import com.fasterxml.jackson.databind.ObjectMapper;


//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
@RestController
@RequestMapping("/_api")

public class LoadInfoRestController {

    @Autowired
	private ILoadInfoService catalogsService;
    @Autowired
	private IPqsService pqsService;

    
    @GetMapping("/admin/loadInfo/{catalogue}")
    public ResponseEntity<?> list(@PathVariable String catalogue){

        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findAll(catalogue);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}

	@GetMapping("/admin/loadInfo/{catalogue}/{id}")
    public ResponseEntity<?> list(@PathVariable String catalogue, @PathVariable String id){
		String len = LocaleContextHolder.getLocale().toString();
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findById(catalogue, id, len, false);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}
	
	
	@PostMapping("/admin/loadInfo/filter/{catalogue}")
    public ResponseEntity<?> filter(@RequestBody Object filter, @PathVariable String catalogue, HttpServletRequest request){

        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		
		Boolean flag=false;
		String referer = (request.getHeader("Referer") == null) ? "" : request.getHeader("Referer");
    	System.out.println("referer"+referer);
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	if(
    			referer.contains("DES/view/admin/loadInfo/drdlr")||
    			referer.contains("DES/view/admin/loadInfo/qsol")) {
    		System.out.println("Contiene el catalogo");
    		flag=true;
    	}
		String len = LocaleContextHolder.getLocale().toString();
		try {
			catalogsEntity = catalogsService.findByCriteria(catalogue, filter, len, flag);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}
	
	@PostMapping("/admin/loadInfo/delete/promotion")
    public ResponseEntity<?> findExisting(@RequestBody Object filter){

        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.deletePromotion(filter);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}
	
	@PostMapping("/admin/loadInfo/del/{catalogue}")
    public ResponseEntity<?> deletePromotion(@RequestBody Object filter, @PathVariable String catalogue){

        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findExisting(catalogue, filter);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}
	
	
	@PostMapping("/admin/loadInfo/{catalogue}")
	public ResponseEntity<?> create(@RequestBody Object entity, @PathVariable String catalogue,HttpServletRequest request) {

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		System.out.println("RESPONSE");
		try {
			System.out.println("CALL SERVICE");
			catalogsEntity = catalogsService.save(catalogue, entity, wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(catalogsEntity.equals("repeat")) {
			response.put("message", "Folio repetido.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}else {
			
			return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		}
		
		
	}
	@PutMapping("/admin/loadInfo/{catalogue}/{id}")
	public ResponseEntity<?> update(@RequestBody Object entity, @PathVariable String catalogue, @PathVariable String id,HttpServletRequest request) {
		//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;
		String len = LocaleContextHolder.getLocale().toString();
		String wwid = (String)request.getSession(false).getAttribute("wwid");
		
		try {
			catalogsEntity = updateEntity(entity, catalogue, id, wwid,len);
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
			
			catalogsService.save(catalogue, catalogsEntity,wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		
		
	}
	
	// @DeleteMapping("/admin/loadInfo/{catalogue}/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void delete(@PathVariable String catalogue, @PathVariable String id) {

	// 	catalogosService.delete(catalogue, id);
	// }

	public Object updateEntity(Object entity, String catalogue, String id, String loggedUser, String lang) throws IllegalArgumentException, Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		switch (catalogue) {
		case "pqs":
			
			Pqs currentTipoPq = (Pqs) catalogsService.findById(catalogue, id, lang, false);
			if(currentTipoPq == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Pqs newTipoPq = mapper.convertValue(entity, Pqs.class);
			if(newTipoPq.obtenerAction() != null && 
			   newTipoPq.obtenerAction().equals("estatus")
			  ){
				System.out.println("delete pqs by id");
				Object pqs=pqsService.deletePqsById(id, loggedUser);
				if (pqs != null) {
					return pqs;
				}else {
					throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
				}
			} else {
				newTipoPq.setLastUpdatedBy(loggedUser);
				newTipoPq.setCreatedBy(currentTipoPq.getCreatedBy());
				newTipoPq.setCreationDate(currentTipoPq.getCreationDate());
				return newTipoPq;
			}
		case "spcodes":
			SpCodes currentTipoSp = (SpCodes) catalogsService.findById(catalogue, id, lang, false);
			if(currentTipoSp == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			SpCodes newTipoSp = mapper.convertValue(entity, SpCodes.class);
			if(newTipoSp.obtenerAction() != null && 
			   newTipoSp.obtenerAction().equals("estatus")
			  ){
				currentTipoSp.setDel(newTipoSp.getDel());
				currentTipoSp.setLastUpdateBy(loggedUser);
				return currentTipoSp;
			} else {
				newTipoSp.setLastUpdateBy(loggedUser);
				newTipoSp.setCreatedBy(currentTipoSp.getCreatedBy());
				newTipoSp.setCreationDate(currentTipoSp.getCreationDate());
				return newTipoSp;
			}

		case "rescates":
			Rescates currentTipoProc = (Rescates) catalogsService.findById(catalogue, id, lang , false);
			if(currentTipoProc == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Rescates newTipoProc = mapper.convertValue(entity, Rescates.class);
			if(newTipoProc.obtenerAction() != null && 
			   newTipoProc.obtenerAction().equals("estatus")
			  ){	
				currentTipoProc.setDel(newTipoProc.getDel());
				currentTipoProc.setLastUpdateBy(loggedUser);
				return currentTipoProc;
			} else {
				newTipoProc.setLastUpdateBy(loggedUser);
				newTipoProc.setCreatedBy(currentTipoProc.getCreatedBy());
				newTipoProc.setCreationDate(currentTipoProc.getCreationDate());
				System.out.println(newTipoProc.getMotivo());
				return newTipoProc;
			}
		case "electronictools":
			ElectronicTools currentTipoProcE = (ElectronicTools) catalogsService.findById(catalogue, id, lang, false);
			if(currentTipoProcE == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ElectronicTools newTipoProcE = mapper.convertValue(entity, ElectronicTools.class);
			if(newTipoProcE.obtenerAction() != null && 
			   newTipoProcE.obtenerAction().equals("estatus")
			  ){
				currentTipoProcE.setDel(newTipoProcE.getDel());
				currentTipoProcE.setLastUpdatedBy(loggedUser);
				return currentTipoProcE;
			} else {
				newTipoProcE.setLastUpdatedBy(loggedUser);
				newTipoProcE.setCreatedBy(currentTipoProcE.getCreatedBy());
				newTipoProcE.setCreationDate(currentTipoProcE.getCreationDate());
				
				return newTipoProcE;
			}
		case "licenciasqsol":
			LicenciasQsol currentTipoProcL = (LicenciasQsol) catalogsService.findById(catalogue, id, lang, false);
			if(currentTipoProcL == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			LicenciasQsol newTipoProcL = mapper.convertValue(entity, LicenciasQsol.class);
			if(newTipoProcL.obtenerAction() != null && 
			   newTipoProcL.obtenerAction().equals("estatus")
			  ){
				currentTipoProcL.setDel(newTipoProcL.getDel());
				currentTipoProcL.setLastUpdatedBy(loggedUser);
				return currentTipoProcL;
			} else {
				newTipoProcL.setLastUpdatedBy(loggedUser);
				newTipoProcL.setCreatedBy(currentTipoProcL.getCreatedBy());
				newTipoProcL.setCreationDate(currentTipoProcL.getCreationDate());
				return newTipoProcL;
			}
		case "nodisponible":
			NoDisponible currentND = (NoDisponible) catalogsService.findById(catalogue, id, lang, false);
			if(currentND== null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			NoDisponible newND = mapper.convertValue(entity, NoDisponible.class);
			if(newND.obtenerAction() != null && 
			   newND.obtenerAction().equals("estatus")
			  ){
				currentND.setDel(newND.getDel());
				currentND.setLastUpdatedBy(loggedUser);
				return currentND;
			} else {
				newND.setLastUpdatedBy(loggedUser);
				newND.setCreatedBy(currentND.getCreatedBy());
				newND.setCreationDate(currentND.getCreationDate());
				
				return newND;
			}
		case "promotion":
			Promotion filter = mapper.convertValue(entity, Promotion.class);
		     
			Promotion realFilter = new Promotion();
			realFilter.setSpCode(filter.getSpCode());
			realFilter.setProgramId(filter.getProgramId());
			realFilter.setPromotionId(filter.getPromotionId());
			
			List<Promotion> currentListP =catalogsService.findPromotionByCriteria(realFilter);
			Promotion currentP = currentListP.get(0);
			if(currentP== null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Promotion newP = mapper.convertValue(entity, Promotion.class);
			
				newP.setLastUpdateBy(loggedUser);
				newP.setCreatedBy(currentP.getCreatedBy());
				newP.setCreationDate(currentP.getCreationDate());
				
				return newP;
		case "espm":
			ESPM filterEs = mapper.convertValue(entity, ESPM.class);
		     
			ESPM realFilterEs = new ESPM();
			realFilterEs.setIdESPM(filterEs.getIdESPM());
			realFilterEs.setIdMotor(filterEs.getIdMotor());
			realFilterEs.setIdRango(filterEs.getIdRango());
			realFilterEs.setIdApp(filterEs.getIdApp());
			
			List<ESPM> currentListEs =catalogsService.findESPMByCriteria(realFilterEs);
			ESPM currentEs = currentListEs.get(0);
			if(currentEs== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ESPM newEs = mapper.convertValue(entity, ESPM.class);
			if(newEs.obtenerAction() != null && 
			   newEs.obtenerAction().equals("estatus")
			  ){
				currentEs.setDel(newEs.getDel());
				currentEs.setLastUpdatedBy(loggedUser);
				return currentEs;
			} else {
				newEs.setLastUpdatedBy(loggedUser);
				newEs.setCreatedBy(currentEs.getCreatedBy());
				newEs.setCreationDate(currentEs.getCreationDate());
				
				return newEs;
			}	
		case "espmEmision":
			ESPM_Emision filterEsEm = mapper.convertValue(entity, ESPM_Emision.class);
		     
			ESPM_Emision realFilterEsEm = new ESPM_Emision();
			realFilterEsEm.setIdESPM(filterEsEm.getIdESPM());
			realFilterEsEm.setIdEmision(filterEsEm.getIdEmision());
			
			List<ESPM_Emision> currentListEsEm =catalogsService.findESPMEmisionByCriteria(realFilterEsEm);
			ESPM_Emision currentEsEm = currentListEsEm.get(0);
			if(currentEsEm== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ESPM_Emision newEsEm = mapper.convertValue(entity, ESPM_Emision.class);
			if(newEsEm.obtenerAction() != null && 
			   newEsEm.obtenerAction().equals("estatus")
			  ){
				currentEsEm.setDel(newEsEm.getDel());
				currentEsEm.setLastUpdatedBy(loggedUser);
				return currentEsEm;
			} else {
				newEsEm.setLastUpdatedBy(loggedUser);
				newEsEm.setCreatedBy(currentEsEm.getCreatedBy());
				newEsEm.setCreationDate(currentEsEm.getCreationDate());
				
				return newEsEm;
			}
		case "espmRegion":
			ESPM_Region filterEsRe = mapper.convertValue(entity, ESPM_Region.class);
		     
			ESPM_Region realFilterEsRe = new ESPM_Region();
			realFilterEsRe.setIdESPM(filterEsRe.getIdESPM());
			realFilterEsRe.setIdRegion(filterEsRe.getIdRegion());
			
			List<ESPM_Region> currentListEsRe =catalogsService.findESPMRegionByCriteria(realFilterEsRe);
			ESPM_Region currentEsRe = currentListEsRe.get(0);
			if(currentEsRe== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ESPM_Region newEsRe = mapper.convertValue(entity, ESPM_Region.class);
			if(newEsRe.obtenerAction() != null && 
			   newEsRe.obtenerAction().equals("estatus")
			  ){
				currentEsRe.setDel(newEsRe.getDel());
				currentEsRe.setLastUpdatedBy(loggedUser);
				return currentEsRe;
			} else {
				newEsRe.setLastUpdatedBy(loggedUser);
				newEsRe.setCreatedBy(currentEsRe.getCreatedBy());
				newEsRe.setCreationDate(currentEsRe.getCreationDate());
				
				return newEsRe;
			}
		case "espmNs":
			ESPM_NS filterEsN = mapper.convertValue(entity, ESPM_NS.class);
		     
			ESPM_NS realFilterEsN = new ESPM_NS();
			realFilterEsN.setIdESPM(filterEsN.getIdESPM());
			realFilterEsN.setIdNs(filterEsN.getIdNs());
			realFilterEsN.setIdPuesto(filterEsN.getIdPuesto());
			realFilterEsN.setIdProgram(filterEsN.getIdProgram());
			
			List<ESPM_NS> currentListEsN =catalogsService.findESPMNSByCriteria(realFilterEsN);
			ESPM_NS currentEsN = currentListEsN.get(0);
			if(currentEsN== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ESPM_NS newEsN = mapper.convertValue(entity, ESPM_NS.class);
			if(newEsN.obtenerAction() != null && 
			   newEsN.obtenerAction().equals("estatus")
			  ){
				currentEsN.setDel(newEsN.getDel());
				currentEsN.setLastUpdatedBy(loggedUser);
				return currentEsN;
			} else {
				newEsN.setLastUpdatedBy(loggedUser);
				newEsN.setCreatedBy(currentEsN.getCreatedBy());
				newEsN.setCreationDate(currentEsN.getCreationDate());
				
				return newEsN;
			}
		case "garantias":
			Garantia filterGar = mapper.convertValue(entity, Garantia.class);
		     
			Garantia realFilterGar = new Garantia();
			realFilterGar.setFolio(filterGar.getFolio());
			realFilterGar.setStatus(filterGar.getStatus());
			realFilterGar.setSpCode(filterGar.getSpCode());
			realFilterGar.setCodigoCta(filterGar.getCodigoCta());
			
			List<Garantia> currentListGar =catalogsService.findGarantiasByCriteria(realFilterGar,lang,false);
			Garantia currentGar = currentListGar.get(0);
			if(currentGar== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Garantia newGar = mapper.convertValue(entity, Garantia.class);
			if(newGar.obtenerAction() != null && 
			   newGar.obtenerAction().equals("estatus")
			  ){
				currentGar.setDel(newGar.getDel());
				currentGar.setLastUpdatedBy(loggedUser);
				return currentGar;
			} else {
				newGar.setLastUpdatedBy(loggedUser);
				newGar.setCreatedBy(currentGar.getCreatedBy());
				newGar.setCreationDate(currentGar.getCreationDate());
				
				return newGar;
			}	
		case "matrizPartesHd":
			MatrizPartesHd filterMP = mapper.convertValue(entity, MatrizPartesHd.class);
		     
			MatrizPartesHd realFilterMP = new MatrizPartesHd();
			realFilterMP.setIdMatriz(filterMP.getIdMatriz());
			realFilterMP.setStatus(filterMP.getStatus());
			realFilterMP.setIdNs(filterMP.getIdNs());
			realFilterMP.setIdTipo(filterMP.getIdTipo());
			
			List<MatrizPartesHd> currentListMP =catalogsService.findMatrizPartesHdByCriteria(realFilterMP, lang, true);
			MatrizPartesHd currentMP = currentListMP.get(0);
			if(currentMP== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			MatrizPartesHd newMP = mapper.convertValue(entity, MatrizPartesHd.class);
			if(newMP.obtenerAction() != null && 
			   newMP.obtenerAction().equals("estatus")
			  ){
				currentMP.setDel(newMP.getDel());
				currentMP.setLastUpdatedBy(loggedUser);
				return currentMP;
			} else {
				newMP.setLastUpdatedBy(loggedUser);
				newMP.setCreatedBy(currentMP.getCreatedBy());
				newMP.setCreationDate(currentMP.getCreationDate());
				
				return newMP;
			}
		case "matrizPartesDet":
			MatrizPartesDet filterMPD = mapper.convertValue(entity, MatrizPartesDet.class);
		     
			MatrizPartesDet realFilterMPD = new MatrizPartesDet();
			realFilterMPD.setIdMatriz(filterMPD.getIdMatriz());
			realFilterMPD.setNp(filterMPD.getNp());
			
			List<MatrizPartesDet> currentListMPD =catalogsService.findMatrizPartesDetByCriteria(realFilterMPD, lang, false);
			MatrizPartesDet currentMPD = currentListMPD.get(0);
			if(currentMPD== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			MatrizPartesDet newMPD = mapper.convertValue(entity, MatrizPartesDet.class);
			if(newMPD.obtenerAction() != null && 
			   newMPD.obtenerAction().equals("estatus")
			  ){
				currentMPD.setDel(newMPD.getDel());
				currentMPD.setLastUpdateBy(loggedUser);
				return currentMPD;
			} else {
				newMPD.setLastUpdateBy(loggedUser);
				newMPD.setCreatedBy(currentMPD.getCreatedBy());
				newMPD.setCreationDate(currentMPD.getCreationDate());
				
				return newMPD;
			}
		case "matrizHtasHd":
			MatrizHtasHd filterMH = mapper.convertValue(entity, MatrizHtasHd.class);
		     
			MatrizHtasHd realFilterMH = new MatrizHtasHd();
			realFilterMH.setIdMatriz(filterMH.getIdMatriz());
			
			List<MatrizHtasHd> currentListMH =catalogsService.findMatrizHtasHdByCriteria(realFilterMH,lang,true);
			MatrizHtasHd currentMH = currentListMH.get(0);
			if(currentMH== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			MatrizHtasHd newMH = mapper.convertValue(entity, MatrizHtasHd.class);
			if(newMH.getAction() != null && 
			   newMH.getAction().equals("estatus")
			  ){
				currentMH.setDel(newMH.getDel());
				currentMH.setLastUpdateBy(loggedUser);
				return currentMH;
			} else {
				newMH.setLastUpdateBy(loggedUser);
				newMH.setCreatedBy(currentMH.getCreatedBy());
				newMH.setCreationDate(currentMH.getCreationDate());
				
				return newMH;
			}	
		case "matrizHtasDet":
			MatrizHtasDet filterMHD = mapper.convertValue(entity, MatrizHtasDet.class);
		     
			MatrizHtasDet realFilterMHD = new MatrizHtasDet();
			realFilterMHD.setIdMatriz(filterMHD.getIdMatriz());
			realFilterMHD.setNp(filterMHD.getNp());
			
			List<MatrizHtasDet> currentListMHD =catalogsService.findMatrizHtasDetByCriteria(realFilterMHD, lang, false);
			MatrizHtasDet currentMHD = currentListMHD.get(0);
			if(currentMHD== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			MatrizHtasDet newMHD = mapper.convertValue(entity, MatrizHtasDet.class);
			if(newMHD.obtenerAction() != null && 
			   newMHD.obtenerAction().equals("estatus")
			  ){
				currentMHD.setDel(newMHD.getDel());
				currentMHD.setLastUpdateBy(loggedUser);
				return currentMHD;
			} else {
				newMHD.setLastUpdateBy(loggedUser);
				newMHD.setCreatedBy(currentMHD.getCreatedBy());
				newMHD.setCreationDate(currentMHD.getCreationDate());
				
				return newMHD;
			}
			
			//------------------------------------------------------
			//				  MATRIZ PARTES BAJA LOGICA 
			//------------------------------------------------------
		case "matPartes":
			MatrizPartesHd oMatP = mapper.convertValue(entity, MatrizPartesHd.class);
		     
			MatrizPartesHd mp = new MatrizPartesHd();
			mp.setIdMatriz(oMatP.getIdMatriz());
			mp.setStatus(oMatP.getStatus());
			mp.setIdNs(oMatP.getIdNs());
			mp.setIdTipo(oMatP.getIdTipo());
			List<MatrizPartesHd> listMatPart =catalogsService.findMatrizPartesHdByCriteria(mp,lang,true);
			MatrizPartesHd currentmp = listMatPart.get(0);
			if(currentmp== null) {                                                             
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			MatrizPartesHd nmp = mapper.convertValue(entity, MatrizPartesHd.class);
			if(nmp.obtenerAction() != null && nmp.obtenerAction().equals("estatus")){
				currentmp.setDel(currentmp.getDel()=='N'?'Y':'N');
				currentmp.setLastUpdatedBy(loggedUser);
				return currentmp;
			} else {
				nmp.setLastUpdatedBy(loggedUser);
				nmp.setCreatedBy(currentmp.getCreatedBy());
				nmp.setCreationDate(currentmp.getCreationDate());
				
				return nmp;
			}
			
		case "confMotor":
			ConfMotor currentCM = (ConfMotor) catalogsService.findById(catalogue, id, lang, false);
			if(currentCM== null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			ConfMotor newCM = mapper.convertValue(entity, ConfMotor.class);
			if(newCM.obtenerAction() != null && 
			   newCM.obtenerAction().equals("estatus")
			  ){
				currentCM.setDel(newCM.getDel());
				currentCM.setLastUpdatedBy(loggedUser);
				return currentCM;
			} else {
				newCM.setLastUpdatedBy(loggedUser);
				newCM.setCreatedBy(currentCM.getCreatedBy());
				newCM.setCreationDate(currentCM.getCreationDate());
				
				return newCM;
			}
		
		default:
			return null;
		}
	}
}

