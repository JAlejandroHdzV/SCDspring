package com.cummins.scd.controllers.catalogs;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

import com.cummins.scd.controllers.ScdMainController;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.entity.UsersSpCodes;
import com.cummins.scd.models.services.catalogs.IUsersService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class UsersController {
	@Autowired
	IUsersService usersService;
	
	private static final Logger logger = Logger.getLogger(UsersController.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	// -------------------------------------------------------------------
	// GET ALL USERS BY DEL 'N'
	// -------------------------------------------------------------------
	@GetMapping("/v1/accessControl/users")
	public ResponseEntity<?> list(HttpServletRequest request) {
		System.out.println("entra a users");
		System.out.println(request);
		List<Users> catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String) request.getSession(false).getAttribute("language");
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
			catalogsEntity = usersService.getUsersByDel();
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
	// GET USER BY WWID
	// -------------------------------------------------------------------
	@GetMapping("/v1/accessControl/users/{id}")
	public ResponseEntity<?> listWWID(HttpServletRequest request,@PathVariable String id) {
		System.out.println("entra a users");
		System.out.println(request);
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String len = LocaleContextHolder.getLocale().toString();
		
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
			catalogsEntity = usersService.getUsersByWWID(id);
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
		// GET USER BY WWID
		// -------------------------------------------------------------------
		@GetMapping("/v1/accessControl/roles")
		public ResponseEntity<?> listRoles(HttpServletRequest request) {
			System.out.println("entra a roles");
			System.out.println(request);
			Object catalogsEntity = null;
			Map<String, Object> response = new HashMap<>();
			String len = LocaleContextHolder.getLocale().toString();
			
			try {
				catalogsEntity = usersService.getRolesByDel(len, false);

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
	// POST USERS
	// -------------------------------------------------------------------

	@PostMapping("/v1/accessControl/users")
	public ResponseEntity<?> create(@RequestBody Object entity, HttpServletRequest request) {

		String wwid = (String) request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			// System.out.println(entity);
			catalogsEntity = usersService.save(wwid, entity);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}
	//------------------------------------------------------------------
	//							UPDATE User
	//------------------------------------------------------------------
	@PutMapping("/v1/accessControl/users")
	public ResponseEntity<?> update(@RequestBody Object filter,HttpServletRequest request) {
		//String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String)request.getSession(false).getAttribute("wwid");
		System.out.println("entra a actualizacion users");

		try {
			catalogsEntity = usersService.update(filter, wwid);
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

	// ----------------------------------------------------------------------
	// DELETE USERS BY ID
	// ---------------------------------------------------------------------

	@PutMapping("/v1/accessControl/users/{id}")
	public ResponseEntity<?> update(@PathVariable String id, HttpServletRequest request) {
		// String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String) request.getSession(false).getAttribute("wwid");

		try {
			catalogsEntity = usersService.delete(id, wwid);
		} catch (IllegalArgumentException mapExcepcion) {
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

			// catalogsService.save(catalogue, catalogsEntity,wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}
	// ----------------------------------------------------------------------
	// DELETE SPCODES BY ID
	// ---------------------------------------------------------------------

	@PutMapping("/v1/accessControl/spcodes/{idUser}/{spcode}")
	public ResponseEntity<?> updateCode(@PathVariable String idUser, @PathVariable String spcode, HttpServletRequest request) {
		// String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		UsersSpCodes catalogsEntity = null;

		String wwid = (String) request.getSession(false).getAttribute("wwid");
		
		try {
			catalogsEntity = usersService.deleteCode(idUser,spcode, wwid);
		} catch (IllegalArgumentException mapExcepcion) {
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

			// catalogsService.save(catalogue, catalogsEntity,wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}
	// -------------------------------------------------------------------
	// GET SPCODES BY WWID
	// -------------------------------------------------------------------
	@GetMapping("/v1/accessControl/spcodes/{id}")
	public ResponseEntity<?> listSPCodes(@PathVariable String id,HttpServletRequest request) {
		System.out.println("entra a users");
		System.out.println(request);
		List<UsersSpCodes> catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String language = (String) request.getSession(false).getAttribute("language");
		// System.out.println(language);
		try {
			// catalogsEntity = Service.findAll("Ingles");
			// catalogsEntity = Service.todosESPM();
			catalogsEntity = usersService.getSpCodesByWWID(id);
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
	// POST SPCODES
	// -------------------------------------------------------------------

	@PostMapping("/v1/accessControl/spcodes")
	public ResponseEntity<?> createSpCode(@RequestBody Object entity, HttpServletRequest request) {

		String wwid = (String) request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			// System.out.println(entity);
			catalogsEntity = usersService.addSpCode(entity,wwid);
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
		// POST USERS
		// -------------------------------------------------------------------

		@PostMapping("/v1/accessControl/users/config")
		public ResponseEntity<?> configurationUser(@RequestBody Object entity, HttpServletRequest request) {
			Map<String, Object> response = new HashMap<>();
			try {
			JsonNode actualObj1 = null;
			actualObj1 = mapper.convertValue(entity, JsonNode.class);
			String Region= actualObj1.get("region").toString().replaceAll("\"", "");
			String Evaluacion=actualObj1.get("evaluacion").toString().replaceAll("\"", "");
			String Distribuidor=actualObj1.get("distribuidor").toString().replaceAll("\"", "");
			String PuntoServicio=actualObj1.get("puntoServicio").toString().replaceAll("\"", "");
			request.getSession(false).setAttribute("region", Region);
			request.getSession(false).setAttribute("evaluacion", Evaluacion);
			request.getSession(false).setAttribute("distribuidor", Distribuidor);
			request.getSession(false).setAttribute("puntoServicio", PuntoServicio);
			String wwid = (String) request.getSession(false).getAttribute("wwid");
			
			} catch (Exception e) {
				response.put("message", "Error when inserting/updating on the data base.");
				response.put("error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} 
			response.put("region",(String)request.getSession(false).getAttribute("region"));
			response.put("evaluacion",(String)request.getSession(false).getAttribute("evaluacion"));
			response.put("distribuidor",(String)request.getSession(false).getAttribute("distribuidor"));
			response.put("puntoServicio",(String)request.getSession(false).getAttribute("puntoServicio"));
			return new ResponseEntity<Object>(response, HttpStatus.OK);

		}
		//-------------------------------------------------------------------
	    //						GET ROLE
	    //-------------------------------------------------------------------
	    @GetMapping("v1/admin/role")
	    public ResponseEntity<?> getRole(HttpServletRequest request)
	    {
	        logger.info("Enter get role method");
	        Map<String, Object> response = new HashMap<>();
	        String role=null;
	        Integer roleInt= 0;
		    try 
		    {
		    	 role = (String) request.getSession(false).getAttribute("role");
		    	 roleInt= new Integer(role);
		    }
		     catch(Exception e) {
		    	 logger.error("Role - error when the user role was queried: "+e.getMessage());
		    	 response.put("error", e.getMessage());
			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		    }
		    response.put("role", roleInt);
		    return new ResponseEntity<Object>(response, HttpStatus.OK);
	    }


}
