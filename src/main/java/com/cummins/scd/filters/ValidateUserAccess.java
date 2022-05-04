package com.cummins.scd.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.cummins.scd.util.RandomString;
//import com.cummins.scd.util.SamlRedirection;
//import com.cummins.scd.util.SamlSessionXmlReader;


@Component
public class ValidateUserAccess extends OncePerRequestFilter {
	protected static final Logger logger = Logger.getLogger(ValidateUserAccess.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("Entra a doFilterInternal");
		request.getSession(true).setAttribute("wwid", "RQ897");
		 filterChain.doFilter(request, response);
		
	}
//	//protected static final Logger logger = LogManager.getLogger(PayrollSuiteMainController.class);
//    protected static final Logger logger = Logger.getLogger(ValidateUserAccess.class);
//    final String loginAzure = "https://login.microsoftonline.com";
//    final String devRef = "https://mxca-abomsd-70-dev-ac.cummins.com/DES/";
//	final String stgRef = "https://payrollsuite-stg.cummins.com/PayrollSuite/";
//	final String prdRef = "https://payrollsuite-prd.cummins.com/PayrollSuite/";
//	final String localRef = "localhost";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        
//        String uri = request.getRequestURI().substring(request.getContextPath().length());
//        String referer = (request.getHeader("Referer") == null) ? "" : request.getHeader("Referer");
//        System.out.println(request.getHeaderNames().nextElement());
//        
//        logger.log(Level.ERROR, "appUrl: " + uri);
//        System.out.println("appUrl: " + uri);
//        try {
//            System.out.println("Fisrt Substring: " + uri.substring(uri.length() - 4));
//            System.out.println("Second Substring: " + uri.substring(0, 5));   
//        } catch (Exception e) {
//            System.out.println("No se pudo obtener substring de 'uri'");
//        }
//               
//        boolean isViewRequest = "/".equals(uri)
//                                || uri.substring(uri.length() - 4).equals(".png")
//                                || uri.substring(uri.length() - 4).equals(".ico")
//                                || uri.substring(uri.length() - 4).equals(".css")
//                                || uri.substring(uri.length() - 3).equals(".js") 
//                                || uri.contains("view")? true : false;
//
//        logger.log(Level.ERROR, "isViewRequest: " + isViewRequest);
//        System.out.println("isViewRequest: " + isViewRequest);
//
//        if("".equals(referer) || referer.contains(devRef) || referer.contains(stgRef) || referer.contains(prdRef) || referer.contains(localRef) || referer.contains(loginAzure)){
//            //If de sesion creada
//            if(request.isRequestedSessionIdValid()){
//
//                System.out.println("Entre a la sesion valida.");
//                
//                String userWwid = (String) request.getSession(true).getAttribute("wwid");
//                if(userWwid == null || "".equals(userWwid)){
//                    logger.log(Level.ERROR, "Sesion de usuario creada, activa y valida");
//                    
//                    //String samlResponse = request.getParameter("SAMLResponse");
//                    String samlResponse = null;
//                    if(request.getParameter("SAMLResponse") != null && request.getParameter("SAMLResponse").length() < 10000){
//                        samlResponse = request.getParameter("SAMLResponse");
//                    } else if (request.getRequestURL().toString().contains("localhost")){
//                        samlResponse = null;
//                    } else {
//                        response.sendError(401, "WARNING: Unauthorized Access");
//                        //response.sendRedirect(SamlRedirection.loginRedirection(request));
//                    }
//                    
//                    logger.log(Level.ERROR, "Sesion Valida: Se lee SAML del response.");
//                    logger.log(Level.ERROR, "SAMLResponse: " + samlResponse);
//
//                    SamlSessionXmlReader samlSessionReader = new SamlSessionXmlReader(samlResponse);
//                    userWwid = samlSessionReader.getUid();
//
//                    if(userWwid != null && "Bad SAML String".equals(userWwid)){
//                        response.sendError(401, "WARNING: Unauthorized Access");
//                    } else if (userWwid == null || "".equals(userWwid)) {
//                        ValidateUserAccess.logger.log(Level.ERROR, "WWID no activo en la sesion ni en SAML");
//                        ValidateUserAccess.logger.log(Level.ERROR, "Redirigiendo a URL de Acceso de Usuario.");
//                        
//                        response.sendRedirect(SamlRedirection.loginRedirection(request));
//                    }
//
//                    request.getSession(true).setAttribute("wwid", userWwid);
//
//                    logger.log(Level.ERROR, "Sesion Valida: Se decodifica nuevamente el SAML y se obtiene el WWID:");
//                    logger.log(Level.ERROR, "WWID de SAML: " + userWwid);
//                }//Fin de validacion de usuario
//
//                String sesionToken = (String) request.getSession(false).getAttribute("CSRFToken");
//                String headerToken = request.getHeader("CSRFToken");
//                String requestToken = (String) request.getAttribute("CSRFToken");
//
//                logger.log(Level.ERROR, "Sesion Valida: Se obtienen los tokens 'CSRFToken'.");
//                logger.log(Level.ERROR, "sesionToken: " + sesionToken);            
//                logger.log(Level.ERROR, "headerToken: " + headerToken);
//                logger.log(Level.ERROR, "requestToken: " + requestToken);
//                
//                filterChain.doFilter(request, response);
//            
//            } else {//Else de sesion no creada
//
//                logger.log(Level.ERROR, "Sesion de usuario no existe o no es valida");
//                System.out.println("Entre a else de sesion no creada.");
//
//                String userWwid = (String) request.getSession(true).getAttribute("wwid");                
//                //String samlResponse = request.getParameter("SAMLResponse");
//
//                String samlResponse = null;
//                if(request.getParameter("SAMLResponse") != null && request.getParameter("SAMLResponse").length() < 10000){
//                    samlResponse = request.getParameter("SAMLResponse");
//                } else if (request.getRequestURL().toString().contains("localhost")){
//                    samlResponse = null;
//                } else {
//                    response.sendError(401, "WARNING: Unauthorized Access");
//                    //response.sendRedirect(SamlRedirection.loginRedirection(request));
//                }
//                
//                logger.log(Level.ERROR, "Sesion Invalida: Se lee SAML del response.");
//                logger.log(Level.ERROR, "SAMLResponse: " + samlResponse);
//
//                System.out.println("Pase la lectura del SAML de sesion no creada.");
//
//               // SamlSessionXmlReader samlSessionReader = new SamlSessionXmlReader(samlResponse);
//                
//                String appUrl = request.getRequestURL().toString().substring(0,request.getRequestURL().toString().length() - request.getRequestURI().length());
//                if(appUrl.contains("localhost") || appUrl.contains("sclappsapp5001")){
//                    //userWwid = "KH651";
//                    userWwid = "RQ897";
//                } else {
//                    //userWwid = samlSessionReader.getUid();
//                    userWwid = "NS813";
//                }
//
//                
//                //Comentar la siguiente línea cuando se haga un deploy. Solo es para uso de ejecución de la aplicación en local.
//               // userWwid = samlSessionReader.getUid() == null ? "KH651" : samlSessionReader.getUid();
//
//                logger.log(Level.ERROR, "Sesion Invalida: Se decodifica el SAML y se obtiene el WWID:");
//                logger.log(Level.ERROR, "WWID de SAML: " + userWwid);
//
//                System.out.println("Establezco WWID Dummy: " + userWwid);
//
//                if(userWwid != null && "Bad SAML String".equals(userWwid)){
//                    response.sendError(401, "WARNING: Unauthorized Access");
//                } else if(userWwid == null || "".equals(userWwid)){//Si no se obtiene algun wwid, se redirecciona al login.
//
//                    logger.log(Level.ERROR, "Sesion Invalida: Se redirecciona al login o usuario no logueado.");
//                    System.out.println("Sesion invalida y se redirecciona al login o usuario no logueado.");
//
//                    if (isViewRequest) {//Si no trae WWID, se redirecciona al login
//                        response.sendRedirect(SamlRedirection.loginRedirection(request));
//                    } else {
//                        response.sendError(401, "El usuario no se ha logueado correctamente");
//                    }
//
//                } else {//Si se lee bien el WWID, se setea en la sesion y se genera el token.
//
//                    logger.log(Level.ERROR, "Sesion Invalida: Se obtiene WWID de SAML. Se setea en la sesion y se genera token 'CSRFToken'.");
//                    System.out.println("Se establece el WWID en la sesion con el token");
//
//                    request.getSession(true).setAttribute("wwid", userWwid);
//                    RandomString tokenGenerator = new RandomString();
//                    String token = tokenGenerator.nextString();
//                    request.getSession(false).setAttribute("CSRFToken", token);
//
//                    System.out.println("Token Generado: " + token);
//
//                    filterChain.doFilter(request, response);
//
//                }
//
//            }
//        } else {
//            response.sendError(401, "El usuario no se ha logueado correctamente");
//        }
//
//        /*String samlResponse = request.getParameter("SAMLResponse");
//        //String samlResponse = "PHNhbWxwOlJlc3BvbnNlIElEPSJfOTllYTUxZmItNTc0OC00OGU3LThiM2EtM2JmY2Y4MWM0MGYwIiBWZXJzaW9uPSIyLjAiIElzc3VlSW5zdGFudD0iMjAyMC0wNi0xNVQyMDoyOTozNS43NDFaIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9wYXlyb2xsc3VpdGUtc3RnLmN1bW1pbnMuY29tL1BheXJvbGxTdWl0ZS8iIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPjxJc3N1ZXIgeG1sbnM9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPmh0dHBzOi8vc3RzLndpbmRvd3MubmV0L2IzMWE1ZDg2LTZkZGEtNDQ1Ny04NWU1LWM1NWJiYzA3OTIzZC88L0lzc3Vlcj48c2FtbHA6U3RhdHVzPjxzYW1scDpTdGF0dXNDb2RlIFZhbHVlPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6c3RhdHVzOlN1Y2Nlc3MiLz48L3NhbWxwOlN0YXR1cz48QXNzZXJ0aW9uIElEPSJfOTcxYTQ5ZWQtMWJiNy00NWUxLTg3M2UtNzA1YzY0ZmM2ZDAwIiBJc3N1ZUluc3RhbnQ9IjIwMjAtMDYtMTVUMjA6Mjk6MzUuNzI1WiIgVmVyc2lvbj0iMi4wIiB4bWxucz0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+PElzc3Vlcj5odHRwczovL3N0cy53aW5kb3dzLm5ldC9iMzFhNWQ4Ni02ZGRhLTQ0NTctODVlNS1jNTViYmMwNzkyM2QvPC9Jc3N1ZXI+PFNpZ25hdHVyZSB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PFNpZ25lZEluZm8+PENhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxkc2lnLW1vcmUjcnNhLXNoYTI1NiIvPjxSZWZlcmVuY2UgVVJJPSIjXzk3MWE0OWVkLTFiYjctNDVlMS04NzNlLTcwNWM2NGZjNmQwMCI+PFRyYW5zZm9ybXM+PFRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIi8+PFRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjwvVHJhbnNmb3Jtcz48RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxlbmMjc2hhMjU2Ii8+PERpZ2VzdFZhbHVlPk0xTFFLVVdHN01sd00rcWxtcXBGMElkTWlzdTRIZ2pUWFBZWEJ0T3h1NlE9PC9EaWdlc3RWYWx1ZT48L1JlZmVyZW5jZT48L1NpZ25lZEluZm8+PFNpZ25hdHVyZVZhbHVlPlF5Nm5zWW5OdFZQQ2hpQ2tIY2JPVFQ3VUdnS0hGZnozN09VRGdKKzFLM1dKVGNlcDFva1pXTlNUU3JDdjhPYS9jNGVOQW1NNm83dElwNXlrU05aOURqNkp6NU5aR0hQYXh6bVIyVXZyUVVkaFNOYnoxY0l0TjBwT1BIaWJXNzNHMjNYUWI2RnFaT2FNUk8wclQ3bFJUL1Eyd1NpTXNpZ3FML0RjcC8yanh1UVQ0WEVoN0c0VHY1eUtsbjhQM0pSQlBqaithc0VjOWFXZFJ3bmMya0t0NERIV2hET0JsNGVRZFVMY1J2TWdVZUZnbGRKSkRReTRWSGRXVEpWMDNzQ1JrNk9mSFg4enJXWE1yanROQ1hyN3lsRXN6M3Y4N2p5MmFESnZqRndmOEoxSzZhVTJaU3BDYU5BaCszTFdTcnkrQTVCZGwyaXBXbHliMHUzays4Rkw4QT09PC9TaWduYXR1cmVWYWx1ZT48S2V5SW5mbz48WDUwOURhdGE+PFg1MDlDZXJ0aWZpY2F0ZT5NSUlDOERDQ0FkaWdBd0lCQWdJUU5lbC9ud2hraTZGQitZS0Y5ZDZUYkRBTkJna3Foa2lHOXcwQkFRc0ZBREEwTVRJd01BWURWUVFERXlsTmFXTnliM052Wm5RZ1FYcDFjbVVnUm1Wa1pYSmhkR1ZrSUZOVFR5QkRaWEowYVdacFkyRjBaVEFlRncweU1EQTJNRGt3T0RVeE5EZGFGdzB5TXpBMk1Ea3dPRFV4TkRkYU1EUXhNakF3QmdOVkJBTVRLVTFwWTNKdmMyOW1kQ0JCZW5WeVpTQkdaV1JsY21GMFpXUWdVMU5QSUVObGNuUnBabWxqWVhSbE1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBd0p6eStRc0VPYjR3TW1rM1lZVytCWkd5QlB3a2FXSXhQbHU0NndwRWJxNFIwVHlVQ2pqSTlja1E2TGNpSzlMNXpyUFdpSkhkQkJUVTEvZENwdjA0cGFQV2RJc3A4TmJZVzVTNGk0a0F5bHZHSU5PVVdkV1ptYkx0Vm45NlFRSXdvYUZST2IweTBBV0dDZlYwdGdDZmp2Ullad3EyaVN5Zll3UmxGWG02N1JMUEE1U09JaVRPY3FxSVBEUWRJZ24vUHE0V0xQcXF2cW5jVHI4ekU0NExLL0VPNmp3TXcxWTNEUnhaV0l2ZmNIWlBSVXJMQlVpOG1FTzRTZSs2L0dVdE5INElhZDdJOXR1QkZBcHNCZUVlSFVDUTZnem5lb2drYlJ5bzUrTTU3aEZTWlpteTBvakxDNDV3MUVGZExNTlUzOXFBMlhmNzVOdkZWOFRHTWNHVE1RSURBUUFCTUEwR0NTcUdTSWIzRFFFQkN3VUFBNElCQVFCMzFNVEtGMjN2ZGdueXpoQ3A3bXN1b1pkSi9CK3NwWE01RnluL3dpSEhvMlRCMHUrL3dUWnBkTGY1Z1Y4WVZ1QmVRVS9lUWJCU0hPekxzTk5YbE1Fa3JmeG0xbExmNU9kYjQyYnJYVlNtMU5qQklNbVZzRzRTQ0RXdGFxd1lDc0Q3UXNSa1piSy9DN3oyUUY0L2ZrYUJKSHExQTY1bVlMK2w4N1RDcXVQK25kbDI3TCtpREFBQXk2cGY1OVdDdm5RSzBnVUFESktuTWloRW9WZ1E0SktMTEJvWnMyazJ4UW84ZmM2YjFHNzRGb25Jb2FRRnF1c28rNFRtTGFSaWI3SEFXajdIYkdHRUhUNEpOalhEdzFlTkdtM2ZjZXFKUUNkd1hvUFFna3orc3hYS0V6WW9BbmlSNVhyZlhSWmI1VlI4MVFjWWNYTjZxVHFOZmt0SUczdTI8L1g1MDlDZXJ0aWZpY2F0ZT48L1g1MDlEYXRhPjwvS2V5SW5mbz48L1NpZ25hdHVyZT48U3ViamVjdD48TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6ZW1haWxBZGRyZXNzIj5taTkzNEBjdW1taW5zLmNvbTwvTmFtZUlEPjxTdWJqZWN0Q29uZmlybWF0aW9uIE1ldGhvZD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmNtOmJlYXJlciI+PFN1YmplY3RDb25maXJtYXRpb25EYXRhIE5vdE9uT3JBZnRlcj0iMjAyMC0wNi0xNVQyMToyOTozNS41MzhaIiBSZWNpcGllbnQ9Imh0dHBzOi8vcGF5cm9sbHN1aXRlLXN0Zy5jdW1taW5zLmNvbS9QYXlyb2xsU3VpdGUvIi8+PC9TdWJqZWN0Q29uZmlybWF0aW9uPjwvU3ViamVjdD48Q29uZGl0aW9ucyBOb3RCZWZvcmU9IjIwMjAtMDYtMTVUMjA6MjQ6MzUuNTM4WiIgTm90T25PckFmdGVyPSIyMDIwLTA2LTE1VDIxOjI5OjM1LjUzOFoiPjxBdWRpZW5jZVJlc3RyaWN0aW9uPjxBdWRpZW5jZT5wcHM8L0F1ZGllbmNlPjwvQXVkaWVuY2VSZXN0cmljdGlvbj48L0NvbmRpdGlvbnM+PEF0dHJpYnV0ZVN0YXRlbWVudD48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vaWRlbnRpdHkvY2xhaW1zL3RlbmFudGlkIj48QXR0cmlidXRlVmFsdWU+YjMxYTVkODYtNmRkYS00NDU3LTg1ZTUtYzU1YmJjMDc5MjNkPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vaWRlbnRpdHkvY2xhaW1zL29iamVjdGlkZW50aWZpZXIiPjxBdHRyaWJ1dGVWYWx1ZT4xNWVhZTU2MS0zYzA5LTRhNDktYmJlMy1mZDUzNjY1YThjYzY8L0F0dHJpYnV0ZVZhbHVlPjwvQXR0cmlidXRlPjxBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9pZGVudGl0eS9jbGFpbXMvaWRlbnRpdHlwcm92aWRlciI+PEF0dHJpYnV0ZVZhbHVlPmh0dHBzOi8vc3RzLndpbmRvd3MubmV0L2IzMWE1ZDg2LTZkZGEtNDQ1Ny04NWU1LWM1NWJiYzA3OTIzZC88L0F0dHJpYnV0ZVZhbHVlPjwvQXR0cmlidXRlPjxBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9jbGFpbXMvYXV0aG5tZXRob2RzcmVmZXJlbmNlcyI+PEF0dHJpYnV0ZVZhbHVlPmh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC9wYXNzd29yZDwvQXR0cmlidXRlVmFsdWU+PEF0dHJpYnV0ZVZhbHVlPmh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC94NTA5PC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9InVpZCI+PEF0dHJpYnV0ZVZhbHVlPm1pOTM0PC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Im1haWwiPjxBdHRyaWJ1dGVWYWx1ZT5qYWlyLmhlcm5hbmRlekBjdW1taW5zLmNvbTwvQXR0cmlidXRlVmFsdWU+PC9BdHRyaWJ1dGU+PEF0dHJpYnV0ZSBOYW1lPSJmaXJzdG5hbWUiPjxBdHRyaWJ1dGVWYWx1ZT5KYWlyPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imxhc3RuYW1lIj48QXR0cmlidXRlVmFsdWU+SGVybmFuZGV6PC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48L0F0dHJpYnV0ZVN0YXRlbWVudD48QXV0aG5TdGF0ZW1lbnQgQXV0aG5JbnN0YW50PSIyMDIwLTA2LTE1VDEyOjM0OjIxLjcxOVoiIFNlc3Npb25JbmRleD0iXzk3MWE0OWVkLTFiYjctNDVlMS04NzNlLTcwNWM2NGZjNmQwMCI+PEF1dGhuQ29udGV4dD48QXV0aG5Db250ZXh0Q2xhc3NSZWY+dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFjOmNsYXNzZXM6UGFzc3dvcmQ8L0F1dGhuQ29udGV4dENsYXNzUmVmPjwvQXV0aG5Db250ZXh0PjwvQXV0aG5TdGF0ZW1lbnQ+PC9Bc3NlcnRpb24+PC9zYW1scDpSZXNwb25zZT4=";
//
//        SamlSessionXmlReader samlSessionReader = new SamlSessionXmlReader(samlResponse);
//
//        String userWwid = samlSessionReader.getUid() == null ? "QF901" : samlSessionReader.getUid();
//        //String userWwid = samlSessionReader.getUid();
//        if (userWwid == null || userWwid.equals("")) {
//            response.sendError(401, "El usuario no se ha logueado correctamente");
//        } else {
//            request.getSession(true).setAttribute("wwid", userWwid);
//            filterChain.doFilter(request, response);
//        }*/
//    }
//
//    /*public static String getRedirectionURL(HttpServletRequest request) {
//        String appUrl = request.getRequestURL().toString().substring(0,request.getRequestURL().toString().length() - request.getRequestURI().length());
//		String redirectionURL = appUrl.contains("stg") ? "https://myapps.microsoft.com/signin/Payrollsuite_Stg/a490f918-53b1-4dc4-ae0c-8287c94aa965?tenantId=b31a5d86-6dda-4457-85e5-c55bbc07923d": 
//                                "http://localhost:8081/PayrollSuite";
//		return redirectionURL;
//	}*/
    
}