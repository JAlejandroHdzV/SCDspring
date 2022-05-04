package com.cummins.scd.controllers;

import java.math.BigInteger;
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

import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.Pqs;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMecanicos;
import com.cummins.scd.models.entity.RelPerfilMotor;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.services.IConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = { "https://mxca-abomsd-70-dev-ac.cummins.com" }) // descomentar cuando se haga deploy a dev.
																		// comentar las demas.

@RestController
@RequestMapping("/_api")
public class ConfigurationRestController {

	@Autowired
	private IConfigurationService configurationsService;

	@GetMapping("/admin/configuration/{catalogue}")
	public ResponseEntity<?> list(@PathVariable String catalogue) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = configurationsService.findAll(catalogue);
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

	@GetMapping("/admin/configuration/{catalogue}/{id}")
	public ResponseEntity<?> list(@PathVariable String catalogue, @PathVariable String id) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = configurationsService.findById(catalogue, id);
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

	@PostMapping("/admin/configuration/filter/{catalogue}")
	public ResponseEntity<?> filter(@RequestBody Object filter, @PathVariable String catalogue) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = configurationsService.findByCriteria(catalogue, filter);
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

	@PostMapping("/admin/configuration/{catalogue}")
	public ResponseEntity<?> create(@RequestBody Object entity, @PathVariable String catalogue,
			HttpServletRequest request) {

		String wwid = (String) request.getSession(false).getAttribute("wwid");

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		System.out.println("RESPONSE");
		try {
			System.out.println("CALL SERVICE");
			catalogsEntity = configurationsService.save(catalogue, entity, wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (catalogsEntity.equals("repeat")) {
			response.put("message", "Folio repetido.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} else {

			return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
		}

	}

	@PutMapping("/admin/configuration/{catalogue}/{id}")
	public ResponseEntity<?> update(@RequestBody Object entity, @PathVariable String catalogue, @PathVariable String id,
			HttpServletRequest request) {
		// String loggedUser = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		String wwid = (String) request.getSession(false).getAttribute("wwid");

		try {
			catalogsEntity = updateEntity(entity, catalogue, id, wwid);
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

			configurationsService.save(catalogue, catalogsEntity, wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}

	// @DeleteMapping("/admin/loadInfo/{catalogue}/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void delete(@PathVariable String catalogue, @PathVariable String id) {

	// catalogosService.delete(catalogue, id);
	// }

	public Object updateEntity(Object entity, String catalogue, String id, String loggedUser)
			throws IllegalArgumentException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		switch (catalogue) {

		// ---------------------------------------------------------------------
		// UPDATE RECORD IN TABLE DES_PERFILES
		// ---------------------------------------------------------------------
		case "perfiles":
			Perfiles oPerfil = (Perfiles) configurationsService.findById(catalogue, id);
			if (oPerfil == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Perfiles newPerfil = mapper.convertValue(entity, Perfiles.class);
			if (newPerfil.obtenerAction() != null && newPerfil.obtenerAction().equals("estatus")) {
				oPerfil.setDel(oPerfil.getDel() == 'N' ? 'Y' : 'N');
				oPerfil.setLastUpdateBy(loggedUser);
				return oPerfil;
			} else {
				newPerfil.setLastUpdateBy(loggedUser);
				newPerfil.setCreatedBy(oPerfil.getCreatedBy());
				newPerfil.setCreationDate(oPerfil.getCreationDate());
				return newPerfil;
			}
			// ---------------------------------------------------------------------
			// UPDATE RECORD IN TABLE DES_REL_PERFIL_MOTOR
			// ---------------------------------------------------------------------
		case "perfilMotor":

			RelPerfilMotor oPerfilMotor = mapper.convertValue(entity, RelPerfilMotor.class);

			RelPerfilMotor realFilterPerfilMotor = new RelPerfilMotor();
			realFilterPerfilMotor.setIdConfMotor(oPerfilMotor.getIdConfMotor());
			realFilterPerfilMotor.setIdPerfil(oPerfilMotor.getIdPerfil());

			List<RelPerfilMotor> oListPMD = configurationsService.findPerfilMotorByCriteria(realFilterPerfilMotor);
			RelPerfilMotor firstPerfilMotor = oListPMD.get(0);
			if (firstPerfilMotor == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			if (oPerfilMotor.getAction() != null && oPerfilMotor.getAction().equals("estatus")) {
				firstPerfilMotor.setDel(firstPerfilMotor.getDel() == 'N' ? 'Y' : 'N');
				firstPerfilMotor.setLastUpdateBy(loggedUser);
				return firstPerfilMotor;
			} else {
				oPerfilMotor.setLastUpdateBy(loggedUser);
				oPerfilMotor.setCreatedBy(firstPerfilMotor.getCreatedBy());
				oPerfilMotor.setCreationDate(firstPerfilMotor.getCreationDate());
				return oPerfilMotor;
			}
			// ---------------------------------------------------------------------
			// UPDATE RECORD IN TABLE DES_REL_PERFIL_EXCS
			// ---------------------------------------------------------------------
		case "perfilExcs":
			RelPerfilExcs oPExcs = mapper.convertValue(entity, RelPerfilExcs.class);
			// p: public RelPerfilExcs(BigInteger idPerfil, BigInteger idModulo, BigInteger
			// idSubmodulo)
			RelPerfilExcs realFilterPerfilExcs = new RelPerfilExcs(oPExcs.getIdPerfil(), oPExcs.getIdModulo(),
					oPExcs.getIdSubmodulo(), oPExcs.getIdConfMotor());
			List<RelPerfilExcs> oListPerfilExcs = configurationsService.findPerfilExcsByCriteria(realFilterPerfilExcs);
			RelPerfilExcs firstPerfilExcs = oListPerfilExcs.get(0);
			if (firstPerfilExcs == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			if (oPExcs.obtenerAction() != null && oPExcs.obtenerAction().equals("estatus")) {
				firstPerfilExcs.setDel(firstPerfilExcs.getDel() == 'N' ? 'Y' : 'N');
				firstPerfilExcs.setLastUpdateBy(loggedUser);
				return firstPerfilExcs;
			} else {
				oPExcs.setLastUpdateBy(loggedUser);
				oPExcs.setCreatedBy(firstPerfilExcs.getCreatedBy());
				oPExcs.setCreationDate(firstPerfilExcs.getCreationDate());
				return oPExcs;
			}
			// ---------------------------------------------------------------------
			// UPDATE RECORD IN TABLE DES_REL_PERFIL_MECS
			// ---------------------------------------------------------------------
		case "perfilMecanicos":
			RelPerfilMecanicos oPMecanicos = mapper.convertValue(entity, RelPerfilMecanicos.class);
			// P: public RelPerfilMecanicos(BigInteger idPerfil, String promotionId)
			RelPerfilMecanicos realFilterPerfilMecanicos = new RelPerfilMecanicos(oPMecanicos.getIdPerfil(),
					oPMecanicos.getPromotionId());
			List<RelPerfilMecanicos> oListPerfilMecanicos = configurationsService
					.findPerfilMecanicosByCriteria(realFilterPerfilMecanicos);
			RelPerfilMecanicos firstPerfilMecanicos = oListPerfilMecanicos.get(0);
			if (firstPerfilMecanicos == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			if (oPMecanicos.obtenerAction() != null && oPMecanicos.obtenerAction().equals("estatus")) {
				// firstPerfilMecanicos.setDel(oPMecanicos.getDel());
				// Character del=(oPMecanicos.getDel()=='N'?'Y':'N');
				firstPerfilMecanicos.setDel(firstPerfilMecanicos.getDel() == 'N' ? 'Y' : 'N');
				firstPerfilMecanicos.setLastUpdateBy(loggedUser);
				return firstPerfilMecanicos;
			} else {
				oPMecanicos.setLastUpdateBy(loggedUser);
				oPMecanicos.setCreatedBy(firstPerfilMecanicos.getCreatedBy());
				oPMecanicos.setCreationDate(firstPerfilMecanicos.getCreationDate());
				return oPMecanicos;
			}
		default:
			return null;
		}
	}

}
