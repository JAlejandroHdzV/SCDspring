package com.cummins.scd.controllers.catalogs;

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

import com.cummins.scd.models.dto.CatQuejasDTO;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.services.catalogs.IEvaluacionesService;
import com.cummins.scd.models.services.catalogs.IQuejasService;

@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/_api")
public class QuejasController {
	@Autowired
	IQuejasService quejasService;
	
	
	
	
	//-------------------------------------------------------------------
	//						POST QUEJAS
	//-------------------------------------------------------------------
	@PostMapping("v1/catalogs/quejas")
	public ResponseEntity<?> create(@RequestBody Object entity,HttpServletRequest request) {
		System.out.print(entity);
		String wwid = (String)request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			//System.out.println(entity);
			catalogsEntity = quejasService.save(wwid, entity);
			if(catalogsEntity.equals("1")) {
				response.put("message", "Error when inserting on the data base.");
				response.put("error", "This record already exists in the database");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
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
		//						GET ALL QUEJAS BY DEL 'N' AND ID EVALUACION
		//-------------------------------------------------------------------
		@GetMapping("v1/catalogs/quejas/{idEvaluacion}")
	    public ResponseEntity<?> list(HttpServletRequest request,@PathVariable String idEvaluacion){
			System.out.println("entra a quejas");
			System.out.println(request);
	        List<Quejas> catalogsEntity = null;
			Map<String, Object> response = new HashMap<>();
			String language = (String)request.getSession(false).getAttribute("language");
			//System.out.println(language);
			try {
				//catalogsEntity = Service.findAll("Ingles");
				//catalogsEntity = Service.todosESPM();
				Quejas filter= new Quejas();
				filter.setDel('N');
				catalogsEntity= quejasService.getQuejasByDel(idEvaluacion);
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
		
		//-------------------------------------------------------------------
				//						GET ALL QUEJAS BY DEL 'N' AND REGION
				//-------------------------------------------------------------------
				@GetMapping("v1/catalogs/quejas/region/{regiones}")
			    public ResponseEntity<?> getAllQuejasByRegionList(HttpServletRequest request,@PathVariable String regiones){
					System.out.println("entra a quejas");
					System.out.println(request);
					List<Evaluaciones> listEv=null; 
			        List<CatQuejasDTO> catalogsEntity = null;
					Map<String, Object> response = new HashMap<>();
					String lang = LocaleContextHolder.getLocale().toString();
					//String language = (String)request.getSession(false).getAttribute("language");
					
					try {
						catalogsEntity=quejasService.getByEvAndRegion(regiones, lang);
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
		
		//----------------------------------------------------------------------
		//						DELETE QUEJAS BY ID 
		//---------------------------------------------------------------------
		
		@PutMapping("v1/catalogs/quejas/{id}")
		public ResponseEntity<?> update(@PathVariable String id,HttpServletRequest request) {
			//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
			Map<String, Object> response = new HashMap<>();
			Quejas catalogsEntity = null;

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			
			try {
				catalogsEntity = quejasService.delete(id, wwid);
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
		//----------------------------------------------------------------------
		//						DELETE QUEJAS BY ID 
		//---------------------------------------------------------------------
		
		@PutMapping("v1/catalogs/quejas/delete/{id}")
		public ResponseEntity<?> deleteAll(@PathVariable String id,HttpServletRequest request) {
			//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
			Map<String, Object> response = new HashMap<>();
			List<Quejas> catalogsEntity = null;

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			
			try {
				catalogsEntity = quejasService.deleteAll(id, wwid);
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
		//							UPDATE QUEJAS
		//------------------------------------------------------------------
		@PutMapping("v1/catalogs/quejas")
		public ResponseEntity<?> update(@RequestBody Quejas filter,HttpServletRequest request) {
			//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
			Map<String, Object> response = new HashMap<>();
			Quejas catalogsEntity = null;

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			System.out.println("entra a actualizacion quejas");
			System.out.println(filter.getIdCatQueja());
			try {
				catalogsEntity = quejasService.update(filter, wwid);
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
