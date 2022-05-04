package com.cummins.scd.controllers.catalogs;

import java.util.HashMap;
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

import com.cummins.scd.models.dao.IESPMDao;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.services.ICatalogsService;
import com.cummins.scd.models.services.ILoadInfoService;
import com.cummins.scd.models.services.catalogs.IEspmService;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
//

@RestController
@RequestMapping("/_api")
public class EspmController {
	@Autowired
	private IEspmService Service;
	
	
	//-------------------------------------------------------------------
	//						GET ALL ESPM BY DEL 'N'
	//-------------------------------------------------------------------
	@GetMapping("v1/loadInfo/espm")
    public ResponseEntity<?> list(HttpServletRequest request){
		System.out.println("entra a espm");
		System.out.println(request);
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String)request.getSession(false).getAttribute("language");
		//System.out.println(language);
		try {
			//catalogsEntity = Service.findAll("Ingles");
			//catalogsEntity = Service.todosESPM();
			ESPM filter= new ESPM();
			filter.setDel('N');
			catalogsEntity= Service.findESPMByCriteria(filter, "Español");
			//catalogsEntity=Service.espmxregion();
			
			System.out.println("hasta aqui todo bien");
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
	
	
	//--------------------------------------------------------------------------------------------
	//							           SAVE RECORD ESPM
	//--------------------------------------------------------------------------------------------
	
	
	@PostMapping("v1/loadInfo/espm")
	public ResponseEntity<?> create(@RequestBody Object entity,HttpServletRequest request) {

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			//System.out.println(entity);
			catalogsEntity = Service.save(entity, wwid,null);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		
		
	}

	
	//-------------------------------------------------------------------
	//						FILTER BY CRITERIA ESPM
	//-------------------------------------------------------------------
	@PostMapping("v1/loadInfo/filter/espm")
    public ResponseEntity<?> list(@RequestBody Object filter){
        Object espmEntities = null;
		Map<String, Object> response = new HashMap<>();
		String len = LocaleContextHolder.getLocale().toString();
		try {
			espmEntities = Service.findESPMByCriteria(filter, len);
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(espmEntities == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(espmEntities, HttpStatus.OK);
	}
	
	//------------------------------------------------------------------
	//							DELETE ESPM
	//------------------------------------------------------------------
	@PutMapping("v1/loadInfo/espm/{id}")
	public ResponseEntity<?> delete( @PathVariable String id,HttpServletRequest request) {
		//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		
		try {
			catalogsEntity = Service.delete( id, wwid);
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
	
	//------------------------------------------------------------------
		//							UPDATE ESPM
		//------------------------------------------------------------------
		@PutMapping("v1/loadInfo/espm")
		public ResponseEntity<?> update(@RequestBody Object filter,HttpServletRequest request) {
			//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
			Map<String, Object> response = new HashMap<>();
			Object catalogsEntity = null;

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			
			try {
				catalogsEntity = Service.update( filter, wwid);
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
	
	
	
	
}
