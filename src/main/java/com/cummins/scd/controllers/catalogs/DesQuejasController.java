package com.cummins.scd.controllers.catalogs;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.DomUtil.NullResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cummins.scd.models.entity.Aux_Partes;
import com.cummins.scd.models.entity.Aux_Status;
import com.cummins.scd.models.entity.Des_Quejas;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.services.catalogs.IDesQuejasService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import oracle.sql.BLOB;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class DesQuejasController {
	
	@Autowired IDesQuejasService Service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger logger = Logger.getLogger(DesQuejasController.class);
	//-------------------------------------------------------------------
	//						GET ALL COMPLAINT
	//-------------------------------------------------------------------
	@GetMapping("v1/complaints/desComplaints")
    public ResponseEntity<?> list(HttpServletRequest request) throws SQLException{
		String wwid = (String)request.getSession(false).getAttribute("wwid");
        Object catalogsEntity = null;
		Map<String, Object> response = new HashMap<>();
		String len = LocaleContextHolder.getLocale().toString();
		catalogsEntity=Service.getAllQuejas(wwid,len);
		
		try {
			
			
			logger.info("Complaints - Enter Get All Method");
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
    //						GET COMPLAINT BY ID
    //-------------------------------------------------------------------
    @GetMapping("v1/complaints/desComplaints/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable BigInteger id)
    {
        logger.info("Complaints - Enter get by id method of controller complaints");
        Des_Quejas responseEntity = null;
        Map<String, Object> response = new HashMap<>();
	    try 
	    {
		    responseEntity= Service.getQuejasById(id);
		    logger.info("Complaints - Query OK");
	    } catch (DataAccessException e) {
		    response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
		    response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", mapExcepcion.getMessage());
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	    if(responseEntity == null) {
		    response.put("duplicated", false); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    }
	    return new ResponseEntity<Object>(responseEntity, HttpStatus.OK);
    }
    
    //-------------------------------------------------------------------
    //						DELETE COMPLAINT BY ID
    //-------------------------------------------------------------------
    @PutMapping("v1/complaints/desComplaints/del/{id}")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable BigInteger id)
    {
        logger.info("Complaints - Enter delete by id method of controller complaints");
        Boolean responseEntity= false;
        Map<String, Object> response = new HashMap<>();
	    try 
	    {
		    responseEntity= Service.deleteQuejaById(id);
		    if(responseEntity==true) {
		    	logger.info("Complaints - Delete OK");
		    	response.put("delete", true);
		    	return new ResponseEntity<Object>(response, HttpStatus.OK);
		    }else {
		    	logger.info("Complaints - Delete fail");
		    	response.put("delete", false);
			    return new ResponseEntity<Object>(response, HttpStatus.OK);
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
	   
	    
    }
    
    
  //-------------------------------------------------------------------
    //						GET COMPLAINTS BY LIST
    //-------------------------------------------------------------------
    @PostMapping("v1/complaints/desComplaints/list/filter")
    public ResponseEntity<?> getByList(HttpServletRequest request, 
    		@RequestBody Object entity
    		)
   
    {
    	String wwid = (String)request.getSession(false).getAttribute("wwid");
    	JsonNode node = mapper.convertValue(entity, JsonNode.class);
    	String listQjas=node.get("queja").toString().replace("\"","");
    	String listDist=node.get("dist").toString().replace("\"","");
    	String listSpCodes=node.get("spCode").toString().replace("\"","");
    	String listRegions=node.get("region").toString().replace("\"","");
    	
    	System.out.println("Complaint: "+listQjas+" Distributor: "+listDist+" SpCodes: "+listSpCodes+" Region: "+listRegions);
        logger.info("Complaints - Enter get by list method of controller complaints");
        Object responseList = null;
        Map<String, Object> response = new HashMap<>();
        String lang = LocaleContextHolder.getLocale().toString();
	    try 
	    {
		    responseList= Service.getQuejasByList(lang,listQjas,listDist,listSpCodes,listRegions,wwid);
		    logger.info("Complaints - Query OK");
	    } catch (DataAccessException e) {
		    response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
		    response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", mapExcepcion.getMessage());
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	    if(responseList == null) {
		    response.put("duplicated", false); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    }
	    return new ResponseEntity<Object>(responseList, HttpStatus.OK);
    }

  //-------------------------------------------------------------------
    //						GET COMPLAINTS BY LIST
    //-------------------------------------------------------------------
    @GetMapping("v1/complaints/desComplaints/spCodes/{listSpCodes}")
    public ResponseEntity<?> getByListSpCodes(HttpServletRequest request, @PathVariable String listSpCodes)
    {
        logger.info("Complaints - Enter get by spcodes method of controller complaints");
        Object responseList = null;
        Map<String, Object> response = new HashMap<>();
        String lang = LocaleContextHolder.getLocale().toString();
	    try 
	    {
		    responseList= Service.getQuejasBySpCodes(lang, listSpCodes);
		    logger.info("Complaints - Query OK");
	    } catch (DataAccessException e) {
		    response.put("message", "Error when making query."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(IllegalArgumentException mapExcepcion) {
		    response.put("message", "Error when mapping data."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    response.put("error", mapExcepcion.getMessage());
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	    if(responseList == null) {
		    response.put("duplicated", false); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    }
	    return new ResponseEntity<Object>(responseList, HttpStatus.OK);
    }
    
  //--------------------------------------------------------------------------------------------
    
	//							        SAVE COMPLAINTS
    //--------------------------------------------------------------------------------------------
  	@PostMapping("v1/complaints/desComplaints")
  	@SuppressWarnings("deprecation")
  	public ResponseEntity<?> create(
  			@RequestParam (required=true, name= "spCode")String  spCode,
  			@RequestParam (required=false, name= "fechaCaptura")String  fechaCaptura,
  			@RequestParam (required=false, name= "fechaQueja")String  fechaQueja,
  			@RequestParam (required=false, name= "cliente")String  cliente,
  			@RequestParam (required=false, name= "contactoQueja")String  contactoQueja,
  			@RequestParam (required=false, name= "idCatQueja")BigInteger  idCatQueja,
  			@RequestParam (required=false, name= "descQueja")String  descQueja,
  			@RequestParam (required=false, name= "segQueja")String  segQueja,
  			@RequestParam (required=false, name= "responsableSeg")String  responsableSeg,
  			@RequestParam (required=false, name= "fechaCierre")String  fechaCierre,
  			@RequestParam (required=false, name= "compromiso")String compromiso,
  			@RequestParam (required=false, name= "reporta")String  reporta,
  			@RequestParam (required=false, name= "estatus")String  estatus,
  			@RequestParam (required=false, name= "validoEval")String  validoEval,
  			@RequestParam (required=false, name= "fileName1")String  fileName1,
  			@RequestParam (required=false, name= "fileType1")String  fileType1,
  			@RequestParam (required=false, name= "fileContent1")MultipartFile  fileContent1,
  			@RequestParam (required=false, name= "fileName2")String  fileName2,
  			@RequestParam (required=false, name= "fileType2")String  fileType2,
  			@RequestParam (required=false, name= "fileContent2")MultipartFile  fileContent2,
  			@RequestParam (required=false, name= "fileName3")String  fileName3,
  			@RequestParam (required=false, name= "fileType3")String  fileType3,
  			@RequestParam (required=false, name= "fileContent3")MultipartFile  fileContent3,
  			@RequestParam (required=false, name= "fileName4")String  fileName4,
  			@RequestParam (required=false, name= "fileType4")String  fileType4,
  			@RequestParam (required=false, name= "fileContent4")MultipartFile  fileContent4,
  			@RequestParam (required=false, name= "fileName5")String  fileName5,
  			@RequestParam (required=false, name= "fileType5")String  fileType5,
  			@RequestParam (required=false, name= "fileContent5")MultipartFile  fileContent5,
  			HttpServletRequest request) 
  	{
  		Des_Quejas entity= new Des_Quejas();
  		
  		try {
  		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); 
  		System.out.println("fechaCaptura: "+fechaCaptura+" fechaQueja: "+fechaQueja+" fechaCierre: "+fechaCierre);//--------------------------- Quitar esta linea de debugg
  		if(fechaCaptura != null && !fechaCaptura.equals("null")) {
  		Date fechaCap = formato.parse(fechaCaptura);
  		fechaCap.setHours(12);
  		entity.setFechaCaptura(fechaCap);
  		}
  		
  		if(fechaQueja!=null&& !fechaQueja.equals("null")) {
  		Date fechaQue = formato.parse(fechaQueja);
  		fechaQue.setHours(12);
  		entity.setFechaQueja(fechaQue);
  		}
  		
  		if(fechaCierre!=null&& !fechaCierre.equals("null")) {
  		Date fechaCie = formato.parse(fechaCierre);
  		fechaCie.setHours(12);
  	    entity.setFechaCierre(fechaCie);
  		}
  		
  		}catch(Exception e) {
  			logger.error("An error ocurred while formatting the dates of the new complaint");
  		}
  		entity.setSpCode(spCode!=null && !spCode.equals("null")?spCode:null);
  	    entity.setCliente(cliente!=null&& !cliente.equals("null")?cliente:null);
  	    entity.setContactoQueja(contactoQueja!=null&& !contactoQueja.equals("null")?contactoQueja:null);
  	  System.out.println("spcode: "+spCode+" cliente: "+cliente+" contactoQueja: "+contactoQueja); //--------------------------- Quitar esta linea de debugg
  	  
  	    entity.setIdCatQuejas(idCatQueja!=null?idCatQueja:null);
  	    entity.setDescQueja(descQueja!=null&& !descQueja.equals("null")?descQueja:null);
  	    entity.setSegQueja(segQueja!=null&& !segQueja.equals("null")?segQueja:null);
  	  System.out.println("idCatQueja: "+idCatQueja+" descQueja: "+descQueja+" segQueja: "+segQueja);//--------------------------- Quitar esta linea de debugg
  	    
  	  	entity.setResponsableSeg(responsableSeg!=null&& !responsableSeg.equals("null")?responsableSeg:null);
  	    entity.setCompromiso(compromiso!=null&& !compromiso.equals("null")?compromiso:null);
  	    entity.setReporta(reporta!=null&& !reporta.equals("null")?reporta:null);
  	  System.out.println("responsableSeg: "+responsableSeg+" compromiso: "+compromiso+" reporta: "+reporta);//--------------------------- Quitar esta linea de debugg
  	    
  	    entity.setEstatus(estatus!=null && !estatus.equals("null")?estatus:null);
  	    entity.setValidoEval(validoEval!=null&& !validoEval.equals("null")?validoEval:null);
  	  System.out.println("estatus: "+estatus+" validoEval: "+validoEval); //--------------------------- Quitar esta linea de debugg
  	    try {
  	    	if(fileContent1!= null) {
  	    		System.out.println("contentType1: "+fileContent1);//--------------------------- Quitar esta linea de debugg
  	    		System.out.println("contentType1: "+fileContent1);//--------------------------- Quitar esta linea de debugg
  	    		System.out.println("content1: "+fileContent1.getBytes());//--------------------------- Quitar esta linea de debugg
  	    		System.out.println("content1, impimiendo nombre: "+fileContent1.getName());//--------------------------- Quitar esta linea de debugg
			entity.setFileContent1(fileContent1.getBytes());
			System.out.println("content1, impimiendo nombre: "+entity.getFileContent1());//--------------------------- Quitar esta linea de debugg
			entity.setFileName1(fileName1);
			System.out.println("fileName1: "+fileName1);//--------------------------- Quitar esta linea de debugg
	  	    entity.setFileType1(fileType1);
	  	  System.out.println("fileType1: "+fileType1);//--------------------------- Quitar esta linea de debugg
  	    	}
  	    	
  	    	if(fileContent2!=null) {
			entity.setFileContent2(fileContent2.getBytes());
			entity.setFileName2(fileName2);
		    entity.setFileType2(fileType2);
  	    	}
  	    	
  	    	if(fileContent3!=null) {
			entity.setFileContent3(fileContent3.getBytes());
			entity.setFileName3(fileName3);
	  	    entity.setFileType3(fileType3);
  	    	}
  	    	
  	    	if(fileContent4!=null) {
			entity.setFileContent4(fileContent4.getBytes());
			entity.setFileName4(fileName4);
		    entity.setFileType4(fileType4);
  	    	}
  	    	
  	    	if(fileContent5!=null) {
			entity.setFileContent5(fileContent5.getBytes());
			entity.setFileName5(fileName5);
	  	    entity.setFileType5(fileType5);
  	    	}
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
  	    String wwid = (String)request.getSession(false).getAttribute("wwid");
  	    Des_Quejas catalogsEntity = null;
  	    Map<String, Object> response = new HashMap<>();

  	    try {
  			logger.info("Complaints - WWID: "+wwid+" - Enter the saved method of the complaints controller, parameters: ");
  			logger.info(entity);
  			catalogsEntity = Service.save(wwid,entity);
  	    } catch (DataAccessException e) {
  	  	    response.put("message", "Error when inserting/updating on the data base.");
  		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  	    } catch (Exception e) {
  		    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  	    }
  	    return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
  	}
  	
  	//--------------------------------------------------------------------------------------------
    //							        UPDATE COMPLAINTS
    //--------------------------------------------------------------------------------------------
    @SuppressWarnings("deprecation")
	@PostMapping("v1/complaints/desComplaints/edit")
  	public ResponseEntity<?> update(
  			@RequestParam (required=true, name= "idQueja")String  idQueja,
  			@RequestParam (required=true, name= "spCode")String  spCode,
  			@RequestParam (required=false, name= "fechaCaptura")String  fechaCaptura,
  			@RequestParam (required=false, name= "fechaQueja")String  fechaQueja,
  			@RequestParam (required=false, name= "cliente")String  cliente,
  			@RequestParam (required=false, name= "contactoQueja")String  contactoQueja,
  			@RequestParam (required=false, name= "idCatQueja")BigInteger  idCatQueja,
  			@RequestParam (required=false, name= "descQueja")String  descQueja,
  			@RequestParam (required=false, name= "segQueja")String  segQueja,
  			@RequestParam (required=false, name= "responsableSeg")String  responsableSeg,
  			@RequestParam (required=false, name= "fechaCierre")String  fechaCierre,
  			@RequestParam (required=false, name= "compromiso")String compromiso,
  			@RequestParam (required=false, name= "reporta")String  reporta,
  			@RequestParam (required=false, name= "estatus")String  estatus,
  			@RequestParam (required=false, name= "validoEval")String  validoEval,
  			@RequestParam (required=false, name= "fileName1")String  fileName1,
  			@RequestParam (required=false, name= "fileType1")String  fileType1,
  			@RequestParam (required=false, name= "fileContent1")MultipartFile  fileContent1,
  			@RequestParam (required=false, name= "fileName2")String  fileName2,
  			@RequestParam (required=false, name= "fileType2")String  fileType2,
  			@RequestParam (required=false, name= "fileContent2")MultipartFile  fileContent2,
  			@RequestParam (required=false, name= "fileName3")String  fileName3,
  			@RequestParam (required=false, name= "fileType3")String  fileType3,
  			@RequestParam (required=false, name= "fileContent3")MultipartFile  fileContent3,
  			@RequestParam (required=false, name= "fileName4")String  fileName4,
  			@RequestParam (required=false, name= "fileType4")String  fileType4,
  			@RequestParam (required=false, name= "fileContent4")MultipartFile  fileContent4,
  			@RequestParam (required=false, name= "fileName5")String  fileName5,
  			@RequestParam (required=false, name= "fileType5")String  fileType5,
  			@RequestParam (required=false, name= "fileContent5")MultipartFile  fileContent5,
  			HttpServletRequest request) 
  	{
  		Des_Quejas entity= new Des_Quejas();
  		try {
  	  		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
  	  	System.out.println("fechaCaptura: "+fechaCaptura+" fechaQueja: "+fechaQueja+" fechaCierre: "+fechaCierre);//--------------------------- Quitar esta linea de debugg
  	  		
  	  	if(fechaCaptura!= null && !fechaCaptura.equals("null")) {
  	  		Date fechaCap = formato.parse(fechaCaptura);
  	  		fechaCap.setHours(12);
  	  		entity.setFechaCaptura(fechaCap);
  	  	}
  	  	if(fechaQueja!=null&& !fechaQueja.equals("null")) {
  	  		Date fechaQue = formato.parse(fechaQueja);
  	  		fechaQue.setHours(12);
  	  		entity.setFechaQueja(fechaQue);
  	  	}
  	  	if(fechaCierre!=null&& !fechaCierre.equals("null")) {
  	  		Date fechaCie = formato.parse(fechaCierre);
  	  		fechaCie.setHours(12);
  	  	    entity.setFechaCierre(fechaCie);
  	  	}
  	  		}catch(Exception e) {
  	  			logger.error("An error occurred while formatting the dates of the complaint");
  	  		}
  		
  		System.out.println("idQueja antes de parsear: "+idQueja);//--------------------------- Quitar esta linea de debugg
  		entity.setIdQueja(idQueja!=null&&!idQueja.equals("")?new BigInteger(idQueja):null);
  		
  		System.out.println("idQueja despues de parsear: "+entity.getIdQueja());//------------- Quitar esta linea de debugg
  		
  		entity.setSpCode(spCode!=null&& !spCode.equals("null")?spCode:null);
  	    entity.setCliente(cliente!=null&& !cliente.equals("null")?cliente:null);
  	    entity.setContactoQueja(contactoQueja!=null&& !contactoQueja.equals("null")?contactoQueja:null);
  	    entity.setIdCatQuejas(idCatQueja);
  	    entity.setDescQueja(descQueja!=null&& !descQueja.equals("null")?descQueja:null);
  	    entity.setSegQueja(segQueja!=null&& !segQueja.equals("null")?segQueja:null);
  	    entity.setResponsableSeg(responsableSeg!=null&& !responsableSeg.equals("null")?responsableSeg:null);
  	    entity.setCompromiso(compromiso!=null&& !compromiso.equals("null")?compromiso:null);
  	    entity.setReporta(reporta!=null&& !reporta.equals("null")?reporta:null);
  	    entity.setEstatus(estatus!=null&& !estatus.equals("null")?estatus:null);
  	    entity.setValidoEval(validoEval!=null&& !validoEval.equals("null")?validoEval:null);
  	    try {
  	    	if(fileContent1!=null) {
			entity.setFileContent1(fileContent1.getBytes());
			entity.setFileName1(fileName1);
	  	    entity.setFileType1(fileType1);
  	    	}
  	    	
  	    	if(fileContent2!=null) {
			entity.setFileContent2(fileContent2.getBytes());
			entity.setFileName2(fileName2);
		    entity.setFileType2(fileType2);
  	    	}
  	    	
  	    	if(fileContent3!=null) {
			entity.setFileContent3(fileContent3.getBytes());
			entity.setFileName3(fileName3);
	  	    entity.setFileType3(fileType3);
  	    	}
  	    	
  	    	if(fileContent4!=null) {
			entity.setFileContent4(fileContent4.getBytes());
			entity.setFileName4(fileName4);
		    entity.setFileType4(fileType4);
  	    	}
  	    	
  	    	if(fileContent5!=null) {
			entity.setFileContent5(fileContent5.getBytes());
			entity.setFileName5(fileName5);
	  	    entity.setFileType5(fileType5);
  	    	}
		} catch (IOException e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
  	    String wwid = (String)request.getSession(false).getAttribute("wwid");
  	    Des_Quejas catalogsEntity = null;
  	    Map<String, Object> response = new HashMap<>();

  	    try {
  			logger.info("Complaints - WWID: "+wwid+" - Enter the update method of the complaints controller, parameters: ");
  			logger.info(entity);
  			catalogsEntity = Service.update(wwid, entity);
  			if(catalogsEntity == null) {
  			    response.put("message", "Query returned 0 results."); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
  			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
  		    }
  	    } catch (DataAccessException e) {
  	    	logger.info("Complaints - WWID: "+wwid+" Error when updating on the data base.");
  			logger.error(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  	  	    response.put("message", "Error when updating on the data base.");
  		    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
  		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  	    } catch (Exception e) {
  	    	logger.info("Complaints - WWID: "+wwid+" Error when updating on the data base.");
  			logger.error(e.getMessage());
  		    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  	    }
  	    return new ResponseEntity<Object>(catalogsEntity, HttpStatus.OK);
  	}
}
