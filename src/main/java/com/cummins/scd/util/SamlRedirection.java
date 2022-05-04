//package com.cummins.scd.util;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class SamlRedirection {
//
//    public static String loginRedirection(HttpServletRequest request){
//        String appUrl = request.getRequestURL().toString().substring(0,request.getRequestURL().toString().length() - request.getRequestURI().length());
//
//        /**
//         * La siguiente URL se utiliza unicamente para realizar pruebas de performance o seguridad. Para poder utilizar usuarios de pruebas.
//         */
//        String urlUserAccessStage = "https://myapps.microsoft.com/signin/Payrollsuite_Stg/978e6579-600a-4719-ab1e-b77aca332beb?tenantId=963e11e2-044a-48f7-b618-008af2a008a5";
//        
//        /**
//         * La siguiente URL se utiliza para UATs. Para poder loguearse con el usuario de produccion de cada usuario.
//         */
//        //String urlUserAccessStage = "https://myapps.microsoft.com/signin/Payrollsuite_Stg/a490f918-53b1-4dc4-ae0c-8287c94aa965?tenantId=b31a5d86-6dda-4457-85e5-c55bbc07923d";
//
//        String redirectionURL = appUrl.contains("dev") ? 
//                                "https://access-dev.cummins.com/RC_OnlineLogin2/cummins/cola/logindisplay.jsp?strtarget=https%253A%252F%252Fmxca-abomsd-70-dev-ac.cummins.com%252FDES%252F&resource_url=https%253A%252F%252Fmxca-abomsd-70-dev-ac.cummins.com%252FDES%252F&status=null&loginAtt=null&entity=cummins&SessionMessage=null&username=string&OAMErrorCode=null" :
//                                appUrl.contains("stg") ? 
//                                urlUserAccessStage: 
//                                appUrl.contains("prd") ? 
//                                "https://myapps.microsoft.com/signin/Payrollsuite_20218_Prd/70212dc5-18ba-495e-94b3-754e1b0f64ae?tenantId=b31a5d86-6dda-4457-85e5-c55bbc07923d": 
//                                "http://localhost:8081/DES";
//		return redirectionURL;
//    }
//}