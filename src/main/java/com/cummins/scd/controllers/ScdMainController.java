package com.cummins.scd.controllers;

//VISTAS

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cummins.scd.models.dao.IRegionDao;
import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.entity.Evaluaciones;
import com.cummins.scd.models.entity.Region;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.services.ICatalogsService;
//import com.cummins.scd.util.SamlRedirection;
//import com.github.ulisesbocchio.spring.boot.security.saml.annotation.SAMLUser;
//import com.github.ulisesbocchio.spring.boot.security.saml.user.SAMLUserDetails;

@Controller
public class ScdMainController {

	@Autowired
	private IRegionDao regionDao;
	private ICatalogsService catalogService;
	@Autowired
	private IUsersDao userDao;

	private static final Logger logger = Logger.getLogger(ScdMainController.class);

	// --------------------------------------------------------------------
	// HOME METHOD
	// --------------------------------------------------------------------

	@RequestMapping({ "/", "index", "/view/" })
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		logger.info("Entre al controlador principal");

		// String Dta=user.getAttribute("wwid");
		// , @SAMLUser SAMLUserDetails user

		// String wwid = (String) request.getSession(false).getAttribute("wwid");

		/*
		 * Esta linea fue agregada por alejandro hernandez if(wwid == null){
		 * response.sendRedirect(SamlRedirection.loginRedirection(request)); }
		 */

		// System.out.println("home handler --- WWID de Sesion: " + wwid);

		/*
		 * User usuario = userDao.findOne(wwid);
		 * 
		 * 
		 * if(usuario == null || usuario.get.equals("0")){ response.sendError(401,
		 * "El usuario no existe en el sistema o se encuentra inactivo."); }
		 */
		request.getSession(true).setAttribute("wwid", "	RQ897");
		return "index";
	}

	// @RequestMapping({ "/view/{idModulo}/{idView}" })
	// public String resolveView(@PathVariable String idModulo, @PathVariable String
	// idView, HttpServletRequest request, HttpServletResponse response, Model
	// model) throws IOException {
	// logger.info("entro en el view");
	// //System.out.println(idModulo);
	// //System.out.println(idView);
	// return idModulo + "/" + idView;
	// }

	// ------------------------------------------------------------------------
	// LOGOUT METHOD
	// ------------------------------------------------------------------------
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		try {
			response.sendRedirect("https://www.cummins.com");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	// -------------------------------------------------------------------------
	// USER IS ADMIN?
	// -------------------------------------------------------------------------
	@ModelAttribute("isAdmin")
	public String getIsAdmin(HttpServletRequest request, HttpServletResponse response) {
		Users user=getWWID(request, response);
		return(user!=null)?user.getIdRol()==BigInteger.valueOf(1)?"1":"0":"0";//cambiar estaconfiguracion cuando se migre a AWS ultimos : 0 : 0
		//return "1";// Cambiar esta linea cuando se integre el SSO
	}

	// -------------------------------------------------------------------------
	// GET USER ROL METHOD
	// -------------------------------------------------------------------------
	@ModelAttribute("Rol")
	public String getRol(HttpServletRequest request, HttpServletResponse response) {
		String idUser = null;
		String Role = null;
		try {
			idUser = (String) request.getSession(false).getAttribute("wwid");
			Role = (String) request.getSession(false).getAttribute("role");
			System.out.println("usuario: " + idUser + " rol: " + Role);
			if ((Role == null && idUser != null && !idUser.equals(""))
					|| Role.equals("") && idUser != null && !idUser.equals("")) {
				logger.info("Unassigned Role");
				logger.info(idUser);
				Users user = userDao.findOne(idUser);
				if (user != null) {
					request.getSession(true).setAttribute("role", user.getIdRol().toString());
					return user.getIdRol().toString();
				} else {
					request.getSession(true).setAttribute("role", "0");
					return "0";
				}
			} else {
				return Role;
			}
		} catch (Exception e) {
			logger.error("Failed to query the wwid: " + idUser + " in the database.");
			request.getSession(true).setAttribute("role", "0");// eliminar esta configuracion cuando se migre a aws
			return "0"; // retornar 0 cuando se migre a aws

		}
	}

	/*
	 * 
	 * 
	 * CAMBIAR LOGICA DE ESTE METODO CUANDO SE PROGRAME LA AUTENTICACION SSO
	 * 
	 * */
	@ModelAttribute("wwid")
	public String getIsUser(HttpServletRequest request, HttpServletResponse response) {
		// ,@RequestHeader("uid") String Uid
		System.out.println("Entra a revisar el Uid");
		String Uid = "";
		try {
			Uid = request.getHeader("uid");
			System.out.println(Uid);
		} catch (Exception e) {

			System.out.println(e);
		}
		// try {

		// String idUser = (String)request.getSession(false).getAttribute("wwid");
		/*
		 * String idUser = Uid; if(idUser==null || idUser=="") { Users user=
		 * userDao.findOne(Uid); if(user!= null ) {
		 * request.getSession(true).setAttribute("wwid", user.getWwid()); idUser =
		 * (String)request.getSession(false).getAttribute("wwid"); } else { idUser = "";
		 * }
		 * 
		 * } return idUser; }catch(Exception e) {
		 * System.out.println("Catch del model attribute wwid");
		 * System.out.println("WWID ERROR: "+e); return "RQ897"; }
		 */
		// Users user=getWWID(request, response, Uid);
		Users user = getWWID(request, response);
		return (user != null) ? user.getWwid() : "RQ897";// Cambiar este dato por "" cuando se migre a produccion
	}
	/*
	 * 
	 * 
	 * CAMBIAR LOGICA DE ESTE METODO CUANDO SE PROGRAME LA AUTENTICACION SSO
	 * 
	 * */
	public Users getWWID(HttpServletRequest request, HttpServletResponse response) {
//    	//, String Uid
//    	//String idUser = Uid;
//    	Users user= null;
//    	try {
//    	logger.info("Lectura uid en metodo getWWID: ");
//    	//String idUser = (String)request.getSession(false).getAttribute("wwid");
//    	
//    	/*if(idUser!=null || idUser!="") {
//    		user= userDao.findOne(Uid);
//    		if(user!= null ) 
//	    	{
//	    		request.getSession(true).setAttribute("wwid", user.getWwid());
//	    	}
//    	}*/
//    	
//    	user.setWwid("RQ897");//esta linea debera cambiarse en la implementacion a aws
//		return user;
//    	}catch(Exception e) {
//    		logger.error("Failed to query the wwid");
//    		logger.error("WWID ERROR: "+e);
//    		user.setWwid("RQ897");//esta linea debera cambiarse en la implementacion a aws
//    		request.getSession(true).setAttribute("wwid", "RQ897");// esta linea debera cambiarse en la implementacion a aws
//    	return user;
//    	}
		String idUser = (String)request.getSession(false).getAttribute("wwid");
		Users user=userDao.findOne(idUser);
		return user;
	}

	

	@RequestMapping("/view/admin/Admin")
	public String resolveAdminMain(HttpServletRequest request, HttpServletResponse response) {
		String isAdmin= getIsAdmin(request, response); 
		 if(isAdmin.equals("1")) {
		 System.out.println("Valor de admin es 1"); 
		 }else { 
			 return "unauthorized"; 
		 }
		return "admin/Admin";
	}

	@RequestMapping("/view/Complaints/complaints")
	public String resolveViewComplaints(HttpServletRequest request, HttpServletResponse response) {
		return "Complaints/complaints";
	}

	// ----------------------------------------------------------------------------
	// ADMIN ROUTES
	// ----------------------------------------------------------------------------
	@RequestMapping("/view/admin/{idModulo}/{idView}")
	public String resolveAdmin(HttpServletRequest request, HttpServletResponse response, @PathVariable String idModulo,
			@PathVariable String idView) {

		
		 String isAdmin= getIsAdmin(request, response); 
		 if(isAdmin.equals("1")) {
		 System.out.println("Valor de admin es 1"); 
		 }else { 
			 return "unauthorized"; 
		 }
		 
		String idUser = (String) request.getSession(false).getAttribute("wwid");
		logger.info("** The wwid: " + idUser + " enters catalogs, path: admin/" + idModulo + "/" + idView);
		return "admin/" + idModulo + "/" + idView;
	}

	// ----------------------------------------------------------------------------
	// ACCESS ROUTES
	// ----------------------------------------------------------------------------
	@RequestMapping("/view/scd/acceso/Acceso")
	public String resolveScd1(HttpServletRequest request, HttpServletResponse response) {
		return "scd/acceso/Acceso";
	}

	// ----------------------------------------------------------------------------
	// SCD ROUTES
	// ----------------------------------------------------------------------------
	@RequestMapping("/view/scd/Scd")
	public String resolveScd2(HttpServletRequest request, HttpServletResponse response) {
		return "scd/Scd";
	}

	// ----------------------------------------------------------------------------
	// PROFILE ROUTES
	// ----------------------------------------------------------------------------
	@RequestMapping("/view/scd/perfil/perfiles")
	public String resolveScd3(HttpServletRequest request, HttpServletResponse response) {
		return "scd/perfil/perfiles";
	}

	@RequestMapping("/view/scd/perfil/Perfil")
	public String resolveScd4(HttpServletRequest request, HttpServletResponse response) {
		return "scd/perfil/Perfil";
	}

}