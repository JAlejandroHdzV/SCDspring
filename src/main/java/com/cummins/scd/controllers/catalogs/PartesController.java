package com.cummins.scd.controllers.catalogs;

import java.util.HashMap;
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
import com.cummins.scd.models.entity.Aux_Partes;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.services.catalogs.IEspmService;
import com.cummins.scd.models.services.catalogs.IPartesService;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class PartesController 
{
    @Autowired
    private IPartesService Service;
    
    private static final Logger logger = Logger.getLogger(PartesController.class);
	
    //-------------------------------------------------------------------
    //						GET ALL PARTS BY DEL 'N'
    //-------------------------------------------------------------------
    @GetMapping("v1/catalogs/parts")
    public ResponseEntity<?> list(HttpServletRequest request)
    {
        logger.info("Parts - Enter get method of controller parts");
        Object catalogsEntity = null;
        Map<String, Object> response = new HashMap<>();
        String len = LocaleContextHolder.getLocale().toString();
	    try 
	    {
		    catalogsEntity= Service.getPartsByDel(false,len);
		    logger.info("Parts - Query OK");
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
    //							        SAVE PART
    //--------------------------------------------------------------------------------------------
  	@PostMapping("v1/catalogs/parts")
  	public ResponseEntity<?> create(@RequestBody Aux_Partes entity,HttpServletRequest request) 
  	{

  	    String wwid = (String)request.getSession(false).getAttribute("wwid");
  	    Aux_Partes catalogsEntity = null;
  	    Map<String, Object> response = new HashMap<>();

  	    try {
  			logger.info("Parts - WWID: "+wwid+" - Enter the saved method of the parts controller, parameters: ");
  			logger.info(entity);
  			catalogsEntity = Service.save(wwid, entity);
  		    if(catalogsEntity==null) {
  			    response.put("message", "Error when inserting on the data base. (Id Duplicated)");
  	  		    response.put("error", "ID Duplicated");
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
  	
    //------------------------------------------------------------------
    //							DELETE PART
    //------------------------------------------------------------------
    @PutMapping("v1/catalogs/parts/{id}")
    public ResponseEntity<?> delete( @PathVariable String id,HttpServletRequest request) 
    {
  	    Map<String, Object> response = new HashMap<>();
  	    Object catalogsEntity = null;
  	    String wwid = (String)request.getSession(false).getAttribute("wwid");
  		
  	    try {
  		    catalogsEntity = Service.delete( id, wwid);
  	    } catch(IllegalArgumentException mapExcepcion) {
  		    response.put("mensaje", "Error al mapear datos.");
  		    response.put("error", mapExcepcion.getMessage());
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
  	    } catch (DataAccessException e) {
  		    response.put("message", "Error when updating on the data base.");
  		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  	    } catch (Exception e) {
  		    response.put("mensaje", "Error when updating on the data base.");
  		    response.put("error", e.getMessage());
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
  	    }
  	    return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
  	}
    
    //--------------------------------------------------------------------------------------------
    //							        UPDATE PART
    //--------------------------------------------------------------------------------------------
    @PutMapping("v1/catalogs/parts")
  	public ResponseEntity<?> update(@RequestBody Aux_Partes entity,HttpServletRequest request) 
    {
  	    String wwid = (String)request.getSession(false).getAttribute("wwid");
  	    Aux_Partes catalogsEntity = null;
  	    Map<String, Object> response = new HashMap<>();

  	    try {
  			logger.info("Parts - WWID: "+wwid+" - Enter the update method of the parts controller, parameters: ");
  			logger.info(entity);
  			catalogsEntity = Service.update(wwid, entity);
  			if(catalogsEntity == null) {
  			    response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
  			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
  		    }
  	    } catch (DataAccessException e) {
  	    	logger.info("Parts - WWID: "+wwid+" Error when updating on the data base.");
  			logger.info(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  	  	    response.put("message", "Error when updating on the data base.");
  		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  	    } catch (Exception e) {
  	    	logger.info("Parts - WWID: "+wwid+" Error when updating on the data base.");
  			logger.info(e.getMessage());
  		    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  	    }
  	    return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
  	}
    
    //-------------------------------------------------------------------
    //						GET PART BY ID
    //-------------------------------------------------------------------
    @GetMapping("v1/catalogs/parts/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable String id)
    {
        logger.info("Parts - Enter get by id method of controller parts");
        Aux_Partes catalogsEntity = null;
        Map<String, Object> response = new HashMap<>();
        //String len = LocaleContextHolder.getLocale().toString();
	    try 
	    {
		    catalogsEntity= Service.findById(id);
		    logger.info("Parts - Query OK");
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
		    response.put("duplicated", false); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    }
	    return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
    }

}
