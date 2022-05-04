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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.dao.IConfSpcodesDao;
import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.services.ILoadInfoService;
import com.cummins.scd.models.services.catalogs.ConfOemService;
import com.cummins.scd.models.services.catalogs.ConfSpcodesService;
import com.cummins.scd.models.services.catalogs.ISpCodesService;
import com.cummins.scd.models.services.catalogs.SpCodesService;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class SPcodeController {
	@Autowired
	ILoadInfoService catalog;
	@Autowired
	ISpCodesService spCodesService;
	
	@Autowired
	IUsersDao userDao;
	
	@Autowired
	ConfSpcodesService confspcodesService;
	
	@Autowired
	ConfOemService confOemService;
	
	@GetMapping("/v1/admin/loadInfo/spcodes/wwid/{region}")
    public ResponseEntity<?> spcodesJoinSpcodes(HttpServletRequest request, @PathVariable String region){
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		try {
			String wwid = (String)request.getSession(false).getAttribute("wwid");
			Users usuario=(Users) userDao.findOne(wwid);
			System.out.println(usuario);
			if(usuario!=null && usuario.getTodas().equals('0')) {
				catalogsEntity = spCodesService.spCodesByWwid(region, wwid);
				System.out.println("entra a revision de spcodes: "+catalogsEntity);
			}else {
				catalogsEntity = spCodesService.getSpCodesByRegion(region);
				System.out.println(catalogsEntity);
			}
			
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

	
	@GetMapping("/v1/admin/loadInfo/spcodes/confspcodes")
    public ResponseEntity<?> validateMotor(HttpServletRequest request){
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		try {
			String wwid = (String)request.getSession(false).getAttribute("wwid");
			//String lang=LocaleContextHolder.getLocale().toString();
			String lang="es_ES";
	
				catalogsEntity = confspcodesService.validaMotorDistribuidor("863","10655","FULL SERVICE","ISX12 G CM2180 EJ","HD","HMLD",lang);

			
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

	@GetMapping("/v1/admin/loadInfo/spcodes/confoem")
    public ResponseEntity<?> validateMotorOEM(HttpServletRequest request){
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		try {
			String wwid = (String)request.getSession(false).getAttribute("wwid");
			//String lang=LocaleContextHolder.getLocale().toString();
			String lang="es_ES";
	
				catalogsEntity = confOemService.validaMotorOem("8","10655","FULL SERVICE","ISX12 G CM2180 EJ","HD","HMLD",lang);

			
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

	
	@GetMapping("/list/spcodesXregion/{region}")
    public ResponseEntity<?> spcodeXregion( @PathVariable String region){
		String len = LocaleContextHolder.getLocale().toString();
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalog.findSpcodesXregion(region, len,false);
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
	
	
	@GetMapping("/v1/admin/loadInfo/spcodes")
    public ResponseEntity<?> spcodesJoinSpcodes(){
	  
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = spCodesService.spCodesJoinSpCodes();
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
	
	@GetMapping("/v1/admin/loadInfo/spcodes/{spCode}")
    public ResponseEntity<?> spcodesById(@PathVariable String spCode){
	  
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = spCodesService.spCodesJoinSpCodesbyId(spCode);
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
	
	
	
	@GetMapping("/v1/admin/loadInfo/spcodes/responsible/{spCode}")
    public ResponseEntity<?> spcodesByResponsible(@PathVariable String spCode){
	  
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = spCodesService.spCodesByResponsibleBranchCode(spCode);
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
	
	@GetMapping("/v1/admin/loadInfo/spcodesbyregion/{idRegion}")
    public ResponseEntity<?> spcodesByRegion(@PathVariable String idRegion){
	  
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = spCodesService.getSpCodesByRegion(idRegion);
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
	
	
	@GetMapping("/v1/admin/loadInfo/spcodes/dist/{listDist}")
    public ResponseEntity<?> spcodesByListDist(@PathVariable String listDist){
	  
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = spCodesService.spCodesByListDist(listDist);
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