package com.cummins.scd.controllers.loadInformation;

import com.cummins.scd.models.dao.IGarantiaDao;
import com.cummins.scd.models.entity.Garantia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
//@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
//
@RestController
@RequestMapping("/_api")
public class GuaranteeController {
    @Autowired
    IGarantiaDao guaranteeDao;

    @GetMapping("admin/loadInfo/guarantee")
    Iterable<Garantia> getAll() {
        return guaranteeDao.findAll();
    }

    @PostMapping("admin/loadInfo/guarantee")
    ResponseEntity<?> save(@RequestBody Garantia garantia, HttpServletRequest request){
        Garantia garantiaResult = null;
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (guaranteeDao.exists(garantia.getFolio())) {
                return null;
            };
    
            String wwid = (String)request.getSession(false).getAttribute("wwid");
            Date date = new Date();
            garantia.setCreationDate(date);
            garantia.setCreatedBy(wwid);
            garantia.setLastUpdateDate(date);
            garantia.setLastUpdatedBy(wwid);
            System.out.println(garantia);
            garantiaResult = guaranteeDao.save(garantia);            
        } catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
			response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", mapExcepcion.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if(garantiaResult == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Garantia>(garantiaResult, HttpStatus.OK);
    }

    @PutMapping("admin/loadInfo/guarantee")
    ResponseEntity<?> update(@RequestBody Garantia garantia, HttpServletRequest request) {
        Garantia garantiaResult = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Garantia foundGuarantee = guaranteeDao.findOne(garantia.getFolio());
            if (foundGuarantee != null) {
                String wwid = (String)request.getSession(false).getAttribute("wwid");
                Date date = new Date();
                garantia.setCreatedBy(foundGuarantee.getCreatedBy());
                garantia.setCreationDate(foundGuarantee.getCreationDate());
                garantia.setLastUpdateDate(date);
                garantia.setLastUpdatedBy(wwid);
                garantiaResult = guaranteeDao.save(garantia);
            } else {
                return null;
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

		if(garantiaResult == null) {
			response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Garantia>(garantiaResult, HttpStatus.OK);
    }

    @DeleteMapping("admin/loadInfo/guarantee/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (guaranteeDao.exists(id)) {
                guaranteeDao.delete(id);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                response.put("message", "La garantia no existe");
                response.put("error", "No se encontro la garantia");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
			response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
