package com.cummins.scd.controllers.catalogs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.services.catalogs.IRegionService;
import com.cummins.scd.models.services.catalogs.ITipoQService;
import com.cummins.scd.models.services.catalogs.IUsersService;

@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/_api")
public class RegionRestController {

	@Autowired
	IRegionService regionService;
	
	@Autowired
	IUsersDao userDao;
	
	
	@GetMapping("/v1/admin/catalogs/regions/wwid")
    public ResponseEntity<?> spcodesJoinSpcodes(HttpServletRequest request){
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		try {
			String wwid = (String)request.getSession(false).getAttribute("wwid");
			Users usuario=(Users) userDao.findOne(wwid);
			System.out.println(usuario);
			if(usuario!=null && usuario.getTodas().equals('0')) {
				catalogsEntity = regionService.getRegionsByWwid(wwid);
			}else if(usuario!=null && usuario.getTodas().equals('1')) {
				catalogsEntity = regionService.getAllRegionsByDel();
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
}
