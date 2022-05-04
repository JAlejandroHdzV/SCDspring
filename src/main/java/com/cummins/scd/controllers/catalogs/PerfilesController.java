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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.services.catalogs.IPerfilesService;


//@CrossOrigin(origins = {"http://localhost:4200"})//descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
@RestController
@RequestMapping("/_api")
public class PerfilesController {
	@Autowired
	IPerfilesService perfilesService;
	
	
	
	// -------------------------------------------------------------------
	// GET  PERFILES BY DEL = 'N'  AND EVALUACION ID
	// -------------------------------------------------------------------
	@GetMapping("v1/configuration/perfiles/{idEvaluacion}")
	public ResponseEntity<?> list(HttpServletRequest request,@PathVariable String idEvaluacion) {
		List<Perfiles> catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String) request.getSession(false).getAttribute("language");
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
			catalogsEntity = perfilesService.getPerfiles(idEvaluacion,null);
			// catalogsEntity=Service.espmxregion();

			System.out.println("hasta aqui todo bien");
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
	// GET  PERFILES BY DEL = 'N'  AND EVALUACION ID
	// -------------------------------------------------------------------
	@GetMapping("v1/configuration/perfilesExc/{idPerfil}")
	public ResponseEntity<?> listExceptions(HttpServletRequest request,@PathVariable String idPerfil) {
		List<RelPerfilExcs> catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String) request.getSession(false).getAttribute("language");
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
			//catalogsEntity = perfilesService.getPerfilExceptions(idPerfil);
			// catalogsEntity=Service.espmxregion();

			System.out.println("hasta aqui todo bien");
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
	@GetMapping("v1/configuration/perfilesMotor/{idPerfilA}/{idPerfil}/{idEv}/{idCopy}")
	public ResponseEntity<?> listPerfilesMotor(HttpServletRequest request,@PathVariable String idPerfil,@PathVariable String idPerfilA,@PathVariable String idEv,@PathVariable String idCopy) {
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String) request.getSession(false).getAttribute("language");
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
		//	catalogsEntity = perfilesService.getPerfilesMotorByConfMotor(wwid,idPerfilA,idPerfil,idEv,idCopy);
			// catalogsEntity=Service.espmxregion();

			System.out.println("hasta aqui todo bien");
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
	// POST PERFIL
	// -------------------------------------------------------------------

	@PostMapping("v1/configuration/perfiles/{idEv}/{idCopia}")
	public ResponseEntity<?> create(@PathVariable String idEv,@PathVariable String idCopia, HttpServletRequest request) {
		System.out.println("si entre!");
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			// System.out.println(entity);
			catalogsEntity = perfilesService.save(wwid,idEv,idCopia);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}
	
			// -------------------------------------------------------------------
			// GET  PERFILES 
			// -------------------------------------------------------------------
			@GetMapping("/v1/configuration/perfiles")
			public ResponseEntity<?> list1(HttpServletRequest request) 
			{
				Object catalogsEntity = null;
				Map<String, Object> response = new HashMap<>();
				String wwid = (String) request.getSession(false).getAttribute("wwid");
				try 
				{
					/*String region=(String)request.getSession(false).getAttribute("region");
					String puntoServicio=(String)request.getSession(false).getAttribute("puntoServicio");*/
					String evaluacion=(String)request.getSession(false).getAttribute("evaluacion");
					String distribuidor=(String)request.getSession(false).getAttribute("distribuidor");
					System.out.println("evaluacion: "+evaluacion+"  distribuidor: "+distribuidor);
					catalogsEntity = perfilesService.getPerfilesByEvAndResp(evaluacion, distribuidor);
					
				} 
				catch (DataAccessException e) 
				{
					response.put("message", "Error when making query."); // TODO: cambiar el mensaje por un tag que se vaya a
																			// usar en el diccionario.
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} 
				catch (IllegalArgumentException mapExcepcion) 
				{
					response.put("message", "Error when mapping data."); // TODO: cambiar el mensaje por un tag que se vaya a
																			// usar en el diccionario.
					response.put("error", mapExcepcion.getMessage());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}

				if (catalogsEntity == null) 
				{
					response.put("message", "Query returned 0 results."); // TODO: cambiar el mensaje por un tag que se vaya a
																			// usar en el diccionario.
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}

				return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
			}


}
