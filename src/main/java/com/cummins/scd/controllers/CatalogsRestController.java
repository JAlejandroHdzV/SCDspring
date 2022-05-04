package com.cummins.scd.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.dao.IRegionDao;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.Category;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.CountryPerRegion;
import com.cummins.scd.models.entity.Emissions;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.QSol;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.ServiceLevel;

import com.cummins.scd.models.entity.Tools;
import com.cummins.scd.models.services.ICatalogsService;

import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = {"http://localhost:4200"})  //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = { "https://mxca-abomsd-70-dev-ac.cummins.com" }) // descomentar cuando se haga deploy a dev.
																		// comentar las demas.
@RestController
@RequestMapping("/_api")

public class CatalogsRestController {

	@Autowired
	private ICatalogsService catalogsService;

	private static final Logger logger = Logger.getLogger(CatalogsRestController.class);

	@GetMapping("/global/lenguaje")
	public ResponseEntity<?> language(HttpServletRequest request) {

		Map<String, Object> response = new HashMap<>();

		String lenguaje = "";
		System.out.println(
				LocaleContextHolder.getLocaleContext().toString() + " " + LocaleContextHolder.getLocale().toString());
		switch (LocaleContextHolder.getLocale().toString()) {
		case "es_ES":
			lenguaje = "es";
			break;
		case "en_US":
			lenguaje = "en";
			break;
		case "pt_BR":
			lenguaje = "pt";
			break;
		}
		response.put("language", lenguaje);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PostMapping("/global/language/{lan}")
	// ,@PathVariable String lan
	public ResponseEntity<?> language1(HttpServletRequest request, @PathVariable String lan) {

		System.out.println("entra a cambiar idioma");
		HttpSession session = request.getSession();
		// request.getSession(true).setAttribute("language", lan);
		session.setAttribute("language", lan);

		Map<String, Object> response = new HashMap<>();

		response.put("Ok", "1");
		String language = (String) request.getSession(false).getAttribute("language");
		System.out.println(language);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/catalogs/{catalogue}")
	public ResponseEntity<?> list(@PathVariable String catalogue, HttpServletRequest request) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findAll(catalogue);
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

	@GetMapping("/catalogs/{catalogue}/{id}")
	public ResponseEntity<?> list(@PathVariable String catalogue, @PathVariable String id) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findById(catalogue, id);
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

	@PostMapping("/catalogs/filter/{catalogue}")
	public ResponseEntity<?> filter(@RequestBody Object filter, @PathVariable String catalogue,
			HttpServletRequest request) {
		Boolean flag = false;
		String referer = (request.getHeader("Referer") == null) ? "" : request.getHeader("Referer");
		System.out.println("referer" + referer);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		if (referer.contains("DES/view/admin/catalogs/applications") || referer.contains("DES/view/admin/catalogs/qsol")
				|| referer.contains("DES/view/admin/catalogs/ranks")
				|| referer.contains("DES/view/admin/catalogs/serviceLevel")
				|| referer.contains("DES/view/admin/catalogs/oems")
				|| referer.contains("DES/view/admin/partsAndTools/tools")
				|| referer.contains("DES/view/admin/catalogs/emissions")) {

			System.out.println("Contiene el catalogo");
			flag = true;
		}
		String len = LocaleContextHolder.getLocale().toString();
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findByCriteria(catalogue, filter, len, flag);
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

	@PostMapping("/catalogs/findExisting/{catalogue}")
	public ResponseEntity<?> findExisting(@RequestBody Object filter, @PathVariable String catalogue) {

		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.findExisting(catalogue, filter);
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

	@PostMapping("/catalogs/{catalogue}")
	public ResponseEntity<?> create(@RequestBody Object entity, @PathVariable String catalogue,
			HttpServletRequest request) {

		String wwid = (String) request.getSession(false).getAttribute("wwid");
		Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();

		try {
			catalogsEntity = catalogsService.save(catalogue, entity, wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when inserting/updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}

	@PutMapping("/catalogs/{catalogue}/{id}")
	public ResponseEntity<?> update(@RequestBody Object entity, @PathVariable String catalogue, @PathVariable String id,
			HttpServletRequest request) {
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();
		Object catalogsEntity = null;

		try {
			catalogsEntity = updateEntity(entity, catalogue, id, wwid, len);
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
			catalogsService.save(catalogue, catalogsEntity, wwid);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating on the data base.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	public Object updateEntity(Object entity, String catalogue, String id, String loggedUser, String lang)
			throws IllegalArgumentException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		switch (catalogue) {

		case "tools":
			Tools currentTipoProc = (Tools) catalogsService.findById(catalogue, id);
			if (currentTipoProc == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Tools newTipoProc = mapper.convertValue(entity, Tools.class);
			if (newTipoProc.obtenerAction() != null && newTipoProc.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentTipoProc.setDel(newTipoProc.getDel());
				currentTipoProc.setLastUpdatedBy(loggedUser);
				return currentTipoProc;
			} else {
				newTipoProc.setLastUpdatedBy(loggedUser);
				newTipoProc.setCreatedBy(currentTipoProc.getCreatedBy());
				newTipoProc.setCreationDate(currentTipoProc.getCreationDate());
				return newTipoProc;
			}
		case "oems":
			Oems currentOem = (Oems) catalogsService.findById(catalogue, id);

			if (currentOem == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentOem.getFileName());
			}
			Oems newOem = mapper.convertValue(entity, Oems.class);

			if (newOem.obtenerAction() != null && newOem.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);

				currentOem.setDel(newOem.getDel());
				currentOem.setLastUpdatedBy(loggedUser);

				return currentOem;

			} else {
				newOem.setLastUpdatedBy(loggedUser);
				newOem.setCreatedBy(currentOem.getCreatedBy());
				newOem.setCreationDate(currentOem.getCreationDate());
				return newOem;
			}
		case "region":
			Region currentRegion = (Region) catalogsService.findById(catalogue, id);

			if (currentRegion == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentRegion.getName());
			}
			Region newRegion = mapper.convertValue(entity, Region.class);

			if (newRegion.obtenerAction() != null && newRegion.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);

				currentRegion.setDel(newRegion.getDel());
				currentRegion.setLastUpdatedBy(loggedUser);
				return currentRegion;

			} else {
				newRegion.setLastUpdatedBy(loggedUser);
				newRegion.setCreatedBy(currentRegion.getCreatedBy());
				newRegion.setCreationDate(currentRegion.getCreationDate());
				return newRegion;
			}
		case "country":
			Country currentCountry = (Country) catalogsService.findById(catalogue, id);

			if (currentCountry == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentCountry.getName());
			}
			Country newCountry = mapper.convertValue(entity, Country.class);

			if (newCountry.obtenerAction() != null && newCountry.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);

				currentCountry.setDel(newCountry.getDel());
				currentCountry.setLastUpdatedBy(loggedUser);
				return currentCountry;

			} else {
				newCountry.setLastUpdatedBy(loggedUser);
				newCountry.setCreatedBy(currentCountry.getCreatedBy());
				newCountry.setCreationDate(currentCountry.getCreationDate());
				return newCountry;
			}

		case "countryPerRegion":

			System.out.println("Trata de mappear");
			CountryPerRegion entidad = mapper.convertValue(entity, CountryPerRegion.class);
			entidad.setDel(null);
			ArrayList<CountryPerRegion> currentCountryRlist = (ArrayList<CountryPerRegion>) catalogsService
					.findByCriteria(catalogue, entidad, lang, false);
			System.out.println("Si lo encuentra");
			CountryPerRegion currentCountryR = currentCountryRlist.get(0);
			System.out.println(currentCountryRlist.get(0));

			if (currentCountryR == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentCountryR.getCountry().getName() + " region "
						+ currentCountryR.getRegion().getName());
			}
			CountryPerRegion newCountryR = mapper.convertValue(entity, CountryPerRegion.class);

			if (newCountryR.obtenerAction() != null && newCountryR.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);

				currentCountryR.setDel(newCountryR.getDel());
				currentCountryR.setLastUpdatedBy(loggedUser);
				return currentCountryR;

			} else {
				newCountryR.setLastUpdatedBy(loggedUser);
				newCountryR.setCreatedBy(currentCountryR.getCreatedBy());
				newCountryR.setCreationDate(currentCountryR.getCreationDate());
				return newCountryR;
			}
		case "motorProducts":
			MotorProducts currentP = (MotorProducts) catalogsService.findById(catalogue, id);

			if (currentP == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentP.getName());
			}
			MotorProducts newP = mapper.convertValue(entity, MotorProducts.class);

			if (newP.obtenerAction() != null && newP.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentP.setDel(newP.getDel());
				currentP.setLastUpdatedBy(loggedUser);
				return currentP;

			} else {
				newP.setLastUpdatedBy(loggedUser);
				newP.setCreatedBy(currentP.getCreatedBy());
				newP.setCreationDate(currentP.getCreationDate());
				return newP;
			}
		case "categoria":
			Category currentC = (Category) catalogsService.findById(catalogue, id);

			if (currentC == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentC.getCategory());
			}
			Category newC = mapper.convertValue(entity, Category.class);

			if (newC.obtenerAction() != null && newC.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentC.setDel(newC.getDel());
				currentC.setLastUpdatedBy(loggedUser);
				return currentC;

			} else {
				newC.setLastUpdatedBy(loggedUser);
				newC.setCreatedBy(currentC.getCreatedBy());
				newC.setCreationDate(currentC.getCreationDate());
				return newC;
			}
		case "ranks":
			Ranks currentR = (Ranks) catalogsService.findById(catalogue, id);

			if (currentR == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentR.getRank());
			}
			Ranks newR = mapper.convertValue(entity, Ranks.class);

			if (newR.obtenerAction() != null && newR.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentR.setDel(newR.getDel());
				currentR.setLastUpdatedBy(loggedUser);
				return currentR;

			} else {
				newR.setLastUpdatedBy(loggedUser);
				newR.setCreatedBy(currentR.getCreatedBy());
				newR.setCreationDate(currentR.getCreationDate());
				return newR;
			}
		case "application":
			Application currentA = (Application) catalogsService.findById(catalogue, id);

			if (currentA == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentA.getApplication());
			}
			Application newA = mapper.convertValue(entity, Application.class);

			if (newA.obtenerAction() != null && newA.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentA.setDel(newA.getDel());
				currentA.setLastUpdatedBy(loggedUser);
				return currentA;

			} else {
				newA.setLastUpdatedBy(loggedUser);
				newA.setCreatedBy(currentA.getCreatedBy());
				newA.setCreationDate(currentA.getCreationDate());
				return newA;
			}
		case "emissions":
			Emissions currentE = (Emissions) catalogsService.findById(catalogue, id);

			if (currentE == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentE.getEmission());
			}
			Emissions newE = mapper.convertValue(entity, Emissions.class);

			if (newE.obtenerAction() != null && newE.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentE.setDel(newE.getDel());
				currentE.setLastUpdatedBy(loggedUser);
				return currentE;

			} else {
				newE.setLastUpdatedBy(loggedUser);
				newE.setCreatedBy(currentE.getCreatedBy());
				newE.setCreationDate(currentE.getCreationDate());
				return newE;
			}
		case "serviceLevel":
			ServiceLevel currentS = (ServiceLevel) catalogsService.findById(catalogue, id);

			if (currentS == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentS.getServiceLevel());
			}
			ServiceLevel newS = mapper.convertValue(entity, ServiceLevel.class);

			if (newS.obtenerAction() != null && newS.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentS.setDel(newS.getDel());
				currentS.setLastUpdatedBy(loggedUser);
				return currentS;

			} else {
				newS.setLastUpdatedBy(loggedUser);
				newS.setCreatedBy(currentS.getCreatedBy());
				newS.setCreationDate(currentS.getCreationDate());
				return newS;
			}
		case "evaluaciones":
			Evaluaciones filterEv = mapper.convertValue(entity, Evaluaciones.class);

			Evaluaciones realFilterEv = new Evaluaciones();
			realFilterEv.setId(filterEv.getId());

			List<Evaluaciones> currentListEv = catalogsService.findEvaluacionesByCriteria(realFilterEv, lang, false);
			Evaluaciones currentEv = currentListEv.get(0);

			if (currentEv == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentEv.getTEvaluacion().getEvaluacion());
			}
			Evaluaciones newEv = mapper.convertValue(entity, Evaluaciones.class);

			if (newEv.obtenerAction() != null && newEv.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);
				currentEv.setDel(newEv.getDel());
				currentEv.setLastUpdatedBy(loggedUser);
				return currentEv;

			} else {
				newEv.setLastUpdatedBy(loggedUser);
				newEv.setCreatedBy(currentEv.getCreatedBy());
				newEv.setCreationDate(currentEv.getCreationDate());
				return newEv;
			}
		case "qsol":
			QSol currentQ = (QSol) catalogsService.findById(catalogue, id);

			if (currentQ == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			} else {
				System.out.println("Se elimina: " + currentQ.obtenerEvaluacion().getText1());
			}
			QSol newQ = mapper.convertValue(entity, QSol.class);

			if (newQ.obtenerAction() != null && newQ.obtenerAction().equals("estatus")) {
				System.out.println("Se elimina: " + id);

				currentQ.setDel(newQ.getDel());
				currentQ.setLastUpdatedBy(loggedUser);
				return currentQ;

			} else {
				newQ.setLastUpdatedBy(loggedUser);
				newQ.setCreatedBy(currentQ.getCreatedBy());
				newQ.setCreationDate(currentQ.getCreationDate());
				return newQ;
			}
		case "quejas":
			Quejas currentQU = (Quejas) catalogsService.findById(catalogue, id);
			if (currentQU == null) {
				throw new Exception("El Tipo Procesamiento con ID: ".concat(id).concat(" no existe."));
			}
			Quejas newQU = mapper.convertValue(entity, Quejas.class);
			if (newQU.getAction() != null && newQU.getAction().equals("estatus")) {
				currentQU.setDel(newQU.getDel());
				currentQU.setLastUpdateBy(loggedUser);
				return currentQU;
			} else {
				newQU.setLastUpdateBy(loggedUser);
				newQU.setCreatedBy(currentQU.getCreatedBy());
				newQU.setCreationDate(currentQU.getCreationDate());

				return newQU;
			}

		default:
			return null;
		}

	}
}
