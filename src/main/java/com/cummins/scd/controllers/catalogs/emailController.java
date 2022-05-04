package com.cummins.scd.controllers.catalogs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cummins.scd.models.dto.QuejasDTO;
import com.cummins.scd.models.services.catalogs.IDesQuejasService;
import com.cummins.scd.models.services.catalogs.IEmailSenderService;
import com.cummins.scd.models.services.catalogs.IQuejasService;
import com.cummins.scd.util.pdfBuilder;

import ch.qos.logback.core.joran.conditional.IfAction;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/_api")
public class emailController {
	@Autowired
	IDesQuejasService quejasService;
	
	@Autowired
	IEmailSenderService emailService;
	
	@Autowired
	MessageSource messageSource;
	
	private static final Logger logger = Logger.getLogger(DesQuejasController.class);
	private static final  String PATH_LOGO_CUMMINS="classpath:static/img/images.png";
	//-------------------------------------------------------------------
	//						GET ALL COMPLAINT
	//-------------------------------------------------------------------
	@PostMapping("v1/email/complaints")
    public ResponseEntity<?> list(
    		@RequestParam("idQueja") BigInteger  idQueja,
    		@RequestParam("mailTo") String  mailTo,
    		@RequestParam("copyTo") String  copyTo,
    		Locale locale,
    		HttpServletRequest request){
		
		String path="";
		try {
			File file = ResourceUtils.getFile(PATH_LOGO_CUMMINS);
			path=file.getPath();
			System.out.println(file.getPath());
			}catch(Exception e) {
				System.out.println("Error path resource: "+e.getMessage());
			}
		System.out.println("IdQueja: "+idQueja+" mailTo: "+mailTo+" copyTo: "+copyTo);
		String wwid = (String)request.getSession(false).getAttribute("wwid");
		Map<String, Object> response = new HashMap<>();
		String lang = LocaleContextHolder.getLocale().toString();
		String[] strMailTo=null;
		String[] strCopyTo=null;
		if(mailTo.length()>1) {
		strMailTo = mailTo.split(",");
		}
		if(copyTo.length()>1) {
		strCopyTo = copyTo.split(",");
		}
		
		try {
			System.out.println("Creando pdf");//--------------------------- Quitar esta linea de debugg
			System.out.println("path: "+path);//--------------------------- Quitar esta linea de debugg
			
			
			QuejasDTO quejaExist=quejasService.getQuejaDtoById( idQueja, lang);
			pdfBuilder pdf= new pdfBuilder();
			ByteArrayOutputStream out=pdf.createPdfDocument(path,quejaExist,messageSource, locale);
			DataSource aAttachmentPlus = new ByteArrayDataSource(out.toByteArray(), "application/octet-stream");
			String send=emailService.sendSimpleMail(strMailTo,strCopyTo,lang,quejaExist.getIdQueja().toString(), quejaExist.getPuntoDeServicio(),aAttachmentPlus, locale, messageSource);
			if(send.equals("send")) {
				System.out.println("Correo enviado");//--------------------------- Quitar esta linea de debugg
				response.put("send", true);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}else {
				System.out.println("Correo no enviado");//--------------------------- Quitar esta linea de debugg
				response.put("send", false);
				response.put("message", "Error when sending email"); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
				response.put("error", send);
				return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
		} catch (Exception e) {
			response.put("message", "Error when sending notification"); //TODO: cambiar el mensaje por un tag que se vaya a usar en el diccionario.
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 

		
	}
	
}
