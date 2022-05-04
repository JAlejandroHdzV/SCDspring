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

import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.services.catalogs.IConfMotorService;
import com.cummins.scd.models.services.catalogs.IEspmService;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class ConfMotorController {
	@Autowired
	private IConfMotorService confMotorService;
	
		//-------------------------------------------------------------------
		//						GET ALL CONFMOTOR BY DEL 'N'
		//-------------------------------------------------------------------
		@GetMapping("v1/admin/loadInfo/confMotor/{id}")
	    public ResponseEntity<?> list(HttpServletRequest request, @PathVariable String id){
			System.out.println("entra a conf motor");
			System.out.println(request);
	        Object catalogsEntity = null;
			Map<String, Object> response = new HashMap<>();
			String len = LocaleContextHolder.getLocale().toString();
			try {
				
				catalogsEntity=confMotorService.confMotor(id,len);
				
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
				//						GET ALL CONFMOTOR BY DEL 'N'
				//-------------------------------------------------------------------
				@GetMapping("v2/admin/loadInfo/confMotor/{id}")
			    public ResponseEntity<?> list1(HttpServletRequest request, @PathVariable String id){
					System.out.println("entra a conf motor");
					System.out.println(request);
			        Object catalogsEntity = null;
					Map<String, Object> response = new HashMap<>();
					String language = (String)request.getSession(false).getAttribute("language");
					//System.out.println(language);
					try {
						
						catalogsEntity=confMotorService.confMotor2(id);
						
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
		//						SAVE RECORD CONFMOTOR
		//-------------------------------------------------------------------
		
		@PostMapping("v1/admin/loadInfo/confMotor")
		public ResponseEntity<?> create(@RequestBody Object entity,HttpServletRequest request) {

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			
			Object catalogsEntity = null;
			Map<String, Object> response = new HashMap<>();
			System.out.println("RESPONSE");
			try {
				System.out.println("Entra a save confMotor");
				catalogsEntity = confMotorService.save( entity, wwid);
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
		//----------------------------------------------------------------------
		//						DELETE CONF MOTOR BY ID 
		//---------------------------------------------------------------------
		
		@PutMapping("v1/admin/loadInfo/confMotor/{id}")
		public ResponseEntity<?> update(@PathVariable String id,HttpServletRequest request) {
			//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
			Map<String, Object> response = new HashMap<>();
			Object catalogsEntity = null;

			String wwid = (String)request.getSession(false).getAttribute("wwid");
			
			try {
				catalogsEntity = confMotorService.delete(id, wwid);
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
				@PutMapping("v1/admin/loadInfo/confMotor")
				public ResponseEntity<?> update(@RequestBody Object filter,HttpServletRequest request) {
					//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
					Map<String, Object> response = new HashMap<>();
					Object catalogsEntity = null;

					String wwid = (String)request.getSession(false).getAttribute("wwid");
					System.out.println("entra a actualizacion confMotor");
					try {
						catalogsEntity = confMotorService.update( filter, wwid);
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
