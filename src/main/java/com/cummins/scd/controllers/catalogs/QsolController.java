package com.cummins.scd.controllers.catalogs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cummins.scd.models.entity.QSol;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.services.catalogs.IPuestosService;
import com.cummins.scd.models.services.catalogs.IQsolService;

@CrossOrigin(origins = { "https://mxca-abomsd-70-dev-ac.cummins.com" }) // descomentar cuando se haga deploy a dev.
																		// comentar las demas.
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/_api")
public class QsolController {
	@Autowired
	IQsolService qsolService;

	@GetMapping("/v1/catalogs/qsol")
	public ResponseEntity<?> spcodesJoinSpcodes() {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			// catalogsEntity = puestosService.puestosActivos();
		} catch (DataAccessException e) {
			response.put("message", "Error when making query."); // TODO: cambiar el mensaje por un tag que se vaya a
																	// usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); // TODO: cambiar el mensaje por un tag que se vaya a
																	// usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (catalogsEntity == null) {
			response.put("message", "Query returned 0 results."); // TODO: cambiar el mensaje por un tag que se vaya a
																	// usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
	}

	// -------------------------------------------------------------------
	// POST QSOL
	// -------------------------------------------------------------------
	@PostMapping("v1/catalogs/qsol")
	public ResponseEntity<?> create(@RequestBody Object entity, HttpServletRequest request) {
		System.out.print(entity);
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			// System.out.println(entity);
			catalogsEntity = qsolService.save(wwid, entity);
			
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
	// ----------------------------------------------------------------------
	// DELETE QSOL BY ID EVALUATION
	// ---------------------------------------------------------------------

	@PutMapping("v1/catalogs/qsol/del/{id}")
	public ResponseEntity<?> deleteAll(@PathVariable String id, HttpServletRequest request) {
		// String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		List<QSol> catalogsEntity = null;

		String wwid = (String) request.getSession(false).getAttribute("wwid");

		try {
			catalogsEntity = qsolService.deleteAll(id, wwid);
		} catch (IllegalArgumentException mapExcepcion) {
			response.put("mensaje", "Error al mapear datos.");
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("mensaje", "Error al actualizar en la base de datos.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}
	
	
			//------------------------------------------------------------------
			//							UPDATE QSOL
			//------------------------------------------------------------------
			@PutMapping("v1/catalogs/qsol")
			public ResponseEntity<?> update(@RequestBody Object entity,HttpServletRequest request) {
				//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
				Map<String, Object> response = new HashMap<>();
				List<QSol> catalogsEntity = null;

				String wwid = (String)request.getSession(false).getAttribute("wwid");
				System.out.println("entra a actualizacion qsol");
				try {
					catalogsEntity = qsolService.update(entity, wwid);
				} catch(IllegalArgumentException mapExcepcion) {
					response.put("mensaje", "Error al mapear datos.");
					response.put("error", mapExcepcion.getMessage());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
				} catch (DataAccessException e) {
					response.put("message", "Error when updating on the data base.");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (Exception e) {
					response.put("mensaje", "Error al actualizar en la base de datos.");
					response.put("error", e.getMessage());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
				
				
			}
			//-------------------------------------------------------------------
			//						GET ALL QSOL BY DEL 'N' AND ID EVALUACION
			//-------------------------------------------------------------------
			@GetMapping("v1/catalogs/qsol/{idEvaluacion}")
		    public ResponseEntity<?> list(HttpServletRequest request,@PathVariable String idEvaluacion){
				System.out.println("Entra a Qsol");
				System.out.println(request);
		        List<QSol> catalogsEntity = null;
				Map<String, Object> response = new HashMap<>();
				String language = (String)request.getSession(false).getAttribute("language");
				//System.out.println(language);
				try {
				
					catalogsEntity= qsolService.getQsolByIdEv(idEvaluacion);
					
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

}